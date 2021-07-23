#!/usr/bin/env bb

(require '[babashka.pods :as pods])

(pods/load-pod 'retrogradeorbit/bootleg "0.1.9")

(require '[pod.retrogradeorbit.bootleg.utils :as utils])

(def html
  (-> [:div
       [:h1 "Using Bootleg From Babashka"]
       [:p "This is a demo"]]
      (utils/convert-to :html)))

(prn "HTML:" html)

(def hiccup
  (utils/convert-to html :hiccup))

(prn "Hiccup:" hiccup)
