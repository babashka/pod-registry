#!/usr/bin/env bb

(require '[babashka.pods :as pods])

(pods/load-pod 'org.babashka/aws "0.1.0")

(require '[pod.babashka.aws :as aws])

(def s3-client (aws/client {:api :s3}))

(aws/doc s3-client :ListBuckets)
