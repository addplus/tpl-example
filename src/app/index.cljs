(ns ^{:hoplon/page "index.html"} app.index
  (:require
    [javelin.core
     :refer [cell? input? cell formula lens cell-map
             set-cell! alts! destroy-cell!]
     :refer-macros [cell= defc defc= set-cell!= dosync cell-doseq
                    with-let cell-let]]
    [hoplon.core :as h
     :refer-macros [defelem text with-init! with-page-load
                    with-timeout with-interval
                    when-tpl if-tpl case-tpl cond-tpl for-tpl loop-tpl]]
    [hoplon.ui :as hui
     :refer [elem image]
     :refer-macros [form window bind-in!]]
    [hoplon.ui.attrs :refer [c r d em px]]
    [hoplon.ui.elems :refer [box doc out mid in elem?]]))

(defc c1 [1 2 3 4])

(def black (c 0x000000))
(def white (c 0xFFFFFF))

(defc clicked false)

(defelem container [attrs elems]
  (elem :sh (r 1 1) :sv (r 1 1) attrs elems))

(defelem h1 [attrs elems]
  (elem :f (em 1.5) :pt (em 1.2) attrs elems))

(defelem row [attrs elems]
  (elem :sh (r 1 1) attrs elems))

(defelem pre [attrs elems]
  (row :ff "courier" attrs elems))

(def appbar-height 50)

(defelem appbar
  "White appbar with add+ logo and user circle at the top of the screen."
  [attrs elems]
  (elem :sh (r 1 1) :sv appbar-height :ph 16 :pv 8 :g 8 :fc black :c white :bb 1 :bc black attrs
        (elem :sv appbar-height :sh (r 1 1) :av :mid :ah :end
              (elem "search")
              (elem "account_circle")
              (elem "me"))))

(defelem content [attrs elems]
  (elem :sv 250 :sh (r 1 1) :b 1 :bc black (str "CONTENT" elems)))

(window
  (container
    (appbar)
    (container :sv (- (r 1 1) appbar-height) :p 10 :g 10 :scroll true
               (content 1)
               (content 2))
    (container
      :p (em 1)
      (h1 :click #(reset! clicked true) "Click me please!!!"))
    (row :b (cell= (if clicked 1 0))
         "If you open your developer console you should see an error when you click above")))

#_(for-tpl [x c1]
    (row (cell= (str x))))
#_(for-tpl [x c1]
    (row (cell= (str (* 1000 x)))))

;(h1 "Both loops are present")
;
#_(row
    (elem
      (for-tpl [x c1]
        (row (cell= (str x)))))
    (elem
      (for-tpl [x c1]
        (row (cell= (str (* 1000 x)))))))

#_(h1 "when-tpl` works")
#_(row
    (when-tpl true
      (row "This appears")))

#_(h1 "`loop-tpl` within `when-tpl` throws an error")
#_(pre "
  (p
      (when-tpl true
            (for-tpl [x [1 2 3]]
                    (p (cell= (str x \". INvisible\"))))))

                    ")

#_(h1 "`loop-tpl` within `when-tpl` works when wrapped within an elem")
#_(row
    (when-tpl true
      (elem
        (for-tpl [x [1 2 3]]
          (row (cell= (str x ". visible")))))))
