(require '[babashka.pods :as pods])

(pods/load-pod 'kpassapk/emacs "0.3.1")

(require '[pod.kpassapk.emacs :as emacs])

(prn (emacs/eval "(+ 1 1)"))
