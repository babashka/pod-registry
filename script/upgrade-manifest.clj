#!/usr/bin/env bb

(ns upgrade-manifest
  (:require [babashka.deps :as deps]
            [babashka.fs :as fs]
            [clojure.string :as str]))

;; TODO: version-clj 2.0 isn't compatible with bb anymore
(deps/add-deps '{:deps {version-clj/version-clj {:mvn/version "2.0.2"}}})

(require '[version-clj.core :as v])

(defn latest-version [pod]
  (let [pod-dir (fs/path "manifests" pod)
        dirs (map fs/file-name (fs/list-dir pod-dir))
        latest (reduce (fn [acc v]
                         (if (v/newer? v acc)
                           v acc))
                       (first dirs)
                       (rest dirs))]
    latest))

(defn upgrade-manifest [pod version]
  (let [latest (latest-version pod)
        pod-dir (fs/path "manifests" pod)
        latest-dir (fs/path pod-dir latest)
        target (fs/path pod-dir version)]
    ;; (fs/create-dirs target) ;; copy-tree doesn't create the dir, TODO: fix?
    (fs/copy-tree latest-dir target)
    (doseq [f (fs/list-dir target)]
      (spit (fs/file f) (str/replace (slurp (fs/file f))
                                     latest version)))))

(when (= *file* (System/getProperty "babashka.file"))
  (let [[pod version] *command-line-args*]
    (upgrade-manifest pod version)))
