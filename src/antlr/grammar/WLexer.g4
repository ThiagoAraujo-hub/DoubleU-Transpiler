lexer grammar WLexer;

// Palavras reservadas
Between: 'between';
Boolean: 'boolean';
Break: 'break';
Continue: 'continue';
Decimal: 'decimal';
Do: 'do';
Else: 'else';
False: 'false';
Function: 'function';
If: 'if';
Input: 'input';
Integer: 'integer';
List: 'list';
Object: 'object';
Output: 'output';
Return: 'return';
True: 'true';
String: 'string';
Void: 'void';
While: 'while';

// Operadores palavra-chave
And: 'and';
Is: 'is';
Not: 'not';
Or: 'or';

// Operadores
SingleAnd: '&';
LeftParen: '(';
RightParen: ')';
LeftBracket: '[';
RightBracket: ']';
LeftBrace: '{';
RightBrace: '}';
Plus: '+';
Minus: '-';
Star: '*';
Div: '/';
Mod: '%';
Assign: '=';
Less: '<';
Greater: '>';
LessEqual: '<=';
GreaterEqual: '>=';
PlusPlus: '++';
MinusMinus: '--';
Comma: ',';
Colon: ':';
Dot: '.';

// Expressões regulares
ID: [a-zA-Z_][a-zA-Z0-9_]*;

OBJID: [a-zA-Z_][a-zA-Z0-9_]*;

INT: [0-9]+;

DECIMAL: [0-9]+ '.' [0-9]+;

STRING: '"' (~["])* '"';

COMMENT: '//' ~[\r\n]*;

WS: [ \t\r\n]+ -> skip;