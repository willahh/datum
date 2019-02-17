(ns datum.datomic.media
  (:require [datahike.api :as d]))

(def schema
  {
   :user/id {:db/valueType :db.type/ref
             :db/cardinality :db.cardinality/one
             :db/unique :db.unique/identity}
   :user/first-name {:db/cardinality :db.cardinality/one
                     :db/index true}
   :user/last-name {:db/cardinality :db.cardinality/one
                    :db/index true}
   :user/company {:db/valueType :db.type/ref
                  :db/cardinality :db.cardinality/one }
   
   :group/id {:db/cardinality :db.cardinality/one
              :db/unique :db.unique/identity}
   :group/name {:db/cardinality :db.cardinality/one
                :db/index true}
   :group/user {:db/valueType :db.type/ref
                :db/cardinality :db.cardinality/one}
   :company/id {:db/valueType :db.type/ref
                :db/cardinality :db.cardinality/one
                :db/unique :db.unique/identity}
   :company/name {:db/cardinality :db.cardinality/one
                  :db/index true}})

(def uri #_"datahike:mem:///test2"
  "datahike:file:///tmp/datum"
  #_"datahike:level:///tmp/api-test2")

(d/delete-database uri)
(d/create-database-with-schema uri schema)
(def conn (d/connect uri))

(d/transact conn [{:user/id 1
                   :user/first-name "William"
                   :user/last-name "Ravel"}
                  {:user/id 2
                   :user/first-name "Floriane"
                   :user/last-name "Passos"}
                  {:user/id 3
                   :user/first-name "User 3"
                   :user/last-name "User last name"}])

(d/transact conn [{:group/id 1
                   :group/name "Groupe 1"}
                  {:group/id 2
                   :group/name "Groupe 2"}
                  {:group/id 3
                   :group/name "Groupe 3"}])

(d/transact conn [{:group/id 2 :group/name "Groupe 2 update"}
                  {:user/id 1 :user/group 2}
                  {:user/id 2 :user/group 2}])

(d/transact conn [{:user/id 1 :user/first-name "William update"}])
(d/transact conn [{:company/id 1 :company/name "Apple"}
                  {:company/id 2 :company/name "Microsoft"}
                  {:company/id 3 :company/name "Google"}
                  {:company/id 4 :company/name "Amazon"}])

(d/transact conn [{:user/id 1 :company/name "Apple"}])


(defn add-group [conn & {:keys [id name]}]
  (d/transact conn [{:group/id id :group/name name}]))

(add-group conn :id 4 :name "Groupe 4")


(d/pull @conn '[*] [:group/id 1])
(d/pull @conn '[*] [:user/id 1])

;; All group
(d/pull-many @conn '[*]
             (d/q '[:find [?group ...]
                    :where
                    [?group :group/id]] @conn))

;; All user
(d/pull-many @conn '[*]
             (d/q '[:find [?user ...]
                    :where
                    [?user :user/id]] @conn))


;; Find all group
(d/q '[:find ?group-name
       :where
       [_ :group/name ?group-name]]
     @conn)

;; Find user at group 2
(d/q '[:find ?user-first-name
       :where
       [?user :user/group 2]
       [?user :user/first-name ?user-first-name]
       ] @conn)

;; Find user at group 2
(d/q '[:find ?user-first-name
       :where
       [?user :user/group 2]
       [?user :user/first-name ?user-first-name]
       ] @conn)

;; Find user at id 1
(d/q '[:find ?user-first-name
       :where
       [?user :user/id 1]
       [?user :user/first-name ?user-first-name]
       ] @conn)


(d/q '[:find ?group-id
       :where
       [?z :group/name "Groupe 1"]
       [?z :group/id ?group-id]
       
       ]
     @conn)