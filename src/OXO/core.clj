(ns OXO.core
  (:require [clojure.contrib.math :as math]
            [clojure.contrib.string :as string]
            [clojure.contrib.pprint :as pprint])
  (:gen-class))

(defrecord State [board moves])

(defn create-states []
  (for [x (range (math/expt 3 9))]
    (string/replace-char \0 \.
          (string/replace-char \1 \O
          (string/replace-char \2 \X (str (pprint/cl-format nil "~3,9,'0r" x)))))))

(defn -main [& args]
  (time
    (let [states (create-states)]
      (println (str (count states) " possible game states")))))
