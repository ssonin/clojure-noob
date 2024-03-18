(ns ssonin.exercism.rna-transcriptions)

(def mappings
  {\G \C \C \G \T \A \A \U})

(defn transcribe
  [ch]
  (assert (contains? mappings ch))
  (mappings ch))

(defn to-rna
  [dna]
  (apply str (for [ch dna] (transcribe ch))))

(to-rna "XCGFGGTDTTAA")