(ns lopen.events.images
  (:require
    [lopen.state :as state]))

(defn loaded! [link]
  (swap! state/db update :images/loaded (fnil conj #{}) link))
