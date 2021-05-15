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

(def hsqldb-example "examples/hsqldb.clj")

(spit hsqldb-example
      (str/replace (slurp hsqldb-example)
                   latest version))

(def mysql-example "examples/mysql.clj")

(spit mysql-example
      (str/replace (slurp mysql-example)
                   latest version))
