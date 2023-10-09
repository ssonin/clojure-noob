(ns ssonin.exercism.numbers.collatz-conjecture)

(defn next-number
  [num]
  (cond
    (< num 1) (throw (IllegalArgumentException. "Negative"))
    (even? num) (/ num 2)
    :else (inc (* 3 num))))

(defn numbers
  [num]
  (cons num (lazy-seq (numbers (next-number num)))))

(defn collatz
  [num]
  (count (take-while #(not= % 1) (numbers num))))

(collatz 12)

