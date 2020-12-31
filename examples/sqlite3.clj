(ns sqlite3-script
  (:require [babashka.pods :as pods]))

(pods/load-pod 'org.babashka/sqlite3 "0.0.1")
(require '[pod.babashka.sqlite3 :as sqlite])

(sqlite/execute! "/tmp/foo.db" ["create table foo (bar)"])
(sqlite/execute! "/tmp/foo.db" ["insert into foo (bar) (?)" "baz"])
(sqlite/query    "/tmp/foo.db" ["select * from foo"])
