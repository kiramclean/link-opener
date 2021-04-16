(ns lopen.state
  (:require
   [reagent.core :as r]))

(def db (r/atom {}))
