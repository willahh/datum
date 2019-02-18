(ns datum.page.main
  (:require [hiccup.page :as page]
            [hiccup.core :as core]))

(defn page-wrapper [request html]
  (page/html5
   [:head
    [:title "Glurps! Administration"]
    [:link {:type "text/css", :href "https://cdn.jsdelivr.net/npm/semantic-ui@2.4.2/dist/semantic.min.css" :rel "stylesheet"}]
    [:script {:type "text/javascript", :src "https://cdn.jsdelivr.net/npm/semantic-ui@2.4.2/dist/semantic.min.js"}]]
   [:body
    [:div.ui.container
     [:div
      [:a {:href "/user/list"} "list"]]
     html]]))