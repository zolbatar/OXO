(ns OXO.state
  (:require [clojure.contrib.math :as math]
            [clojure.contrib.string :as string]
            [clojure.contrib.pprint :as pprint]))

(defrecord State [board moves])

(defn rotate [state]
  (str (get state 6) (get state 3) (get state 0)
       (get state 7) (get state 4) (get state 1)
       (get state 8) (get state 5) (get state 2)))

(defn rotate-90 [state]
  (rotate state))

(defn rotate-180 [state]
  (rotate (rotate state)))

(defn rotate-270 [state]
  (rotate (rotate (rotate state))))

(defn is-state-valid? [state]
  (let [countO (count (filter #(= % \O) state))
        countX (count (filter #(= % \X) state))]
    (and

      ; Basics of the game
      (< countO countX)
      (<= (math/abs (- countX countO)) 1)
      (< (+ countX countO) 9))

      ; Matching rotation already?
      (not (= state (rotate-90 state)))
      (not (= state (rotate-180 state)))
      (not (= state (rotate-270 state)))))

(defn create-states []
  (filter #(not (nil? %))
    (for [x (range (math/expt 3 9))]
      (let [state (string/replace-char \0 \.
        (string/replace-char \1 \O
          (string/replace-char \2 \X (str (pprint/cl-format nil "~3,9,'0r" x)))))
            valid (is-state-valid? state)]
        (when valid state)))))


