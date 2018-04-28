(ns my-exercise.search
  "Page for matching-election search results"
  (:require [clojure.tools.logging :as log]
            [hiccup.page :refer [html5]]
            [my-exercise.network :as net]
            [my-exercise.ocd :as ocd]
            [ring.util.anti-forgery :refer [anti-forgery-field]]))

(def address-keys ["street" "street-2" "city" "state" "zip"])

(defn params->address [params]
  (if (map? params)
    (select-keys params address-keys)
    nil))

;; TODO more sophisticated validation
(defn is-valid?
  "Params is valid iff it's a map, containing at least address-keys"
  [params]
  (let [address-map (params->address params)]
    (and (map? params)
         (>= (count address-map) (count address-keys)) ; may have extra keys
         (not (some empty? address-map))))) ; TODO this may not be working right

;; TODO Really just a placeholder
(defn invalid-message [params]
  [:div
   [:h1 "Your input was invalid!"]
   [:p "You entered:" params]])

(defn search
  "Top-level function for handling election search"
  [params]
  (let [{:strs [street street-2 city state zip]} params
        state-ocd (ocd/state state)
        city-ocd (ocd/city state-ocd city)]
    (log/debug (str "Searching against" params))
    (net/request-elections #{state-ocd city-ocd})))

;; TODO important -- make network call asynchronously and add it
;; to page when it comes back. Don't leave the user hanging.
;; TODO this desperately needs formatting & prettification. But I'm nearly out
;; of time, so I'm going to spend the rest going back and making some improvements
(defn election-search [params]
  [:div {:class "election-search"}
   [:h3 "Checking for elections matching " (params->address params)] ; TODO prettify
   [:p " "]
   [:h3 "Results:"]
   [:p (:body (search params))]
   ]
  )

;; TODO better to just call params->address here and pass the address around.
;; don't want to stop and refactor now.
(defn page [request]
  (let [params (:form-params request)
        valid-address-params? (is-valid? params)]
    (if valid-address-params?
      (html5
       (anti-forgery-field)
       (election-search params))
      (html5
       (invalid-message params)))))
