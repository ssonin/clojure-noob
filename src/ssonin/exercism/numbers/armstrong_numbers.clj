(ns ssonin.exercism.numbers.armstrong-numbers)

(defn get-digits
  [num]
  (loop [acc '()
         n num]
    (if (= n 0)
      acc
      (recur (conj acc (rem n 10)) (quot n 10)))))

(defn pow
  [base exponent]
  (loop [acc 1
         power exponent]
    (if (= power 0)
      acc
      (recur (* acc base) (- power 1)))))

(defn armstrong?
  [num]
  (let [digits (get-digits num)
        power (count digits)
        folded (reduce
                 (fn [acc n] (+ acc (long (pow n power))))
                 0
                 digits)]
    (==
      num
      folded)))

(= (get-digits 21897142587612075)
   (map #(Integer/parseInt %) (clojure.string/split "21897142587612075" #"")))


(pow 3 3)


(armstrong? 153)
(armstrong? 21897142587612075)



