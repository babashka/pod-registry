#!/usr/bin/env bb

(require '[babashka.pods :as pods])

(pods/load-pod 'io.github.mbjarland/clj-ant "1.0.0-alpha.5")

(require '[clj-ant.pod :as a]
         '[clj-ant.tasks :as t])

(let [dir (doto (java.io.File/createTempFile "clj-ant-pod-example" "")
            .delete
            .mkdirs)
      out (java.io.File. dir "out")]
  (a/execute [(t/mkdir :dir (str out))] :level :warn)
  (prn {:created? (.isDirectory out)
        :copy-todir-type (get-in (a/describe :copy) [:attrs "todir" :type])}))
