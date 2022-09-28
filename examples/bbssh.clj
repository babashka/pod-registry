#!/usr/bin/env bb

(require '[babashka.pods :as pods])

(pods/load-pod 'epiccastle/bbssh "0.1.0")

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
    ["content🚀" {:name "string"}]    ;; string data
    [(byte-array [1 2 3 4])
     {:name "byte-array"}]           ;; byte-array data
    [(io/input-stream (byte-array [0xf0 0x9f 0x9a 0x80 0x00]))
     {:name "input-stream"
      :size 5
      }]                             ;; input stream
    ]
   "bbssh-test"                      ;; remote path
   {:session session                 ;; options
    :recurse? true})

  ;; copy to local
  (scp/scp-from "remote-path" "local-path" {:session session}))
