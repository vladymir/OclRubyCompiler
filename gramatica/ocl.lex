package compil.inv.parser;

import java_cup.runtime.*;
import java.io.IOException;

import compil.inv.parser.OclSym;
import static compil.inv.parser.OclSym.*;

%%
%class OCL_Lexer
%public
%line
%column
%cup

%{   
    private Symbol symbol(int type) {
        return new Symbol(type, yyline, yycolumn);
    }
    
    private Symbol symbol(int type, Object value) {
        return new Symbol(type, yyline, yycolumn, value);
    }
%}
LineTerminator 	= \r|\n|\r\n
LPAREN			= \(
RPAREN			= \)
LBRACK			= \[
RBRACK			= \]
LCURLY			= \{
RCURLY			= \}
COLON			= :
DCOLON			= ::
COMMA			= ,
EQUAL			= \=
NEQUAL			= <>
LT				= <
GT				= >
LE				= <=
GE				= >=
RARROW			= ->
DOT				= \.
POUND			= #
SEMICOL			= ;
BAR				= \|
PLUS			= \+
MINUS			= \-
MULT			= \*
DIVIDE			= \/
NEQUAL          = <>
RELOP           = {LT}|{GT}|{LE}|{GE}|{NEQUAL}
LETRA 			= [a-zA-Z]
DIGITO 			= [0-9]
INTEIRO			= {DIGITO}+
NUM_OPT			= ({DOT}{DIGITO}+)?((e|E)(PLUS|MINUS)?{DIGITO}+)?
NUMERO          = {INTEIRO}{NUM_OPT}
TYPE            = String|Integer|Boolean|Real
ID		 		= {LETRA}({LETRA}|{NUMERO})*{MULT}?
STRING          = \"({LETRA}|{NUMERO})*\"
WhiteSpace      = {LineTerminator} | [ \t\f]


%%
/* ------------------------Lexical Rules Section---------------------- */
   
"context" { return symbol(OclSym.CONTEXT); }
"inv" { return symbol(OclSym.INV); }
"select" { return symbol(OclSym.SELECT); }
"exists" { return symbol(OclSym.EXISTS); }
"including" { return symbol(OclSym.INCLUDING); }
"excluding" { return symbol(OclSym.EXCLUDING); }
"oclIsNew" { return symbol(OclSym.OCL_IS_NEW); }
"size" { return symbol(OclSym.SIZE); }
"self" { return symbol(OclSym.SELF); }
"true" { return symbol(OclSym.TRUE); }
"false" { return symbol(OclSym.FALSE); }
"set" { return symbol(OclSym.SET); }

"or" { return symbol(OclSym.OR); }
"xor" { return symbol(OclSym.XOR); }
"and" { return symbol(OclSym.AND); }
"not" { return symbol(OclSym.NOT); }
{RELOP}|"=" { return symbol(OclSym.RELOP, yytext()); }
{PLUS} { return symbol(OclSym.PLUS); }
{MULT} { return symbol(OclSym.MULT); }
{DIVIDE} { return symbol(OclSym.DIVIDE); }
{MINUS} { return symbol(OclSym.MINUS); }

"if" { return symbol(OclSym.IF); }
"then" { return symbol(OclSym.THEN); }
"else" { return symbol(OclSym.ELSE); }
"endif" { return symbol(OclSym.ENDIF); }
"implies" { return symbol(OclSym.IMPLIES); }
"set" { return symbol(OclSym.SET); }

{TYPE} { return symbol(OclSym.TYPE, yytext()); }
{LBRACK} { return symbol(OclSym.LBRACK); }
{RBRACK} { return symbol(OclSym.RBRACK); }
{LCURLY} { return symbol(OclSym.LCURLY); }
{RCURLY} { return symbol(OclSym.RCURLY); }
{BAR} { return symbol(OclSym.BAR); }
{RARROW} { return symbol(OclSym.RARROW); }
{DOT} { return symbol(OclSym.DOT); }
{COLON} { return symbol(OclSym.COLON); }
{COMMA} { return symbol(OclSym.COMMA); }
{LPAREN} { return symbol(OclSym.LPAREN); }
{RPAREN} { return symbol(OclSym.RPAREN); }
{DCOLON} { return symbol(OclSym.DCOLON); }
{ID} { return symbol(OclSym.ID, yytext()); }
{STRING} { return symbol(OclSym.STRING, yytext()); }
{NUMERO} { return symbol(OclSym.NUMERO, yytext());}
{WhiteSpace} { }
{LineTerminator} { }

