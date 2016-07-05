; To inform IntelliJ explicitely about deftask, set-env!, task-options!
(require '[boot.core :refer :all])

(require 'clojure.xml)

(defn build-edn [x]
  (let [[groupdId] (:groupId x)
        [artifactId] (:artifactId x)
        [version] (:version x)]
    (vector (symbol (str groupdId "/" artifactId))
            (str version))))

(defn convert [result x]
  (let [val (:content x) key (:tag x)] (assoc result key val)))

(defn build-deps [col]
  (map (partial reduce convert {}) col))

(defn get-deps [x]
  (when-let [tag (:tag x)] (if (= tag :dependencies) (:content x))))

(defn maven-import
  "Makes a vector of maps of deps from a local pom.xml file" []
  (when-let [rdr (java.io.File. "pom.xml")]
    (->> rdr
         clojure.xml/parse
         :content
         (mapcat get-deps)
         (map :content)
         build-deps
         (map build-edn)
         vec)))

(set-env!
  :dependencies (maven-import)
  :source-paths #{"src"})

(require
  '[tailrecursion.boot-static :refer [serve]]
  '[adzerk.boot-cljs :refer [cljs]]
  '[adzerk.boot-reload :refer [reload]]
  '[hoplon.boot-hoplon :refer [hoplon]])

(deftask dev
         []
         (comp
           (watch)
           (speak)
           (checkout :dependencies '[[hoplon/ui "0.0.1-SNAPSHOT"]])
           (hoplon)
           (reload)
           (cljs)
           (serve :port 8000)))
