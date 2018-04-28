(ns my-exercise.search-test
  (:require [my-exercise.search :as sut]
            [clojure.test :as t :refer [deftest is]]))

(def partial-request {"street" "foo"})

(def complete-request {"street" "foo"
                       "street-2" "bar"
                       "city" "Encino"
                       "state" "CA"
                       "zip" 28801})

(deftest is-valid?-test
  (is (= false (sut/is-valid? nil)))
  (is (= false (sut/is-valid? 172)))
  (is (= false (sut/is-valid? {"street" ""
                         "street-2" ""
                         "city" ""
                         "state" ""}))) ; no zip
  (is (= true (sut/is-valid? complete-request)))
  )
