(require '[babashka.pods :as pods])

(pods/load-pod 'org.babashka/tools-deps-native "0.1.0")

(require '[clojure.tools.deps :as td])

(->> (td/create-basis {:project "deps.edn"
                       :extra '{:deps {buddy/buddy-core {:mvn/version "1.10.1"}}}})
     :classpath-roots
     (take 2))

;;=> ("src" "/Users/borkdude/.m2/repository/buddy/buddy-core/1.10.1/buddy-core-1.10.1.jar")
