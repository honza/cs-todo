(defproject todo "0.1.0-SNAPSHOT"
  :description "Todo app"
  :url "https://github.com/honza/cs-todo"
  :license {:name "BSD"}
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [org.clojure/clojurescript "0.0-2080"]
                 [prismatic/dommy "0.1.1"]]
  :plugins [[lein-cljsbuild "1.0.1-SNAPSHOT"]]
  :cljsbuild {
    :builds [{
      :source-paths ["src-cljs"]
      :compiler {
        :output-to "resources/public/js/main.js"
        :optimizations :advanced
        :pretty-print false}}]})
