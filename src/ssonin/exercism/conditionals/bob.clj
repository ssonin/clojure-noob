(ns ssonin.exercism.conditionals.bob
  (:require [clojure.string :as str]))

(defn all-uppercase?
  [s]
  (let [letters (filter #(Character/isLetter %) s)]
    (and (not (empty? letters))
         (every?
           #(Character/isUpperCase %)
           letters))))

(defn response-for
  [s]
  (let [t (str/trim s)]
    (cond
      (and (all-uppercase? t)
           (str/ends-with? t "?")) "Calm down, I know what I'm doing!"
      (all-uppercase? t) "Whoa, chill out!"
      (str/ends-with? t "?") "Sure."
      (re-matches #"[\s]*" t) "Fine. Be that way!"
      :else "Whatever.")))

(response-for "1, 2, 3")
(response-for "Okay if like my  spacebar  quite a bit?   ")