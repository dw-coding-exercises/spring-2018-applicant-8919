(ns my-exercise.ocd
  "Handles OCD-ID formatting etc"
  (:require [clojure.string :as string])
  )

;; TODO currently assumes country = us -- generalize

(defn lower-no-space [s]
  (-> s
      string/lower-case
      (string/replace #"\s+" "")))

(defn state
  "Given a state name, return an OCD_ID for the state."
  [state]
  (str "ocd-division/country:us/state:" (lower-no-space state)))

(defn city
  "Given a preface (currently from state-ocd) and a city name, return an OCD-ID
  for the city."
  [preface city]
  (str preface "/place:" (lower-no-space city)))
