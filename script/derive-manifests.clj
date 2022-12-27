#!/usr/bin/env bb

(ns derive-manifests
  (:require [babashka.deps :as deps]
            [clojure.edn :as edn]
            [clojure.java.io :as io]
            [clojure.string :as str]))

;; Install version-clj on the classpath
(deps/add-deps '{:deps {version-clj/version-clj {:mvn/version "2.0.2"}}})
(require '[version-clj.core :as v])

(defn edn-read
  [path]
  (with-open [r (io/reader path)]
    (edn/read (java.io.PushbackReader. r))))

(def recent-manifests
  (->> (java.io.File. "manifests/")
       file-seq
       (filter #(str/ends-with? % "manifest.edn"))
       (map edn-read)
       ;; reversed because we want higher to lower
       (sort (fn [x y] (v/version-compare (:pod/version y) (:pod/version x))))

       ;; get first of the pods group only
       (group-by :pod/name)
       (reduce (fn [agg [_ vs]] (conj agg (first vs))) [])
       (sort-by :pod/name)))

(defn format-name
  [pod]
  (->> pod
       :pod/artifacts
       first
       :artifact/url
       (re-find #"https:\/\/github\.com\/[A-Za-z0-9-]*\/[A-Za-z0-9-]*")
       (format "[%s](%s)" (:pod/name pod))))

(def ^:const programming-langs
  {:clojure "[<img src=\"https://upload.wikimedia.org/wikipedia/commons/5/5d/Clojure_logo.svg\" alt=\"clojure\" width=\"24\" height=\"24\">](https://clojure.org/)"
   :go "[<img src=\"https://go.dev/blog/go-brand/Go-Logo/SVG/Go-Logo_Blue.svg\" alt=\"golang\" width=\"24\" height=\"24\">](https://golang.org/)"
   :rust "[<img src=\"https://upload.wikimedia.org/wikipedia/commons/d/d5/Rust_programming_language_black_logo.svg\" alt=\"rust\" width=\"24\" height=\"24\">](https://www.rust-lang.org/)"
   :haskell "[<img src=\"https://upload.wikimedia.org/wikipedia/commons/1/1c/Haskell-Logo.svg\" alt=\"haskell\" width=\"24\" height=\"24\">](https://haskell.org/)"})

(defn generate-table
  [pods]
  (reduce
   (fn [table {:pod/keys [description version example language] :as p}]
     (str table
          (format "| %s | %s | %s | %s | %s |\n"
                  (format-name p)
                  description
                  version
                  (if (str/blank? example) "" (format "[link](%s)" example))
                  (programming-langs (keyword language)))))
   (str "| Pod | Description | Latest version | Example | Language |\n"
        "| --- | --- | --- | --- | --- |\n")
   pods))

(defn insert-table!
  []
  (let [readme-path "README.md"
        readme-content (slurp readme-path)
        table (generate-table recent-manifests)

        new-content
        (str/replace readme-content
                     ;; if there's table already, replace it
                     ;; #"## Registered pods\n\n.*\n\n## Registering a pod"
                     #"\#\# Registered pods\n\n(.*\|\n)*"
                     (str "## Registered pods\n\n" table))]
    (spit readme-path new-content)))

(when (= *file* (System/getProperty "babashka.file"))
  (insert-table!))

nil
