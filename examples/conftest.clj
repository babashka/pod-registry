#!/usr/bin/env bb

(require '[babashka.pods :as pods]
         '[babashka.fs :as fs])
(pods/load-pod 'ilmoraunio/conftest "0.0.4")
(require '[pod.ilmoraunio.conftest :as conftest])

(spit "my.edn" "{:hello :world}")
(spit "my.json" "{\"hello\": \"world\"}")

(try 
  (prn (conftest/parse "my.edn"))
  (prn (conftest/parse "my.edn" "my.json"))
  (prn (conftest/parse-as "edn" "my.edn"))
  (prn (conftest/parse-as "json" "my.json"))
  (finally
    (fs/delete "my.edn")
    (fs/delete "my.json")))
