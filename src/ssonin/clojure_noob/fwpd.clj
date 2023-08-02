(ns ssonin.clojure-noob.fwpd)

(def filename "suspects.csv")

(slurp (clojure.java.io/resource filename))

(def vamp-keys [:name :glitter-index])
(defn str->int
  [str]
  (Integer. str))

(def conversions {:name identity
                  :glitter-index str->int})

(defn convert
  [vamp-key value]
  ((get conversions vamp-key) value))
(convert :glitter-index "3")

(defn parse
  "Convert a CSV into rows of columns"
  [string]
  (map #(clojure.string/split % #",")
       (clojure.string/split string #"\n")))
(parse (slurp (clojure.java.io/resource filename)))
(def parsed (parse (slurp (clojure.java.io/resource filename))))

(defn mapify
  "Return a seq of maps like {:name \"Edward Cullen\" :glitter-index 10}"
  [rows]
  (map (fn [unmapped-row]
         (reduce (fn [row-map [vamp-key value]]
                   (assoc row-map vamp-key (convert vamp-key value)))
                 {}
                 (map vector vamp-keys unmapped-row)))
       rows))
(map vector vamp-keys ["Edward Cullen" "10"])
(first (mapify parsed))
(mapify parsed)

(defn glitter-filter
  [minimum-glitter records]
  (map :name (filter #(>= (:glitter-index %) minimum-glitter) records)))
(glitter-filter 3 (mapify parsed))

(def suspects (mapify parsed))

(def fuknuckle {:name "Fuknuckle First", :glitter-index 13})

(defn validate
  [validators record]
  (reduce
    #(and %1 %2)
    (map (fn [[name validator-function]]
           (apply validator-function [record]))
         validators)))

(validate {:name #(contains? % :name),
           :glitter-index #(contains? % :glitter-index)}
          fuknuckle)

(defn append
  [new-suspect suspects]
  (if (validate {:name #(contains? % :name),
                 :glitter-index #(contains? % :glitter-index)}
                new-suspect)
    (conj suspects new-suspect)
    suspects))
(append fuknuckle suspects)
(def imposter {:name "Dwight"})
(append imposter suspects)




