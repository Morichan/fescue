grammar Classes;

@header {
package parser;
}

property
    :   visibility? divided? name propType? multiplicityRange? defaultValue? propModefiers?
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
    :   COLON
        (   IDENTIFIER
        |   primitiveType
        )
    ;

multiplicityRange
    :   LBRACK (lower rangeSplit)? upper RBRACK
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

rangeSplit
    :   RANGE
    ;

valueSpecification
    :   LPAREN expression* (COMMA expression*)* RPAREN
    ;

defaultValue
    :   ASSIGN expression
    ;

propModefiers
    :   LBRACE propModefier (COMMA propModefier)* RBRACE
    ;

propModefier
    :   READONLY
    |   UNION
    ;

expression
    :   IDENTIFIER
    |   literal
    |   expression bop='.'
        (   IDENTIFIER
        |   primitiveType
        |   explicitGenericInvocationSuffix
        )
    |   expression arguments
    |   NEW creator
    |   bop=('+' | '-') expression
    |   expression bop=('*'|'/'|'%') expression
    |   expression bop=('+'|'-') expression
    |   primitiveType arguments?
    ;

creator
    :   createdName classCreatorRest
    ;

createdName
    :   IDENTIFIER ('.' IDENTIFIER)*
    |   primitiveType
    ;

classCreatorRest
    :   arguments
    ;

superSuffix
    :    arguments
    |    DOT IDENTIFIER arguments?
    ;

explicitGenericInvocationSuffix
    :    IDENTIFIER arguments
    |    primitiveType arguments
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

PUBLIC:             '+';
PRIBATE:            '-';
PROTECTED:          '#';
PACKAGE:            '~';

UNLIMITATION:       '*';
RANGE:              '..';

NEW:                'new';

BOOLEAN:            'bool' | 'boolean';
CHAR:               'c' | 'char' | 'character';
BYTE:               'byte';
SHORT:              's' | 'short';
INT:                'i' | 'int' | 'integer';
LONG:               'l' | 'long';
FLOAT:              'f' | 'float';
DOUBLE:             'lf' | 'double';

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