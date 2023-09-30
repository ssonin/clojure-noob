(ns ssonin.exercism.numbers.all-your-base)

(defn base10->basex
  "Converts n in base 10 to base x representing result as a list of digits"
  ([n x]
   (base10->basex n x '()))
  ([n x acc]
   (if (== n 0)
     acc
     (recur (quot n x) x (cons (rem n x) acc)))))

(defn powers-of-x
  "Returns k powers of x in descending order"
  [x k]
  (map #(long (Math/pow x %)) (reverse (range k))))

(defn drop-leading-zeros
  [n]
  (cond
    (empty? n) n
    :else
    (let [trimmed (drop-while #(= 0 %) n)]
      (if (empty? trimmed)
        '(0)
        trimmed))))

(defn basex->base10
  "Converts n in base x represented as a list of digits to an integer
  in base 10"
  ([n x]
   (let [num-digits (count n)
         powers (powers-of-x x num-digits)
         terms (map vector n powers)]
     (reduce
       (fn [acc [term mult]]
         (if (or (neg? term) (>= term x))
           (throw (IllegalArgumentException. "Negative"))
           (+ acc (* term mult))))
       0
       terms))))

(defn convert
  "Converts n in base-x represented as a list of digits to an integer
  in base-y also represented as a list of digits"
  [base-x n base-y]
  (let [trimmed-n (drop-leading-zeros n)]
    (cond
      (= trimmed-n '(0)) '(0)
      (or (< base-x 2) (< base-y 2)) nil
      :else (try
              (base10->basex
                (basex->base10 trimmed-n base-x)
                base-y)
              (catch IllegalArgumentException e
                nil)))))


(convert 2 '(1) 10)
(convert 2 '(1 0 1) 10)
(convert 10 '(5) 2)
(convert 2 '(1 0 1 0 1 0) 10)
(convert 10 '(4 2) 2)
(convert 3 '(1 1 2 0) 16)
(convert 16 '(2 10) 3)
(convert 97 '(3 46 60) 73)
(convert 2 () 10)
(convert 10 '(0) 2)
(convert 10 '(0 0 0) 2)
(convert 7 '(0 6 0) 10)
(convert 2 '(1 -1 1 0 1 0) 10)
(convert 2 '(1 2 1 0 1 0) 10)
(convert 1 () 10)
(convert 2 '(1 0 1 0 1 0) 1)
(convert 0 () 10)
(convert 10 '(7) 0)
(convert -2 '(1) 10)
(convert 2 '(1) -7)
(convert -2 '(1) -7)
