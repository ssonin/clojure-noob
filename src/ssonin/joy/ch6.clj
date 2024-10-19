(ns ssonin.joy.ch6)

(defn rand-ints
  [n]
  (take n (repeatedly #(rand-int n))))

(defn sort-parts
  "Lazy, tail-recursive, incremental quicksort.  Works against
       and creates partitions based on the pivot, defined as 'work'."
  [work]
  (lazy-seq
    (loop [[part & parts] work]
      (if-let [[pivot & xs] (seq part)]
        (let [smaller? #(< % pivot)]
          (recur (list*
                   ;(doall (filter smaller? xs))
                   (filter smaller? xs)
                   pivot
                   ;(doall (remove smaller? xs))
                   (remove smaller? xs)

                   parts)))
        (when-let [[x & parts] parts]
          (cons x (sort-parts parts)))))))

(defn qsort [xs]
  (sort-parts (list xs)))

(qsort [5 3 1 7 4 2 8 6])