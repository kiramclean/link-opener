(ns lopen.views.layout
  (:require
   [reagent.ratom :as rr]
   [lopen.events.user-input :as uie]
   [clojure.pprint :as pp]
   [lopen.state :as state]
   [lopen.ui.common :as ui]
   [lopen.views.results :as results]))

(defn link-form []
  [:form.flex.flex-col
   [:label {:for "links"}
    "Paste links here, each on its own line. If the link is to an image, it will load below."]
   [ui/textarea
    {:aria-label "Links, each on their own line"
     :id "links"
     :name "links"
     :rows 10
     :cols 50
     :value (:form.input.raw/links @state/db)
     :on-change uie/handle-link-input-change}]
   [:div.flex.items-baseline.justify-between
    [results/progress @state/counts]
    [ui/button {:type "submit" :on-click uie/handle-link-form-submit} "Go!"]]])

(defn debug []
  (pp/pprint @state/db)
  ;; [:div (str @state/db)]
  )

(defn main []
  [:div.dark:bg-gray-900.dark:text-gray-200.h-screen
   ;;[debug]
   [:div.container.mx-auto.p-2
    [:h1.text-2xl.font-semibold.pb-4 "Bulk link viewer"]
    [ui/card [link-form]]
    [results/images @state/links]]])
