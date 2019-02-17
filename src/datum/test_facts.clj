(ns datum.test-facts
  (:require  [datahike.api :as d]
             [clojure.edn :as edn]))

(def uri "datahike:file:///tmp/datum")
(def conn (d/connect uri))

(defn insert-data []
  (d/transact conn (edn/read-string (slurp "resources/data/user.edn")))
  (d/transact conn (edn/read-string (slurp "resources/data/group.edn")))
  (d/transact conn (edn/read-string (slurp "resources/data/company.edn")))
  (d/transact conn [{:group/id 2 :group/name "Groupe 2 update"}
                    {:user/id 1 :user/group 2}
                    {:user/id 2 :user/group 2}])
  (d/transact conn [{:user/id 1 :user/first-name "William update"}])
  (d/transact conn [{:user/id 1 :company/name "Apple"}]))


;; (d/pull @conn '[*] [:user/id 1])
(d/pull-many @conn '[*]
             (d/q '[:find [?group ...]
                    :where
                    [?group :group/id]] @conn))

(d/pull-many @conn '[*]
             (d/q '[:find [?user ...]
                    :where
                    [?user :user/id]] @conn))

(d/pull-many @conn '[*]
             (d/q '[:find [?user ...]
                    :where
                    [?user :user/id]
                    ] @conn))

(d/pull-many @conn '[*]
             (d/q '[:find [?group ...]
                    :where
                    [?group :group/name "Groupe 2"]
                    [?group :group/id ?groupid]
                    [?user :user/group ?groupid]] @conn))

(d/q '[:find ?group ?firstname
       :where
       [?group :group/name "Groupe 2"]
       [?group :group/id ?groupid]
       [?user :user/group ?groupid]
       [?user :user/first-name ?firstname]]
     @conn)

(let [group-name "Groupe 2"]
  (d/pull-many @conn '[*]
               (d/q '[:find ?group ?user
                      :where
                      [?group :group/name "Groupe 2"]
                      [?group :group/id ?groupid]
                      [?user :user/group ?groupid]
                      [?user :user/first-name ?firstname]]
                    @conn)
               ))

(d/q '[:find ?group ?user
       :where
       [?group :group/name "Groupe 2"]
       [?group :group/id ?groupid]
       [?user :user/group ?groupid]
       [?user :user/first-name ?firstname]
       ]
     @conn)

(d/pull-many @conn '[:group/name]
             [4 5])

(d/pull-many @conn '[:group/name]
             #{[5 2] [5 1]})

(d/pull-many @conn '[:group/name]
             #{[5 2] [5 1]})

(d/pull-many @conn '[[:group/name :user/first-name]]
             #{[5 2] [5 1]})

(d/pull-many @conn '[{:group [:group/name]}]
             [4 5]
             )

;; (d/pull-many @conn '[[:group/name]]
;;              [[4] [5]]
;;              )

(d/pull-many @conn '[{[:group/name :as "Name"]
                      [[:group/id :as "Id"]]}]
             [4 5]
             )