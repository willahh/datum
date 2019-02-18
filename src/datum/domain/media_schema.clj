(ns datum.domain.media-schema)

(def schema
  {
   :media/id {:db/valueType :db.type/ref
             :db/cardinality :db.cardinality/one
             :db/unique :db.unique/identity}
   :media/filename {:db/valueType :db.type/string
                    :db/cardinality :db.cardinality/one
                    :db/index true}
   :media/size {:db/valueType :db.type/bigint
                :db/cardinality :db.cardinality/one}
   :media/title {:db/valueType :db.type/string
                 :db/cardinality :db.cardinality/one
                 :db/index true}
   :media/description {:db/valueType :db.type/string
                 :db/cardinality :db.cardinality/one
                 :db/index true}
   :media/category {:db/valueType :db.type/ref
                    :db/cardinality :db.cardinality/many}
   })