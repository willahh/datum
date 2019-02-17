(ns datum.server
  (:require
   ;; [compojure.core :refer :all]
   ;; [compojure.route :as route]
   [datahike.api :as d]
   [datum.domain.user-schema :as user-schema]))

(def uri "datahike:file:///tmp/datum")

(def schema (merge {}
                   user-schema/schema))

;; (d/delete-database uri)
(d/create-database-with-schema uri schema)
(def conn (d/connect uri))


;; Route
;; (defroutes app-routes
;;   (GET "/"
;;        []
;;        "test"))

;; (def handler
;;   (->
;;    (routes
;;     #'app-routes)))