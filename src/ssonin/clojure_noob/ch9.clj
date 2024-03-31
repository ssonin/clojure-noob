(ns ssonin.clojure-noob.ch9)

; promise

(def yak-butter-international
  {:store "Yak Butter International"
   :price 90
   :smoothness 90})
(def butter-than-nothing
  {:store "Butter Than Nothing"
   :price 150
   :smoothness 83})
;; This is the butter that meets our requirements
(def baby-got-yak
  {:store "Baby Got Yak"
   :price 94
   :smoothness 99})

(defn mock-api-call
  [result]
  (Thread/sleep 1000)
  result)

(defn satisfactory?
  "If the butter meets our criteria, return the butter, else return false"
  [butter]
  (and (<= (:price butter) 100)
       (>= (:smoothness butter) 97)
       butter))

(time (some (comp satisfactory? mock-api-call)
            [yak-butter-international butter-than-nothing baby-got-yak]))

(time
  (let [butter-promise (promise)]
    (doseq [butter [yak-butter-international butter-than-nothing baby-got-yak]]
      (future (if-let [satisfactory-butter (satisfactory? (mock-api-call butter))]
                (deliver butter-promise satisfactory-butter))))
    (println "And the winner is:" @butter-promise)))



(declare search-on search-on-google search-on-bing)

(defn find-on-bing-or-google
  "Searches for s on Bing and Google and returns the HTML of the first page returned by the search"
  [s]
  (let [search-result-promise (promise)]
    (doseq [search-fn [search-on-google search-on-bing]]
      (future (let [result (search-fn s)]
                (deliver search-result-promise result))))
    @search-result-promise))

(defn search-on
  [search-engine s]
  ((comp slurp str) "https://" search-engine "/search?q=" s))

(defn search-on-google
  [s]
  (search-on "www.google.com" s))

(defn search-on-bing
  [s]
  (search-on "www.bing.com" s))

(find-on-bing-or-google "chandler")

