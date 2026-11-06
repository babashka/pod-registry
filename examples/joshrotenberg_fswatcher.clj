#!/usr/bin/env bb

;; A drop-in for org.babashka/fswatcher: the same pod.babashka.fswatcher
;; namespace, built in Rust on notify.

(require '[babashka.pods :as pods]
         '[babashka.fs :as fs])
(pods/load-pod 'joshrotenberg/fswatcher "0.1.1")

(require '[pod.babashka.fswatcher :as fw])

(def tmp (str (fs/create-temp-dir)))

;; Events are maps with :type and :path. :delay-ms is the quiet period used to
;; coalesce bursts of events on one path (default 2000).
(def w (fw/watch tmp prn {:recursive true :delay-ms 200}))

(Thread/sleep 200)
(spit (str (fs/path tmp "a.txt")) "hello")
;; {:type :create, :path ".../a.txt"}
;; (macOS can also report the temp directory's own creation first)
(Thread/sleep 500)

(fw/unwatch w)
(fs/delete-tree tmp)
