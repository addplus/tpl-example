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
     :refer [elem submit image]
     :refer-macros [button form window bind-in!]]
    [hoplon.ui.attrs :refer [+ - * / c r b pt em px d]]
    [hoplon.ui.elems :refer [box doc out mid in elem?]]))

(defc c1 [1 2 3 4])

(defelem h1 [attrs elems]
  (elem :f (em 1.5) :pt (em 1.2) attrs elems))

(defelem p [attrs elems]
  (elem :w (r 1 1) attrs elems))

(defelem pre [attrs elems]
  (p :ff "courier" attrs elems))

(window
  :p (em 1)
  (h1 "First loop missing")
  (p
    (for-tpl [x c1]
      (p (cell= (str x))))
    (for-tpl [x c1]
      (p (cell= (str (* 1000 x))))))

  (h1 "Both loops are present")
  (p
    (elem
      (for-tpl [x c1]
        (p (cell= (str x)))))
    (elem
      (for-tpl [x c1]
        (p (cell= (str (* 1000 x)))))))

  (h1 "when-tpl` works")
  (p
    (when-tpl true
      (p "This appears")))

  (h1 "`loop-tpl` within `when-tpl` throws an error")
  (pre "
  (p
      (when-tpl true
            (for-tpl [x [1 2 3]]
                    (p (cell= (str x \". INvisible\"))))))

                    ")

  (h1 "`loop-tpl` within `when-tpl` works when wrapped within an elem")
  (p
    (when-tpl true
      (elem
        (for-tpl [x [1 2 3]]
          (p (cell= (str x ". visible"))))))))
