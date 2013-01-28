(defproject todo "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.4.0"]
                 [prismatic/dommy "0.0.1"]]
  :plugins [[lein-cljsbuild "0.3.0"]]
  :cljsbuild {
    :builds [{
      ; The path to the top-level ClojureScript source directory:
      :source-paths ["src-cljs"]
      ; The standard ClojureScript compiler options:
      ; (See the ClojureScript compiler documentation for details.)
      :compiler {
        :output-to "resources/public/js/main.js"
        :optimizations :whitespace
        :pretty-print true}}]}
            )
