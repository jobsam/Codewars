(ns simple_pig_latin)

;Move the first letter of each word to the end of it, then add "ay" to the end of the word. Leave punctuation marks untouched.
;
;Examples
;
;pigIt('Pig latin is cool'); // igPay atinlay siay oolcay
;pigIt('Hello world !');     // elloHay orldway !

;https://www.codewars.com/kata/520b9d2ad5c005041100000f
(defn pig-it [s]
  (if s
    (let [str-vec (re-seq #"\w+" s) ;get words
          excl (re-seq #"[^a-zA-Z0-9\s]" s) ;match non word characters
          ret (concat
                (map #(let [frst (first %)
                            res (apply str (rest %))]
                        (str res frst "ay")) str-vec) excl)]
      (clojure.string/join " " ret))
    nil))

