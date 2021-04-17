(ns lopen.views.layout
  (:require
   [lopen.events.user-input :as uie]
   [lopen.state :as state]
   [lopen.ui.common :as ui]))

(defn link-form []
  [:form.flex.flex-col
   [:label {:for "links"} "Paste your links here, each on its own line"]
   [ui/textarea
    {:id "links"
     :name "links"
     :rows 10
     :cols 50
     :on-change uie/handle-link-input-change}]
   [:div.text-right
    [ui/button {:type "submit" :on-click uie/handle-link-form-submit} "Go!"]]])

(defn debug []
  [:div (str @state/db)])

(defn main []
  [:div.dark:bg-gray-900.dark:text-gray-100.h-screen
   [:div.container.mx-auto.p-2
    [:div.bg-gray-100.dark:bg-gray-700.py-5.px-6.mt-2.rounded.shadow-sm
     [:h1.text-2xl.font-semibold.pb-4 "Bulk link viewer"]
     [:p "Paste links here, each on its own line. If the link is to an image, it will load below."]
     [link-form]]]])
