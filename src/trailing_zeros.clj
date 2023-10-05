(ns trailing_zeros)

;Write a program that will calculate the number of trailing zeros in a factorial of a given number.
;
;N! = 1 * 2 * 3 *  ... * N
;
;Be careful 1000! has 2568 digits...
;
;For more info, see: http://mathworld.wolfram.com/Factorial.html

;https://www.codewars.com/kata/52f787eb172a8b4ae1000a34
(defn zeros [n]
  (loop [count 0
         i 5]
    (if (< i n)
      (recur (+ count (quot n i)) (* i 5))
      count)))