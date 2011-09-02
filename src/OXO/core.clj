(ns OXO.core
  (:use [OXO.state])
  (:gen-class))

(defn -main [& args]
  (time
    (let [states (create-states)]
      (println (str (count states) " possible game states")))))
