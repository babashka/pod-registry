(require '[babashka.pods :as pods])

(pods/load-pod 'kpassapk/emacs "0.4.0")

(require '[pod.kpassapk.emacs :as emacs])

(prn (emacs/eval "(+ 1 1)"))
