;; @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
;; @ Copyright (c) Michael Leahcim                                                      @
;; @ You can find additional information regarding licensing of this work in LICENSE.md @
;; @ You must not remove this notice, or any other, from this software.                 @
;; @ All rights reserved.                                                               @
;; @@@@@@ At 2018-10-18 19:54 <thereisnodotcollective@gmail.com> @@@@@@@@@@@@@@@@@@@@@@@@

(ns
    ^{:doc "Transliteration package. Contains cyrilic->latin and latin->cyrilic 
            transliterations "
      :author "Michael Leahcim"}
    thereisnodot.utils.transliterate
  (:require [thereisnodot.akronim.core :refer [defns]]))

(def ^{:private true} trans
  [["Ya"  "Я"]
   ["Ja"  "Я"]
   ["ya"  "я"]
   ["ja"  "я"]
   ["Je"  "Э"]
   ["je"  "э"]
   ["ä"  "э"]
   ["Ju"  "Ю"]
   ["Yu"  "Ю"]
   ["ju"  "ю"]
   ["yu"  "ю"]
   ["ü"  "ю"]
   ["Ch"  "Ч"]
   ["ch"  "ч"]
   ["Shh"  "Щ"]
   ["W"  "В"]
   ["shh"  "щ"]
   ["w"  "в"]
   ["Sh"  "Ш"]
   ["sh"  "ш"]
   ["Zh"  "Ж"]
   ["zh"  "ж"]
   ["Yo"  "Ё"]
   ["Jo"  "Ё"]
   ["yo"  "ё"]
   ["jo" "ё"]
   ["ö" "ё"]
   ["jo"  "ё"]
   ["ö"  "ё"]
   ["H"  "Х"]
   ["h"  "х"]
   ["X"  "Кс"]
   ["x"  "кс"]
   ["##"  "ъ"]
   ["A"  "А"]
   ["a"  "а"]
   ["B"  "Б"]
   ["b"  "б"]
   ["V"  "В"]
   ["v"  "в"]
   ["G"  "Г"]
   ["g"  "г"]
   ["D"  "Д"]
   ["d"  "д"]
   ["Z"  "З"]
   ["z"  "з"]
   ["I"  "И"]
   ["i"  "и"]
   ["J"  "Й"]
   ["j"  "й"]
   ["K"  "К"]
   ["k"  "к"]
   ["L"  "Л"]
   ["l"  "л"]
   ["M"  "М"]
   ["m"  "м"]
   ["N"  "Н"]
   ["n"  "н"]
   ["O"  "О"]
   ["o"  "о"]
   ["P"  "П"]
   ["p"  "п"]
   ["R"  "Р"]
   ["r"  "р"]
   ["S"  "С"]
   ["s"  "с"]
   ["T"  "Т"]
   ["t"  "т"]
   ["U"  "У"]
   ["u"  "у"]
   ["F"  "Ф"]
   ["f"  "ф"]
   ["C"  "Ц"]
   ["c"  "ц"]
   ["Y"  "Ы"]
   ["y"  "ы"]
   ["'"   "ь"]
   ["'"   "Ь"]
   ["#"  "ъ"]
   ["#"  "Ъ"]
   ["E"  "Е"]
   ["e"  "е"]])

(defn- trans-cyrillic-latin
  [phrase  direction]
  (clojure.string/replace 
   (reduce
    (fn [prev next]
      (condp = direction
        "en-ru"
        (clojure.string/replace prev (first next) (last next))
        "ru-en"         
        (clojure.string/replace prev (last next) (first next))))
    phrase
    trans)
   #"[`'#]+",""))


(defns latin-str->cyrillic
  "Primitive transliteration of latin to cyrillic"
  [(latin-str->cyrillic "Hello world") => "Хелло ворлд"
   (latin-str->cyrillic "Nothing works") => "Нотхинг воркс"
   (latin-str->cyrillic "Foo bar") => "Фоо бар"]
  [phrase-str]
  (trans-cyrillic-latin phrase-str "en-ru"))

(defns cyrillic-str->latin
  "Primitive transliteration of cyrillic to latin."
  [(cyrillic-str->latin "Хелло ворлд" ) => "Hello world"
   (cyrillic-str->latin "Нотхинг воркс") => "Nothing worx"
   (cyrillic-str->latin "Фоо бар" ) => "Foo bar"]
  [phrase-str]
  (trans-cyrillic-latin phrase-str "ru-en"))
