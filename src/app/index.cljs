(ns ^{:hoplon/page "index.html"} app.index
  (:require
    [javelin.core :as j]
    [hoplon.core :as h
     :refer-macros [defelem text with-init! with-page-load
                    with-timeout with-interval
                    when-tpl if-tpl case-tpl cond-tpl for-tpl loop-tpl]]
    [hoplon.ui :as hui
     :refer [elem submit image]
     :refer-macros [button form window bind-in!]]
    [hoplon.ui.attrs :refer [+ - * / c r b pt em px d]]
    [hoplon.ui.elems :refer [box doc out mid in elem?]]))

(j/defc c1 [1 2 3 4])

(hui/window
  (elem
    (for-tpl [x c1]
      (elem :w (r 1 1)
            (j/cell= (str x))))
    (for-tpl [x c1]
      (elem :w (r 1 1)
            (j/cell= (str (* 1000 x)))))))
