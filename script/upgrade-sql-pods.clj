#!/usr/bin/env bb

(ns upgrade-sql-pods
  (:require [clojure.string :as str]))

(load-file "script/upgrade-manifest.clj")
(require 'upgrade-manifest)

(def version (first *command-line-args*))
(assert version "No version")

(def latest (upgrade-manifest/latest-version "org.babashka/hsqldb"))

(upgrade-manifest/upgrade-manifest "org.babashka/hsqldb" version)
(upgrade-manifest/upgrade-manifest "org.babashka/postgresql" version)
(upgrade-manifest/upgrade-manifest "org.babashka/mssql" version)
(upgrade-manifest/upgrade-manifest "org.babashka/mysql" version)


(doseq [example ["examples/hsqldb.clj" "examples/mysql.clj" "examples/postgresql.clj"]]
  (spit example
        (str/replace (slurp example)
                     latest version)))
