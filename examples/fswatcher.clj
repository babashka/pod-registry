#!/usr/bin/env bb

(require '[babashka.pods :as pods])
(pods/load-pod 'org.babashka/fswatcher "0.0.3")

(require '[pod.babashka.fswatcher :as fw])

(fw/watch "." prn {:delay-ms 5000})

(println "Watching current directory for changes... Press Ctrl-C to quit.")

@(promise)
