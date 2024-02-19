(require '[babashka.pods :as pods]
         #_'[babashka.fs :as fs])

(pods/load-pod 'org.babashka/tools-deps-native "0.1.6")

(require '[clojure.tools.deps :as td])

(def current-file *file*)


(->> (td/create-basis {:project "x.edn"
                       :extra '{:deps {buddy/buddy-core {:mvn/version "1.10.1"}}}})
     :classpath-roots
     (take 2)
     prn)

;;=> ("src" "/Users/borkdude/.m2/repository/buddy/buddy-core/1.10.1/buddy-core-1.10.1.jar")
