parser grammar WParser;
options {
	tokenVocab = WLexer;
}

// Principais
program: ( statement | functionDeclaration)+;

statement:
	declaration
	| arrayDeclaration
	| assignment
	| objectLiteralAssignment
	| ifStatement
	| whileLoop
	| doWhileLoop
	| jumpStatement
	| inputStatement
	| outputStatement
	| COMMENT
	| primaryExpression;

// Tipos
typeSpecifier: Integer | Decimal | Boolean | String;

// Declaração
declaration:
	(typeSpecifier ID)
	| typeSpecifier ID Assign (
		inputStatement
		| expression
		| declarationConditionalExpression
	);

// Atribuição
assignment:
	ID Assign (
		inputStatement
		| expression
		| assignmentConditionalExpression
	);

// Estruturas condicionais
condition: LeftParen expression RightParen;

ifStatement: If condition contextProgram (elseStatement)?;

elseStatement: Else contextProgram;

contextProgram: LeftBrace (statement)* RightBrace;

returnConditionalExpression:
	expression If expression Else expression;

declarationConditionalExpression:
	expression If expression Else expression;

assignmentConditionalExpression:
	expression If expression Else expression;

// Estruturas de repetição
whileCondition: While condition;

doWhileLoop: Do contextProgram whileCondition;

whileLoop: whileCondition contextProgram;

jumpStatement: Break | Continue | returnStatement;

// Operações
binaryOperator:
	Plus
	| Minus
	| Star
	| Div
	| Mod
	| Is
	| Is Not
	| Greater
	| Less
	| GreaterEqual
	| LessEqual
	| And
	| Or;

unaryOperator: Minus | Not | PlusPlus | MinusMinus;

// Expressões
primaryExpression: unaryOperator expression | functionCall;

expression:
	expression binaryOperator expression
	| unaryOperator expression
	| literal
	| ID
	| LeftParen expression RightParen
	| functionCall
	| propertyAccess
	| arrayAccess;

// Objetos
objectLiteralAssignment: Object ID Assign objectLiteral;

objectLiteral: LeftBrace (pair (Comma pair)*)? RightBrace;

pair: typeSpecifier ID Colon literal;

propertyAccess: ID Dot ID;

// Arrays
arrayDeclaration:
	List Less typeSpecifier Greater ID Assign arrayLiteral;

arrayLiteral:
	LeftBracket (expression (Comma expression)*)? RightBracket;

arrayAccess: ID LeftBracket INT RightBracket;

// Funções
functionDeclaration:
	Function functionTypeSpecifier functionDeclarator contextProgram;

functionTypeSpecifier: (typeSpecifier | Void);

functionDeclarator: ID argumentsDeclarator;

argumentsDeclarator: LeftParen args RightParen;

args: (declaration (Comma declaration)*)?;

returnStatement:
	Return (expression | returnConditionalExpression)?;

functionCall: ID LeftParen arguments RightParen;

arguments: (expression (Comma expression)*)?;

inputStatement: Input LeftParen RightParen;

outputStatement: Output LeftParen (STRING | ID) RightParen;

literal: INT | DECIMAL | STRING | True | False;