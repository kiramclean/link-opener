(ns lopen.layout
  (:require
   [lopen.links :as links]
   [lopen.state :as state]))

(defn link-form []
  [:form.flex
   [:label {:for "links"} "Paste your links here, each on its own line"]
   [:textarea {:id "links"
               :name "links"
               :rows 10
               :cols 50
               :on-change links/input-change}]
   [:button {:type "submit" :on-click links/handle-form-submit} "Go!"]])

(defn debug []
  [:div (str @state/db)])

(defn main []
  [:div.container.mx-auto.bg-blue-25
   [link-form]
   [:div {:id "results"}]
   ;; [debug]
   ])
