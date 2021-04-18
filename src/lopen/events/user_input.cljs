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

(def error-messages
  {:no-input "The box is empty!"})

(defn set-error [db error]
  (-> db
      (assoc :ui.form/error (error-messages error))
      (dissoc :links/parsed :images/loaded :images/errored)))

(defn update-links [db]
  (let [new-links (parse-input (:form.input.raw/links db))]
    (-> db
        (dissoc :ui.form/error)
        (assoc :links/parsed new-links)
        (update :images/loaded set/intersection new-links)
        (update :images/errored set/intersection new-links))))

(defn handle-link-form-submit [event]
  (.preventDefault event)
  (if (str/blank? (:form.input.raw/links @state/db))
    (swap! state/db set-error :no-input)
    (swap! state/db update-links)))
