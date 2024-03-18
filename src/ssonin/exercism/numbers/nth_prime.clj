(ns ssonin.exercism.numbers.nth-prime)

(defn prime?
  "Given a number x and a sequence of consecutive prime numbers in ascending order,
  returns true if x is not divisible by any of them, false otherwise."
  [x primes]
  (let [ceil (int (Math/sqrt x))]
    (loop [nums primes]
      (cond
        (or (empty? nums) (> (first nums) ceil)) true
        (== 0 (mod x (first nums))) false
        :else (recur (rest nums))))))

(defn next-prime
  "Given a vector of consecutive prime numbers in ascending order starting with 2,
  returns the vector with the next prime number conjoined."
  ([]
   (vector 2))
  ([primes]
   (if (= [2] primes)
     (conj primes 3)
     (loop [candidate (+ 2 (last primes))]
       (if (prime? candidate primes)
         (conj primes candidate)
         (recur (+ 2 candidate)))))))

(defn nth-prime
  "Returns the prime number in the nth position."
  [n]
  (if (< n 1)
    (throw (IllegalArgumentException. "Less than 1"))
    (last (last (take n (iterate next-prime [2]))))))

(time (let [result (nth-prime 10001)]
        println result))