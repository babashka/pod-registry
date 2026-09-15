(require '[babashka.pods :as pods])

(pods/load-pod 'kpassapk/emacs "0.5.2")

(require '[pod.kpassapk.emacs :as emacs])

(prn (emacs/eval "(+ 1 1)"))
