(ns clojure-lsp
  (:require
   [babashka.pods :as pods]))

(pods/load-pod 'com.github.clojure-lsp/clojure-lsp "2022.11.03-00.14.57")

(require '[clojure-lsp.api :as api])
;; see https://cljdoc.org/d/com.github.clojure-lsp/clojure-lsp/2022.11.03-00.14.57/api/clojure-lsp.api

(api/clean-ns! {}) ;; see settings in .lsp/config.edn

;; @ericdallo: somehow this isn't working, even when there are lint warnings:
(defn foo [unused])
(comment unresolved-symbol)
(api/diagnostics {})
