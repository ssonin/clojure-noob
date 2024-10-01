(ns ssonin.exercism.protein-translation
  (:require [clojure.string :refer [join]]))

(defn translate-codon
  [codon]
  ({"AUG" "Methionine"
    "UUU" "Phenylalanine"
    "UUC" "Phenylalanine"
    "UUA" "Leucine"
    "UUG" "Leucine"
    "UCU" "Serine"
    "UCC" "Serine"
    "UCA" "Serine"
    "UCG" "Serine"
    "UAU" "Tyrosine"
    "UAC" "Tyrosine"
    "UGU" "Cysteine"
    "UGC" "Cysteine"
    "UGG" "Tryptophan"
    "UAA" :stop
    "UAG" :stop
    "UGA" :stop} codon))

(defn translate-rna
  [rna]
  (->> (partition 3 rna)
       (map join)
       (map translate-codon)
       (take-while #(not= % :stop))))

(translate-rna "AUG")