(ns lopen.views.results
  (:require
   [lopen.events.images :as images]
   [lopen.state :as state]
   [lopen.ui.common :as ui]))

(defn progress-bar [progress]
  [:div.relative.pt-1.flex-grow.mx-5
   [:div.h-2.mb-4.text-xs.flex.rounded.bg-gray-600
    [:div.shadow-none.flex.flex-col.text-center.whitespace-nowrap.justify-center.bg-green-400.rounded.transition-all
     {:style {:width (str progress "%")}}]]])

(defn progress []
  (when-let [submitted-links (:links/parsed @state/db)]
    (let [loaded-count (count (:images/loaded @state/db))
          total-count (count submitted-links)]
      [:<>
       [:p.font-semibold.text-sm.text-gray-600.m-0
        (str loaded-count " of " total-count " loaded")]
       [progress-bar (* 100 (/ loaded-count total-count))]])))

(defn images []
  [:div
   (for [link (:links/parsed @state/db)]
     [ui/card
      {:class "grid grid-cols-3"}
      [:img
       {:src link
        :alt (str "Failed to load image: " link)
        :on-load (partial images/loaded! link)}]])])
