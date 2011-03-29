/**
 * Copyright (c) 2001 Alexander V. Konstantinou (akonstan@acm.org)
 *
 * Permission to use, copy, modify, distribute and sell this software
 * and its documentation for any purpose is hereby granted without fee,
 * provided that the above copyright notice appear in all copies and
 * that both that copyright notice and this permission notice appear
 * in supporting documentation.  Alexander V. Konstantinou makes no
 * representations about the suitability of this software for any
 * purpose.  It is provided "as is" without express or implied warranty.
 *
 * ANTLR Object Constraint Language (OCL) grammar.  This grammar complies
 * with the UML 1.4 OCL grammar (version 0.1c)
 *
 * $Id: ocl.g,v 1.1 2001/05/25 21:32:02 akonstan Exp $
 *
 * The latest version can be obtained at:
 *
 *         http://www.cs.columbia.edu/~akonstan/ocl/
 *
 * References:
 *
 *   - http://www.antlr.org/
 *   - http://www.omg.org/uml/
 *   - http://www.klasse.nl/ocl/
 *
 * History:
 *
 * - Version 1.0: original release
 */

class OclParser extends Parser;

options {
  k=2;
  exportVocab=OCL;
}

oclFile
  : ( "package" packageName oclExpressions "endpackage" )+
  ;

packageName
  : pathName
  ;

oclExpressions
  : (constraint)*
  ;

constraint 
  : contextDeclaration 
    (   ("def" (NAME)? COLON (letExpression)*)
        | (stereotype (NAME)? COLON oclExpression)
    )+
  ;

contextDeclaration 
  : "context" (operationContext | classifierContext);

classifierContext
  : (NAME COLON NAME)
  | NAME
  ;

operationContext
  : NAME DCOLON operationName LPAREN formalParameterList RPAREN
    ( COLON returnType )?
    ;

stereotype
    : "pre"
    | "post"
    | "inv"
    ;

operationName
  : NAME 
  | EQUAL
  | PLUS
  | MINUS
  | LT
  | LE
  | GE
  | GT
  | DIVIDE
  | MULT
  | NEQUAL
  | "implies"
  | "not"
  | "or"
  | "xor"
  | "and"
  ;

formalParameterList
  : ( NAME COLON typeSpecifier 
      (COMMA NAME COLON typeSpecifier)*
    )?
  ;

typeSpecifier
  : simpleTypeSpecifier
  | collectionType
  ;

collectionType
  : collectionKind LPAREN simpleTypeSpecifier RPAREN
  ;

oclExpression
  : ( ( letExpression )* "in" )? expression
  ;

returnType
  : typeSpecifier
  ;

expression
  : logicalExpression
  ;

letExpression
  : "let" NAME ( LPAREN formalParameterList RPAREN )?
    ( COLON typeSpecifier )?
    EQUAL expression
  ;

ifExpression
  : "if" expression
    "then" expression
    "else" expression
    "endif"
  ;

logicalExpression
  : relationalExpression
    ( logicalOperator relationalExpression )*
  ;

relationalExpression
  : additiveExpression
    ( relationalOperator additiveExpression )?
  ;

additiveExpression
  : multiplicativeExpression
    ( addOperator multiplicativeExpression )*
  ;

multiplicativeExpression
  : unaryExpression
    ( multiplyOperator unaryExpression )*
  ;

unaryExpression
  : ( unaryOperator postfixExpression )
  | postfixExpression
  ;

postfixExpression
  : primaryExpression ( (DOT | RARROW) propertyCall )*
  ;

primaryExpression
  : literalCollection 
  | (   STRING
      | NUMBER
      | (NAME DCOLON NAME (DCOLON NAME)*)
    ) => literal 
  | propertyCall
  | LPAREN expression RPAREN 
  | ifExpression
  ;

