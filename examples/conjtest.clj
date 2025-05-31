#!/usr/bin/env bb

(require '[babashka.pods :as pods]
         '[babashka.fs :as fs])
(pods/load-pod 'ilmoraunio/conjtest "0.0.5")
(require '[pod-ilmoraunio-conjtest.api :as conjtest])

(spit "my.edn" "{:hello :world}")
(spit "my.json" "{\"hello\": \"world\"}")
(spit "my.properties" "SAMPLE_VALUE=something-here\nother.value.url=https://example.com/\nsecret.value.exception=f9761ebe-d4dc-11eb-8046-1e00e20cdb95")

(try 
  (prn (conjtest/parse "my.edn"))
  (prn (conjtest/parse "my.edn" "my.json"))
  (prn (conjtest/parse-as "edn" "my.edn"))
  (prn (conjtest/parse-as "json" "my.json"))
  (prn (conjtest/parse-go "my.edn" "my.json"))
  (prn (conjtest/parse-go-as "edn" "my.edn"))
  (prn (conjtest/parse-go-as "json" "my.json"))
  (prn (conjtest/parse* {:keywordize? true} "my.properties"))
  (prn (conjtest/parse-as* {:keywordize? true} "properties" "my.properties"))
  (prn (conjtest/parse-go* {:keywordize? true} "my.properties"))
  (prn (conjtest/parse-go-as* {:keywordize? true} "properties" "my.properties"))
  (finally
    (fs/delete "my.edn")
    (fs/delete "my.json")
    (fs/delete "my.properties")))
