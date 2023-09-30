(ns ssonin.exercism.floats.interest-is-interesting)

(defn interest-rate
  "Returns the interest rate based on the specified balance."
  [balance]
  (cond
    (neg? balance) -3.213
    (< balance 1000) 0.5
    (< balance 5000) 1.621
    :else 2.475))

(defn- double->BigDecimal
  [x]
  (BigDecimal. (str x)))

(defn annual-balance-update
  "Returns the annual balance update, taking into account the interest rate."
  [balance]
  (let [annual-earnings
        (/ (* balance (double->BigDecimal (Math/abs (interest-rate balance)))) 100)]
    (double->BigDecimal (+ balance annual-earnings))))

(defn amount-to-donate
  "Returns how much money to donate based on the balance and the tax-free percentage."
  [balance tax-free-percentage]
  (if (neg? balance)
    0
    (let [tax (double->BigDecimal (/ tax-free-percentage 100))]
      (int (* balance tax 2)))))

