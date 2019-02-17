(ns datum.domain.user-html
  (:require [datum.page.main :as main]
            [datum.domain.user-dao :as user-dao]))

(defn html-list [request]
  [:table.ui.celled.table
   [:theadnnn
    [:tr
     [:th {:width 20} "id"]
     [:th "first-name"]
     [:th "last-name"]
     [:th "company"]
     [:th "group"]]]
   [:tbody
    (let [records (user-dao/find-user-list)]
      (for [record records]
        [:tr
         [:th (:user/id record)]
         [:th (:user/first-name record)]
         [:th (:user/last-name record)]
         [:th (:user/company record)]
         ;; [:th (:user/group record)]
         ]))]])

(defn html-show [request]
  [:table.ui.celled.table
   [:thead
    [:tr
     [:th {:colspan 2} "show table"]
     ]]
   [:tbody
    [:tr
     [:td {:width 200} "id"]
     [:td "1"]]
    [:tr
     [:td "name"]
     [:td "Nom"]]]])

(defn html-form [request]
  [:table.ui.celled.table
   [:thead
    [:tr
     [:th {:colspan 2} "form table"]
     ]]
   [:tbody
    [:tr
     [:td "id"]
     [:td [:input {:name "id" :value ""}]]]
    [:tr
     [:td "first-name"]
     [:td [:input {:name "first-name" :value "f"}]]]
    ]])

(defn html [request]
  (main/page-wrapper
   request
   [:div
    (html-list request)
    (html-show request)
    (html-form request)]))

