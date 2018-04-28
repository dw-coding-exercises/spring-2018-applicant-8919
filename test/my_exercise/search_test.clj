(ns my-exercise.search-test
  (:require [my-exercise.search :as sut]
            [clojure.test :as t :refer [deftest is]]))

(def partial-request {"street" "foo"})

(def complete-request {"street" "foo"
                       "street-2" "bar"
                       "city" "CA"
                       "zip" 28801})

(deftest is-valid?-test
  (is (= (sut/is-valid? nil) false))
  (is (= (sut/is-valid? 172) false))
  (is (= (sut/is-valid? complete-request) true))
  )
