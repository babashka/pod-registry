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
;; "HTML:" "<div><h1>Using Bootleg From Babashka</h1><p>This is a demo</p></div>"

(def hiccup
  (utils/convert-to html :hiccup))

(prn "Hiccup:" hiccup)
;; "Hiccup:" [:div [:h1 "Using Bootleg From Babashka"] [:p "This is a demo"]]

(def hickory
  (utils/convert-to html :hickory))

(prn "Hickory:" hickory)
