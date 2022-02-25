#!/usr/bin/env bb

(require '[babashka.pods :as pods])

(pods/load-pod 'epiccastle/spire "0.1.0-alpha.17")

(require '[pod.epiccastle.spire.transport :as transport]
         '[pod.epiccastle.spire.module.shell :as shell])

(transport/ssh {:username "remoteuser"
                :hostname "remotehost"}
               (prn (shell/shell {:cmd "whoami; hostname"})))
