(ns ssonin.exercism.strings.pangram
  (:require [clojure.string :as str :refer [lower-case]]))

(def alphabet
  (set (map char (range 97 123))))

(defn clean
  [s]
  (str/replace s #"[^a-zA-Z]+" ""))

(defn pangram?
  [s]
  (let [cleaned ((comp lower-case clean) s)]
    (= alphabet (set cleaned))))

(pangram? "THE_quick_brown_fox_jumps_over_the_lazy_dog")