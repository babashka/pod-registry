#!/usr/bin/env bb

(require '[babashka.pods :as pods])

(pods/load-pod 'frozenlock/bbolt "0.1.1")

(require '[pod.frozenlock.bbolt :as bbolt])

(def db (bbolt/open "/tmp/example.db"))

;; A write transaction: create a bucket and put a few keys.
(let [tx (bbolt/begin db)]
  (bbolt/put-many tx "people" [["alice" "1"] ["bob" "2"]])
  (bbolt/commit tx))

;; A read transaction: point read, range scan, count.
(let [tx (bbolt/begin-read db)]
  (println "alice:" (bbolt/get tx "people" "alice"))
  (println "keys: " (mapv first (bbolt/scan tx "people" nil false nil false 0)))
  (println "count:" (bbolt/count tx "people"))
  (bbolt/rollback tx))

(bbolt/close db)
