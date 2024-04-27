(ns ssonin.exercism.lists.card-games)

(defn rounds
  "Takes the current round number and returns
   a `list` with that round and the _next two_."
  [n]
  (take 3 (iterate inc n)))

(defn concat-rounds
  "Takes two lists and returns a single `list`
   consisting of all the rounds in the first `list`,
   followed by all the rounds in the second `list`"
  [l1 l2]
  (concat l1 l2))

(defn contains-round?
  "Takes a list of rounds played and a round number.
   Returns `true` if the round is in the list, `false` if not."
  [l n]
  (boolean (some #{n} l)))

(defn card-average
  "Returns the average value of a hand"
  [hand]
  (let [sum #(reduce + %)]
    (/ (double (sum hand)) (count hand))))

(defn median
  [coll]
  (let [mid #(nth %1 (quot (count %1) 2))
        mid-1 #(dec (mid %1))]
    (cond
      (odd? (count coll)) (double (mid coll))
      (even? (count coll)) (card-average ((juxt mid-1 mid) coll)))))

(defn approx-average?
  "Returns `true` if average is equal to either one of:
  - Take the average of the _first_ and _last_ number in the hand.
  - Using the median (middle card) of the hand."
  [hand]
  (let [avg (card-average hand)
        first-last-avg (card-average [(first hand) (last hand)])]
    (or (= avg first-last-avg)
        (= avg (median hand)))))

(defn statistics
  [hand]
  (loop [coll hand
         result {:even {:sum 0, :count 0}, :odd {:sum 0, :count 0}}
         turns (cycle [:even :odd])]
    (if (empty? coll)
      result
      (recur
        (rest coll)
        (update result (first turns) #(merge-with + %1 {:sum (first coll) :count 1}))
        (rest turns)))))

(defn average-even-odd?
  "Returns true if the average of the cards at even indexes
   is the same as the average of the cards at odd indexes."
  [hand]
  (let [stats (statistics hand)
        avg #(double (/ (:sum %1) (:count %1)))]
    (= (avg (:even stats))
       (avg (:odd stats)))))

(defn maybe-double-last
  "If the last card is a Jack (11), doubles its value
   before returning the hand."
  [hand]
  (loop [coll hand
         result []]
    (if (empty? coll)
      result
      (recur (rest coll)
             (if (= coll [11])
               (conj result 22)
               (conj result (first coll)))))))




