#!/usr/bin/env bb

(ns go-sqlite3
  (:require
   [babashka.pods :as pods]))

(pods/load-pod 'org.babashka/go-sqlite3 "0.1.2")
(require '[pod.babashka.go-sqlite3 :as sqlite])

(sqlite/execute! "/tmp/foo.db" ["create table foo (bar)"])
(sqlite/execute! "/tmp/foo.db" ["insert into foo (bar) values (?)" "baz"])
(sqlite/query    "/tmp/foo.db" ["select * from foo"])
