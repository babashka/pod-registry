(require '[babashka.pods :as pods])

(pods/load-pod 'org.babashka/tools-deps-native "0.0.4")

(require '[clojure.tools.deps.alpha :as tda])

(->> (tda/create-basis {:project "deps.edn"})
     :classpath-roots
     (take 2))

;;= > ("src" "/Users/borkdude/.m2/repository/org/clojure/clojure/1.10.1/clojure-1.10.1.jar")
