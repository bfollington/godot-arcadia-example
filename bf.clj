(ns bf
  (:require [arcadia.core :as a]
            [arcadia.linear :refer [v2 v2+]]
            [arcadia.2D :refer [position! position]])
  (:import [Godot GD Mathf]))

(defn sin [t]
  (Mathf/Sin t))

(defn cos [t]
  (Mathf/Cos t))

(def elapsed (atom 0))
(def base-position (atom (v2 0 0)))

(defn do-update [node key dt]
  (swap! elapsed #(+ % dt))
  (let [origin (a/state node :initial-pos)
        t (* @elapsed (a/state node :speed))
        x (* 267 (* (cos (* 2 t)) (sin t)))
        y (* 128 (sin t))
        new-pos (v2+ (v2 x y) origin)]
    (position! node new-pos)))

(defn ready [node key]
  (a/log "Setting initial state...")
  (a/set-state node :initial-pos (position node))
  (a/set-state node :speed (GD/RandRange 0.5 2)))