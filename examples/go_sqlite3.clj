#!/usr/bin/env bb

(ns go-sqlite3
  (:require
   [babashka.fs :as fs]
   [babashka.pods :as pods]))

(pods/load-pod 'org.babashka/go-sqlite3 "0.3.12")
(require '[pod.babashka.go-sqlite3 :as sqlite])

(when-not (fs/exists? "/tmp/foo.db")
  (sqlite/execute! "/tmp/foo.db" ["create table foo (bar)"]))
(let [conn (sqlite/get-connection "/tmp/foo.db")]
  (sqlite/execute! conn ["insert into foo (bar) values (?)" "baz"])
  (prn (sqlite/query conn ["select * from foo"])))
