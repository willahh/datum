(ns datum.domain.user-schema)

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
   :user/group {:db/valueType :db.type/ref
                :db/cardinality :db.cardinality/many }})