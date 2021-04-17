(ns lopen.state
  (:require
   [reagent.core :as r]))

(defonce db (r/atom {}))
