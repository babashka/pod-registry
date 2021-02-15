#!/usr/bin/env bb

(require '[babashka.pods :as pods])

(pods/load-pod 'borkdude/clj-kondo "2021.02.13")

(require '[pod.borkdude.clj-kondo :as clj-kondo])

(-> (clj-kondo/run! {:lint ["examples"]})
    :summary)
