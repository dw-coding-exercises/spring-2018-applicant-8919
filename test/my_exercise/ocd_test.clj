(ns my-exercise.ocd-test
  (:require [my-exercise.ocd :as sut]
            [clojure.test :as t :refer [deftest is]]))

(deftest test-ocd
  (let [state-ocd (sut/state "nj")]
    (prn "state-ocd" state-ocd)
    (is (= "ocd-division/country:us/state:nj" state-ocd))
    (is (= "ocd-division/country:us/state:nj/place:newark" (sut/city state-ocd "newark")))))
