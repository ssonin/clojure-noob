(ns ssonin.exercism.strings.pig-latin
  (:require [clojure.string :refer [starts-with? join split]]))

(defn first-vowel?
  [word]
  (or (starts-with? word "xr")
      (starts-with? word "yt")
      (re-matches #"^[aeiou].*" word)))

(defn first-consonant?
  [word]
  (if-let [match (or (re-find #"[^aeiou]{2,}(?=y)" word)
                     (re-find #"^\w(?=y$)" word)
                     (re-find #"^[^aeiou]+" word))]
    (count match)))

(defn first-consonant-qu?
  [word]
  (if-let [match (re-find #"^[^aeiou]*qu" word)]
    (count match)))

(defn translate-word
  [word]
  (if (first-vowel? word)
    (str word "ay")
    (if-let [idx (or (first-consonant-qu? word)
                     (first-consonant? word))]
      (str (subs word idx) (subs word 0 idx) "ay")
      word)))

(defn translate
  ([s]
   (let [words (split s #"\s+")]
     (join " " (for [w words] (translate-word w))))))

(translate "quick fast run")                                ;ickquay astfay unray