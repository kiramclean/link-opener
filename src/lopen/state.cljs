(ns lopen.state
  (:require
   [reagent.core :as r]
   [reagent.ratom :as rr]))

(defonce db (r/atom {}))

(def links (rr/cursor db [:links/parsed]))

(def show-progress? (rr/reaction (pos? (count @links))))

(def counts (rr/reaction (->> (select-keys @db [:images/loaded :images/errored :links/parsed])
                              (map (fn [[k v]] [k (count v)]))
                              (into {}))))
