![fescue_logo_mini](https://user-images.githubusercontent.com/20200292/35912038-d45a7a86-0c3e-11e8-8b4c-d660d0d6db48.png)



|Main|Develop|
|:--:|:--:|
|[![Build Status](https://travis-ci.org/Morichan/fescue.svg?branch=master)](https://travis-ci.org/Morichan/fescue)|[![Build Status](https://travis-ci.org/Morichan/fescue.svg?branch=develop)](https://travis-ci.org/Morichan/fescue)|
|[![codecov](https://codecov.io/gh/Morichan/fescue/branch/master/graph/badge.svg)](https://codecov.io/gh/Morichan/fescue)|[![codecov](https://codecov.io/gh/Morichan/fescue/branch/develop/graph/badge.svg)](https://codecov.io/gh/Morichan/fescue)|
|![GitHub last commit (master)](https://img.shields.io/github/last-commit/Morichan/fescue/master.svg)|![GitHub last commit (develop)](https://img.shields.io/github/last-commit/Morichan/fescue/develop.svg)|

[![license](https://img.shields.io/github/license/Morichan/fescue.svg)](LICENSE)

[![Java version](https://img.shields.io/badge/java-9+-4c7e9f.svg)](https://www.java.com/en/)
[![JUnit version](https://img.shields.io/badge/junit-5+-dc524a.svg)](http://junit.org/junit5/)
[![Gradle version](https://img.shields.io/badge/gradle-4.3+-007042.svg)](https://gradle.org/docs/)
[![ANTLR version](https://img.shields.io/badge/antlr-4+-ec312e.svg)](http://junit.org/junit5/)

[![GitHub tag](https://img.shields.io/github/tag/Morichan/fescue.svg)](https://github.com/Morichan/fescue/tags)
[![GitHub release](https://img.shields.io/github/release/Morichan/fescue/all.svg)](https://github.com/Morichan/fescue/releases)
[![GitHub closed pull requests](https://img.shields.io/github/issues-pr-closed-raw/Morichan/fescue.svg)](https://github.com/Morichan/fescue/pulls?q=is%3Apr+is%3Aclosed)



# fescue

[ANTLR v4](https://github.com/antlr/antlr4)を利用した、UMLのクラス図における、属性と操作の文法ファイル、およびそれを元に生成した構文解析機を利用する属性と操作の要素抽出ライブラリです。

現在、文字列として属性と操作の抽出に対応（一部未対応）しています。



# 機能

各ファイルの機能について説明します。

## ClassFeature.g4

`src/main/resources/ClassFeature.g4`に置いてあります。

UMLのクラス図における属性の文章を入力すると、構文木を生成します。
使い方および構文解析機の作り方については、[ANTLR v4](https://github.com/antlr/antlr4)を参考にしてください。

## fescue-0.3.1.jar

上記の構文解析機を利用した、属性の各要素抽出器です。

次のように利用してください。

```java
import usage.AttributeEvaluation;
import usage.OperationEvaluation;

/**
 * クラスの属性または機能マネージャ
 *
 * <pre>
 *     {@code
 *     $ javac -cp fescue-0.3.1.jar FeatureManager.java
 *     $ java -cp ./;fescue-0.3.1.jar;antlr.jar FeatureManager
 *     }
 * </pre>
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
        System.out.println(
                "visibility: " + visibility +
                ", name: " + name +
                ", type: " + propType);
    }

    private static void printOperation() {
        OperationEvaluation evaluation = new OperationEvaluation();
        evaluation.setText("+ operation() : double");
        evaluation.walk();

        String name = evaluation.extractName();
        String visibility = evaluation.extractVisibility();
        String returnType = evaluation.extractReturnType();

        // "visibility: +, name: operation, type: double"
        System.out.println(
                "visibility: " + visibility +
                ", name: " + name +
                ", type: " + returnType);
    }
}
```

テストコードに使用例や対応構文を書いていますので、そちらもご覧ください。



# 文法

それぞれの文法、および各要素について説明します。
元となる文法は「UML2.0仕様書 2.1対応」 (ISBN: 978-4274066634) を参考にしました。
詳細は`/src/main/resources/ClassFeature.g4`をご覧ください。

## 属性

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

## 操作

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
(operation (visibility +)
    (name selectProjectData)
    (parameterList (
        (parameter
            (direction in)
            (parameterName (name projectId))
            (typeExpression (type : (primitiveType char)))
            (multiplicityRange [ (upper *) ])
            (defaultValue = (expression (literal "hogehoge001")))) ,
        (parameter
            (parameterName (name data))
            (typeExpression (type : Data))
            (multiplicityRange [
                (lower (valueSpecification ( (expression (expression lowCount) - (expression (literal (integerLiteral 1)))) )))
                ..
                (upper *) ])
            (defaultValue =
                (expression
                    (expression
                        (expression
                            (expression getDataList)
                            (arguments ( (expressionList (expression dataId) , (expression count)) ))) . get)
                            (arguments ( (expressionList (expression (expression Project) . number)) ))))
            (paramProperties (properties { (propModifier readOnly) }))) ))
    (returnType (type : Data))
    (operProperties { (operProperty query) , (operProperty redefines (operName (name selectProjectDataImpl))) }))
```

![example_operation](https://user-images.githubusercontent.com/20200292/36794366-76f3f2fe-1ce3-11e8-9dd6-9a670f347e7a.png)



# 未対応の内容

構文解析機、またはそれを利用したツールの未対応の内容について説明します。

## 文法ファイルにおける未対応内容

### 多重度の下限および上限の間に記述する範囲演算子（`'..'`）と下限または上限と間のスペース無しによる解析ミス

多重度を記述する際に、`'[0..1]'`のように下限および上限と範囲演算子`'..'`の間にスペースを挿入しない場合、構文解析機が正しい構文木を生成できません。
Jarファイルの方を利用する場合は、文字列置換により間にスペースを挿入する（`'[0..1]' -> '[0 .. 1]'`）ため、問題ありません。

## Usageパッケージにおける文字列抽出としての未対応内容

### 各要素の抽出文字列の粒度

属性と操作のオプションの文字列や、多重度における文字列など、複数の要素を含む可能性がある要素を個別に抽出できません。

### 操作のパラメータの要素の抽出

操作のパラメータの要素の抽出ができません。
これは、操作のパラメータに多重度や既定値など、複数の要素を含んでおり、単純な文字列として抽出するべきではないと判断したためです。

## 共通する未対応内容

### 属性のprop-modifierにおけるOCL文法

OCLの文法を記述していないため、属性のプロパティとしてOCLを記述しても正しい構文木を生成できません。

### 操作のoper-propertyにおけるOCL文法

OCLの文法を記述していないため、操作のプロパティとしてOCLを記述しても正しい構文木を生成できません。

### 操作のparam-propertyにおけるOCL文法

OCLの文法を記述していないため、操作のパラメータのプロパティとしてOCLを記述しても正しい構文木を生成できません。
