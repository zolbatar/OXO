(ns OXO.core
  (:gen-class))

(defrecord State [board moves])

(defn create-states []
  (dotimes [x (expt 3 9)]
    (println x)
  ))

(defn -main [& args]
  (create-states))