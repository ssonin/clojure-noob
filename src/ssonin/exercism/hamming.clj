(ns ssonin.exercism.hamming)

(defn distance
  [strand1 strand2]
  (if (not= (count strand1) (count strand2))
    nil
    (apply + (map #(if (= %1 %2) 0 1)
                  strand1
                  strand2))))