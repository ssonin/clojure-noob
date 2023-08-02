(ns ssonin.exercism.conditionals.robot_simulator
  (:require [clojure.string :as str]))

(def turns
  {:north {:R :east,  :L :west},
   :east  {:R :south, :L :north},
   :south {:R :west,  :L :east},
   :west  {:R :north, :L :south}})

(def advancements
  {:north {:x 0, :y 1},
   :east  {:x 1, :y 0},
   :south {:x 0, :y -1},
   :west  {:x -1, :y 0}})

(defn robot
  [coordinates bearing]
  {:bearing bearing, :coordinates coordinates})

(defn turn
  "Returns a robot at the same position after making a turn as per command."
  [command robot-starting-pos]
  (let [current-direction (:bearing robot-starting-pos)]
    (robot
      (:coordinates robot-starting-pos)
      (get (get turns current-direction) command current-direction))))

(defn step
  "Returns a robot in a new position after making a step in the current direction."
  [robot-starting-pos]
  (let [bearing (:bearing robot-starting-pos)]
    (robot
      (merge-with +
                  (:coordinates robot-starting-pos)
                  (get advancements bearing {:x 0, :y 0}))
      bearing)))

(defn choose-move
  "Returns either a step or a turn based on the command."
  [command]
  (if (= :A command)
    step
    (partial turn command)))

(defn parse-commands
  [commands]
  (map keyword (clojure.string/split commands #"")))

(defn simulate
  [commands start]
  (reduce (fn [current-pos command]
            ((choose-move command) current-pos))
          start
          (parse-commands commands)))

(turn (robot {:x 0 :y 0} :north) :L)
(step (robot {:x 0 :y 0} :north))
(step (robot {:x 0 :y 0} :east))
(step (robot {:x 0 :y 0} :south))
(step (robot {:x 0 :y 0} :west))

(simulate "RAALAL" (robot {:x 7 :y 3} :north))
(simulate "R" (robot {:y 0, :x 0} :north))
(simulate "LAAARRRALLLL" (robot {:y 4, :x 8} :south))


(defn reformat
  "Takes a string representing a log line and formats it
   with the message first and the log level in parentheses."
  [s]
  (let [matched (re-matches #"\[(\w+)\]:(.+)" s)]
    (clojure.core/str
      (get matched 2)
      " ("
      (str/lower-case (get matched 1))
      ")")))

(reformat "[ERROR]: Stack overflow")
(reformat "[ERROR]: \t Corrupt disk\t \t \r\n")