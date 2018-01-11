| |Main|Develop|
|:--|:--:|:--:|
|Build|[![Build Status](https://travis-ci.org/Morichan/ClassesGrammar.svg?branch=master)](https://travis-ci.org/Morichan/ClassesGrammar)|[![Build Status](https://travis-ci.org/Morichan/ClassesGrammar.svg?branch=develop)](https://travis-ci.org/Morichan/ClassesGrammar)|
|Codecov|[![codecov](https://codecov.io/gh/Morichan/ClassesGrammar/branch/master/graph/badge.svg)](https://codecov.io/gh/Morichan/ClassesGrammar)|[![codecov](https://codecov.io/gh/Morichan/ClassesGrammar/branch/develop/graph/badge.svg)](https://codecov.io/gh/Morichan/ClassesGrammar)|

# 概要

ANTLR4を利用した、UMLのクラス図における、属性と操作の文法ファイル、およびそれを元に生成した構文解析機を利用する要素抽出ライブラリです。

現在、属性に対応（一部未対応）しています。



# 機能

各ファイルの機能について説明します。

## Classes.g4

`src/main/resources/Classes.g4`に置いてあります。

UMLのクラス図における属性の文章を入力すると、構文木を生成します。
使い方および構文解析機の作り方については、[ANTLR v4](https://github.com/antlr/antlr4)を参考にしてください。

## classes-0.1.0.jar

上記の構文解析機を利用した、属性の各要素抽出器です。

次のように利用してください。

```java:AttributeManager.java
import usage.AttributeEvaluation;

/**
 * 属性管理
 *
 * $ javac -cp classes.jar AttributeManager.java
 * $ java -cp ./;classes.jar;antlr.jar AttributeManager
 */
 class AttributeManager {

    public static void main(String args[]) {
        AttributeEvaluation evaluation = new AttributeEvaluation();

        evaluation.setAttribute("- attribute : int");
        evaluation.walk();

        String name = evaluation.extractName();
        String visibility = evaluation.extractVisibility();
        String propType = evaluation.extractPropType();

        // "visibility: -, name: attribute, type: int"
        System.out.println("visibility: " + visibility + ", name: " + name + ", type: " + propType);
    }
}
```

テストコードに使用例や対応構文を書いていますので、そちらもご覧ください。



# 文法

それぞれの文法、および各要素について説明します。
なお、元となる文法はUML2.0仕様書（2.1対応版）を参考にしました。

## 属性

属性の文法は次の通りです。

```EBNF:AttributeGrammar
<property> ::= [<visibility>] ['/'] <name> [':' <prop-type>] ['[' <multiplicity> ']'] ['=' <default>] ['{' <prop-modifier> [',' <prop-modifier>]*'}']
```

* visibility: 可視性（例、`'+', '-'`）
* '/': 派生
* name: 名前
* prop-type: 型
* multiplicity: 多重度（例、`'*', '0 .. 1'`）
* default: 既定値（例、`'true', '3 + x'`）
* prop-modifier: 修飾子（例、`'readOnly', 'subsets instance'`）



# 開発環境

* Windows 10 Pro
* IntelliJ 2017.3.2
* Java9
* Gradle 4.4.1



# 未対応の内容

構文解析機、またはそれを利用したツールの未対応の内容について説明します。

## 構文解析機のみの未対応内容

### 多重度の下限および上限の間に記述する範囲演算子（`'..'`）と下限または上限とのスペース無しにおける解析ミス

多重度を記述する際に、`'[0..1]'`のように下限および上限と範囲演算子`'..'`の間にスペースを挿入しない場合、構文解析機が正しい構文木を生成できません。
Jarファイルの方を利用する場合は、文字列置換により間にスペースを挿入するため、問題ありません。

## それ以外の未対応内容

### 属性のprop-modifierにおけるOCL文法

OCLの文法を記述していないため、属性の修飾子としてOCLを記述しても正しい構文木を生成できません。

### 操作

操作の文法を記述していないため、操作の正しい構文木を生成できません。
