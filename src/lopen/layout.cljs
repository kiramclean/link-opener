(ns lopen.layout
  (:require
   [lopen.links :as links]
   [lopen.state :as state]))

(defn link-form []
  [:form.flex.flex-col
   [:label.pb-2 {:for "links"} "Paste your links here, each on its own line"]
   [:textarea.border.border-gray-300.rounded.dark:bg-gray-900.dark:border-gray-900
    {:id "links"
     :name "links"
     :rows 10
     :cols 50
     :on-change links/input-change}]
   [:div.text-right.mt-3
    [:button.bg-green-400.rounded.py-2.px-10.font-semibold.dark:text-white.dark:bg-green-600
     {:type "submit" :on-click links/handle-form-submit} "Go!"]]])

(defn debug []
  [:div (str @state/db)])

(defn main []
  [:div.dark:bg-gray-900.dark:text-gray-100.h-screen
   [:div.container.mx-auto.p-2
    [:div.bg-gray-100.dark:bg-gray-700.py-5.px-6.mt-2.rounded.shadow-sm
     [:h1.text-2xl.font-semibold.pb-4 "Bulk link viewer"]
     [:p "Paste links here, each on its own line. If the link is to an image, it will load below."]
     [link-form]]]])
