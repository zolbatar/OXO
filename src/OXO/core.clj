(ns OXO.core
  (:use [OXO.state])
  (:gen-class))

(defn -main [& args]
  (time
    (let [states (create-states)]
      (println (str (count states) " possible game states"))
      (println (first states))
      (println (rotate-90 (first states)))
      (println (rotate-180 (first states)))
      (println (rotate-270 (first states)))

      )))
