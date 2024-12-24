#!/usr/bin/env bb
(require
 '[cheshire.core :as json :refer [parse-string]]
 '[babashka.pods :as pods])
(pods/load-pod "pod-bogue1979-nats")
(require '[pod.bogue1979.nats :as nats])

;; interact with KV store
(def kvopts
  {:host "localhost"
   :nkey (slurp (str (System/getenv "HOME") "/.config/nats/local.seed")),
   :msg "_"
   :bucket "first-bucket"})

(prn (nats/kvput (merge kvopts {:key "foo" :value "bar"})))
(prn (nats/kvget (merge kvopts {:key "foo"})))

;; watch for kv changes
(nats/kvwatchbucket (fn [msg] (prn msg)) kvopts)
(deref (promise))

;; publish
(def opts
  {:host "localhost"
   :nkey (slurp (str (System/getenv "HOME") "/.config/nats/local.seed")),
   :msg "{\"foo\": \"baz\"}"
   :subject "metrics.test"})

(prn (nats/publish opts))

;; subscribe
(defn prn-message
  [msg]
  (prn (try (parse-string (:data msg) true)
            (catch Exception _ {:error "Error parsing json", :message msg})
            (finally (:data msg)))))

(nats/subscribe (fn [x] (prn-message x)) opts)
(deref (promise))

;; request
(println (try
           (nats/request (merge opts {:timeout_seconds 10}))
           (catch Exception e (str "Error: " (.getMessage e)))))
