(require '[babashka.pods :as pods])

(pods/load-pod 'rorokimdim/stash "0.3.2")

(require '[pod.rorokimdim.stash :as stash])

(stash/init {"encryption-key" "foo"
             "stash-path" "foo.stash"
             "create-stash-if-missing" true})

(prn (stash/version))

(stash/set :foo :bar "baz")

(prn (stash/get :foo :bar))
