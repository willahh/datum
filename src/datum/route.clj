(ns datum.route
  (:use compojure.core)
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [compojure.core :as compojure]
            [compojure.handler :as handler]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [datum.domain.user-html :as user-html]))

(defroutes app-routes
  (GET "/"
       []
       "/page/list")
  (GET "/page/list"
       [request]
       (user-html/html request)))

(defonce app
  (-> 
   (routes
    #'app-routes)
   (wrap-defaults
    (-> site-defaults
        (assoc-in [:security :anti-forgery] false)))))
;; (run-jetty app {:port 3000})
