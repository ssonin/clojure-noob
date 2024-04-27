(ns ssonin.exercism.numbers.octal)

(defn to-decimal
  [s]
  (let [digits (map #(Character/digit % 8) s)]
    (if (some neg? digits)
      0
      (->> digits
           (map * (mapv #(Math/pow 8 %) (reverse (range (count s)))))
           (apply +)
           (int)))))