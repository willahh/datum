(defproject datum "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.9.0"]
                 [org.clojure/clojurescript "1.10.339"]
                 [org.clojure/java.jdbc "0.7.8"]
                 [ring "1.7.1"]
                 ;; [ring "1.6.3"]
                 [ring/ring-defaults "0.3.1"]
                 [ring/ring-json "0.4.0"]
                 [mysql/mysql-connector-java "5.1.6"]
                 [io.replikativ/datahike "0.1.2"]
                 ;; [reagent "0.8.1"]
                 [honeysql "0.9.4"]
                 ;; [compojure "1.6.0"]
                 [compojure "1.6.1"]
                 [hiccup "1.0.5"]
                 [prone "1.5.1"]]
  :repl-options {:init-ns datum.core}
  :plugins [[lein-ring "0.7.3"]]
  :ring {:handler datum.route/app
         :init datum.route/init})
