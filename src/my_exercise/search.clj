(ns my-exercise.search
  "Page for matching-election search results"
  (:require [clojure.tools.logging :as log]
            [hiccup.page :refer [html5]]
            [my-exercise.ocd :as ocd]
            [ring.util.anti-forgery :refer [anti-forgery-field]]))

(def address-keys ["street" "street-2" "city" "state" "zip"])

(defn params->address [params]
  (select-keys params address-keys))

;; TODO more sophisticated validation
(defn is-valid?
  "Params is valid iff it's a map, containing at least address-keys"
  [params]
  true
  ;; TODO commented during early dev
  ;; (prn "params->address:" (params->address params))
  ;; (let [address-map (params->address params)]
  ;;   (and (map? params)
  ;;        (>= (count address-map) (count address-keys)) ; may have extra keys
  ;;        (not (some empty? address-map))))
  )

;; TODO could use massive improvement
(defn invalid-message [params]
  [:div
   [:h1 "Your input was invalid!"]
   [:p "You entered:" params]])

;; (defn )

(defn search
  "Top-level function for handling election search"
  [params] ; TODO
  (let [{:strs [street street-2 city state zip]} params
        state-ocd (ocd/state state)
        city-ocd (ocd/city state-ocd city)]

    ;; TODO confirm non-nil? Or how will API do against nil vals?
    (log/warn (str "form-params: " params))
    (log/warn "STREET CITY ETC:")
    (log/warn street city street-2 state zip)
    )
  )

;; TODO important -- make network call asynchronously and add it
;; to page when it comes back
(defn election-search [params]
  ;; TODO state, eg mount? Probably not, can be stateless.
  (def rq params) ; TODO
  (search params)
  [:div {:class "election-search"}
   [:p "Checking for elections matching " (params->address params)]
   [:p "U R the man u so bad"]
   ]
  )

;; TODO better to just call params->address here and pass the address around.
;; don't want to stop and refactor now.
(defn page [request]
  (let [params (:form-params request)
        valid-address-params? (is-valid? params)]
    (if valid-address-params?
      (do (prn "valid!")
          (html5
        (anti-forgery-field)
        (election-search params)))
      (html5
       (invalid-message params)))))