propertyCallParameters
    : ( LPAREN NAME (COMMA NAME)*
        (COLON simpleTypeSpecifier)?
        (SEMICOL NAME COLON typeSpecifier EQUAL expression)?
        BAR ) =>
        LPAREN declarator (actualParameterList)? RPAREN
    |   LPAREN (actualParameterList)? RPAREN
  ;

literal
  : STRING
  | NUMBER
  | enumLiteral
  ;

enumLiteral
  : NAME DCOLON NAME ( DCOLON NAME )*
  ;


simpleTypeSpecifier
  : pathName
  ;

literalCollection
  : collectionKind LCURLY
    (collectionItem (COMMA collectionItem)* )?
    RCURLY
  ;

collectionItem
  : expression (DOTDOT expression)?
  ;

propertyCall
  : pathName
    (timeExpression)?
    (qualifiers)?
    (propertyCallParameters)?
  ;

qualifiers
  : LBRACK actualParameterList RBRACK
  ;

declarator
  : NAME (COMMA NAME)*
    (COLON simpleTypeSpecifier)?
    (SEMICOL NAME COLON typeSpecifier EQUAL expression)?
    BAR
  ;

pathName
  : NAME (DCOLON NAME)*
  ;

timeExpression
  : ATSIGN "pre"
  ;

actualParameterList
  : expression (COMMA expression)*
  ;

logicalOperator
  : "and"
  | "or"
  | "xor"
  | "implies"
  ;

collectionKind
  : "Set"
  | "Bag"
  | "Sequence"
  | "Collection"
  ;

relationalOperator
  : EQUAL
  | GT
  | LT
  | GE
  | LE
  | NEQUAL
  ;

addOperator
  : PLUS
  | MINUS
  ;

multiplyOperator
  : MULT
  | DIVIDE
  ;

unaryOperator
  : MINUS
  | "not"
  ;

//////////////////////////////////////////////////////////////////////////////

class OclLexer extends Lexer;

options {
  exportVocab=OCL;
  testLiterals=false;
  k = 2;
}

WS	
options {
  paraphrase = "white space";
}
	:	(' '
	|	'\t'
	|	'\n'  { newline(); }
	|	'\r')
		{ $setType(Token.SKIP); }
	;

LPAREN			: '(';
RPAREN			: ')';
LBRACK			: '[';
RBRACK			: ']';
LCURLY			: '{';
RCURLY			: '}';
COLON			: ':';
DCOLON			: "::";
COMMA			: ',';
EQUAL			: '=';
NEQUAL			: "<>";
LT				: '<';
GT				: '>';
LE				: "<=";
GE				: ">=";
RARROW			: "->";
DOTDOT			: "..";
DOT				: '.';
POUND			: '#';
SEMICOL			: ';';
BAR				: '|';
ATSIGN			: '@';
PLUS			: '+';
MINUS			: '-';
MULT			: '*';
DIVIDE			: '/';

NAME 
options {
  testLiterals = true;
}
    : ( ('a'..'z') | ('A'..'Z') | ('_') ) 
        ( ('a'..'z') | ('0'..'9') | ('A'..'Z') | ('_') )* 
  ;

NUMBER 
  : ('0'..'9')+
    ( { LA(2) != '.' }? '.' ('0'..'9')+ )?
    ( ('e' | 'E') ('+' | '-')? ('0'..'9')+ )?
  ;

STRING : '\'' ( ( ~ ('\'' | '\\' | '\n' | '\r') )
	| ('\\' ( ( 'n' | 't' | 'b' | 'r' | 'f' | '\\' | '\'' | '\"' )
		| ('0'..'3')
		  (
			options {
			  warnWhenFollowAmbig = false;
			}
		  :	('0'..'7')
			(	
			  options {
				warnWhenFollowAmbig = false;
			  }
			:	'0'..'7'
			)?
		  )?
		|	('4'..'7')
		  (
			options {
			  warnWhenFollowAmbig = false;
			}
		  :	('0'..'9')
		  )? ) ) )* '\''
    ;

SL_COMMENT: "--" (~'\n')* '\n' {$setType(Token.SKIP); newline();}
        ;
