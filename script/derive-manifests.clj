#!/usr/bin/env bb

(ns derive-manifests
  (:require [clojure.edn :as edn]
            [clojure.java.io :as io]
            [clojure.string :as str]
            [hiccup2.core :as html]))

(defn edn-read
  [path]
  (with-open [r (io/reader path)]
    (edn/read (java.io.PushbackReader. r))))

(def recent-manifests
  (->> (java.io.File. "manifests/")
       file-seq
       (filter #(str/ends-with? % "manifest.edn"))
       (map edn-read)
       (sort-by :pod/version)
       (reverse)
       (group-by :pod/name)
       (reduce (fn [agg [_ vs]] (conj agg (first vs))) [])))

(def table
  ;; This can be done using either `hiccup` or `clojure.pprint/cl-format`
  (html/html [:table
              [:tr
               [:th "Pod"]
               [:th "Description"]
               [:th "Latest version"]]
              (for [pod recent-manifests
                    :let [{name :pod/name
                           description :pod/description
                           version :pod/version} pod]]
                [:tr
                 [:td name]
                 [:td description]
                 [:td version]])]))

(defn insert-table!
  [table]
  (let [readme-path "README.md"
        readme-content (slurp readme-path)
        new-content (str/replace readme-content
                                 #"## Registered pods\n\n"
                                 (str "## Registered pods\n\n" table "\n\n"))]
    (spit readme-path new-content)))

(insert-table! table)
