(ns OXO.state
  (:require [clojure.contrib.math :as math]
            [clojure.contrib.string :as string]
            [clojure.contrib.pprint :as pprint]))

(defrecord State [replaceby moves])

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
      (< countO countX)
      (<= (math/abs (- countX countO)) 1)
      (< (+ countX countO) 9))))

(defn possible-moves [state])

(defn state-map []
  (into (sorted-map)
    (filter #(not (nil? %))
      (for [x (range (math/expt 3 9))]
        (let [state (string/replace-char \0 \.
          (string/replace-char \1 \O
            (string/replace-char \2 \X (str (pprint/cl-format nil "~3,9,'0r" x)))))]
          (when (is-state-valid? state) {state x}))))))

(def *STATE-MAP* (state-map))

(defn standard-states []
  (for [x *STATE-MAP*]
    (let [state (second x)
          state-90 (rotate-90 state)
          state-180 (rotate-180 state)
          state-270 (rotate-270 state)]
;              (list {:state state :data (State. nil (possible-moves state))}
;                    {:state state-90 :data (State. state (possible-moves state))}
;                    {:state state-180 :data (State. state (possible-moves state-180))}
;                    {:state state-270 :data (State. state (possible-moves state-270))}))))))))
      {:state state :data (State. nil (possible-moves state))})))

;(defn standard-states []
;  (apply sorted-map
;    (flatten
;      (filter #(not (nil? %))
;        (for [x (range (math/expt 3 9))]
;          (let [state (get *STATE-MAP* x)
;                state-90 (rotate-90 state)
;                state-180 (rotate-180 state)
;                state-270 (rotate-270 state)]
;            (when (is-state-valid? state)
;              (list {:state state :data (State. nil (possible-moves state))}
;                    {:state state-90 :data (State. state (possible-moves state))}
;                    {:state state-180 :data (State. state (possible-moves state-180))}
;                    {:state state-270 :data (State. state (possible-moves state-270))}))))))))

;(defn rotated-states []
;  (flatten
;    (filter #(not (nil? %))
;      (for [x (range (math/expt 3 9))]
;        (let [state (get *STATE-MAP* x)
;              state-90 (rotate-90 state)
;              state-180 (rotate-180 state)
;              state-270 (rotate-270 state)]
;          (when (is-state-valid? state)
;            (list state-90 state-180 state-270)))))))

(defn create-states []
  (let [states (standard-states)
        keys (map :state states)
        first-key (first keys)]
    ))
;(println states)))
;(println first-key)
;(doseq [x (filter #(= (:state %) first-key) states)]
;  (println x))))
