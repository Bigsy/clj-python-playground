# python-interop

A simple example of using libpython-clj with built-in modules and a simple package


### Create a virtualenv folder and install the example Python package (included)


```
 python3 -m venv env 
 source env/bin/activate
 ```

Install the example package:

```
$ pip3 install ./funniest
```

### Validate the package installation

```
$ ./env/bin/python
Python 3.6.9 (default, Nov  7 2019, 10:44:02) 
[GCC 8.3.0] on linux
Type "help", "copyright", "credits" or "license" for more information.
>>> import funniest
>>> funniest.joke()
'Wenn ist das Nunstück git und Slotermeyer? Ja! ... Beiherhund das Oder die Flipperwaldt gersput.'
```

### Interop testing

#### Setup

Adjust the properties of your Python runtime on `src/appcompany/python.clj`, in this case we're loading from a `virtualenv` environment, so the path is relative to the current directory:

```clojure
(initialize-python! "./env/bin/python")
```

#### REPL Testing

Load the file `src/appcompany/funapp.clj` in the REPL and start evaluating the expressions in the `comment` section in the bottom of the file:

```clojure
(comment

  ;; Encode base 64 in Python and decode in java
  (-> "IT WORKS!" encode-python decode-java)

  ;; Evaluating this expression should return a string
  (let [pyjoke (py/from-import funniest joke)]
       (pyjoke))

  ) ;; end comment
```

## How to create another similar package

See https://github.com/clj-python/clj-template#usage

## License

Copyright © 2020 Denis Fuenzalida

This program and the accompanying materials are made available under the
terms of the Eclipse Public License 2.0 which is available at
http://www.eclipse.org/legal/epl-2.0.

This Source Code may also be made available under the following Secondary
Licenses when the conditions for such availability set forth in the Eclipse
Public License, v. 2.0 are satisfied: GNU General Public License as published by
the Free Software Foundation, either version 2 of the License, or (at your
option) any later version, with the GNU Classpath Exception which is available
at https://www.gnu.org/software/classpath/license.html.
