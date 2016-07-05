(ns ^{:hoplon/page "hoplon.html"} app.hoplon
  (:require
    [javelin.core :as j]
    [hoplon.core :as h
     :refer-macros [defelem text with-init! with-page-load
                    with-timeout with-interval
                    when-tpl if-tpl case-tpl cond-tpl for-tpl loop-tpl]]))

(j/defc c1 [1 2 3 4])

(h/html
  (h/head)
  (h/body
    (h/div
      (for-tpl [x c1]
        (h/div x))
      (for-tpl [x c1]
        (h/div (j/cell= (* 1000 x)))))))
