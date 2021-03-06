(ns datum.domain.user-dao
  (:require [datahike.api :as d]))

(def uri "datahike:file:///tmp/datum")
(def conn (d/connect uri))

(defn find-user-list []
  (d/pull-many @conn '[*]
               (d/q '[:find [?user ...]
                      :where
                      [?user :user/id]] @conn)))


(defn find-by [field value]
  (d/pull-many @conn '[*]
               (d/q '[:find [?user ...]
                      :where
                      [?user field value]]
                    @conn)))

(defn insert [id first-name last-name]
  (d/transact conn [{:user/id 1
                     :user/first-name first-name
                     :user/last-name last-name}]))

(comment
  @conn
  (find-user-list)
  (find-by :user/first-name "William update 2")
  (insert 4 "User 4 fn" "User 4 ln")
  )

