![fescue_logo_mini](https://user-images.githubusercontent.com/20200292/35912038-d45a7a86-0c3e-11e8-8b4c-d660d0d6db48.png)



|Main|Develop|
|:--:|:--:|
|[![Build Status](https://travis-ci.org/Morichan/fescue.svg?branch=master)](https://travis-ci.org/Morichan/fescue)|[![Build Status](https://travis-ci.org/Morichan/fescue.svg?branch=develop)](https://travis-ci.org/Morichan/fescue)|
|[![codecov](https://codecov.io/gh/Morichan/fescue/branch/master/graph/badge.svg)](https://codecov.io/gh/Morichan/fescue)|[![codecov](https://codecov.io/gh/Morichan/fescue/branch/develop/graph/badge.svg)](https://codecov.io/gh/Morichan/fescue)|
|![GitHub last commit (master)](https://img.shields.io/github/last-commit/Morichan/fescue/master.svg)|![GitHub last commit (develop)](https://img.shields.io/github/last-commit/Morichan/fescue/develop.svg)|

[![license](https://img.shields.io/github/license/Morichan/fescue.svg)](LICENSE)

[![Java version](https://img.shields.io/badge/java-9+-4c7e9f.svg)](https://www.java.com/en/)
[![JUnit version](https://img.shields.io/badge/junit-5+-dc524a.svg)](http://junit.org/junit5/)
[![Gradle version](https://img.shields.io/badge/gradle-4.4+-007042.svg)](https://gradle.org/docs/)
[![ANTLR version](https://img.shields.io/badge/antlr-4+-ec312e.svg)](http://junit.org/junit5/)

[![GitHub tag](https://img.shields.io/github/tag/Morichan/fescue.svg)](https://github.com/Morichan/fescue/tags)
[![GitHub release](https://img.shields.io/github/release/Morichan/fescue/all.svg)](https://github.com/Morichan/fescue/releases)
[![GitHub closed pull requests](https://img.shields.io/github/issues-pr-closed-raw/Morichan/fescue.svg)](https://github.com/Morichan/fescue/pulls?q=is%3Apr+is%3Aclosed)



# fescue

The grammar file of Attribute and Operation in UML Class Diagram using [ANTLR v4](https://github.com/antlr/antlr4).
And, the Feature Elements Section of Class in UML Extraction library that uses the parser generated based on the grammar file.

[ANTLR v4](https://github.com/antlr/antlr4)を利用した、UMLのクラス図における、属性と操作の文法ファイル、およびそれを元に生成した構文解析機を利用する属性と操作の要素抽出ライブラリです。



# Build Process

Windows and Linux (and other?)

Gradle version >= 4.2.1

```sh
$ git clone https://github.com/Morichan/fescue
$ cd ./fescue
$ gradle build
$ ls ./build/libs/
fescue-2.1.0.jar
```



# How to Use

By Maven

```xml
<dependency>
  <groupId>io.github.morichan</groupId>
  <artifactId>fescue</artifactId>
  <version>2.1.0</version>
</dependency>
```

By Gradle

```gradle
apply plugin: 'java'

repositories {
    mavenCentral()
}

dependencies {
    compile 'io.github.morichan:fescue:2.1.0'
}
```

Explain how to use each file and its function.

各ファイルの使い方と機能について説明します。

## ClassFeature.g4

It is put on `src/main/antlr/ClassFeature.g4` .

When you enter a sentence of the Attribute in UML Class Diagram, it generates a syntax tree.
Please refer to [ANTLR v4](https://github.com/antlr/antlr4) for usage and how to make a syntax analyzer.

UMLのクラス図における属性の文章を入力すると、構文木を生成します。
使い方および構文解析機の作り方については、[ANTLR v4](https://github.com/antlr/antlr4)を参考にしてください。

![Feature's Class Diagram](https://github.com/Morichan/fescue/blob/master/documents/feature_class_diagram.svg)

## fescue-2.1.0.jar

Each element extractor of the Attribute and the Operation using the above syntactic analyzer.

上記の構文解析機を利用した、属性および操作の各要素抽出器です。

```java
import io.github.morichan.fescue.sculptor.OperationSculptor;
import io.github.morichan.fescue.feature.Operation;
import io.github.morichan.fescue.sculptor.AttributeSculptor;
import io.github.morichan.fescue.feature.Attribute;

/**
 * <p> How to use the fescue, which the Class Feature like the Attribute and Operation </p>
 *
 * <p> How to Compile </p>
 *
 * <pre>
 *     {@code
 *     $ javac -encoding utf8 -classpath .;fescue-2.1.0.jar Main.java
 *     $ java -classpath .;fescue-2.1.0.jar;antlr-4.7.1-complete.jar Main
 *     }
 * </pre>
 *
 * <p> Result </p>
 *
 * <pre>
 *     {@code
 *     - number : int
 *     number
 *     int
 *
 *     + setNumber(number : int) : void
 *     setNumber
 *     void
 *     }
 * </pre>
 */
class Main {

    public static void main(String[] args) {
        printAttribute();
        printOperation();
    }

    private static void printAttribute() {
        AttributeSculptor sculptor = new AttributeSculptor();
        Attribute attribute;

        sculptor.parse("- number : int");
        attribute = sculptor.carve();

        System.out.println(attribute); // "- number : int"
        System.out.println(attribute.getName()); // "number"
        System.out.println(attribute.getType()); // "int"

        System.out.println(); // newline char
    }

    private static void printOperation() {
        OperationSculptor sculptor = new OperationSculptor();
        Operation operation;

        sculptor.parse("+ setNumber(number : int) : void");
        operation = sculptor.carve();

        System.out.println(operation); // "+ setNumber(number : int) : void"
        System.out.println(operation.getName()); // "setNumber"
        System.out.println(operation.getReturnType()); // "void"
    }
}
```

各クラスのJavadocやテストコードに使用例や対応構文を書いていますので、そちらもご覧ください。



# Grammar: 文法

それぞれの文法、および各要素について説明します。
元となる文法は「UML2.0仕様書 2.1対応」 (ISBN: 978-4274066634) を参考にしました。
詳細は`/src/main/antlr/ClassFeature.g4`をご覧ください。

## Attribute: 属性

属性の文法は次の通りです。

```EBNF
<property> ::= [<visibility>] ['/'] <name> [':' <prop-type>] ['[' <multiplicity> ']'] ['=' <default>] ['{' <prop-modifier> [',' <prop-modifier>]*'}']
```

### 各要素の説明

* visibility: 可視性（例、`'+', '-'`）
* '/': 派生
* name: 属性名
* prop-type: 型
* multiplicity: 多重度（例、`'*', '0 .. 1'`）
* default: 既定値（例、`'true', '3 + x'`）
* prop-modifier: プロパティ（例、`'readOnly', 'subsets instance'`）

### 入力例と出力結果

```text
- projectId : char [0 .. *] = id + 001 {readOnly, subsets id}
```


```elisp
(property
    (visibility -)
    (name projectId)
    (propType (type : (primitiveType char)))
    (multiplicityRange [ (lower (integerLiteral 0)) .. (upper *) ])
    (defaultValue = (expression (expression id) + (expression (literal (integerLiteral 001)))))
    (propModifiers (properties { (propModifier readOnly) , (propModifier subsets (propertyName (name id))) })))
```

![example_attribute](https://user-images.githubusercontent.com/20200292/35249331-eb8265e2-0014-11e8-84ec-0b27b0325618.PNG)

## Operation: 操作

操作の文法は次の通りです。

```EBNF
<operation> ::= [<visibility>] <name> '(' <parameter-list> ')' [':' <return-type>] ['{' <oper-property> [',' <oper-property>]* '}']

<parameter-list> ::= <parameter> [',' <parameter>]*
<parameter> ::= [<direction>] <parameter-name> ':' <type-expression> ['[' <multiplicity> ']'] ['=' <default'>] ['{' <param-property> [',' <param-property>]* '}']
```

### 各要素の説明

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

### 入力例と出力結果

```text
+ selectProjectData(in projectId : char [*] = "hogehoge001", data : Data [(lowCount - 1)..*] = getDataList(dataId, count).get(Project.number) {readOnly}) : Data {query, redefines selectProjectDataImpl}
```

```elisp
(operation
    (visibility +)
    (name selectProjectData)
    (parameterList
        (
            (parameter
                (direction in)
                (parameterName (name projectId))
                (typeExpression (type : (primitiveType char)))
                (multiplicityRange [ (upper *) ])
                (defaultValue = (expression (literal "hogehoge001")))) ,
            (parameter
                (parameterName (name data))
                (typeExpression (type : Data))
                (multiplicityRange
                    [
                        (lower (valueSpecification ( (expression (expression lowCount) - (expression (literal (integerLiteral 1)))) )))
                        ..
                        (upper *)
                    ])
                (defaultValue =
                    (expression
                        (expression
                            (expression
                                (expression getDataList)
                                (arguments ( (expressionList (expression dataId) , (expression count)) )))
                            .
                            get)
                        (arguments ( (expressionList (expression (expression Project) . number)) ))))
                (paramProperties (properties { (propModifier readOnly) })))
        ))
    (returnType (type : Data))
    (operProperties { (operProperty query) , (operProperty redefines (operName (name selectProjectDataImpl))) }))
```

![example_operation](https://user-images.githubusercontent.com/20200292/36794366-76f3f2fe-1ce3-11e8-9dd6-9a670f347e7a.png)



# Unsupported Contents: 未対応内容

構文解析機、またはそれを利用したツールの未対応内容について説明します。

## 文法ファイルにおける未対応内容

### 多重度の下限および上限の間に記述する範囲演算子（`'..'`）と下限または上限と間のスペース無しによる解析ミス

多重度を記述する際に、`'[0..1]'`のように下限および上限と範囲演算子`'..'`の間にスペースを挿入しない場合、構文解析機が正しい構文木を生成できません。
Jarファイルの方を利用する場合は、文字列置換により間にスペースを挿入する（`'[0..1]' -> '[0 .. 1]'`）ため、問題ありません。
また、文法ファイルのみを使う場合でも、間にスペースを挿入することで利用できます。

これはANTLR側の問題の可能性もあるため、現在対応は保留です。

## 共通する未対応内容

### UML2.5.1との定義文法の差異

UML2.5.1では、属性および操作の定義文法が異なります。
最新のバージョンでの記述法を用いることができません。
影響範囲などについては検証中です。

### 属性のprop-modifierおよび操作のoper-propertyとparam-propertyにおけるOCL文法

OCLの文法を記述していないため、次の3つにOCLを記述しても正しい構文木を生成できません。

* 属性のプロパティ (prop-modifier)
* 操作のプロパティ (oper-property)
* 操作のパラメータのプロパティ (param-property)

ただし、文字列をそのまま出力することは可能です。
もしOCL文法の構文解析機があれば、そちらを利用して構文解析することをオススメします。