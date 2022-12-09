#!/usr/bin/env bb

(require '[babashka.pods :as pods])

(pods/load-pod 'clj-kondo/clj-kondo "2022.12.08")

(require '[pod.borkdude.clj-kondo :as clj-kondo])

(-> (clj-kondo/run! {:lint ["examples"]})
    :summary)
