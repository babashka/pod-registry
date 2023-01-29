#!/usr/bin/env bb

(require '[babashka.pods :as pods]
         '[clojure.java.io :as io])

(pods/load-pod 'epiccastle/bbssh "0.3.0")

(require '[pod.epiccastle.bbssh.core :as bbssh]
         '[pod.epiccastle.bbssh.scp :as scp])

(let [session (bbssh/ssh "remote-host" {:username "remote-user"})]

  ;; execute
  (-> (bbssh/exec session "echo 'I am running over ssh'" {:out :string})
      deref
      :out
      println)

  ;; copy to remote
  (scp/scp-to

   ;; multiple sources
   [(io/file "single-file")          ;; file
    (io/file "directory")            ;; directory
    ["content🚀" {:filename "string"}]    ;; string data
    [(byte-array [1 2 3 4])
     {:filename "byte-array"}]           ;; byte-array data
    [(io/input-stream (byte-array [0xf0 0x9f 0x9a 0x80 0x00]))
     {:filename "input-stream"
      :size 5
      }]                             ;; input stream
    ]
   "remote-path"                     ;; remote path
   {:session session                 ;; options
    :recurse? true})

  ;; copy to local
  (scp/scp-from "remote-path" "local-path" {:session session}))
