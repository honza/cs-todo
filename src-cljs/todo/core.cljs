(ns todo.core
  (:require [clojure.string :as s]
            [dommy.template :as template]))

(def todos (atom {}))
(def task-id (atom 0))
(defn log [message]
  (.log js/console message))

(declare change-handler)
  
;; Todo manipulation ----------------------------------------------------------

(defn get-id []
  (let [new-id (inc @task-id)]
    (reset! task-id new-id)
    new-id))

(defn add-todo [todo]
  (let [id (get-id)
        todo (merge {:id id} todo)]
    (reset! todos (merge {id todo} @todos))))

(defn remove-todo [& ids]
  (reset! todos (apply dissoc @todos ids)))

;; DOM ------------------------------------------------------------------------

(def nothing-el
  (template/node
    [:span.label.label-success "Nothing to do!"]))

(defn by-id [id]
  (.getElementById js/document id))

(defn empty-el [el]
  (set! (.-innerHTML el) ""))

(defn render [todo]
  (let [box (if (todo :done)
              (template/node [:input {:type "checkbox" :checked "checked"}])
              (template/node [:input {:type "checkbox"}]))]
    (set! (.-onclick box) (fn [t] (change-handler todo)))
    (if (todo :done)
      (template/node
        [:li box [:del (todo :task)]])
      (template/node
        [:li box (todo :task)]))))

(defn redraw []
  (let [items (map render (vals @todos))
        list-el (by-id "list")]
    (empty-el list-el)
    (if (seq items)
      (dorun (map #(.appendChild list-el %) items))
      (.appendChild list-el nothing-el))))

;; Handlers ------------------------------------------------------------------- 

(defn create-handler []
  (let [textarea (by-id "textarea")
        content (.-value textarea)]
    (add-todo {:task content
               :done false})
    (set! (.-value textarea) "")
    (redraw)))

(defn clear-completed-handler []
  (let [todos (vals @todos)
        done-tasks (filter :done todos)]
  (apply remove-todo (map :id done-tasks))
  (redraw)))

(defn change-handler [todo]
  (let [todo {:task (todo :task)
              :done (not (todo :done))
              :id (todo :id)}]
    (reset! todos (merge @todos {(todo :id) todo}))
    (redraw)))

;; Fire up the engines --------------------------------------------------------

(defn init []
  (let [submit (by-id "create")
        clear (by-id "clear")]
    (set! (.-onclick submit) create-handler)
    (set! (.-onclick clear) clear-completed-handler)
    (redraw)))

(set! (.-onload js/window) init)
