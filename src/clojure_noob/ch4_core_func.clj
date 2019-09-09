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

;; retrieve a value associated with a keyword
;; from a collection of maps
(def identities
  [{:alias "Batman" :real "Bruce Wayne"}
   {:alias "Santa" :real "Your mom"}])
(map :real identities)


; reduce

;; reduce a collection
(reduce str ["a" "b" "c"])

;; transform map's values
(reduce (fn [new-map [key val]]
          (assoc new-map key (inc val)))
        {} {:max 30 :min 10})

;; filter map's values according to a predicate
(reduce (fn [new-map [key val]]
          (if (> val 4)
            (assoc new-map key val)
            new-map))
        {} {:human 8.1, :critter 0.0})

;; simple map implementation via reduce
(defn my-map
  [func coll]
  (reduce (fn [new-seq coll]
            (concat new-seq (list (func coll))))
          () coll))

(my-map inc [1 2 3])


;; take, drop, take-while, drop-while
(take 3 (range 10))
(drop 5 (range 42))

(take-while #(> (mod % 3) 0) (range 1 20 4))
(drop-while #(> (mod % 3) 0) (range 1 20 4))





