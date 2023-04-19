# Pod registry

The central place to register [babashka pods](https://github.com/babashka/pods) for easy usage with babashka.

- [Pod registry](#pod-registry)
  - [Loading and using a pod](#loading-and-using-a-pod)
  - [Registered pods](#registered-pods)
  - [Registering a pod](#registering-a-pod)

## Loading and using a pod

Registered pods can be loaded using a qualified symbol and a version string:

```clojure
(require '[babashka.pods :as pods])
(pods/load-pod 'org.babashka/buddy "0.1.0")
```

From then on, a pod exposes namespaces which can be called like regular Clojure:

```clojure
(require '[pod.babashka.buddy.core.hash :as hash])
(hash/md5 "foo")
```

## Registered pods

| Pod | Description | Latest version | Example | Language |
| --- | --- | --- | --- | --- |
| [atomisthq/tools.docker](https://github.com/atomisthq/pod-atomisthq-tools) | docker golang utils for clojure | 0.1.0 | [link](https://raw.githubusercontent.com/atomisthq/pod-atomisthq-tools.docker/main/dev/user.clj) | [<img src="https://go.dev/blog/go-brand/Go-Logo/SVG/Go-Logo_Blue.svg" alt="golang" width="24" height="24">](https://golang.org/) |
| [borkdude/clj-kondo](https://github.com/clj-kondo/clj-kondo) | A static analyzer and linter for Clojure code that sparks joy | 2023.04.14 | [link](examples/clj-kondo.clj) | [<img src="https://upload.wikimedia.org/wikipedia/commons/5/5d/Clojure_logo.svg" alt="clojure" width="24" height="24">](https://clojure.org/) |
| [com.fluree/crypto](https://github.com/fluree/pod-fluree-crypto) | A Clojure(Script) crypto library for the Fluree graph database | 0.1.2 | [link](examples/fluree-crypto.clj) | [<img src="https://upload.wikimedia.org/wikipedia/commons/5/5d/Clojure_logo.svg" alt="clojure" width="24" height="24">](https://clojure.org/) |
| [com.github.clojure-lsp/clojure-lsp](https://github.com/clojure-lsp/clojure-lsp) | Language Server (LSP) for Clojure | 2022.11.03-00.14.57 | [link](examples/clojure-lsp.clj) | [<img src="https://upload.wikimedia.org/wikipedia/commons/5/5d/Clojure_logo.svg" alt="clojure" width="24" height="24">](https://clojure.org/) |
| [epiccastle/bbssh](https://github.com/epiccastle/bbssh) | SSH remote execution and file copy | 0.3.0 | [link](examples/bbssh.clj) | [<img src="https://upload.wikimedia.org/wikipedia/commons/5/5d/Clojure_logo.svg" alt="clojure" width="24" height="24">](https://clojure.org/) |
| [epiccastle/spire](https://github.com/epiccastle/spire) | Pragmatic idempotent machine provisioning over SSH (mac and linux only) | 0.1.2 | [link](examples/spire.clj) | [<img src="https://upload.wikimedia.org/wikipedia/commons/5/5d/Clojure_logo.svg" alt="clojure" width="24" height="24">](https://clojure.org/) |
| [huahaiy/datalevin](https://github.com/juji-io/datalevin) | Datalevin Database | 0.8.12 | [link](examples/datalevin.clj) | [<img src="https://upload.wikimedia.org/wikipedia/commons/5/5d/Clojure_logo.svg" alt="clojure" width="24" height="24">](https://clojure.org/) |
| [justone/brisk](https://github.com/justone/brisk) | Freeze and thaw with Nippy at the command line | 0.3.0 | [link](examples/brisk.clj) | [<img src="https://upload.wikimedia.org/wikipedia/commons/5/5d/Clojure_logo.svg" alt="clojure" width="24" height="24">](https://clojure.org/) |
| [justone/tabl](https://github.com/justone/tabl) | Make tables from data in your terminal | 0.3.0 | [link](examples/tabl.clj) | [<img src="https://upload.wikimedia.org/wikipedia/commons/5/5d/Clojure_logo.svg" alt="clojure" width="24" height="24">](https://clojure.org/) |
| [lispyclouds/docker](https://github.com/lispyclouds/pod-lispyclouds-docker) | A babashka pod for interacting with docker via clj-docker-client | 0.1.1 | [link](examples/docker.clj) | [<img src="https://upload.wikimedia.org/wikipedia/commons/5/5d/Clojure_logo.svg" alt="clojure" width="24" height="24">](https://clojure.org/) |
| [org.babashka/aws](https://github.com/babashka/pod-babashka-aws) | AWS client based on [aws-api](https://github.com/cognitect-labs/aws-api) | 0.1.2 | [link](examples/aws.clj) | [<img src="https://upload.wikimedia.org/wikipedia/commons/5/5d/Clojure_logo.svg" alt="clojure" width="24" height="24">](https://clojure.org/) |
| [org.babashka/buddy](https://github.com/babashka/pod-babashka-buddy) | Cryptographic API provided by buddy | 0.3.4 | [link](examples/buddy.clj) | [<img src="https://upload.wikimedia.org/wikipedia/commons/5/5d/Clojure_logo.svg" alt="clojure" width="24" height="24">](https://clojure.org/) |
| [org.babashka/etaoin](https://github.com/babashka/pod-babashka-etaoin) | Etaoin, a pure Clojure webdriver protocol implementation | 0.1.0 | [link](examples/etaoin.clj) | [<img src="https://upload.wikimedia.org/wikipedia/commons/5/5d/Clojure_logo.svg" alt="clojure" width="24" height="24">](https://clojure.org/) |
| [org.babashka/filewatcher](https://github.com/babashka/pod-babashka-filewatcher) | Filewatcher based on Rust notify | 0.0.1 | [link](examples/filewatcher.clj) | [<img src="https://upload.wikimedia.org/wikipedia/commons/d/d5/Rust_programming_language_black_logo.svg" alt="rust" width="24" height="24">](https://www.rust-lang.org/) |
| [org.babashka/fswatcher](https://github.com/babashka/pod-babashka-fswatcher) | Filewatcher based on Go fsnotify | 0.0.4 | [link](examples/fswatcher.clj) | [<img src="https://go.dev/blog/go-brand/Go-Logo/SVG/Go-Logo_Blue.svg" alt="golang" width="24" height="24">](https://golang.org/) |
| [org.babashka/go-sqlite3](https://github.com/babashka/pod-babashka-go-sqlite3) | Interact with sqlite3 | 0.1.2 | [link](examples/go_sqlite3.clj) | [<img src="https://go.dev/blog/go-brand/Go-Logo/SVG/Go-Logo_Blue.svg" alt="golang" width="24" height="24">](https://golang.org/) |
| [org.babashka/hsqldb](https://github.com/babashka/babashka-sql-pods) | HSQLDB access via next.jdbc | 0.1.1 | [link](examples/hsqldb.clj) | [<img src="https://upload.wikimedia.org/wikipedia/commons/5/5d/Clojure_logo.svg" alt="clojure" width="24" height="24">](https://clojure.org/) |
| [org.babashka/instaparse](https://github.com/babashka/pod-babashka-instaparse) | Instaparse pod | 0.0.2 | [link](examples/instaparse.clj) | [<img src="https://upload.wikimedia.org/wikipedia/commons/5/5d/Clojure_logo.svg" alt="clojure" width="24" height="24">](https://clojure.org/) |
| [org.babashka/lanterna](https://github.com/babashka/pod-babashka-lanterna) | A Clojurey wrapper around the Lanterna terminal output library. | 0.0.1 | [link](examples/lanterna.clj) | [<img src="https://upload.wikimedia.org/wikipedia/commons/5/5d/Clojure_logo.svg" alt="clojure" width="24" height="24">](https://clojure.org/) |
| [org.babashka/mssql](https://github.com/babashka/babashka-sql-pods) | MSSQL access via next.jdbc | 0.1.1 | [link](examples/hsqldb.clj) | [<img src="https://upload.wikimedia.org/wikipedia/commons/5/5d/Clojure_logo.svg" alt="clojure" width="24" height="24">](https://clojure.org/) |
| [org.babashka/mysql](https://github.com/babashka/babashka-sql-pods) | MySQL access via next.jdbc | 0.1.1 | [link](examples/mysql.clj) | [<img src="https://upload.wikimedia.org/wikipedia/commons/5/5d/Clojure_logo.svg" alt="clojure" width="24" height="24">](https://clojure.org/) |
| [org.babashka/oracle](https://github.com/babashka/babashka-sql-pods) | Oracle access via next.jdbc | 0.1.0 |  | [<img src="https://upload.wikimedia.org/wikipedia/commons/5/5d/Clojure_logo.svg" alt="clojure" width="24" height="24">](https://clojure.org/) |
| [org.babashka/parcera](https://github.com/babashka/pod-babashka-parcera) | Grammar-based Clojure(script) parser	 | 0.0.1 | [link](examples/parcera.clj) | [<img src="https://upload.wikimedia.org/wikipedia/commons/5/5d/Clojure_logo.svg" alt="clojure" width="24" height="24">](https://clojure.org/) |
| [org.babashka/postgresql](https://github.com/babashka/babashka-sql-pods) | Postgresql access via next.jdbc | 0.1.1 | [link](examples/postgresql.clj) | [<img src="https://upload.wikimedia.org/wikipedia/commons/5/5d/Clojure_logo.svg" alt="clojure" width="24" height="24">](https://clojure.org/) |
| [org.babashka/tools-deps-native](https://github.com/babashka/tools-deps-native) | Tools deps alpha as a pod	 | 0.1.0 | [link](examples/tools-deps-native.clj) | [<img src="https://upload.wikimedia.org/wikipedia/commons/5/5d/Clojure_logo.svg" alt="clojure" width="24" height="24">](https://clojure.org/) |
| [retrogradeorbit/bootleg](https://github.com/retrogradeorbit/bootleg) | Template processing command line tool to help build static websites. Inbuilt support for html, hiccup, hickory, selmer, mustache, markdown, enlive, json, yaml and edn. | 0.1.9 | [link](examples/bootleg.clj) | [<img src="https://upload.wikimedia.org/wikipedia/commons/5/5d/Clojure_logo.svg" alt="clojure" width="24" height="24">](https://clojure.org/) |
| [rorokimdim/stash](https://github.com/rorokimdim/stash) | Encrypted text storage | 0.3.2 | [link](examples/stash.clj) | [<img src="https://upload.wikimedia.org/wikipedia/commons/1/1c/Haskell-Logo.svg" alt="haskell" width="24" height="24">](https://haskell.org/) |
| [tommy-mor/go-valve-query](https://github.com/tommy-mor/go-valve-query) | query/rcon valve source engine servers | 0.1.1 |  | [<img src="https://go.dev/blog/go-brand/Go-Logo/SVG/Go-Logo_Blue.svg" alt="golang" width="24" height="24">](https://golang.org/) |
| [tzzh/aws](https://github.com/tzzh/pod-tzzh-aws) | Interact with AWS | 0.0.3 | [link](examples/tzzh_aws.clj) | [<img src="https://go.dev/blog/go-brand/Go-Logo/SVG/Go-Logo_Blue.svg" alt="golang" width="24" height="24">](https://golang.org/) |
| [tzzh/mail](https://github.com/tzzh/pod-tzzh-mail) | Send emails | 0.0.2 | [link](examples/tzzh_mail.clj) | [<img src="https://go.dev/blog/go-brand/Go-Logo/SVG/Go-Logo_Blue.svg" alt="golang" width="24" height="24">](https://golang.org/) |

## Registering a pod

To register a pod, create a nested directory in `manifests` with the following structure:

```
<org>/<pod-name>/<version>
```

and add a `manifest.edn` file like the following example:

```clojure
{:pod/name tzzh/mail
 :pod/description "Send emails"
 :pod/version "0.0.2"
 :pod/license ""
 :pod/example "https://raw.githubusercontent.com/babashka/pod-registry/master/examples/tzzh_mail.clj"
 :pod/language "go"
 :pod/artifacts
 [{:os/name "Linux.*"
   :os/arch "amd64"
   :artifact/url "https://github.com/tzzh/pod-tzzh-mail/releases/download/v0.0.2/pod-tzzh-mail_0.0.2_Linux_x86_64.zip"
   :artifact/executable "pod-tzzh-mail"}
  {:os/name "Mac.*"
   :os/arch "x86_64"
   :artifact/url "https://github.com/tzzh/pod-tzzh-mail/releases/download/v0.0.2/pod-tzzh-mail_0.0.2_Darwin_x86_64.zip"
   :artifact/executable "pod-tzzh-mail"}
  {:os/name "Windows.*"
   :os/arch "amd64"
   :artifact/url "https://github.com/tzzh/pod-tzzh-mail/releases/download/v0.0.2/pod-tzzh-mail_0.0.2_Windows_x86_64.zip"
   :artifact/executable "pod-tzzh-mail.exe"}]}
```

The required fields are `:pod/name`, `:pod/version` and `:pod/artifacts`. For the sake of programmatical integrity you can populate `:pod/description`, `:pod/example` (with a link) and `:pod/language` fields as well.

You can then load the pod in your babashka script as follows:

```clojure
(require '[babashka.pods :as pods])
(pods/load-pod 'tzzh/mail "0.0.2")

(require '[pod.tzzh.mail :as m])
(m/send-mail ...)
```

The `:pod/artifacts` vector will be matched in order on operating system and
architecture. For the first match, the `:artifact/url`, a zip file, will be
downloaded and extracted. After extraction there should be a file with the same
name as `:artifact/executable` which will be made executable and invoked as the
pod.
