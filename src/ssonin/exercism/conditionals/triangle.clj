(ns ssonin.exercism.conditionals.triangle)

(defn is-valid?
  [a b c]
  (and
    (every? #(pos? %) [a b c])
    (>= (+ a b) c)
    (>= (+ a c) b)
    (>= (+ b c) a)))

(defn equilateral?
  [a b c]
  (and
    (is-valid? a b c)
    (= a b c)))

(defn isosceles?
  [a b c]
  (and
    (is-valid? a b c)
    (or
      (= a b)
      (= b c)
      (= c a))))

(defn scalene?
  [a b c]
  (and
    (is-valid? a b c)
    (= (count (set [a b c])) 3)))