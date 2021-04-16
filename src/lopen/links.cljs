(ns lopen.links
  (:require [lopen.state :as state]
            [clojure.string :as str]))

(defn input-change [event]
  (swap! state/db assoc :form/links (-> event .-target .-value)))

(defn parse-input [input]
  (str/split input #"\n"))

(defn append-el [link]
  (println "HELLOOOOO")
  (let [new-image (.createElement js/document "img")
        app-el (.getElementById js/document "results")]
    (set! (.-src new-image) link)
    (js/console.log new-image)
    (.appendChild app-el new-image)
    ))

(defn handle-form-submit [event]
  (.preventDefault event)

  (let [links (-> @state/db :form/links parse-input)]
    (for [link links]
      ;; add each one to a dom element
      (append-el link)))
  ;; get text area data
  ;; parse each line
  ;; put each image in app db
  ;; render a preview for each link
  )
