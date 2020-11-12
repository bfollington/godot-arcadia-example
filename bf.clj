(ns bf
  (:require [arcadia.core :as a]
            [arcadia.linear :refer [v2 v2+]]
            [arcadia.2D :refer [position!]])
  (:import [Godot Mathf]))

(defn sin [t]
  (Mathf/Sin t))

(defn cos [t]
  (Mathf/Cos t))

(def elapsed (atom 0))

(defn foo-update [node key dt]
  (a/log @elapsed)
  (swap! elapsed #(+ % dt))
  (let [offset (v2 256 256)
        x (* 32 (sin @elapsed))
        y (* 32 (cos @elapsed))
        new-pos (v2+ (v2 x y) offset)]
    (position! node new-pos)))
