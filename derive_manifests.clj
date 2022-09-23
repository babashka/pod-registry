(require '[clojure.edn :as edn]
         '[hiccup2.core :as html])

(def recents
  (->> (java.io.File. "manifests/")
       file-seq
       (filter #(str/ends-with? % "manifest.edn"))
       (map #(str/replace (str %) #"^manifests\/|\/manifest.edn$" ""))
       (map #(str/split % #"\/"))
       (sort-by (fn [[_ _ ver]] ver))
       (group-by (fn [[org lib _]] [org lib]))
       (map #(-> % second last))
       ))

(defn edn-read
  [path]
  (with-open [r (io/reader path)]
    (edn/read (java.io.PushbackReader. r))))

(def read-manifests
  (->> (java.io.File. "manifests/")
       file-seq
       (filter #(str/ends-with? % "manifest.edn"))
       (map edn-read)
       )
  )

(def data {:pod/name 'org.babashka/aws,
           :pod/description "",
           :pod/version "0.0.6",
           :pod/license "",
           :pod/artifacts
           [{:os/name "Linux.*",
             :os/arch "amd64",
             :artifact/url
             "https://github.com/babashka/pod-babashka-aws/releases/download/v0.0.6/pod-babashka-aws-0.0.6-linux-amd64-static.zip",
             :artifact/executable "pod-babashka-aws"}
            {:os/name "Mac.*",
             :os/arch "x86_64",
             :artifact/url
             "https://github.com/babashka/pod-babashka-aws/releases/download/v0.0.6/pod-babashka-aws-0.0.6-macos-amd64.zip",
             :artifact/executable "pod-babashka-aws"}
            {:os/name "Windows.*",
             :os/arch "amd64",
             :artifact/url
             "https://github.com/babashka/pod-babashka-aws/releases/download/v0.0.6/pod-babashka-aws-0.0.6-windows-amd64.zip",
             :artifact/executable "pod-babashka-aws.exe"}]})

(def nested-data [data])

(def table
  (html/html [:table
                    [:tr
                     [:th "Pod"]
                     [:th "Description"]
                     [:th "Version"]]
                    (for [pod nested-data]
                      [:tr
                       [:td (:pod/name pod)]
                       [:td (:pod/description pod)]
                       [:td (:pod/version pod)]])]))

(comment
  (spit "generated-table.html" table)
  )
