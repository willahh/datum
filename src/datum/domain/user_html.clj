(ns datum.domain.user-html
  (:require [datum.page.main :as main]
            [datum.domain.user-dao :as user-dao]))

(defn html-list [request]
  (main/page-wrapper
   request
   [:div
    [:table.ui.celled.table
     [:thead
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
           [:td (:user/id record)]
           [:td (:user/first-name record)]
           [:td (:user/last-name record)]
           [:td (:user/company record)]
           [:td [:a {:href (str "/user/form/" 1)} "edit"]]
           ]))]]]))

(defn html-form [request]
  (main/page-wrapper
   request
   [:div
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
      ]]]))

