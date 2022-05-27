(require '[babashka.pods :as pods])

(pods/load-pod 'jaydeesimon/jsoup)

(require '[jsoup])

(-> (curl/get "https://clojure.org")
    :body
    (jsoup/select "div.clj-header-message")
    first
    :text)
"Clojure is a robust, practical, and fast programming language with a set of useful features that together form a simple, coherent, and powerful tool."
