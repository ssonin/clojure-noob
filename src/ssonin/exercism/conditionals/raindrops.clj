(ns ssonin.exercism.conditionals.raindrops)

(defn- divisible-by?
  [n div]
  (= (rem n div) 0))

(defn- pl*ng
  [classifier result]
  (if (classifier) result))

(defn- pling
  [n]
  (pl*ng #(divisible-by? n 3) "Pling"))

(defn- plang
  [n]
  (pl*ng #(divisible-by? n 5) "Plang"))

(defn- plong
  [n]
  (pl*ng #(divisible-by? n 7) "Plong"))

(defn convert
  [n]
  (let [converted (apply str (map #(% n) [pling plang plong]))]
    (if (clojure.string/blank? converted)
      (str n)
      converted)))

(convert 52)
(convert 105)



