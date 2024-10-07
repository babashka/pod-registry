#!/usr/bin/env bb

(require '[babashka.http-client :as http])
(require '[babashka.pods :as pods])

(pods/load-pod 'com.github.jackdbd/jsoup "0.4.0")

(require '[pod.jackdbd.jsoup :as jsoup])

(def text (-> (http/get "https://clojure.org")
              :body
              (jsoup/select "div p")
              first
              :text))

(println text)
