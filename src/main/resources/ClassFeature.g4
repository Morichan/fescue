grammar ClassFeature;

@header {
package parser;
}

property
    :   visibility? divided? name propType? multiplicityRange? defaultValue? propModifiers?
    ;

operation
    :   visibility? name parameterList returnType? operProperties?
    ;

visibility
    :   PUBLIC
    |   PRIBATE
    |   PROTECTED
    |   PACKAGE
    ;

divided
    :   SLASH
    ;

name
    :   IDENTIFIER
    ;

propType
    :   type
    ;

type
    :   COLON
        (   IDENTIFIER
        |   primitiveType
        )
    ;

multiplicityRange
    :   LBRACK (lower RANGE)? upper RBRACK
    ;

lower
    :   integerLiteral
    |   valueSpecification
    ;

upper
    :   UNLIMITATION
    |   integerLiteral
    |   valueSpecification
    ;

valueSpecification
    :   LPAREN expression* (COMMA expression*)* RPAREN
    ;

defaultValue
    :   ASSIGN expression
    ;

propModifiers
    :   properties
    ;

properties
    :   LBRACE propModifier (COMMA propModifier)* RBRACE
    ;

propModifier
    :   READONLY
    |   UNION
    |   SUBSETS propertyName
    |   REDEFINES propertyName
    |   ORDERED
    |   UNIQUE
    ;

propertyName
    :   name
    |   expression
    ;

parameterList
    :   LPAREN (parameter (COMMA parameter)*)* RPAREN
    ;

parameter
    :   direction? parameterName typeExpression multiplicityRange? defaultValue? paramProperties?
    ;

direction
    :   IN
    |   OUT
    |   INOUT
    |   RETURN
    ;

parameterName
    :   name
    |   expression
    ;

typeExpression
    :   type
    ;

paramProperties
    :   properties
    ;

returnType
    :   type
    |   COLON VOID
    ;

operProperties
    :   LBRACE operProperty (COMMA operProperty)* RBRACE
    ;

operProperty
    :   REDEFINES operName
    |   QUERY
    |   ORDERED
    |   UNIQUE
    ;

operName
    :   name
    |   expression
    ;

expression
    :   LPAREN expression RPAREN
    |   literal
    |   IDENTIFIER
    |   expression bop='.'
        (   IDENTIFIER
        |   explicitGenericInvocationSuffix
        )
    |   expression arguments
    |   NEW creator
    |   bop=('+' | '-') expression
    |   bop=('!' | 'not' | 'NOT') expression
    |   expression bop=('*'|'/'|'%') expression
    |   expression bop=('+'|'-') expression
    |   expression bop=('<=' | '>=' | '>' | '<') expression
    |   expression bop=('==' | '!=') expression
    |   expression bop=('&&' | 'and' | 'AND') expression
    |   expression bop=('||' | 'or' | 'OR') expression
    ;

creator
    :   createdName classCreatorRest
    ;

createdName
    :   IDENTIFIER ('.' IDENTIFIER)*
    ;

classCreatorRest
    :   arguments
    ;

explicitGenericInvocationSuffix
    :    IDENTIFIER arguments
    ;

arguments
    : LPAREN expressionList? RPAREN
    ;

expressionList
    : expression (',' expression)*
    ;

primitiveType
    :   BOOLEAN
    |   CHAR
    |   BYTE
    |   SHORT
    |   INT
    |   LONG
    |   FLOAT
    |   DOUBLE
    ;

literal
    :   integerLiteral
    |   floatLiteral
    |   SQUOT_LITERAL
    |   DQUOT_LITERAL
    |   BOOL_LITERAL
    |   NULL_LITERAL
    ;

integerLiteral
    :   DECIMAL_LITERAL
    |   HEX_LITERAL
    |   OCT_LITERAL
    |   BINARY_LITERAL
    ;

floatLiteral
    :   FLOAT_LITERAL
    |   HEX_FLOAT_LITERAL
    ;

READONLY:           'readOnly';
UNION:              'union';
SUBSETS:            'subsets';
REDEFINES:          'redefines';
ORDERED:            'ordered';
UNIQUE:             'unique';
QUERY:              'query';
IN:                 'in';
OUT:                'out';
INOUT:              'inout';
RETURN:             'return';

