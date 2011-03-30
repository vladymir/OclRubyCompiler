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
LineTerminator		= \r|\n|\r\n
LPAREN			= \(
RPAREN			= \)
SEMICOL			= ;
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
BAR				= \|
PLUS				= \+
MINUS			= \-
MULT			= \*
DIVIDE			= \/
NEQUAL			= <>

DIGITO			= [0-9]
INTEIRO			= {DIGITO}+
NUM_OPT			= ({DOT}{DIGITO}+)?((e|E)(PLUS|MINUS)?{DIGITO}+)?
NUMERO			= {INTEIRO}{NUM_OPT}

LETRA 			= [a-zA-Z]
ID				= {LETRA}({LETRA}|{NUMERO})*{MULT}?
STRING			= \"({LETRA}|{NUMERO})*\"
WhiteSpace		= {LineTerminator} | [ \t\f]


%%
/* ------------------------Lexical Rules Section---------------------- */
   
"context" { return symbol(OclSym.CONTEXT); }
"inv" { return symbol(OclSym.INV); }

"or" { return symbol(OclSym.OR); }
"xor" { return symbol(OclSym.XOR); }
"and" { return symbol(OclSym.AND); }
"not" { return symbol(OclSym.NOT); }

{LT} { return symbol(OclSym.LT); }
{GT} { return symbol(OclSym.GT); }
{LE} { return symbol(OclSym.LE); }
{GE} { return symbol(OclSym.GE); }
{EQUAL} { return symbol(OclSym.EQUAL); }
{NEQUAL} { return symbol(OclSym.NEQUAL); }

{PLUS} { return symbol(OclSym.PLUS); }
{MINUS} { return symbol(OclSym.MINUS); }
{MULT} { return symbol(OclSym.MULT); }
{DIVIDE} { return symbol(OclSym.DIVIDE); }

{SEMICOL} { return symbol(OclSym.SEMICOL); }
{COLON} { return symbol(OclSym.COLON); }
{DCOLON} { return symbol(OclSym.DCOLON); }
{LPAREN} { return symbol(OclSym.LPAREN); }
{RPAREN} { return symbol(OclSym.RPAREN); }
{COMMA} { return symbol(OclSym.COMMA); }
{BAR} { return symbol(OclSym.BAR); }
{RARROW} { return symbol(OclSym.RARROW); }
{DOT} { return symbol(OclSym.DOT); }

"if" { return symbol(OclSym.IF); }
"then" { return symbol(OclSym.THEN); }
"else" { return symbol(OclSym.ELSE); }
"endif" { return symbol(OclSym.ENDIF); }
"implies" { return symbol(OclSym.IMPLIES); }

{STRING} { return symbol(OclSym.STRING, yytext()); }
{ID} { return symbol(OclSym.ID, yytext()); }
{NUMERO} { return symbol(OclSym.NUMERO, yytext());}

{WhiteSpace} { }
{LineTerminator} { }
