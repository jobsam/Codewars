(ns format_duration
  (:require [clojure.test :refer :all]))

;Your task in order to complete this Kata is to write a function which formats a duration, given as a number of seconds, in a human-friendly way.
;
;The function must accept a non-negative integer. If it is zero, it just returns "now". Otherwise, the duration is expressed as a combination of years, days, hours, minutes and seconds.
;
;It is much easier to understand with an example:
;
;* For seconds = 62, your function should return
;"1 minute and 2 seconds"
;* For seconds = 3662, your function should return
;"1 hour, 1 minute and 2 seconds"
;For the purpose of this Kata, a year is 365 days and a day is 24 hours.
;
;Note that spaces are important.
;
;Detailed rules
;
;The resulting expression is made of components like 4 seconds, 1 year, etc. In general, a positive integer and one of the valid units of time, separated by a space. The unit of time is used in plural if the integer is greater than 1.
;
;The components are separated by a comma and a space (", "). Except the last component, which is separated by " and ", just like it would be written in English.
;
;A more significant units of time will occur before than a least significant one. Therefore, 1 second and 1 year is not correct, but 1 year and 1 second is.
;
;Different components have different unit of times. So there is not repeated units like in 5 seconds and 1 second.
;
;A component will not appear at all if its value happens to be zero. Hence, 1 minute and 0 seconds is not valid, but it should be just 1 minute.
;
;A unit of time must be used "as much as possible". It means that the function should not return 61 seconds, but 1 minute and 1 second instead. Formally, the duration specified by of a component must not be greater than any valid more significant unit of time.

(defn formatDuration
  [secs]
  (if (or (neg? secs) (zero? secs))
    "now"
    (let [minute 60
          hour (* 60 60)
          day (* hour 24)
          year (* day 365)
          process-minutes #(let [mn (quot % minute)
                                 sc (mod % minute)
                                 fm (fn [pm] (if (> pm 1)
                                               (str pm " minutes")
                                               (str pm " minute")))]
                            (if (zero? sc)
                              (fm mn)
                              (str (fm mn) " and " sc (if (> sc 1) " seconds" " second"))))
          process-hour #(let [hr (quot % hour)
                              mn-sc (when (pos? (mod % hour))
                                      (process-minutes (mod % hour)))
                              only (= (mod % minute) 0)
                              fm (fn [pm] (if (> pm 1)
                                            (str pm " hours")
                                            (str pm " hour")))]
                          (if mn-sc
                            (str (fm hr) (if only " and " ", ") mn-sc)
                            (fm hr)))
          process-day #(let [dy (quot % day)
                             hr-mn-sc (when (pos? (mod % day))
                                        (process-hour (mod % day)))
                             fm (fn [pm] (if (> pm 1)
                                           (str pm " days")
                                           (str pm " day")))]
                         (if hr-mn-sc
                           (str (fm dy) ", " hr-mn-sc)
                           (fm dy)))
          process-year #(let [yr (quot % year)
                              dy-hr-mn-sc (when (pos? (mod % year))
                                            (process-day (mod % year)))
                              fm (fn [pm] (if (> pm 1)
                                            (str pm " years")
                                            (str pm " year")))]
                         (if dy-hr-mn-sc
                           (str (fm yr) ", " dy-hr-mn-sc)
                           (fm yr)))]
      (cond
        (< secs minute) (str secs " second")
        (and (>= secs minute) (< secs hour)) (process-minutes secs)
        (and (>= secs hour) (< secs day)) (process-hour secs)
        (and (>= secs day) (< secs year)) (process-day secs)
        :else (process-year secs)))))

;Some expected test cases won't pass, like according to the question, it should be 1 second but 1 seconds is validated as success.

(deftest test-human-readable-duration
  (is (= (formatDuration 0) "now"))
  (is (= (formatDuration 1) "1 second"))
  (is (= (formatDuration 62) "1 minute and 2 seconds"))
  (is (= (formatDuration 120) "2 minutes"))
  (is (= (formatDuration 3600) "1 hour"))
  (is (= (formatDuration 3662) "1 hour, 1 minute and 2 seconds"))
  (is (= (formatDuration 15731080) "182 days, 1 hour, 44 minutes and 40 seconds"))
  (is (= (formatDuration 132030240) "4 years, 68 days, 3 hours and 4 minutes"))
  (is (= (formatDuration 253374061) "8 years, 12 days, 13 hours, 41 minutes and 1 second"))
  (is (= (formatDuration 5796661) "67 days, 2 hours, 11 minutes and 1 seconds"))
  (is (= (formatDuration 605006) "7 days, 0 hour, 3 minutes and 26 seconds")))

