(ns my-exercise.core
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [ring.middleware.reload :refer [wrap-reload]]
            [my-exercise.home :as home]
            [my-exercise.search :as search]))

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
