(ns ssonin.clojure-noob.ch4-core-func)

; map

;; one argument
(map inc [1 2 3])

;; several arguments
(map str ["a" "b" "c"] ["A" "B" "C"] ["42" "42"])

(def human-consumption [8.1 7.3 6.6 5.0])
(def critter-consumption [0.0 0.2 0.3 1.1])
(defn unify-diet-data
  [human critter]
  {:human human :critter critter})
(map unify-diet-data human-consumption critter-consumption)

;; collection of functions instead of a collection of values
(def sum #(reduce + %))
(def avg #(/ (sum %) (count %)))
(defn stats
  [numbers]
  (map #(% numbers) [sum count avg]))
(stats [1 2 3])

;; retrieve a value associated with a keyword from a collection of maps
(def identities
  [{:alias "Batman" :real "Bruce Wayne"}
   {:alias "Santa" :real "Ur mom"}])
(map :real identities)



; reduce

;; reduce a collection
(reduce str ["a" "b" "c"])

;; transform map's values
(reduce (fn [new-map [key val]]
          (assoc new-map key (inc val)))
        {} {:max 30 :min 10})

;; filter map's values according to a predicate
(reduce (fn [new-map [key val]]
          (if (> val 4)
            (assoc new-map key val)
            new-map))
        {} {:human 8.1, :critter 0.0})

;; simple map implementation via reduce
(defn my-map
  [f coll]
  (reduce (fn [new-seq element]
            (concat new-seq (list (f element))))
          ()
          coll))
(my-map inc [1 2 3])

;; simple filter implementation via reduce
(defn my-filter
  [pred coll]
  (reduce (fn [new-seq element]
            (if (pred element)
              (concat new-seq (list element))
              new-seq))
          ()
          coll))
(my-filter #(> % 5) [1 3 7 4 8 -3 18])

;; simple some implementation via reduce
(defn my-some
  [pred coll]
  (reduce (fn [acc element]
            (or acc (pred element)))
          nil
          coll))
(my-some #(> % 5) '(1 2 3 4))
(my-some #(> % 5) ())

;; take, drop, take-while, drop-while
(take 3 (range))
(drop 5 (range 42))

(take-while #(> (mod % 3) 0) (range 1 20 4))
(drop-while #(> (mod % 3) 0) (range 1 20 4))

;; filter
(filter #(= (mod % 2) 0) (range 20))

;; some
(some #(and (= (mod % 2) 0) %) (range 20))

;; sort
(sort ["b" "aaa" "bb" "a"])

;; sort-by
(sort-by count ["111" "22" "3"])

; Lazy Seq efficiency
(def vampire-database
  {0 {:makes-blood-puns? false, :has-pulse? true :name "McFishwich"}
   1 {:makes-blood-puns? false, :has-pulse? true :name "McMackson"}
   2 {:makes-blood-puns? true, :has-pulse? false :name "Damon Salvatore"}
   3 {:makes-blood-puns? true, :has-pulse? true :name "Mickey Mouse"}})

(defn vampire-related-details
  [ssn]
  (Thread/sleep 1000)
  (get vampire-database ssn))

(defn vampire?
  [record]
  (and (:makes-blood-puns? record)
       (not (:has-pulse? record))
       record))

(defn identify-vampire
  [ssn-list]
  (first (filter vampire?
                 (map vampire-related-details ssn-list))))

(time (vampire-related-details 0))                          ; time cost of a single call
(time (def mapped-details
        (map vampire-related-details (range 0 1000000))))   ; instant since evaluation is delayed
(time (first mapped-details))                               ; takes about 32 seconds due to chunking
(time (first mapped-details))                               ; consequent call takes almost no time
(time (identify-vampire (range 0 1000000)))



; Infinite sequences
(concat ["nah"] (take 5 (repeat "fukunah")))
(take 3 (repeatedly #(rand-int 10)))
(defn even-numbers
  ([] (even-numbers 0))
  ([n] (cons n (lazy-seq (even-numbers (+ n 2))))))

(take 20 (even-numbers))



; Collection abstraction
; is about the data structure as a whole

;; into
(map identity {:sunlight-reaction "Glitter!"})
(into {} (map identity {:sunlight-reaction "Glitter!"}))

(map identity [:garlic :sesame-oil :fried-eggs])
(into [] (map identity [:garlic :sesame-oil :fried-eggs]))

;; conj (takes a rest parameter as opposed to into)
(conj [1 2 3] 4)
(conj [1 2 3] [4 5])
(conj [1 2 3] 4 5 6)

;; apply
(apply max [1 2 3])                                         ;; equivalent to (max 1 2 3)
(apply conj [1 2 3] [4 5 6])                                ;; equivalent to (conj [1 2 3] 4 5 6)

;; partial
(defn my-partial
  [partialised-fn & args]
  (fn [& more-args]
    (apply partialised-fn (into args more-args))))

(def add20 (my-partial + 20))
(add20 3 4 5)

;; complement
(def my-non-neg? (complement neg?))
(my-non-neg? 1)
(my-non-neg? -1)

(defn my-complement
  [fun]
  (fn [& args]
    (not (apply fun args))))



