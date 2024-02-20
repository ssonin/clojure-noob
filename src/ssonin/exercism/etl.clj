(ns ssonin.exercism.etl
  (:require [clojure.string :as str]))

(defn transform
  [source]
  (into {}
        (for [k (keys source)
              v (source k)]
          [(str/lower-case v) k])))

(transform {1 ["WORLD"]})