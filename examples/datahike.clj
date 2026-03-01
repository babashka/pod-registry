#!/usr/bin/env bb

(require '[babashka.pods :as pods])

(pods/load-pod 'replikativ/datahike "0.7.1659")

(require '[datahike.pod :as d])

(def config {:store  {:backend :file
                      :path "/tmp/bb-datahike-pod"}
             :keep-history? true
             :schema-flexibility :read})

(d/delete-database config)

(d/create-database config)

(def conn (d/connect config))

(d/transact conn [{:name  "Alice", :age   20}
                  {:name  "Bob", :age   30}
                  {:name  "Charlie", :age   40}
                  {:age 15}])

(def db (d/db conn))

(d/q '[:find ?e ?n ?a
       :where
       [?e :name ?n]
       [?e :age ?a]]
     db)

(d/release-db db)

(d/pull (d/db conn) '[*] 3)

(d/with-db [db (d/db conn)]
  (d/q {:query '{:find [?e ?n ?a]
                 :where
                 [[?e :name ?n]
                  [?e :age ?a]]}
        :args [db]}))
