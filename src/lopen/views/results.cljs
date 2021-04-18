(ns lopen.views.results
  (:require
   [lopen.events.images :as images]
   [lopen.ui.common :as ui]
   [lopen.state :as state]))

(defn progress-bar [progress errored]
  [:div.relative.pt-1.flex-grow.mx-5
   [:div.h-2.flex.rounded.bg-gray-600.transition-all.justify-between
    [:div.bg-green-400.transition-all
     {:class (if (pos? errored) "rounded-l" "rounded")
      :style {:width (str progress "%")}}]
    [:div.bg-red-400.rounded-r.transition-all
     {:style {:width (str errored "%")}}]]])

(defn progress [{:keys [images/loaded images/errored links/parsed]}]
  [:div.flex.w-full.items-baseline
   (when @state/show-progress?
     [:<>
      [:p.font-semibold.text-sm.text-gray-600.m-0
       (str loaded " of " parsed " loaded")]
      [progress-bar
       (* 100 (/ loaded parsed))
       (* 100 (/ errored parsed))]])])

(defn images [links]
  [:<>
   (for [link links]
     ^{:key link}
     [:<>
      [:div.flex.justify-end.bg-gray-200.p-2.rounded-t.dark:bg-gray-700
       [:button.bg-red-300.rounded-full.w-5.h-5.flex.items-center.justify-center.focus:ring-2.focus:ring-blue-600.dark:bg-red-700.outline-none
        {:aria-label "close"
         :on-click (partial images/remove! link)}
        ui/close-icon]]
      [ui/card
       {:class "text-center rounded-b"}
       ^{:key link}
       [:img.mx-auto
        {:src link
         :alt (str "Failed to load image: " link)
         :on-load (partial images/loaded! link)
         :on-error (partial images/error! link)}]]])])
