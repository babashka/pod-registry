#!/usr/bin/env bb

(require '[babashka.pods :as pods])

(pods/load-pod 'huahaiy/datalevin "0.8.16")

(require '[pod.huahaiy.datalevin :as d])

(def conn (d/get-conn "/tmp/mydb"))

(d/transact! conn [{:greeting "Hello world!"}])

(println (d/q '[:find ?g :where [_ :greeting ?g]] (d/db conn)))

(d/close conn)
