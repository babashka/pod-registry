#!/usr/bin/env bb

(require '[babashka.pods :as pods])
(pods/load-pod 'org.babashka/fswatcher "0.0.1")

(require '[pod.babashka.fswatcher :as fw])

(fw/watch "." (fn [event] (prn event)) {:delay-ms 5000})

@(promise)
