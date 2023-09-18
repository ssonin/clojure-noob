(ns ssonin.clojure-noob.ch3-crash-course)

; control flow
;; do
(if true
  (do (println "Success!")
      "By Zeus's hammer!")
  (do (println "Failure!")
      "By Aquaman's trident!"))

;; when
(when true
  (println "Success!")
  "abra cadabra")

;; or: the first truthy else last
(or false nil :large_I_mean_venti :why_cant_I_just_say_large)
(or false nil (= (* 2 2) 5))

;; and: the last else the first falsy
(and (= 0 0) "do" "it" "yourself")
(and (= 0 0) "do" nil "yourself")

; data structures
;; string and concat
(str "Metal " "Up " "Your " "Ass")

;; maps
;;; create
{:a 1, :b 2}
(hash-map :a 1 :b 2)
;;; look up values
(get {:a 0 :b 1} :c)
(get {:a 0 :b 1} :c "unicorns?")
(get-in {:a 0 :b {:c "ho hum"}} [:b :c])
;;; map as a function
({:name "The Human Coffeepot"} :name)
;;; keyword as a function
(:a {:a 1 :b 2 :c 3})
(:d {:a 1 :b 2 :c 3} "No gnome knows homes like Noah knows")

;; vectors
[3 2 1]
(get [3 "2" {:a 1, :b 2}] 0)
;;; conjoin on vectors happens at the end
(conj [1 2 3] 4)
(conj [1 2 3] [4 5])
(conj [1 2 3] 4 5 6)

;; lists
'(1 2 3)
(list 1 2 3)
;;; conjoin on lists happens at the beginning
(conj (list 1 2 3) 0)

;; sets
#{"kurt vonnegut" 20 :icicle}
(hash-set 1 1 2 2)
(def not-set '(1 1 2 2 3 3))
(set not-set)
(conj #{:a :b} :b)
;;; check for membership
(contains? #{:a :b} :a)
(:a #{:a :b})
(get #{:a :b} "kurt vonnegut")

; functions
((or + -) 1 2 3)

;; n-arity
(defn no-params
  ([] "I take no parameters!")
  ([x] (str "I take one parameter: " x))
  ([x y] (str "Two parameters! That's nothing! Pah! I will smoosh them "
              "together to spite you! " x y)))
;; variable-arity
(defn favorite-things
  [name & things]
  (str "Hi, " name ", here are my favorite things: "
       (clojure.string/join ", " things)))
(favorite-things "Doreen" "gum" "shoes" "kara-te")

;; destructuring
(defn my-first
  [[first-thing]] ; Notice that first-thing is within a vector
  first-thing)
(my-first ["oven" "bike" "war-axe"])
(my-first [])
(defn receive-treasure-location
  [{:keys [lat lng] :as treasure-location}]
  (println (str "Treasure lat: " lat))
  (println (str "Treasure lng: " lng))
  (println (:keys treasure-location))
  (println treasure-location))
(receive-treasure-location {:lat 28.22 :lng 81.33})

;; anonymous functions
(#(* %1 3) 12)
(#(identity %&) 1 "blarg" :yip)

;; closure
(defn inc-maker
  "Create a custom incrementor"
  [inc-by]
  #(+ % inc-by))
(def inc3 (inc-maker 3))
(inc3 7)

;; let
(let [a 1
      b 2
      c 3]
  (list a b c c c b a ))


(def asym-hobbit-body-parts [{:name "head" :size 3}
                             {:name "left-eye" :size 1}
                             {:name "left-ear" :size 1}
                             {:name "mouth" :size 1}
                             {:name "nose" :size 1}
                             {:name "neck" :size 2}
                             {:name "left-shoulder" :size 3}
                             {:name "left-upper-arm" :size 3}
                             {:name "chest" :size 10}
                             {:name "back" :size 10}
                             {:name "left-forearm" :size 3}
                             {:name "abdomen" :size 6}
                             {:name "left-kidney" :size 1}
                             {:name "left-hand" :size 2}
                             {:name "left-knee" :size 2}
                             {:name "left-thigh" :size 4}
                             {:name "left-lower-leg" :size 3}
                             {:name "left-achilles" :size 1}
                             {:name "left-foot" :size 2}])

(defn matching-part
  [part]
  {:name (clojure.string/replace (:name part) #"^left-" "right-")
   :size (:size part)})

(defn symmetrise-body-parts
  "Expects a seq of maps that have a :name and :size"
  [asym-body-parts]
  (loop [remaining-asym-parts asym-body-parts
         final-body-parts []]
    (if (empty? remaining-asym-parts)
      final-body-parts
      (let [[head & tail] remaining-asym-parts]
        (recur tail
               (into final-body-parts
                     (set [head (matching-part head)])))))))
(symmetrise-body-parts asym-hobbit-body-parts)


(defn better-symmetrise-body-parts
  ""
  [asym-body-parts]
  (reduce (fn [resulting-parts part]
            (into resulting-parts (set [(matching-part part) part])))
          []
          asym-body-parts))
(= (symmetrise-body-parts asym-hobbit-body-parts)
   (better-symmetrise-body-parts asym-hobbit-body-parts))


(defn hit
  ""
  [asym-body-parts]
  (let [sym-body-parts (better-symmetrise-body-parts asym-body-parts)
        body-size-sum (reduce + (map :size sym-body-parts))
        target (rand body-size-sum)]
    (println body-size-sum)
    (println target)
    (loop [[part & remaining] sym-body-parts
           accumulated-size (:size part)]
      (if (< target accumulated-size)
        part
        (recur remaining (+ accumulated-size (:size (first remaining))))))))
(hit asym-hobbit-body-parts)



(defn plus-hundred
  "Returns an argument incremented by 100"
  [arg]
  (+ arg 100))

(defn dec-maker
  "Returns a custom decrement function"
  [dec-by]
  #(- % dec-by))

(defn mapset
  "Returns a set consisting of the result of applying f
  to the items of coll"
  [f coll]
  (set (map f coll)))

;; 4clojure, 22
(defn my-count
  "Returns the number of elements in a sequence"
  [coll]
  (loop [remaining coll
         result 0]
    (if (empty? remaining)
      result
      (recur (rest remaining) (inc result)))))
(my-count '(1 2 3 4 5))
(my-count "Hello World")
(my-count asym-hobbit-body-parts)

;; 4clojure, simplified 23
(defn my-reverse
  ""
  [coll]
  (loop [remaining coll
         result '()]
    (if (empty? remaining)
      result
      (let [[head & tail] remaining]
        (recur tail (conj result head))))))
(my-reverse [1 2 3 4 5])
(my-reverse "Hello World")
(my-reverse asym-hobbit-body-parts)

(plus-hundred 101)
(plus-hundred -100)

(def dec9 (dec-maker 9))
(dec9 11)

(mapset inc [-1 1 1 2])

