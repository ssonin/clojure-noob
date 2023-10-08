(ns ssonin.exercism.floats.space-age)

(defn on-earth
  [age-in-seconds]
  (double (/
            (BigInteger. (str age-in-seconds))
            (BigInteger. (str 31557600)))))

(defn on-*
  [age-in-seconds orbital-period]
  (/ (on-earth age-in-seconds) orbital-period))

(defn on-mercury
  [age-in-seconds]
  (on-* age-in-seconds 0.2408467))

(defn on-venus
  [age-in-seconds]
  (on-* age-in-seconds 0.61519726))

(defn on-mars
  [age-in-seconds]
  (on-* age-in-seconds 1.8808158))

(defn on-jupiter
  [age-in-seconds]
  (on-* age-in-seconds 11.862615))

(defn on-saturn
  [age-in-seconds]
  (on-* age-in-seconds 29.447498))

(defn on-uranus
  [age-in-seconds]
  (on-* age-in-seconds 84.016846))

(defn on-neptune
  [age-in-seconds]
  (on-* age-in-seconds 164.79132))

(on-earth 1000000000)
(on-mercury 2134835688)