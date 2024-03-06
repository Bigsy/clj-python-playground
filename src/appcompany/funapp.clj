(ns appcompany.funapp
  (:require [hashp.core]
            [cheshire.core :refer :all]
          [libpython-clj2.python :as py :refer [py. py.- py.. py* py**]]
            appcompany.python
            [libpython-clj2.require :refer [require-python import-python]]))

(import-python)

(require-python '[builtins :as python])
(require-python '[base64 :as pybase64])
(require-python '[funniest :as pyfunniest])
(require-python '[google_play_scraper :as gps])


(defn encode-python [s]
  (let [bytes (python/bytearray s "ascii")                  ;; bytes = bytearray(s, "ascii")
        encoded (pybase64/b64encode bytes)                  ;; encoded = base64.b64encode(bytes)
        b64enc (py. encoded decode "utf-8")]                ;; b64enc = encoded.decode("utf-8")
    b64enc))

(defn decode-java [s]
  (-> (.decode (java.util.Base64/getDecoder) s)
      (String. "UTF-8")))

(def google-play-scraper (py/import-module "google_play_scraper"))


;; Test the expressions below in the REPL:
(comment

  ;; Encode base 64 in Python and decode in java
  (-> "IT WORKS!" encode-python decode-java)

  ;; See https://python-packaging.readthedocs.io/en/latest/minimal.html
  ;; >>> import funniest
  ;; >>> funniest.joke()

  ;; Evaluating this expression should return a string
  (let [pyjoke (py/from-import funniest joke)]
    (pyjoke))

  (let [testing (py/from-import google_play_scraper app)]
    (testing "com.bowerswilkins.splice"
             :lang "en"
             :country "us"))

  (let [testing (py/from-import google_play_scraper reviews)
        result (testing "com.bowerswilkins.splice"
                        :lang "en"
                        :country "us"
                        :count 5)]
     result
    )


  (google-play-scraper/app
    "com.nianticlabs.pokemongo"
    :lang "en"
    :country "us"))

