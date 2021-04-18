(ns lopen.events.user-input
  (:require
   [lopen.state :as state]
   [clojure.string :as str]
   [clojure.set :as set]))

(defn handle-link-input-change [event]
  (swap! state/db assoc :form.input.raw/links (-> event .-target .-value)))

(defn parse-input [input]
  (->> (str/split input #"\n")
       (remove str/blank?)
       set))

(defn update-links [db]
  (let [new-links (parse-input (:form.input.raw/links db))]
    (-> db
        (assoc :links/parsed new-links)
        (update :images/loaded set/intersection new-links)
        (update :images/errored set/intersection new-links))))

(defn handle-link-form-submit [event]
  (.preventDefault event)
  (swap! state/db update-links))
