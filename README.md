# Pod registry

The central place to register [babashka pods](https://github.com/babashka/pods) for easy usage with babashka.

- [Pod registry](#pod-registry)
  - [Registering a pod](#registering-a-pod)
  - [Registered pods](#registered-pods)

## Registering a pod

To register a pod, create a nested directory in `manifests` with the following structure:

```
<org>/<pod-name>/<version>
```

and add a `manifest.edn` file like the following example:

``` clojure
{:pod/name tzzh/mail
 :pod/description ""
 :pod/version "0.0.2"
 :pod/license ""
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

The required fields are `:pod/name`, `:pod/version` and `:pod/artifacts`.

You can then load the pod in your babashka script as follows:

```
(require '[babashka.pods :as pods])
(pods/load-pod 'tzzh/mail "0.0.2")

(require '[pod.tzzh.mail :as m])
(m/send-mail ...)
```


The `:pod/artifacts` vector will be matched in order on operating system and
architecture. For the first match, the `:articact/url`, a zip file, will be
downloaded and extracted. After extraction there should be a file with the same
name as `:artifact/executable` which will be made executable and invoked as the
pod.

## Registered pods

| Pod name      | Description           | Latest version  | Repo    |
| ------------- |-----------------------|-----------------|---------|
| borkdude/clj-kondo | A clojure linter that sparks joy | 2020.12.12 | [link](https://github.com/borkdude/clj-kondo) |
| justone/brisk | Freeze and thaw with Nippy | 0.2.0 | [link](https://github.com/justone/brisk) |
