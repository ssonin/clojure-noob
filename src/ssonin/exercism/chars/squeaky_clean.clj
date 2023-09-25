(ns ssonin.exercism.chars.squeaky-clean
  (:require [clojure.string :as str]))

(defn replace-spaces
  [s]
  (str/replace s #"\s" "_"))

(defn replace-unicode
  [s]
  (str/replace s #"[\u0000-\u001F\u007F-\u009F]" "CTRL"))

(defn capitalise
  [s]
  (apply str (cons
               (Character/toUpperCase (first s))
               (rest s))))

(defn kebab->camel
  [s]
  (let [parts (str/split s #"-")]
    (apply str (cons
                 (first parts)
                 (map capitalise (rest parts))))))

(defn omit-non-letters
  [s]
  (apply str (filter
               #(or (= \_ %) (Character/isLetter %))
               (seq s))))

(defn omit-lower-case-greek
  [s]
  (str/replace s #"[α-ω]" ""))

(defn clean
  "TODO: add docstring"
  [s]
  (omit-lower-case-greek
    (omit-non-letters
      (kebab->camel
        (replace-unicode
          (replace-spaces s))))))

(clean "9 -abcĐ😀ω")

