(ns ssonin.exercism.strings.log-levels
  (:require [clojure.string :as str]))

(defn message
  "Takes a string representing a log line
   and returns its message with whitespace trimmed."
  [s]
  (str/trim (last (str/split s #":"))))

(defn log-level
  "Takes a string representing a log line
   and returns its level in lower-case."
  [s]
  (str/lower-case (get (re-matches #"(?s)\[(\w+)\].*" s) 1)))

(defn reformat
  "Takes a string representing a log line and formats it
   with the message first and the log level in parentheses."
  [s]
  (clojure.core/str
    (message s)
    " ("
    (log-level s)
    ")"))

(reformat "[ERROR]: \t Corrupt disk\t \t \r\n")