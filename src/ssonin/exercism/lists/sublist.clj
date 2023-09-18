(ns ssonin.exercism.lists.sublist)

(defn prefix?
  [list1 list2]
  (or (empty? list1)
      (and (not (empty? list2))
           (== (first list1) (first list2))
           (recur (rest list1) (rest list2)))))

(prefix? [3 4 5] [0 1 2 3 4 5])
(prefix? [3 4 5] [3 1 2 3 4 5])
(prefix? [3 4 5] [3 4 5 1 4 5])
(prefix? [] [3 4 5 1 4 5])
(prefix? [] [])
(prefix? [2] [])
(prefix? [3 4 5] [3 4 6])

(defn sublist?
   [list1 list2]
   (and (<= (count list1) (count list2))
        (or (prefix? list1 list2)
            (recur list1 (rest list2)))))

(sublist? [3 4 5] [0 1 2 3 4 5])
(sublist? [3 4 5] [0 1 2 3 4 6])
(sublist? [1 3] [1 2 3])
(sublist? [1 3] [])
(sublist? [] [1 2 3])
(sublist? [] [])
(sublist? [1 2] [1 2 3])
(sublist? [1 2 3] [1 2 3])


(defn classify
  [list1 list2]
  (let [size1 (count list1)
        size2 (count list2)]
    (cond
      (and (empty? list1) (empty? list2)) :equal
      (< size1 size2) (if (sublist? list1 list2)
                        :sublist
                        :unequal)
      (> size1 size2) (if (sublist? list2 list1)
                        :superlist
                        :unequal)
      :else (if (== (first list1) (first list2))
              (recur (rest list1) (rest list2))
              :unequal))))

(classify [3 4 5] [0 1 2 3 4 5])
(classify [1 3] [1 2 3])