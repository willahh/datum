(ns datum.server
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [datahike.api :as d]
            [datum.domain.user-schema :as user-schema]))

;; (def uri #_"datahike:mem:///test2"
;;   "datahike:file:///tmp/datum"
;;   #_"datahike:level:///tmp/api-test2")

(def uri "datahike:file:///tmp/datum")

(def schema (merge {}
                   user-schema/schema))

;; (d/delete-database uri)
(d/create-database-with-schema uri schema)
(def conn (d/connect uri))




;; Route

(defroutes app-routes
  (GET "/"
       []
       "test"))

(def handler
  (->
   (routes
    #'app-routes)))