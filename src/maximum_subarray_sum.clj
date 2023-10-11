(ns maximum_subarray_sum
  (:require [clojure.test :refer :all]))



;The maximum sum subarray problem consists in finding the maximum sum of a contiguous subsequence in an array or list of integers:
;
;(max-sequence [-2, 1, -3, 4, -1, 2, 1, -5, 4])
;;; should be 6: [4, -1, 2, 1]
;Easy case is when the list is made up of only positive numbers and the maximum sum is the sum of the whole array. If the list is made up of only negative numbers, return 0 instead.
;
;Empty list is considered to have zero greatest sum. Note that the empty list or array is also a valid sublist/subarray.

(defn max-sequence [arr]
  (if (empty? arr)
    0
    (loop [max-sum (first arr)
           current-sum (first arr)
           remaining (rest arr)]
      (if (empty? remaining)
        (max max-sum 0)
        (recur (max max-sum (+ current-sum (first remaining)))
               (max (+ current-sum (first remaining)) (first remaining))
               (rest remaining))))))

(deftest simple
  (is (= (max-sequence  [-2, 1, -3, 4, -1, 2, 1, -5, 4]) 6))
  (is (= (max-sequence  [7 4 11 -11 39 36 10 -6 37 -10 -32 44 -26 -34 43 43]) 155))
  (is (= (max-sequence  (range 1 1000)) 499500))
  (is (= (max-sequence  [-2 -1 -3 -4 -1 -2 -1 -5 -4]) 0)))