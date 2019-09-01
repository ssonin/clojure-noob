(ns clojure-noob.ch3-crash-course)

(defn plus-hundred
  "Returns an argument incremented by 100"
  [arg]
  (+ arg 100))

(defn dec-maker
  "Returns a custom decrement function"
  [dec-by]
  #(- % dec-by))

(defn mapset
  "Returns a set consisting of the result of applying f
  to the items of coll"
  [f coll]
  (set (map f coll)))

(plus-hundred 101)
(plus-hundred -100)

(def dec9 (dec-maker 9))
(dec9 11)

(mapset inc [-1 1 1 2])

