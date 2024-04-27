(ns ssonin.exercism.numbers.difference-of-squares)

(defn sum-of-squares
  [n]
  (->> (range (inc n))
       (map #(Math/pow % 2))
       (reduce +)
       (int)))

(defn square-of-sum
  [n]
  (->> (range (inc n))
       (apply +)
       (#(Math/pow % 2))
       (int)))

(defn difference
  [n]
  (- (square-of-sum n) (sum-of-squares n)))
