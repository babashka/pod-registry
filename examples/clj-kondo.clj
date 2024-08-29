#!/usr/bin/env bb

(require '[babashka.pods :as pods])

(pods/load-pod 'clj-kondo/clj-kondo "2024.08.29")

(require '[pod.borkdude.clj-kondo :as clj-kondo])

(-> (clj-kondo/run! {:lint ["examples"]})
    :summary)
