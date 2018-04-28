(ns my-exercise.core
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [ring.middleware.reload :refer [wrap-reload]]
            [my-exercise.home :as home]
            [my-exercise.search :as search]))

;; NOTES FROM CANDIDATE:

;; - I've sprinkled a bunch of TODOs around for stuff I would have worked on if
;;   I'd had more time.

;; - Took me a little while to stop writing the way I would usually, so I ended
;;   up misjudging time a bit -- if I had it to do over, I would have spent more
;;   time making existing stuff more robust, and a bit less time trying to
;;   get end-to-end functionality.

;; - I made pretty fine-grained commits so that y'all could get a sense of my
;;   process if you were inclined to.

;; TODO favicon
;; TODO check on browser, verify that page is reasonably responsive
;; TODO CSS?
;; TODO double check other browsers / other OSs

(defroutes app
  (GET "/" [] home/page)
  (POST "/search" [] search/page)
  (route/resources "/")
  (route/not-found "Not found"))

(def handler
  (-> app
      (wrap-defaults site-defaults)
      wrap-reload))
