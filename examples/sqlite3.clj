(ns sqlite3-script
  (:require [babashka.deps :as deps]))

(pods/load-pod 'org.babashka/sqlite3 "0.0.1")
(require '[pod.babashka.sqlite3 :as sqlite])
