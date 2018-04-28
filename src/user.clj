(ns user
  (:require [my-exercise.core :as core]
            [ring.adapter.jetty :as jetty]))

(defn run []
  (jetty/run-jetty core/handler {:port 3010
                                 :join? false}))
