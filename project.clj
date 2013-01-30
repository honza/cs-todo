(defproject todo "0.1.0-SNAPSHOT"
  :description "Todo app"
  :url "https://github.com/honza/cs-todo"
  :license {:name "BSD"}
  :dependencies [[org.clojure/clojure "1.4.0"]
                 [prismatic/dommy "0.0.1"]]
  :plugins [[lein-cljsbuild "0.3.0"]]
  :cljsbuild {
    :builds [{
      :source-paths ["src-cljs"]
      :compiler {
        :output-to "resources/public/js/main.js"
        :optimizations :advanced
        :pretty-print false}}]})
