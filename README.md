# utils WIP

A set of useful functions for Clojure programming language
Spreadsheet, Scaling, Pagination, Markdown and others


## Installation 
Put 
`[TODO 0.1.0]` to your `project.clj`

Below is the documentation on what is available within each namespace
Most of it is autogenerated with the help of
[akronim](https://github.com/MichaelLeachim/akronim) library. 

```
This readme file is AUTOGENERATED. Do not edit. Check README_template.md for source
```

## CSS
## Markdown
## Spreadsheets

## Collections
**hashmaps->sparse-table** `(hashmaps->sparse-table datum)
(hashmaps->sparse-table datum null-type)
(hashmaps->sparse-table datum null-type order-by)`
Will turn a list of hashmaps into a sparse table. 
   Useful when exporting data into a spreadsheet. 
   First row is a headers row
Usage:
```clojure
(require '[thereisnodot.utils.collections :refer [hashmaps->sparse-table]])
(hashmaps->sparse-table [{:hello "world", :blab "blip", :blop "12"}
                         {:1 "asd", :2 "zc", :hello "nothing"}])
;; => (list (list "hello" "blab" "blop" "1" "2")
;;          (list "world" "blip" "12" nil nil)
;;          (list "nothing" nil nil "asd" "zc"))

(hashmaps->sparse-table [{:name "hello", :surname "world"}
                         {:name "blab", :surname "blip", :whatever "blop"}]
                        "n/a"
                        [:whatever :name :surname])
;; => (list (list "whatever" "name" "surname")
;;          (list "n/a" "hello" "world")
;;          (list "blop" "blab" "blip"))
```
<hr>

**meta-iterate** `(meta-iterate data-set)`
Provide metadata in the form {:index :last? :first?} on a collection
Usage:
```clojure
(require '[thereisnodot.utils.collections :refer [meta-iterate]])
(for [[item meta-item] (meta-iterate (range 10 14))] [item meta-item])
;; => (list [10 {:index 0, :last? false, :first? true}]
;;          [11 {:index 1, :last? false, :first? false}]
;;          [12 {:index 2, :last? false, :first? false}]
;;          [13 {:index 3, :last? true, :first? false}])
```
<hr>

**map-longest** `(map-longest fn & colls)`
Opposite of map. On multiple collections will iterate until 
   the longest sequence has no more members.
   Will hang when used with lazy collections
Usage:
```clojure
(require '[thereisnodot.utils.collections :refer [map-longest]])
(map-longest list (range 1 6) (range 1 3) (range 10 15))
;; => (list (list 1 1 10)
;;          (list 2 2 11)
;;          (list 3 nil 12)
;;          (list 4 nil 13)
;;          (list 5 nil 14))
```
<hr>

**filter-map-key** `(filter-map-key filter-fn some-map)`
Will filter on key of a hashmap
Usage:
```clojure
(require '[thereisnodot.utils.collections :refer [filter-map-key]])
(filter-map-key (fn* [p1__12080#] (= p1__12080# :1)) {:1 1, :2 2, :3 3})
;; => {:1 1}
```
<hr>

**filter-map-val** `(filter-map-val filter-fn some-map)`
WIll filter on value of a hashmap
Usage:
```clojure
(require '[thereisnodot.utils.collections :refer [filter-map-val]])
(filter-map-val (fn* [p1__12070#] (= p1__12070# 1)) {:1 1, :2 2, :3 3})
;; => {:1 1}
```
<hr>

**jaccard-words** `(jaccard-words some-str some-another-str)`
Calculate Jaccard distance between two strings split by space
Usage:
```clojure
(require '[thereisnodot.utils.collections :refer [jaccard-words]])
(jaccard-words "Hello my friend" "goodbye my friend")
;; => 1/2

(jaccard-words "hello nobody" "buy one")
;; => 0

(jaccard-words "a b c d e n" "a b c d e")
;; => 5/6

(jaccard-words "a n" "a n")
;; => 1
```
<hr>

**map-val** `(map-val f m)`
Will execute function over value of the map
Usage:
```clojure
(require '[thereisnodot.utils.collections :refer [map-val]])
(map-val inc {:1 1, :2 2})
;; => {:1 2, :2 3}
```
<hr>

**pad-numbers** `(pad-numbers n c pad-symbol)`
Pad numbers - takes a number and the length to pad to as arguments
Usage:
```clojure
(require '[thereisnodot.utils.collections :refer [pad-numbers]])
(pad-numbers 10 1234567 0)
;; => "1234567000"
```
<hr>

**jaccard** `(jaccard a b)`
Will calculate Jaccard distance over two sets
Usage:
```clojure
(require '[thereisnodot.utils.collections :refer [jaccard]])
(jaccard #{:b :a} #{:c :d})
;; => 0

(jaccard #{:c :b :a} #{:c :d})
;; => 1/4

(jaccard #{:c :b :a} #{:b :a})
;; => 2/3

(jaccard #{:b :a} #{:b :a})
;; => 1
```
<hr>

**nested-group-by** `(nested-group-by fs coll & [final-fn])`
From: https://stackoverflow.com/a/38842018/3362518
   Like group-by but instead of a single function, this is given a list or vec
   of functions to apply recursively via group-by. An optional `final` argument
   (defaults to identity) may be given to run on the vector result of the final
   group-by. !!careful, deep nesting is not supported
Usage:
```clojure
(require '[thereisnodot.utils.collections :refer [nested-group-by]])
(nested-group-by [first second]
                 [["A" 2011 "Dan"] ["A" 2011 "Jon"] ["A" 2010 "Tim"]
                  ["B" 2009 "Tom"]])
;; => {"A" {2010 [["A" 2010 "Tim"]], 2011 [["A" 2011 "Dan"] ["A" 2011 "Jon"]]},
;;     "B" {2009 [["B" 2009 "Tom"]]}}
```
<hr>

**pad-coll** `(pad-coll n coll pad-with)`
Pad collection <coll> with <pad-with> until <n> is reached
Usage:
```clojure
(require '[thereisnodot.utils.collections :refer [pad-coll]])
(pad-coll 10 [3 4] nil)
;; => [3 4 nil nil nil nil nil nil nil nil]

(pad-coll 10 [3 4] "n/a")
;; => [3 4 "n/a" "n/a" "n/a" "n/a" "n/a" "n/a" "n/a" "n/a"]
```
<hr>

**order-by-collection** `(order-by-collection input order-coll)`
Will order input hashmap by order collection. Will append 
   non appearing at the end
Usage:
```clojure
(require '[thereisnodot.utils.collections :refer [order-by-collection]])
(order-by-collection {:a 1, :b 34, :c 87, :h 47, :d 12} [:h :d])
;; => (list 47 12 1 34 87)
```
<hr>

**invert-map** `(invert-map dataset)`
Turn values into keys and reverse. 
   Basically, an inverted index. See tests for example
Usage:
```clojure
(require '[thereisnodot.utils.collections :refer [invert-map]])
(invert-map {:F [:a :b :n], :Q [:c :d :n]})
;; => {:a [:F], :b [:F], :c [:Q], :d [:Q], :n [:F :Q]}

(invert-map
  {1 ["hello" "world"], 2 ["not" "important"], 3 ["very" "important" "thing"]})
;; => {"hello" [1],
;;     "important" [2 3],
;;     "not" [2],
;;     "thing" [3],
;;     "very" [3],
;;     "world" [1]}
```
<hr>

## Scales
**x-y->tile-lat-lon** `(x-y->tile-lat-lon xtile ytile zoom)`
Will calculate latitude and longitude coordinates of a map tile, 
   when given tile X Y and  a zoom level. 
   You can check example on OpenStreetMap: 
   https://www.openstreetmap.org/#map=18/42.1440/41.6780
Usage:
```clojure
(require '[thereisnodot.utils.scale :refer [x-y->tile-lat-lon]])
(x-y->tile-lat-lon 161421 97171 18)
;; => [42.14405981155153 41.678009033203125]
```
<hr>

**paginate** `(paginate current last-item)`
Will create pagination based on 
   current page and the amount of pages as input
Usage:
```clojure
(require '[thereisnodot.utils.scale :refer [paginate]])
(paginate 1 3)
;; => (list {:page-num 1, :name "1", :cur? true, :first? true, :last? false}
;;          {:page-num 2, :name "2", :cur? false, :first? false, :last? false}
;;          {:page-num 3, :name "3", :cur? false, :first? false, :last? true})

(map :name (paginate 37 40))
;; => (list "1" "..." "35" "36" "37" "38" "39" "40")

(map :name (paginate 12 30))
;; => (list "1" "..." "10" "11" "12" "13" "14" "..." "30")
```
<hr>

**lat-lon->tile-x-y** `(lat-lon->tile-x-y lat-deg lon-deg zoom)`
Will calculate X and Y coordinates of a map tile, 
   when given latitude longitude and a zoom level. 
   Which you can check on OpenStreetMap: 
   https://c.tile.openstreetmap.org/18/161421/97171.png
Usage:
```clojure
(require '[thereisnodot.utils.scale :refer [lat-lon->tile-x-y]])
(lat-lon->tile-x-y 42.1438 41.6781 18)
;; => [161421 97171]
```
<hr>

**log-scale** `(log-scale x1 x2 y1 y2 x)`
Will calculate log scaling (mapping)
   from one metric into another
Usage:
```clojure
(require '[thereisnodot.utils.scale :refer [log-scale]])
(map int (map (partial log-scale 1 5 10 50) (range 1 6)))
;; => (list 10 14 22 33 49)
```
<hr>

**scale** `(scale A B C D X)`
Will calculate linear scaling (mapping) from one metric into 
Usage:
```clojure
(require '[thereisnodot.utils.scale :refer [scale]])
(map (partial scale 1 5 10 50) (range 1 6))
;; => (list 10 20N 30N 40N 50)
```
<hr>

**log-scale-round** `(log-scale-round x1 x2 y1 y2 x)`
Will calculate rounded log scale
Usage:
```clojure
(require '[thereisnodot.utils.scale :refer [log-scale-round]])
(map (partial log-scale-round 1 5 10 50) (range 1 6))
;; => (list 10.0 14.0 22.0 33.0 49.0)
```
<hr>

**golden-ratio** `(golden-ratio size step)`
Will calculate golden ratio
Usage:
```clojure
(require '[thereisnodot.utils.scale :refer [golden-ratio]])
(map (partial golden-ratio 1) (range 1 10))
;; => (list 1 2 4 6 11 17 29 46 75)

(map (partial golden-ratio 5) (range 1 10))
;; => (list 8 13 21 34 55 89 145 234 379)
```
<hr>

**euclidean-distance** `(euclidean-distance vec-a vec-b)`
Will calculate Euclidean distance between two vectors
   of the same size. 
Usage:
```clojure
(require '[thereisnodot.utils.scale :refer [euclidean-distance]])
(euclidean-distance [1 2 3 4] [5 6 7 8])
;; => 8.0
```
<hr>

**font-size-in-a-tag-cloud** `(font-size-in-a-tag-cloud min-font-size max-font-size items-in-the-biggest-tag items)`
Will linearly calculate font size of a tag in a tag cloud. 
   Courtesy of: https://stackoverflow.com/a/3717340/3362518
Usage:
```clojure
(require '[thereisnodot.utils.scale :refer [font-size-in-a-tag-cloud]])
(map (partial font-size-in-a-tag-cloud 10 30 6) (range 1 5))
;; => (list 5N 10N 15N 20N)
```
<hr>

**tag-font-log-normalized** `(tag-font-log-normalized items-in-the-biggest-tag items)`
Will non linearly calculate font size of a tag in a tag cloud
Usage:
```clojure
(require '[thereisnodot.utils.scale :refer [tag-font-log-normalized]])
(map (partial tag-font-log-normalized 10) (range 1 10))
;; => (list 0.33 0.53 0.65 0.73 0.79 0.85 0.89 0.93 0.96)
```
<hr>

**haversine** `(haversine lat-1 lng-1 lat-2 lng-2)
(haversine lat-1 lng-1 lat-2 lng-2 radius)`
Will calculate Haversine distance between two points on a shphere
   Correctness checked at: https://www.vcalc.com/wiki/vCalc/Haversine+-+Distance
Usage:
```clojure
(require '[thereisnodot.utils.scale :refer [haversine]])
(haversine 42.1438 41.6781 42.144 41.678)
;; => 0.0237180780670015
```
<hr>

## Strings
**truncate-words-by-chars** `(truncate-words-by-chars amount input)
(truncate-words-by-chars amount input ending)`
will intelligently truncate (without splitting words, with appending ... in the end, if exists)
Usage:
```clojure
(require '[thereisnodot.utils.strings :refer [truncate-words-by-chars]])
(truncate-words-by-chars 40 "This is a beautiful sunny day")
;; => "This is a beautiful sunny day"

(truncate-words-by-chars 30 "This is a beautiful sunny day")
;; => "This is a beautiful sunny ..."

(truncate-words-by-chars 20 "This is a beautiful sunny day")
;; => "This is a ..."

(truncate-words-by-chars 10 "This is a beautiful sunny day")
;; => "This ..."
```
<hr>

**number->roman** `(number->roman number-int)`
Number to Roman
Usage:
```clojure
(require '[thereisnodot.utils.strings :refer [number->roman]])

```
<hr>

**human-date** `(human-date year month day)`
Will make a human readable date
Usage:
```clojure
(require '[thereisnodot.utils.strings :refer [human-date]])
(human-date 2017 12 12)
;; => "Twelfth of December, 2017"

(human-date 2000 10 10)
;; => "Tenth of October, 2000"

(human-date 2000 1 1)
;; => "First of January, 2000"
```
<hr>

**pluralize->as-ies** `(pluralize->as-ies root-str number-int)`
Pluralize English with -ies suffix
Usage:
```clojure
(require '[thereisnodot.utils.strings :refer [pluralize->as-ies]])
(pluralize->as-ies "strawberr" 1)
;; => "strawberry"

(pluralize->as-ies "strawberr" 2)
;; => "strawberries"
```
<hr>

**number-ordinal->english** `(number-ordinal->english number-int)`
Number ordinal to English
Usage:
```clojure
(require '[thereisnodot.utils.strings :refer [number-ordinal->english]])
(number-ordinal->english 3)
;; => "third"
```
<hr>

**lorem-ipsum** `(lorem-ipsum amount-of-words)
(lorem-ipsum amount-of-words rand-fn)`
Generate lorem ipsum of words of a given size
   Accepts second parameter as a source of randomness. 
   Default rand-int
Usage:
```clojure
(require '[thereisnodot.utils.strings :refer [lorem-ipsum]])
(lorem-ipsum 5 (fn [_] 0))
;; => (list "sed" "sed" "sed" "sed" "sed")
```
<hr>

**remove-new-lines** `(remove-new-lines datum)`
Will remove new lines from text
Usage:
```clojure
(require '[thereisnodot.utils.strings :refer [remove-new-lines]])
(remove-new-lines "Hello\n                      world")
;; => "Hello                       world"
```
<hr>

**pluralize->as-s** `(pluralize->as-s root-str number-int)`
Pluralize English with -s suffix
Usage:
```clojure
(require '[thereisnodot.utils.strings :refer [pluralize->as-s]])
(pluralize->as-s "friend" 1)
;; => "friend"

(pluralize->as-s "friend" 2)
;; => "friends"
```
<hr>

**number->english** `(number->english number-int)`
Number to English
Usage:
```clojure
(require '[thereisnodot.utils.strings :refer [number->english]])
(number->english 3)
;; => "three"
```
<hr>

## Framerate
**format-frames** `(format-frames item)`
null
Usage:
```clojure
(require '[thereisnodot.utils.framerate :refer [format-frames]])

```
<hr>

**number->frame-rate** `(number->frame-rate frames frame-rate)`
Will take the amount of frames and frame rate.
   Will output the amount of 
   :hours :minutes  :seconds and :frames
   Will also output the pretty printed 
   view in the :string
Usage:
```clojure
(require '[thereisnodot.utils.framerate :refer [number->frame-rate]])

```
<hr>

## HTML
**styles-map->string** `(styles-map->string data)`
Will turn a map style of CSS into an inline style of CSS
Usage:
```clojure
(require '[thereisnodot.utils.html :refer [styles-map->string]])
(styles-map->string {:background "green", :color "white", :font-weight "900"})
;; => "background:green; color:white; font-weight:900;"
```
<hr>

**gen-layout-class** `(gen-layout-class pivot a b c)`
Will generate position specific class. 
   For example: For the pivot 3 
   classes are: 
   text-align:left; text-align:center; text-align:right; 
   Useful when implementing something on CSS grid
Usage:
```clojure
(require '[thereisnodot.utils.html :refer [gen-layout-class]])
(take 9 (gen-layout-class 3 "left" "justify" "right"))
;; => (list "left"
;;          "justify" "right"
;;          "left" "justify"
;;          "right" "left"
;;          "justify" "right")
```
<hr>

**catcher** `(catcher err-binding on-error & body)`
evaluates body, on error, creates an exception, that binds to a variable declared in
  [err-binding]. Example 
  (catcher
   [blabus]
   (println (.getMessage blabus))
   (/ 1 0))
Usage:
```clojure
(require '[thereisnodot.utils.html :refer [catcher]])

```
<hr>

**yes** `(yes field & body)`
(yes [] [:hello]) => nil
   (yes "blab" [:hello]) => [:hello]
Usage:
```clojure
(require '[thereisnodot.utils.html :refer [yes]])

```
<hr>

**html->unescaped-html** `(html->unescaped-html some-str)`
null
Usage:
```clojure
(require '[thereisnodot.utils.html :refer [html->unescaped-html]])

```
<hr>

**backlet** `(backlet text class-item)`
Will change last letter in hiccup form
Usage:
```clojure
(require '[thereisnodot.utils.html :refer [backlet]])
(backlet "Hello" "blab")
;; => [:span "Hell" [:span {:class "blab"} "o"]]
```
<hr>

**grid-amount** `(grid-amount size amount)`
Percentage on classical grid. Size is the amount of blocks in a grid
Usage:
```clojure
(require '[thereisnodot.utils.html :refer [grid-amount]])
(map (partial grid-amount 16) (range 16))
;; => (list "0.0%" "6.25%"
;;          "12.5%" "18.75%"
;;          "25.0%" "31.25%"
;;          "37.5%" "43.75%"
;;          "50.0%" "56.25%"
;;          "62.5%" "68.75%"
;;          "75.0%" "81.25%"
;;          "87.5%" "93.75%")
```
<hr>

**style** `(style link)`
Will make style in a Hiccup syntax
Usage:
```clojure
(require '[thereisnodot.utils.html :refer [style]])
(style "some-link.css")
;; => [:link {:href "some-link.css", :rel "stylesheet", :type "text/css"}]
```
<hr>

**parse-sorting-field** `(parse-sorting-field input)`
Will parse sorting field like this: (year | -year) (price | -price) (age | -age) e.t.c.
   (parse-sorting-field "-age") => {:inverse? true, :field :age}
Usage:
```clojure
(require '[thereisnodot.utils.html :refer [parse-sorting-field]])
(parse-sorting-field "-age")
;; => {:field :age, :inverse? true}

(parse-sorting-field "age")
;; => {:field :age, :inverse? false}

(parse-sorting-field "blab")
;; => {:field :blab, :inverse? false}
```
<hr>

**inlet** `(inlet text text-class)`
Will change first letter in hiccup form
Usage:
```clojure
(require '[thereisnodot.utils.html :refer [inlet]])
(inlet "Hello" "blab")
;; => [:span [:span {:class "blab"} "H"] "ello"]
```
<hr>

## Transliterate
**cyrillic-str->latin** `(cyrillic-str->latin phrase-str)`
null
Usage:
```clojure
(require '[thereisnodot.utils.transliterate :refer [cyrillic-str->latin]])

```
<hr>

**latin-str->cyrillic** `(latin-str->cyrillic phrase-str)`
null
Usage:
```clojure
(require '[thereisnodot.utils.transliterate :refer [latin-str->cyrillic]])

```
<hr>

## FS
**temp-file** `(temp-file)
(temp-file prefix extension)`
null
Usage:
```clojure
(require '[thereisnodot.utils.fs :refer [temp-file]])

```
<hr>

**zip-dir** `(zip-dir archive-name directory)`
will zip directory into a folder
Usage:
```clojure
(require '[thereisnodot.utils.fs :refer [zip-dir]])

```
<hr>


## License

Copyright © 2018 Michael Leahcim

Distributed under the Eclipse Public License 
