(ns lopen.events.user-input
  (:require
   [lopen.state :as state]
   [clojure.string :as str]))

(defn handle-link-input-change [event]
  (swap! state/db assoc :form.input.raw/links (-> event .-target .-value)))

(defn parse-input [input]
  (str/split input #"\n"))

(defn handle-link-form-submit [event]
  (.preventDefault event)

  (let [links (-> @state/db :form.input.raw/links parse-input set)]
    (swap! state/db assoc :links/parsed links)))
