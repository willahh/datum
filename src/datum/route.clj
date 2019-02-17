(ns datum.route
  (:use compojure.core)
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [compojure.core :as compojure]
            [compojure.handler :as handler]
            ;; [ring.middleware.json :refer [wrap-json-response]]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            ))

;; "a"
;; (use 'ring.adapter.jetty)

;; (use 'ring.middleware.resource
;;      'ring.middleware.defaults
;;      'ring.util.response
;;      'ring.middleware.session.cookie
;;      'ring.middleware.session
;;      'ring.middleware.flash
;;      'ring.middleware.content-type
;;      'ring.middleware.not-modified
;;      'ring.middleware.params
;;      'ring.middleware.keyword-params)

(defroutes app-routes
  (GET "/a"
       []
       "a22")
  (GET "/b"
       []
       "bb"))

(defonce app
  (-> 
   (routes
    #'app-routes
    ;; (wrap-json-response api-route-json)
    ;; admin-group-route
    )
   (wrap-defaults
    (-> site-defaults
        (assoc-in [:security :anti-forgery] false)))))

;; (run-jetty app {:port 3000})
