(ns clojure-lsp
  (:require
   [babashka.pods :as pods]))

(pods/load-pod 'com.github.clojure-lsp/clojure-lsp "2022.01.03-19.46.10")

(require '[clojure-lsp.api :as api])
;; see https://cljdoc.org/d/com.github.clojure-lsp/clojure-lsp/2021.12.20-00.36.56/api/clojure-lsp.api

(api/clean-ns! {:settings {:source-paths ["examples"]}})
