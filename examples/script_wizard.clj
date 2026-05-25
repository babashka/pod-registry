#!/usr/bin/env bb

(require '[babashka.pods :as pods])

(pods/load-pod 'enigmacurry/script-wizard "0.3.0")

(require '[pod.enigmacurry.script-wizard :as wiz]
         '[clojure.string :as str])

;; --- ask ---

;; Basic ask
(def name (wiz/ask "What is your name?"))

;; Ask with a default value
(def city (wiz/ask "What city do you live in?" :default "New York"))

;; Ask allowing blank input
(def nickname (wiz/ask "What's your nickname? (optional)" :allow-blank true))

;; Ask with autocompletion suggestions
(def lang (wiz/ask "Favorite programming language?"
                   :suggestions ["Clojure" "Ruby" "Bash" "Rust" "Python" "Go" "Haskell" "Elixir"]))

;; --- confirm ---

;; Basic confirm (no default)
(def likes-coffee? (wiz/confirm "Do you like coffee?"))

;; Confirm defaulting to yes
(def wants-updates? (wiz/confirm "Want to receive updates?" :default :yes))

;; Confirm defaulting to no
(def is-robot? (wiz/confirm "Are you a robot?" :default :no))

;; --- choose ---

;; Basic choose
(def color (wiz/choose "Pick a color:" ["red" "green" "blue" "yellow" "purple"]))

;; Choose with a default
(def editor (wiz/choose "Preferred editor:" ["vim" "emacs" "vscode" "helix"] :default "vim"))

;; --- select ---

;; Basic multi-select
(def foods (wiz/select "What foods do you love?" ["pizza" "tacos" "sushi" "ramen" "curry" "pasta" "beef"]))

;; Multi-select with defaults pre-checked
(def hobbies (wiz/select "Pick your hobbies:"
                         ["coding" "reading" "gaming" "hiking" "cooking" "music"]
                         :default ["coding" "reading"]))

;; --- date ---

;; Basic date picker
(def start-date (wiz/date "Pick a start date:"))

;; Date with constraints
(def deadline (wiz/date "Pick a deadline:"
                        :min-date "2026-01-01"
                        :max-date "2026-12-31"
                        :week-start "monday"
                        :help-message "Must be within 2026"))

;; --- summary ---
(println)
(println "=== Results ===")
(println (str "Name:      " name))
(println (str "City:      " city))
(println (str "Nickname:  " (if (str/blank? nickname) "(none)" nickname)))
(println (str "Language:  " lang))
(println (str "Coffee:    " (if likes-coffee? "yes" "no")))
(println (str "Updates:   " (if wants-updates? "yes" "no")))
(println (str "Robot:     " (if is-robot? "yes" "no")))
(println (str "Color:     " color))
(println (str "Editor:    " editor))
(println (str "Foods:     " (str/join ", " foods)))
(println (str "Hobbies:   " (str/join ", " hobbies)))
(println (str "Start:     " start-date))
(println (str "Deadline:  " deadline))

;; --- menu ---

(defn network-menu []
  (wiz/menu "Network"
    [["Show IP address" #(println (:out (babashka.process/shell {:out :string} "hostname" "-I")))]
     ["Show hostname" #(println (:out (babashka.process/shell {:out :string} "hostname")))]
     ["Back" nil]]))

(wiz/menu "Main Menu"
  [["Greet" #(println (str "Hello, " name "!"))]
   ["Write a note" #(let [note (wiz/editor "Write long-form notes using your preferred $EDITOR")]
                      (println)
                      (println "--- Your note ---")
                      (println note))]
   ["Network" network-menu]
   ["Quit" nil]])
