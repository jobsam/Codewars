(ns create_phone_number)

;Write a function that accepts an array of 10 integers (between 0 and 9), that returns a string of those numbers in the form of a phone number.
;
;Example
;
;createPhoneNumber([1, 2, 3, 4, 5, 6, 7, 8, 9, 0]) // => returns "(123) 456-7890"
;The returned format must be correct in order to complete this challenge.
;
;Don't forget the space after the closing parentheses!

;https://www.codewars.com/kata/525f50e3b73515a6db000b83
(defn create-phone-number
  [numbers]
  (let [numbers-str (map str numbers)]
    ;changed the numbers to a stirng
    (str "(" (nth numbers-str 0) (nth numbers-str 1) (nth numbers-str 2) ") "
         (nth numbers-str 3) (nth numbers-str 4) (nth numbers-str 5) "-"
         (nth numbers-str 6) (nth numbers-str 7) (nth numbers-str 8) (nth numbers-str 9))))
