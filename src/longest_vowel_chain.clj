(ns longest_vowel_chain)


;The vowel substrings in the word codewarriors are o,e,a,io. The longest of these has a length of 2. Given a lowercase string that has alphabetic characters only (both vowels and consonants) and no spaces, return the length of the longest vowel substring. Vowels are any of aeiou.
;
;Good luck!
;
;If you like substring Katas, please try:
;

(defn solve [s]
  (loop [max-length 0 ; Initialize the maximum length to 0
         current-length 0 ; Initialize the current substring length to 0
         s (seq s)] ; Convert the input string to a sequence for iteration
    (if (empty? s)
      max-length ; Return the maximum length when we reach the end of the string
      (let [char (first s)]
        (if (#{\a \e \i \o \u} char) ; Check if the character is a vowel
          (recur (max max-length (inc current-length)) ; Increment the current substring length and update max
                 (inc current-length)
                 (rest s)) ; Move to the next character
          (recur max-length ; Reset the current substring length to 0
                 0
                 (rest s))))))) ; Move to the next character