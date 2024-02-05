(require '[babashka.pods :as pods]
         '[babashka.fs :as fs])

(pods/load-pod 'org.babashka/tools-deps-native "0.1.4")

(require '[clojure.tools.deps :as td])

(def current-file *file*)


(->> (td/create-basis {:project (str (fs/file (fs/parent *file*) "tools-deps-native.edn"))
                       :extra '{:deps {buddy/buddy-core {:mvn/version "1.10.1"}}}})
     :classpath-roots
     (take 2)
     prn)

;;=> ("src" "/Users/borkdude/.m2/repository/buddy/buddy-core/1.10.1/buddy-core-1.10.1.jar")