PUBLIC:             '+';
PRIBATE:            '-';
PROTECTED:          '#';
PACKAGE:            '~';

UNLIMITATION:       '*';
RANGE:              '..';

NEW:                'new';

BOOLEAN:            'bool' | 'boolean';
CHAR:               'c' | 'char' | 'character';
BYTE:               'i8' | 'int8' | 'int8_t' | 'byte';
SHORT:              'i16' | 'int16' | 'int16_t' | 'short';
INT:                'i32' | 'int32' | 'int32_t' | 'int' | 'integer';
LONG:               'i64' | 'int64' | 'int64_t' | 'long';
FLOAT:              'f32' | 'float';
DOUBLE:             'lf' | 'f64' | 'double';
VOID:               'void';

LPAREN:             '(';
RPAREN:             ')';
LBRACE:             '{';
RBRACE:             '}';
LBRACK:             '[';
RBRACK:             ']';
SQUOT:              '\'';
DQUOT:              '"';
COMMA:              ',';
DOT:                '.';

ASSIGN:             '=';
COLON:              ':';
SLASH:              '/';

DECIMAL_LITERAL:    ('0' | [1-9] (Digits? | '_'+ Digits)) [lL]?;
HEX_LITERAL:        '0' [xX] [0-9a-fA-F] ([0-9a-fA-F_]* [0-9a-fA-F])? [lL]?;
OCT_LITERAL:        '0' ('_'* | 'o'?) [0-7] ([0-7_]* [0-7])? [lL]?;
BINARY_LITERAL:     '0' [bB] [01] ([01_]* [01])? [lL]?;

INTEGER_LITERAL:    Digits;

FLOAT_LITERAL:      (Digits '.' Digits? | '.' Digits) ExponentPart? [fFdD]?
             |      Digits (ExponentPart [fFdD]? | [fFdD])
             ;

HEX_FLOAT_LITERAL:  '0' [xX] (HexDigits '.'? | HexDigits? '.' HexDigits) [pP] [+-]? Digits [fFdD]?;

BOOL_LITERAL:       TRUL_LITERAL
            |       FALSE_LITERAL
            ;

TRUL_LITERAL:       'true' | 'TRUE' | 'True' | '1';
FALSE_LITERAL:      'false' | 'FALSE' | 'False' | '0';

NULL_LITERAL:       NULL | NUL | NIL | NONE | UNDEF;

NULL:               'null' | 'NULL' | 'Null';
NUL:                'nul' | 'NUL' | 'Nul';
NIL:                'nil' | 'NIL' | 'Nil';
NONE:               'none' | 'NONE' | 'None';
UNDEF:              'undef' | 'UNDEF' | 'Undef';

WS:                 [ \t\r\n\u000C]+ -> channel(HIDDEN);

SQUOT_LITERAL:      SQUOT (~['\\\r\n] | EscapeSequence)* SQUOT;
DQUOT_LITERAL:      DQUOT (~["\\\r\n] | EscapeSequence)* DQUOT;

IDENTIFIER:         Letter LetterOrDigit*;

fragment ExponentPart
    :   [eE] [+-]? Digits
    ;

fragment EscapeSequence
    :   '\\' [0abtnvfrs"'\\]
    |   '\\' ([0-3]? [0-7])? [0-7]
    |   '\\' 'u'+ HexDigit HexDigit HexDigit HexDigit
    ;

fragment HexDigits
    :   HexDigit ((HexDigit | '_')* HexDigit)?
    ;

fragment HexDigit
    :   [0-9a-fA-F]
    ;

fragment Digits
    :   [0-9] ([0-9_]* [0-9])?
    ;

fragment LetterOrDigit
    :   Letter
    |   [0-9]
    ;

fragment Letter
    :   [a-zA-Z$_] // these are the "java letters" below 0x7F
    |   ~[\u0000-\u007F\uD800-\uDBFF] // covers all characters above 0x7F which are not a surrogate
    |   [\uD800-\uDBFF] [\uDC00-\uDFFF] // covers UTF-16 surrogate pairs encodings for U+10000 to U+10FFFF
    ;