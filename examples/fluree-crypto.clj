#!/usr/bin/env bb

(require '[babashka.pods :as pods])
(pods/load-pod 'com.fluree/crypto "0.1.0")

(require '[pod.fluree.crypto :as crypto])

;; sign a message with a secp256k1 key
(def keys
  (crypto/generate-key-pair))

(def message
  "Babashka rocks!")

(defn signature []
  (let [sig (crypto/sign-message message (:private keys))]
    (println "Signing message:" (pr-str message))
    (println "Signature:" sig)
    sig))

;; verify signature
(defn verify [msg sig]
  (let [pub-key (:public keys)
        valid? (crypto/verify-signature pub-key msg sig)]
    (if valid?
      (println "Signature is valid!")
      (println "Signature is invalid, uh oh!"))))

(defn run! []
  (let [sig (signature)]
    (verify message sig)))

(run!)
