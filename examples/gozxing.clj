#!/usr/bin/env bb

(require '[babashka.pods :as pods])
(pods/load-pod 'org.babashka/gozxing "0.0.1")

(require '[pod.babashka.gozxing :as qr])

;; encode text into a QR png
(qr/encode "https://babashka.org" "qr.png")

;; decode it back
(println (qr/decode "qr.png")) ;;=> https://babashka.org
