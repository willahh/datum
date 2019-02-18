(ns datum.domain.keyword-schema)

(def schema
  {
   :keyword/id {:db/valueType :db.type/ref
             :db/cardinality :db.cardinality/one
             :db/unique :db.unique/identity}
   :keyword/name {
                  ;:db/valueType :db.type/string
                  :db/cardinality :db.cardinality/one}
   :keyword/keyword {:db/valueType :db.type/ref
                     :db/isComponent true
                     :db/cardinality :db.cardinality/many}

   })