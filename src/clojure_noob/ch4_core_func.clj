(ns clojure-noob.ch4-core-func)

; map

;; one argument
(map inc [1 2 3])

;; several arguments
(map str ["a" "b" "c"] ["A" "B" "C"] ["42" "42"])

(def human-consumption [8.1 7.3 6.6 5.0])
(def critter-consumption [0.0 0.2 0.3 1.1])
(defn unify-diet-data
  [human critter]
  {:human human :critter critter})
(map unify-diet-data human-consumption critter-consumption)

;; collection of functions instead of a collection of values
(def sum #(reduce + %))
(def avg #(/ (sum %) (count %)))
(defn stats
  [numbers]
  (map #(% numbers) [sum count avg]))
(stats [1 2 3])