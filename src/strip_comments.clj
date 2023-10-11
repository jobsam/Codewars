(ns strip_comments
  (:require [clojure.test :refer :all]))

;Complete the solution so that it strips all text that follows any of a set of comment markers passed in. Any whitespace at the end of the line should also be stripped out.
;
;Example:
;
;Given an input string of:
;
;apples, pears # and bananas
;grapes
;bananas !apples
;The output expected would be:
;
;apples, pears
;grapes
;bananas
;The code would be called like so:
;
;var result = solution("apples, pears # and bananas\ngrapes\nbananas !apples", ["#", "!"])
;// result should == "apples, pears\ngrapes\nbananas"


(defn strip-comments [input markers]
  (->> (clojure.string/split-lines input)
       (map (fn [line]
              (let [comment-index (first (keep-indexed (fn [idx char]
                                                         (when (some #(= (str char) %) markers)
                                                           idx)) line))]
                (if comment-index
                  (subs line 0 comment-index)
                  line))))
       (map #(clojure.string/trimr %))
       (clojure.string/join "\n")))



(deftest strip-comments-tests
  (are [text symbols expected] (= (strip-comments text symbols) expected)
                               "apples, pears # and bananas\ngrapes\nbananas !apples"
                               '("#" "!")
                               "apples, pears\ngrapes\nbananas"

                               "a #b\nc\nd $e f g"
                               '("#" "$")
                               "a\nc\nd"

                               "a \n b \nc "
                               '("#" "$")
                               "a\n b\nc"))