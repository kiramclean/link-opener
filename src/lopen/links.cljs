(ns lopen.links
  (:require [lopen.state :as state]
            [clojure.string :as str]))



(defn append-el! [link]
  (let [new-image (.createElement js/document "img")
        app-el (.getElementById js/document "results")]
    (set! (.-src new-image) link)
    (set! (.-crossorigin new-image) "anonymous")
    (.appendChild app-el new-image)))
