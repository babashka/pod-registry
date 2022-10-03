#!/usr/bin/env bb

(require '[babashka.pods :as pods])

(pods/load-pod 'justone/brisk "0.3.0")

(require '[pod.brisk :as brisk])

;; from/to file
(brisk/freeze-to-file "pod.nippy" {:han :solo})
(prn (brisk/thaw-from-file "pod.nippy"))

;; from/to string
(let [frozen (brisk/freeze-to-string {:princess :leia})]
  (println "frozen:" frozen)
  (prn (brisk/thaw-from-string frozen)))

;; encrypted (see brisk readme for more options)
(brisk/freeze-to-file "pod.encrypted.nippy" {:han :solo} {:password [:cached "my-password"]})
(prn (brisk/thaw-from-file "pod.encrypted.nippy" {:password [:cached "my-password"]}))