/*
LineTerminator = \r|\n|\r\n

LPAREN			= "("
RPAREN			= \)
LBRACK			= \[
RBRACK			= \]
LCURLY			= \{
RCURLY			= \}
COLON			= :
DCOLON			= ::
COMMA			= ,
EQUAL			= =
NEQUAL			= <>
LT				= <
GT				= >
LE				= <=
GE				= >=
RARROW			= ->
DOTDOT			= ..
DOT				= .
POUND			= #
SEMICOL			= ;
BAR				= \|
ATSIGN			= \@
PLUS			= \+
MINUS			= \-
MULT			= \*
DIVIDE			= \/
DOTDOT          = ..

LETRA 			= [a-zA-Z]
DIGITO 			= [0-9]
NUMERO 			= {DIGITO}+
TYPE            = String|Integer|Boolean|Real
ID		 		= {LETRA}({LETRA}|{NUMERO})*
STRING          = \"[ ~]*\"

WhiteSpace     = {LineTerminator} | [ \t\f]
*

%%

/* ------------------------Lexical Rules Section---------------------- */

/*keywords*/
<YYINITIAL> "context" { System.out.println(yytext());return symbol(OclSym.CONTEXT); }
<YYINITIAL> "inv" { System.out.println(yytext());return symbol(OclSym.INV); }
<YYINITIAL> "select" { return symbol(OclSym.SELECT); }
<YYINITIAL> "exists" { return symbol(OclSym.EXISTS); }
<YYINITIAL> "forAll" { return symbol(OclSym.FOR_ALL); }
<YYINITIAL> "including" { return symbol(OclSym.INCLUDING); }
<YYINITIAL> "excluding" { return symbol(OclSym.EXCLUDING); }
<YYINITIAL> "oclIsNew" { return symbol(OclSym.OCL_IS_NEW); }
<YYINITIAL> "size" { return symbol(OclSym.SIZE); }
<YYINITIAL> "self" { return symbol(OclSym.SELF); }

{LT} { return symbol(OclSym.RELOP, OclSym.LT); }
{LE} { return symbol(OclSym.RELOP, OclSym.LE); }
{GT} { return symbol(OclSym.RELOP, OclSym.GT); }
{GE} { return symbol(OclSym.RELOP, OclSym.GE); }
{EQUAL} { System.out.println(yytext());return symbol(OclSym.RELOP, OclSym.EQUAL); }
{NEQUAL} { return symbol(OclSym.RELOP, OclSym.NEQUAL); }


{WhiteSpace} { System.out.println("espaco em branco");}
{LineTerminator} {}

{PLUS} { return symbol(OclSym.PLUS); }
{MULT} { return symbol(OclSym.MULT); }
{DIVIDE} { return symbol(OclSym.DIVIDE); }
{MINUS} { return symbol(OclSym.MINUS); }

"if" { System.out.println("if"); return symbol(OclSym.IF); }
"then" { return symbol(OclSym.THEN); }
"else" { return symbol(OclSym.ELSE); }
"endif" { return symbol(OclSym.ENDIF); }
"implies" { return symbol(OclSym.IMPLIES); }
"set" { return symbol(OclSym.SET); }
"true" { return symbol(OclSym.TRUE); }
"false" { return symbol(OclSym.FALSE); }

{DOTDOT} { return symbol(OclSym.DOTDOT); }
{COLON} { System.out.println(": PORRA ");return symbol(OclSym.COLON); }
{COMMA} { return symbol(OclSym.COMMA); }
{LPAREN} { return symbol(OclSym.LPAREN); }
{RPAREN} { return symbol(OclSym.RPAREN); }
{DCOLON} { return symbol(OclSym.DCOLON); }
{TYPE} { return symbol(OclSym.TYPE, yytext()); }
{ID} { return symbol(OclSym.ID, yytext()); }
{STRING} { return symbol(OclSym.STRING, yytext()); }
{NUMERO} { /*return*/ symbol(OclSym.NUMERO, new Integer(yytext()));}*/
