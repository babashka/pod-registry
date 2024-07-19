(ns clojure-lsp
  (:require
   [babashka.pods :as pods]))

(pods/load-pod 'com.github.clojure-lsp/clojure-lsp "2024.04.22-11.50.26")

(require '[clojure-lsp.api :as api])
;; see https://cljdoc.org/d/com.github.clojure-lsp/clojure-lsp/2024.04.22-11.50.26/api/clojure-lsp.api

(api/clean-ns! {}) ;; see settings in .lsp/config.edn

;; @ericdallo: somehow this isn't working, even when there are lint warnings:
(defn foo [unused])
(comment unresolved-symbol)
(api/diagnostics {})
