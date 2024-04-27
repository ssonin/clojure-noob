(ns ssonin.exercism.numbers.largest-series-product)

(defn largest-product
  [n s]
  (assert (<= n (count s)))
  (assert (every? #(Character/isDigit %) s))
  (->> (map #(Character/digit % 10) s)
       (partition n 1)
       (map #(reduce * %))
       (apply max)))