#!/usr/bin/env bb

(require '[babashka.pods :as pods])
(pods/load-pod 'joshrotenberg/treesitter "0.1.0")

(require '[pod.joshrotenberg.treesitter :as ts])

(prn (ts/languages))
;; => [:clojure :go :javascript :python :rust :tsx :typescript]

(def code "struct Point { x: f64, y: f64 }

impl Point {
    fn norm(&self) -> f64 { (self.x * self.x + self.y * self.y).sqrt() }
}

fn origin() -> Point { Point { x: 0.0, y: 0.0 } }
")

;; The definitions in source order, with nested ones pointing at their parent.
(doseq [d (ts/outline :rust code)]
  (prn (select-keys d [:name :kind :start-line :parent])))

;; Captures of a tree-sitter query.
(prn (map :text (ts/query :rust code "(function_item name: (identifier) @fn)")))
;; => ("norm" "origin")

;; tree-sitter's S-expression for the tree.
(println (ts/parse :rust "fn f() {}" {:as :sexp}))

;; The language comes from the file extension.
(prn (ts/language-of "src/app/core.clj"))
;; => :clojure
