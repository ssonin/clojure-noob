(ns ssonin.exercism.eliuds-eggs
  (:require [clojure.string :as str]))

(defn egg-count [n]
  (->> (str/split (Integer/toBinaryString n) #"")
       (map #(Integer/parseInt %))
       (apply +)))

(egg-count 89)