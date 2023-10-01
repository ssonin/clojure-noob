(ns ssonin.exercism.strings.anagram
  (:require [clojure.string :as str]))

(def base-occurrences
  (into (vector-of :int) (repeat 26 0)))

(defn char->index
  "Converts a lower-case English alphabet letter to a positional index in the array
  where index 0 corresponds to 'a' and index 25 - to 'z'"
  [ch]
  (- (int ch) 97))

(defn occurrences
  "Returns a vector of length 58 representing occurrences of individual letters in word"
  [word]
  (reduce (fn [new-occurrences ch]
            (let [index (char->index ch)
                  previous-val (nth new-occurrences index)]
              (assoc new-occurrences index (inc previous-val))))
          base-occurrences
          (seq word)))

(defn anagram?
  "Returns true if candidate is an anagram of word"
  [word candidate]
  (= (occurrences word) (occurrences candidate)))

(defn anagrams-for
  [word prospect-list]
  (let [lower-case-word (str/lower-case word)]
    (vec (filter #(and
                    (not= word %)
                    (not= word (str/upper-case %))
                    (anagram? lower-case-word (str/lower-case %)))
                 prospect-list))))

(anagrams-for "good" ["dog" "goody"])
(anagrams-for "allergy" ["gallery" "ballerina" "regally" "clergy"  "largely"   "leading"])
(anagrams-for "banana" ["banana"])
(anagrams-for "BANANA" ["banana"])
(anagrams-for "Orchestra" ["cashregister" "Carthorse" "radishes"])