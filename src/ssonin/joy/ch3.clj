(ns ssonin.joy.ch3
  (:import [java.awt Frame Dimension Color]))

; a comparison of (when-not (empty? coll)) vs (when (seq coll))
(defn factorial [n]
  (assert (int? n))
  (assert (>= n 0))
  (reduce * 1N (range n 0 -1)))

;(time (loop [coll (vec (range 3000))]
;        (when-not (empty? coll)
;          (factorial (first coll))
;          (recur (rest coll)))))
;
;(time (loop [coll (vec (range 3000))]
;        (when (seq coll)
;          (factorial (first coll))
;          (recur (rest coll)))))

; xors
(defn xors [xs ys]
  (for [x (range xs) y (range ys)]
    [x y (bit-xor x y)]))


(def frame
  (doto (Frame.)
    (.setVisible true)
    (.setSize (Dimension. 200 200))))

(defn f-values [f xs ys]
  (for [x (range xs) y (range ys)]
    [x y (rem (f x y) 256)]))

(defn draw-values
  [f xs ys]
  (let [gfx (.getGraphics frame)]
    (.clearRect gfx 0 0 xs ys)
    (.setSize frame (Dimension. xs ys))
    (doseq [[x y v] (f-values f xs ys)]
      (.setColor gfx (Color. v v v))
      (.fillRect gfx x y 1 1))))

;(draw-values bit-xor 256 256)
;(draw-values bit-and 256 256)
;(draw-values + 256 256)
;(draw-values * 256 256)
;(draw-values bit-and-not 256 256)
