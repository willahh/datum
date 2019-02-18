(ns datum.test-facts
  (:require  [datahike.api :as d]
             [clojure.edn :as edn]))

(def uri "datahike:file:///tmp/datum")
(def conn (d/connect uri))

(defn insert-data []
  (d/transact conn (edn/read-string (slurp "resources/data/user.edn")))
  (d/transact conn (edn/read-string (slurp "resources/data/group.edn")))
  (d/transact conn (edn/read-string (slurp "resources/data/company.edn")))
  (d/transact conn (edn/read-string (slurp "resources/data/profile.edn")))
  (d/transact conn (edn/read-string (slurp "resources/data/media.edn")))
  (d/transact conn (edn/read-string (slurp "resources/data/keyword.edn")))
  (d/transact conn [{:group/id 2 :group/name "Groupe 2 update"}
                    {:user/id 1 :user/group 2}
                    {:user/id 2 :user/group 2}
                    {:user/id 3 :user/group 1}])
  (d/transact conn [{:user/id 1 :user/first-name "William update"}])
  (d/transact conn [{:user/id 1 :company/name "Apple"}])

  ;; Add keyword relations
  (d/transact conn [{:keyword/id 1 :keyword/keyword 2}])
  )

(comment
  (insert-data))

(d/pull-many @conn '[*]
             (d/q '[:find [?group ...]
                    :where
                    [?group :group/id]] @conn))
(d/pull-many @conn '[*]
             (d/q '[:find [?media ...]
                    :where
                    [?media :media/id]] @conn))

(d/pull-many @conn '[*]
             (d/q '[:find [?user ...]
                    :where
                    [?user :user/id]] @conn))

(d/pull-many @conn '[*]
             (d/q '[:find [?media ...]
                    :where
                    [?media :media/id]
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

  ;; Find all keyword

(d/q '[:find (pull ?keyword [*])
       :where
       [?keyword :keyword/id]]
     @conn)

(d/q '[:find (pull ?keyword
                   [:keyword/name
                    {:keyword/keyword [:db/id :keyword/name]}])
       :where
       [?keyword :keyword/id]]
     @conn)

(d/q '[:find (pull ?groupid [*])
       :where
       [?groupid :group/id]]
     @conn)

(d/q '[:find (pull ?user [:user/first-name {:user/group
                                            [:db/id :group/name]}
                          :user/last-name
                          ])
       :where
       [?user :user/id]
       [?group :group/id]]
     @conn)

(d/q '[:find (pull ?user [:user/first-name
                          {:user/group [:db/id :group/id]}])
       :where
       [?user :user/id]
       [?user :user/group ?groupid]
       [?group :group/id ?groupid]
       ]
     @conn)

(d/q '[:find (pull ?group [:group/name
                           {:group/user
                            [:db/id :user/id]}])
       :where
       [?user :user/id]
       [?user :user/group ?groupid]
       [?group :group/id ?groupid]
       ]
     @conn)

(d/q '[:find (pull ?e [*])
       :where
       [?e :user/id]
       ]
     @conn)


(d/q '[:find (pull ?user [*])
       :where
       [?user :user/id]]
     @conn)



  ;; (let [group-name "Groupe 2"]
  ;;   (d/pull-many @conn '[*]
  ;;                (d/q '[:find ?group ?user
  ;;                       :where
  ;;                       [?group :group/name "Groupe 2"]
  ;;                       [?group :group/id ?groupid]
  ;;                       [?user :user/group ?groupid]
  ;;                       [?user :user/first-name ?firstname]]
  ;;                     @conn)
  ;;                ))

  ;; Find group by Group name

(d/q '[:find ?group ?firstname
       :in $ ?group-name
       :where
       [?group :group/name ?group-name]
       [?group :group/id ?groupid]
       [?user :user/group ?groupid]
       [?user :user/first-name ?firstname]
       ]
     @conn "Groupe 2")

  ;; Find all entities

(d/q '[:find ?e ?name ?value
       :where
       [?e ?name ?value]]
     @conn)

  ;; Find all group

(d/q '[:find (pull ?group [*])
       :where
       [?group :group/id]]
     @conn)

  ;; Find all user

(d/q '[:find (pull ?user [*])
       :where
       [?user :user/id ?userid]]
     @conn)

  ;; TODO Find all user with nested relations

(d/q '[:find (pull ?user [:user/first-name
                          {:user/group [:db/id :group/name]}])
       :where
       [?user :user/id ?userid]]
     @conn)

(d/q '[:find (pull ?user [:user/first-name
                          {:user/group [:db/id :group/name]}])
       :where
       [?user :user/id ?userid]]
     @conn)

(d/q '[:find (pull ?group [:group/name])
       :where
       [?group :group/id ?groupid]
       ]
     @conn)

(d/q '[:find (pull ?group [*])
       :where
       [?group :group/id]]
     @conn)

(d/q '[:find [pull ?group [:group/name]]
       :where
       [?group :group/id]
       [?user :user/id]
       ]
     @conn)

(d/q '[:find (pull ?user [:user/first-name
                          {:user/group [:db/id :group/name]}])
       :where
       [?user :user/id]]
     @conn)

(d/q '[:find (pull ?user [:user/first-name
                          {:user/group [:db/id :group/name]}])
       :where
       [?user :user/id]
       [?group :group/id]
       ]
     @conn)

  ;; (d/q '[:find ?user-first-name
  ;;        :where
  ;;        [?group :group/id]
  ;;        [?user :user/id]
  ;;        [?user :user/first-name ?user-first-name]
  ;;        ]
  ;;      @conn)

  ;; (d/pull-many @conn '[:group/name]
  ;;              [4 5])

  ;; (d/pull-many @conn '[:group/name]
  ;;              #{[5 2] [5 1]})
  

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

;; (d/pull-many @conn '[{[:group/name :as "Name"]
;;                       [[:group/id :as "Id"]]}]
;;              [4 5]
;;              )