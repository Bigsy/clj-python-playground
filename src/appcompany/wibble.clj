(ns appcompany.wibble
  (:require [libpython-clj2.require :refer [require-python]]
            [cheshire.core :as json]
            [libpython-clj2.python :as py :refer [py. py.- py.. py* py**]]))

(py/initialize!)



(comment (let [gps (py/import-module "google_play_scraper")
               app-details (py. gps "permissions" "com.bowerswilkins.splice"
                                :lang "en"
                                :country "us")

               _ #p (py/dir  app-details)]
           (-> (str app-details)
               (clojure.string/replace #"'" "\"")
               (json/parse-string true))))

(let [gps (py/import-module "google_play_scraper")
      app-details (py. gps "reviews" "com.bowerswilkins.splice"
                       :lang "en"
                       :count 2
                       :country "us")
      _ #p (py/dir  app-details)]

  ;_ (py/call-attr app-details "print")]
  #p (-> app-details
         (py/get-item 1)))




