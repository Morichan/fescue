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
    :   LBRACK (lower '..')? upper RBRACK
    ;

lower
    :   INTEGER_LITERAL
    |   valueSpecification
    ;

upper
    :   UNLIMITATION
    |   INTEGER_LITERAL
    |   valueSpecification
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
    |   CHAR_LITERAL
    |   STRING_LITERAL
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

ASSIGN:             '=';
COLON:              ':';
SLASH:              '/';

DECIMAL_LITERAL:    ('0' | [1-9] (Digits? | '_'+ Digits)) [lL]?;
HEX_LITERAL:        '0' [xX] [0-9a-fA-F] ([0-9a-fA-F_]* [0-9a-fA-F])? [lL]?;
OCT_LITERAL:        '0' '_'* [0-7] ([0-7_]* [0-7])? [lL]?;
BINARY_LITERAL:     '0' [bB] [01] ([01_]* [01])? [lL]?;

INTEGER_LITERAL:     Digits;

FLOAT_LITERAL:      (Digits '.' Digits? | '.' Digits) ExponentPart? [fFdD]?
             |       Digits (ExponentPart [fFdD]? | [fFdD])
             ;

HEX_FLOAT_LITERAL:  '0' [xX] (HexDigits '.'? | HexDigits? '.' HexDigits) [pP] [+-]? Digits [fFdD]?;

BOOL_LITERAL:       TRUL_LITERAL
            |       FALSE_LITERAL
            ;

TRUL_LITERAL:       [tT] ('rue' | 'RUE');
FALSE_LITERAL:      [fF] ('alse' | 'ALSE');

NULL_LITERAL:       [nN] ('ull' | 'ULL' | 'ul' | 'UL' | 'il' | 'IL');

WS:                 [ \t\r\n\u000C]+ -> channel(HIDDEN);

CHAR_LITERAL:       SQUOT ([^'\\\r\n] | EscapeSequence) SQUOT;
STRING_LITERAL:     DQUOT ([^"\\\r\n] | EscapeSequence)* DQUOT;

IDENTIFIER:         Letter LetterOrDigit*;

fragment ExponentPart
    : [eE] [+-]? Digits
    ;

fragment EscapeSequence
    :    '\\' [btnfr"'\\]
    |    '\\' ([0-3]? [0-7])? [0-7]
    |    '\\' 'u'+ HexDigit HexDigit HexDigit HexDigit
    ;

fragment HexDigits
    : HexDigit ((HexDigit | '_')* HexDigit)?
    ;

fragment HexDigit
    :    [0-9a-fA-F]
    ;

fragment Digits
    : [0-9] ([0-9_]* [0-9])?
    ;

fragment LetterOrDigit
    :    Letter
    |    [0-9]
    ;

fragment Letter
    :    [a-zA-Z$_] // these are the "java letters" below 0x7F
    |    ~[\u0000-\u007F\uD800-\uDBFF] // covers all characters above 0x7F which are not a surrogate
    |    [\uD800-\uDBFF] [\uDC00-\uDFFF] // covers UTF-16 surrogate pairs encodings for U+10000 to U+10FFFF
    ;