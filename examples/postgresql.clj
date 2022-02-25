(require '[babashka.pods :as pods])

(pods/load-pod 'org.babashka/postgresql "0.1.0")

(require '[pod.babashka.postgresql :as pg])

;; start postgres: docker run --rm --name some-postgres -p 5432:5432 -e POSTGRES_PASSWORD=mysecretpassword postgres

(def db {:dbtype "postgresql"
         :user "postgres"
         :database "postgres"
         :password "mysecretpassword"})

(pg/execute! db ["drop table if exists mytable;"])
(pg/execute! db ["create table mytable ( foobar int );"])
(pg/execute! db ["insert into mytable (foobar) values (3);"])
(pg/execute! db ["select * from mytable"])
;;=> [{:mytable/foobar 3}]
