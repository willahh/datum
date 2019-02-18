(ns datum.domain.group-schema)

(def schema
  {
   :group/id {:db/valueType :db.type/ref
              :db/cardinality :db.cardinality/one
              :db/unique :db.unique/identity}
   :group/name {:db/cardinality :db.cardinality/one
                :db/index true}
   :group/user {:db/valueType :db.type/ref
                :db/cardinality :db.cardinality/many
                :db/isComponent true }})