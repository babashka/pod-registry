(require '[clojure.edn :as edn]
         '[hiccup2.core :as html])

;; Proporsals:
;; 1. If manifest's description is empty, the script could fetch repo
;; description from GitHub API
;; 2. HTML table or Markdown one (with https://clojuredocs.org/clojure.pprint/cl-format)
;; 3. Add field in manifest for :pod/example link
;; 4. Add field in manifest for :pod/language link

(defn edn-read
  [path]
  (with-open [r (io/reader path)]
    (edn/read (java.io.PushbackReader. r))))

(def recent-manifests
  (->> (java.io.File. "manifests/")
       file-seq
       (filter #(str/ends-with? % "manifest.edn"))
       (map edn-read)
       (sort-by :pod/version)
       (reverse)
       (group-by :pod/name)
       (reduce (fn [agg [_ vs]] (conj agg (first vs))) [])
       ;; TODO: Make a transducer out of that
       ))

(def table
  ;; This can be done using either `hiccup` or `clojure.pprint/cl-format`
  (html/html [:table
              [:tr
               [:th "Pod"]
               [:th "Description"]
               [:th "Latest version"]]
              (for [pod recent-manifests]
                ;; TODO: select keys
                [:tr
                 [:td (:pod/name pod)]
                 [:td (:pod/description pod)]
                 [:td (:pod/version pod)]])]))

(defn insert-table-to-readme
  [table]
  (let [readme "README.md"]))

(comment
  (spit "generated-table.html" table)

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
  )
