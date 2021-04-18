(ns lopen.views.results
  (:require
   [lopen.events.images :as images]
   [lopen.state :as state]
   [lopen.ui.common :as ui]))

(defn progress-bar [progress errored]
  [:div.relative.pt-1.flex-grow.mx-5
   [:div.h-2.flex.rounded.bg-gray-600.transition-all.justify-between
    [:div.bg-green-400.transition-all
     {:class (if (pos? errored) "rounded-l" "rounded")
      :style {:width (str progress "%")}}]
    [:div.bg-red-400.rounded-r.transition-all
     {:style {:width (str errored "%")}}]]])

(defn progress []
  [:div.flex.w-full.items-baseline
   (let [loaded-count (count (:images/loaded @state/db))
         error-count (count (:images/errored @state/db))
         submitted-links (:links/parsed @state/db)
         total-count (count submitted-links)]
     (when (seq submitted-links)
       [:<>
        [:p.font-semibold.text-sm.text-gray-600.m-0
         (str loaded-count " of " total-count " loaded")]
        [progress-bar (* 100 (/ loaded-count total-count)) (* 100 (/ error-count total-count))]]))])

(defn images []
  [:<>
   (for [link (:links/parsed @state/db)]
     ^{:key link}
     [ui/card
      {:class "text-center"}
      ^{:key link}
      [:img.mx-auto
       {:src link
        :alt (str "Failed to load image: " link)
        :on-load (partial images/loaded! link)
        :on-error (partial images/error! link)}]])])
