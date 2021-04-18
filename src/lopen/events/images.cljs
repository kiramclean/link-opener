(ns lopen.events.images
  (:require
    [lopen.state :as state]
    [clojure.string :as str]))

(defn loaded! [link]
  (swap! state/db update :images/loaded (fnil conj #{}) link))

(defn error! [link]
  (swap! state/db update :images/errored (fnil conj #{}) link))

(defn remove! [link]
  (swap! state/db (fn [db]
                    (-> db
                        (update :form.input.raw/links str/replace (re-pattern (str link "\n")) "")
                        (update :links/parsed disj link)
                        (update :images/loaded disj link)
                        (update :images/errored disj link)))))
