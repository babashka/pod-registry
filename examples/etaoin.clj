#!/usr/bin/env bb

(require '[babashka.pods :as pods])

(pods/load-pod 'org.babashka/etaoin "0.0.3")

(require '[pod.babashka.etaoin :as eta])

(def driver (eta/firefox))

(eta/go driver "https://clojure.org")

(eta/quit driver)
