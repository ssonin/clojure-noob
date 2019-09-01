(ns clojure-noob.core
  (:gen-class))

(str "Metal " "Up " "Your " "Ass")

(def m {:a 0 :b 1})
m
(def my-map {:fuku "once"})
my-map


(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))

(defn plus-hundred
  "Returns an argument incremented by 100"
  [arg]
  (+ arg 100))

(defn dec-maker
  [dec-by]
  #(- % dec-by))

(plus-hundred 101)

(def dec9 (dec-maker 9))
(dec9 9)
