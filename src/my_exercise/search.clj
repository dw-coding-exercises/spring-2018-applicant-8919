(ns my-exercise.search
  (:require [clojure.tools.logging :as log]
            [hiccup.page :refer [html5]]
            [ring.util.anti-forgery :refer [anti-forgery-field]]))

(defn election-search [request]
  ;; TODO restart capability
  ;; TODO state, eg mount? Probably not, can be stateless.
  (log/warn "HELLO from logging")
  ;; (log/warn (with-out-str (clojure.pprint/pprint request)))
  (def rq request) ; TODO
  [:div {:class "election-search"}
   [:p "U R the man u so bad"]
   ]


  )

(defn page [request]
  (prn "YO YO from page!")
  (html5
   (anti-forgery-field)
   (election-search request))) ; TODO change to [] ?
