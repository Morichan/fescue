| |Main|Develop|
|:--|:--:|:--:|
|Build|[![Build Status](https://travis-ci.org/Morichan/ClassesGrammar.svg?branch=master)](https://travis-ci.org/Morichan/ClassesGrammar)|[![Build Status](https://travis-ci.org/Morichan/ClassesGrammar.svg?branch=develop)](https://travis-ci.org/Morichan/ClassesGrammar)|
|Codecov|[![codecov](https://codecov.io/gh/Morichan/ClassesGrammar/branch/master/graph/badge.svg)](https://codecov.io/gh/Morichan/ClassesGrammar)|[![codecov](https://codecov.io/gh/Morichan/ClassesGrammar/branch/develop/graph/badge.svg)](https://codecov.io/gh/Morichan/ClassesGrammar)|

# 概要

ANTLR4を利用した、UMLのクラス図における、属性と操作の文法ファイル、およびその構文解析機を利用するサンプルプログラムです。

現在、属性に対応しています。



# 文法

それぞれの文法、および各要素について説明します。
なお、元となる文法はUML2.0仕様書（2.1対応版）を参考にしました。

## 属性

属性の文法は次の通りです。

```EBNF:PropertyByUML
<property> ::= [<visibility>] ['/'] <name> [':' <prop-type>] ['[' <multiplicity> ']'] ['=' <default>] ['{' <prop-modifier> [',' <prop-modifier>]*'}']
```
