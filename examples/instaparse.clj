(ns instaparse)

(require '[babashka.pods :as pods])

(pods/load-pod 'org.babashka/instaparse "0.0.3")

(require '[pod.babashka.instaparse :as insta])

(def as-and-bs
  (insta/parser
   "S = AB*
    AB = A B
    A = 'a'+
    B = 'b'+"))

#_(prn (as-and-bs "aaaaabbbaaaabb"))

(prn (insta/parse as-and-bs "aaaaabbbaaaabb"))

(def failure (insta/parse as-and-bs "xaaaaabbbaaaabb"))

(prn failure)
(prn :failure (insta/failure? failure))
