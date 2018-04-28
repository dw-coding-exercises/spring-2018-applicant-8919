(ns my-exercise.network
  "Handles network calls which the app needs to make"
  (:require [clj-http.client :as http]
            [clojure.string :as string]))

(defn request-elections
  "Given a set of OCD-IDS, request matching elections from Turbovote. Example URL:
  https://api.turbovote.org/elections/upcoming?district-divisions=ocd-division/country:us/state:nj,ocd-division/country:us/state:nj/place:newark"
  [ocd-ids]
  (let [turbovote-url "https://api.turbovote.org/elections/upcoming"]
    (http/get turbovote-url
              {:query-params {"district-divisions" (string/join "," ocd-ids)}})))
