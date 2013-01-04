(ns leiningen.copy-deps
    (:require [clojure.java.io :as io]
              [leiningen.core.classpath :as cp]))

(defn copy-deps [project]
  (let [dir (io/file "target" "lib")
        jars (map io/file (filter (partial re-find #"\.jar$") (cp/get-classpath project)))]
    (if-not (.exists dir)
      (.mkdir dir))
    (doseq [jar jars]
      (println "Copying" (.getName jar) "to" (.getPath dir))
      (io/copy jar (io/file dir (.getName jar))))))
