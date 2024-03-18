(ns ssonin.exercism.nucleotide-count)

(def valid-nucleotides
  #{\A, \C, \G, \T})

(defn validate
  [nucleotide]
  (if-not (contains? valid-nucleotides nucleotide)
    (throw (IllegalArgumentException. (str nucleotide)))))

(defn count-of-nucleotide-in-strand
  [nucleotide strand]
  (validate nucleotide)
  (reduce (fn [acc curr]
            (if (= nucleotide curr)
              (inc acc)
              acc))
          0
          strand))

(defn nucleotide-counts
  [strand]
  (reduce (fn [acc nucleotide]
            (validate nucleotide)
            (update-in acc [nucleotide] inc))
          {\A 0, \C 0, \G 0, \T 0}
          strand))

(nucleotide-counts "AGCTTTTCATTCTGACTGCAACGGGCAATATGTCTCTGTGTGGATTAAAAAAAGAGTGTCTGATAGCAGC")