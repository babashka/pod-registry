#!/usr/bin/env bb

(require '[babashka.pods :as pods]
         '[babashka.fs :as fs])
(pods/load-pod 'ilmoraunio/conjtest "0.0.2")
(require '[pod-ilmoraunio-conjtest.api :as conjtest])

(spit "my.edn" "{:hello :world}")
(spit "my.json" "{\"hello\": \"world\"}")

(try 
  (prn (conjtest/parse "my.edn"))
  (prn (conjtest/parse "my.edn" "my.json"))
  (prn (conjtest/parse-as "edn" "my.edn"))
  (prn (conjtest/parse-as "json" "my.json"))
  (prn (conjtest/parse-go "my.edn" "my.json"))
  (prn (conjtest/parse-go-as "edn" "my.edn"))
  (prn (conjtest/parse-go-as "json" "my.json"))
  (finally
    (fs/delete "my.edn")
    (fs/delete "my.json")))
