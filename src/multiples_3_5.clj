(ns multiples_3_5)

;https://www.codewars.com/kata/514b92a657cdc65150000006

;DESCRIPTION:
;
;If we list all the natural numbers below 10 that are multiples of 3 or 5, we get 3, 5, 6 and 9. The sum of these multiples is 23.
;
;Finish the solution so that it returns the sum of all the multiples of 3 or 5 below the number passed in.
;
;Additionally, if the number is negative, return 0.
;
;Note: If the number is a multiple of both 3 and 5, only count it once.

(defn solution [number]
  ;validate negative
  (if (neg? number)
    0
    (apply +
           (filter #(when (or (= 0 (mod % 3 )) (= 0 (mod % 5 )))
                      %) (range number)))))