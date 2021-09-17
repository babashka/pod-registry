#!/usr/bin/env bb

(require '[babashka.pods :as pods])

(pods/load-pod 'huahaiy/datalevin "0.5.14")

(require '[pod.huahaiy.datalevin :as d])

(def conn (d/get-conn "/tmp/mydb"))

(d/transact! conn [{:greeting "Hello world!"}])

(d/q '[:find ?g :where [_ :greeting ?g]] (d/db conn))

(d/close conn)
