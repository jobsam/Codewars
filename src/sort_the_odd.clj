(ns sort_the_odd)

;Task
;
;You will be given an array of numbers. You have to sort the odd numbers in ascending order while leaving the even numbers at their original positions.
;
;Examples
;
;[7, 1]  =>  [1, 7]
;[5, 8, 6, 3, 4]  =>  [3, 8, 6, 5, 4]
;[9, 8, 7, 6, 5, 4, 3, 2, 1, 0]  =>  [1, 8, 3, 6, 5, 4, 7, 2, 9, 0]

;solution 1

(defn sort-array [arr]
  (if (seq arr)
    (let [arr (vec arr)
          odd-num (filter odd? arr)
          val-map (loop [i 0
                         c (count arr)
                         res {}]
                    (let [res (assoc res (keyword (str i)) (get arr i))]
                      (if (< i (dec c))
                        (recur (inc i) c res)
                        res)))
          odd-indx (sort (remove nil?
                                 (map (fn [[k v]]
                                        (when (odd? v)
                                          (Integer/parseInt (name k))))
                                      val-map)))
          sorted-odd (sort odd-num)]
      (loop [idx (first odd-indx)
             idx-nxt (next odd-indx)
             val (first sorted-odd)
             val-nxt (next sorted-odd)
             res arr]
        (let [res (assoc res idx val)]
          (if idx-nxt
            (recur (first idx-nxt) (next idx-nxt) (first val-nxt) (next val-nxt) res)
            res))))
    []))


;Soln 2

(defn sort-array2 [arr]
  (if (seq arr)
    (let [odd-num (filter odd? arr)
          sorted-odd (sort odd-num)]
      (loop [odds sorted-odd
             res []
             coll arr]
        (if (empty? coll)
          (concat res (reverse odds))
          (if (odd? (first coll))
            (recur (next odds) (conj res (first odds)) (rest coll))
            (recur odds (conj res (first coll)) (rest coll))))))
    []))