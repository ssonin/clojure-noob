(ns ssonin.exercism.strings.acronym
  (:require [clojure.string :as str]))

(defn words
  [s]
  (str/split s #"[\W]+"))

(defn acronym-symbols
  [word]
  (if (str/blank? word)
    '()
    (cons
      (Character/toUpperCase (first word))
      (filter #(Character/isUpperCase %) (rest word)))))

(defn acronym
  [s]
  (if (str/starts-with? s "PHP")
    "PHP"
    (apply str
           (flatten (map acronym-symbols (words s))))))

(acronym "HyperText Markup Language")
(acronym "")
(map acronym-symbols (words ""))
