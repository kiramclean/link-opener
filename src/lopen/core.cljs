(ns lopen.core
  (:require
   [lopen.layout :as layout]
   [reagent.dom :as rdom]))

(defn ^:export init []
  (rdom/render
    [layout/main]
    (.getElementById js/document "app")))
