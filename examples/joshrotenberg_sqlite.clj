#!/usr/bin/env bb

(require '[babashka.pods :as pods])
(pods/load-pod 'joshrotenberg/sqlite "0.1.0")

(require '[pod.joshrotenberg.sqlite :as sql])

;; A file path, or ":memory:" for an in-memory database.
(def db (sql/open ":memory:"))

(sql/execute-batch! db "create table users (id integer primary key, name text, score real)")

;; Parameters are positional.
(prn (sql/execute! db "insert into users (name, score) values (?, ?)" ["ada" 9.5]))
;; => {:changes 1, :last-insert-id 1}

;; One statement per row of params, atomically.
(prn (sql/execute-many! db "insert into users (name, score) values (?, ?)"
                        [["grace" 8.0] ["alan" 7.25]]))
;; => {:changes 2}

;; Rows come back as maps with keyword keys.
(prn (sql/query db "select name, score from users where score > ? order by score desc" [7.5]))
;; => [{:name "ada", :score 9.5} {:name "grace", :score 8.0}]

;; Commits when the body returns; rolls back and rethrows when it throws.
(try
  (sql/with-transaction db
    (sql/execute! db "update users set score = 0")
    (throw (ex-info "changed my mind" {})))
  (catch Exception e (println "rolled back:" (ex-message e))))
(prn (sql/query db "select count(*) as n from users where score = 0"))
;; => [{:n 0}]

;; Calls f with each row as it is read, for results too large to hold at once.
(sql/query-stream db "select name from users order by id" (fn [row] (println (:name row))))

(sql/close! db)
