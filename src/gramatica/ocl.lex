package compil.inv.parser;

import java_cup.runtime.*;

import compil.inv.parser.OCL_Sym;
import static compil.inv.parser.OCL_Sym.*;

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


%eofval{
  return new java_cup.runtime.Symbol(OCL_Sym.EOF);
%eofval}

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
UNDER			= _

DIGITO			= [0-9]
INTEIRO			= {DIGITO}+
NUM_OPT			= ({DOT}{DIGITO}+)?((e|E)(PLUS|MINUS)?{DIGITO}+)?
NUMERO			= {INTEIRO}{NUM_OPT}

LETRA 			= [a-zA-Z]
ID				= ({LETRA}|{UNDER})({LETRA}|{NUMERO}|{UNDER})*
STRING			= \'(.)*\'

WhiteSpace		= {LineTerminator} | [ \t\f]
COMENTARIO 		= "--" .* {LineTerminator}

%%
/* ------------------------Lexical Rules Section---------------------- */

   
"context" { return symbol(OCL_Sym.CONTEXT); }
"inv" { return symbol(OCL_Sym.INV); }

"or" { return symbol(OCL_Sym.OR); }
"xor" { return symbol(OCL_Sym.XOR); }
"and" { return symbol(OCL_Sym.AND); }
"not" { return symbol(OCL_Sym.NOT); }
"true" { return symbol(OCL_Sym.TRUE); }
"false" { return symbol(OCL_Sym.FALSE); }

{LT} { return symbol(OCL_Sym.LT); }
{GT} { return symbol(OCL_Sym.GT); }
{LE} { return symbol(OCL_Sym.LE); }
{GE} { return symbol(OCL_Sym.GE); }
{EQUAL} { return symbol(OCL_Sym.EQUAL); }
{NEQUAL} { return symbol(OCL_Sym.NEQUAL); }

{PLUS} { return symbol(OCL_Sym.PLUS); }
{MINUS} { return symbol(OCL_Sym.MINUS); }
{MULT} { return symbol(OCL_Sym.MULT); }
{DIVIDE} { return symbol(OCL_Sym.DIVIDE); }

{SEMICOL} { return symbol(OCL_Sym.SEMICOL); }
{COLON} { return symbol(OCL_Sym.COLON); }
{DCOLON} { return symbol(OCL_Sym.DCOLON); }
{LPAREN} { return symbol(OCL_Sym.LPAREN); }
{RPAREN} { return symbol(OCL_Sym.RPAREN); }
{COMMA} { return symbol(OCL_Sym.COMMA); }
{BAR} { return symbol(OCL_Sym.BAR); }
{RARROW} { return symbol(OCL_Sym.RARROW); }
{DOT} { return symbol(OCL_Sym.DOT); }
"if" { return symbol(OCL_Sym.IF); }
"then" { return symbol(OCL_Sym.THEN); }
"else" { return symbol(OCL_Sym.ELSE); }
"endif" { return symbol(OCL_Sym.ENDIF); }
"implies" { return symbol(OCL_Sym.IMPLIES); }

"size" { return symbol(OCL_Sym.SIZE); }
"includes" { return symbol(OCL_Sym.INCLUDES); }
"excludes" { return symbol(OCL_Sym.EXCLUDES); }
"forAll" { return symbol(OCL_Sym.FORALL); }
"select" { return symbol(OCL_Sym.SELECT); }
"exists" { return symbol(OCL_Sym.EXISTS); }
"includesAll" { return symbol(OCL_Sym.INCLUDESALL); }

{STRING} { return symbol(OCL_Sym.STRING, yytext()); }
{ID} { return symbol(OCL_Sym.ID, yytext()); }
{NUMERO} { return symbol(OCL_Sym.NUMERO, yytext());}

{WhiteSpace} { }
{LineTerminator} { }
{COMENTARIO} { }

. { throw new RuntimeException("Illegal char at line: " + (yyline+1) + " column: " + (yycolumn+1)); }
