|Main|Develop|
|:--:|:--:|
|[![Build Status](https://travis-ci.org/Morichan/ClassesGrammar.svg?branch=master)](https://travis-ci.org/Morichan/ClassesGrammar)|[![Build Status](https://travis-ci.org/Morichan/ClassesGrammar.svg?branch=develop)](https://travis-ci.org/Morichan/ClassesGrammar)|
|[![codecov](https://codecov.io/gh/Morichan/ClassesGrammar/branch/master/graph/badge.svg)](https://codecov.io/gh/Morichan/ClassesGrammar)|[![codecov](https://codecov.io/gh/Morichan/ClassesGrammar/branch/develop/graph/badge.svg)](https://codecov.io/gh/Morichan/ClassesGrammar)|
|![GitHub last commit (master)](https://img.shields.io/github/last-commit/Morichan/ClassesGrammar/master.svg)|![GitHub last commit (develop)](https://img.shields.io/github/last-commit/Morichan/ClassesGrammar/develop.svg)|

[![Java version](https://img.shields.io/badge/java-9+-4c7e9f.svg)](https://www.java.com/en/)
[![Gradle version](https://img.shields.io/badge/gradle-4.3+-007042.svg)](https://gradle.org/docs/)
[![JUnit version](https://img.shields.io/badge/junit-5+-dc524a.svg)](http://junit.org/junit5/)

[![license](https://img.shields.io/github/license/Morichan/ClassesGrammar.svg)](LICENSE)

[![GitHub tag](https://img.shields.io/github/tag/Morichan/ClassesGrammar.svg)](https://github.com/Morichan/ClassesGrammar/tags)
[![GitHub release](https://img.shields.io/github/release/Morichan/ClassesGrammar/all.svg)](https://github.com/Morichan/ClassesGrammar/releases)
[![GitHub closed pull requests](https://img.shields.io/github/issues-pr-closed-raw/Morichan/ClassesGrammar.svg)](https://github.com/Morichan/ClassesGrammar/pulls?q=is%3Apr+is%3Aclosed)

# 概要

ANTLR4を利用した、UMLのクラス図における、属性と操作の文法ファイル、およびそれを元に生成した構文解析機を利用する要素抽出ライブラリです。

現在、属性と操作の抽出に対応（一部未対応）しています。



# 機能

各ファイルの機能について説明します。

## Classes.g4

`src/main/resources/ClassFeature.g4`に置いてあります。

UMLのクラス図における属性の文章を入力すると、構文木を生成します。
使い方および構文解析機の作り方については、[ANTLR v4](https://github.com/antlr/antlr4)を参考にしてください。

## classes-0.2.0.jar

上記の構文解析機を利用した、属性の各要素抽出器です。

次のように利用してください。

```java:FeatureManager.java
import usage.AttributeEvaluation;
import usage.OperationEvaluation;

/**
 * クラスの属性または機能マネージャ
 *
 * $ javac -cp classes-0.2.0.jar FeatureManager.java
 * $ java -cp ./;classes-0.2.0.jar;antlr.jar FeatureManager
 */
class FeatureManager {

    public static void main(String args[]) {
        printAttribute();
        printOperation();
    }

    private static void printAttribute() {
        AttributeEvaluation evaluation = new AttributeEvaluation();
        evaluation.setText("- attribute : int");
        evaluation.walk();

        String name = evaluation.extractName();
        String visibility = evaluation.extractVisibility();
        String propType = evaluation.extractPropType();

        // "visibility: -, name: attribute, type: int"
        System.out.println("visibility: " + visibility + ", name: " + name + ", type: " + propType);
    }

    private static void printOperation() {
        OperationEvaluation evaluation = new OperationEvaluation();
        evaluation.setText("+ operation() : double");
        evaluation.walk();

        String name = evaluation.extractName();
        String visibility = evaluation.extractVisibility();
        String returnType = evaluation.extractReturnType();

        // "visibility: +, name: operation, type: double"
        System.out.println("visibility: " + visibility + ", name: " + name + ", type: " + returnType);
    }
}
```

テストコードに使用例や対応構文を書いていますので、そちらもご覧ください。



# 文法

それぞれの文法、および各要素について説明します。
なお、元となる文法はUML2.0仕様書（2.1対応版）を参考にしました。
詳細は`/src/main/resources/ClassFeature.g4`をご覧ください。

## 属性

属性の文法は次の通りです。

```EBNF:AttributeGrammar
<property> ::= [<visibility>] ['/'] <name> [':' <prop-type>] ['[' <multiplicity> ']'] ['=' <default>] ['{' <prop-modifier> [',' <prop-modifier>]*'}']
```

* visibility: 可視性（例、`'+', '-'`）
* '/': 派生
* name: 属性名
* prop-type: 型
* multiplicity: 多重度（例、`'*', '0 .. 1'`）
* default: 既定値（例、`'true', '3 + x'`）
* prop-modifier: プロパティ（例、`'readOnly', 'subsets instance'`）

## 操作

操作の文法は次の通りです。

```EBNF:OperationGrammar
<operation> ::= [<visibility>] <name> '(' <parameter-list> ')' [':' <return-type>] ['{' <oper-property> [',' <oper-property>]* '}']

<parameter-list> ::= <parameter> [',' <parameter>]*
<parameter> ::= [<direction>] <parameter-name> ':' <type-expression> ['[' <multiplicity> ']'] ['=' <default'>] ['{' <param-property> [',' <param-property>]* '}']
```

* visibility: 可視性（例、`'+', '-'`）
* name: 操作名
* parameter-list: パラメータリスト
    * parameter: パラメータ
    * direction: 方向（例、`'in', 'out', 'return'`）
    * parameter-name: パラメータ名
    * type-expression: パラメータの型
    * multiplicity: 多重度（属性のmultiplicityと同じ）
    * default: パラメータの既定値
    * param-property: パラメータのプロパティ（表記の定義なし）
* return-type: 戻り値を持っている場合はその型
* oper-property: プロパティ（例、`'query', 'redefines method()'`）



# 未対応の内容

構文解析機、またはそれを利用したツールの未対応の内容について説明します。

## 構文解析機のみの未対応内容

### 多重度の下限および上限の間に記述する範囲演算子（`'..'`）と下限または上限と間のスペース無しによる解析ミス

多重度を記述する際に、`'[0..1]'`のように下限および上限と範囲演算子`'..'`の間にスペースを挿入しない場合、構文解析機が正しい構文木を生成できません。
Jarファイルの方を利用する場合は、文字列置換により間にスペースを挿入する（`'[0..1]' -> '[0 .. 1]'`）ため、問題ありません。

## それ以外の未対応内容

### 属性のprop-modifierにおけるOCL文法

OCLの文法を記述していないため、属性のプロパティとしてOCLを記述しても正しい構文木を生成できません。

### 操作のoper-propertyにおけるOCL文法

OCLの文法を記述していないため、操作のプロパティとしてOCLを記述しても正しい構文木を生成できません。

### 操作のparam-propertyにおけるOCL文法

OCLの文法を記述していないため、操作のパラメータのプロパティとしてOCLを記述しても正しい構文木を生成できません。
