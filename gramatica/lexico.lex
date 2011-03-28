package compil.inv.parser;

import java_cup.*;
import java_cup.runtime.*;
      
%%
%class OCL_Lexer
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


%%
/* ------------------------Lexical Rules Section---------------------- */
   
/*keywords*/
<YYINITIAL> "context" { System.out.println(" context ");return symbol(OclSym.CONTEXT); }
<YYINITIAL> "body" { return symbol(OclSym.BODY); }
<YYINITIAL> "pre" { return symbol(OclSym.PRE); }
<YYINITIAL> "post" { return symbol(OclSym.POST); }
<YYINITIAL> "inv" { System.out.println(" invariante "); return symbol(OclSym.INV); }
<YYINITIAL> "init" { return symbol(OclSym.INIT); }
<YYINITIAL> "select" { return symbol(OclSym.SELECT); }
<YYINITIAL> "exists" { return symbol(OclSym.EXISTS); }
<YYINITIAL> "forAll" { return symbol(OclSym.FOR_ALL); }
<YYINITIAL> "including" { return symbol(OclSym.INCLUDING); }
<YYINITIAL> "excluding" { return symbol(OclSym.EXCLUDING); }
<YYINITIAL> "oclIsNew" { return symbol(OclSym.OCL_IS_NEW); }
<YYINITIAL> {ATSIGN} "pre" { return symbol(OclSym.AT_PRE); }
<YYINITIAL> "result" { return symbol(OclSym.RESULT); }
<YYINITIAL> "size" { return symbol(OclSym.SIZE); }
<YYINITIAL> "self" { return symbol(OclSym.SELF); }

{LT} { return symbol(OclSym.RELOP, OclSym.LT); }
{LE} { return symbol(OclSym.RELOP, OclSym.LE); }
{GT} { return symbol(OclSym.RELOP, OclSym.GT); }
{GE} { return symbol(OclSym.RELOP, OclSym.GE); }
{EQUAL} { return symbol(OclSym.RELOP, OclSym.EQUAL); }
{NEQUAL} { return symbol(OclSym.RELOP, OclSym.NEQUAL); }

{PLUS} { return symbol(OclSym.PLUS); }
{MULT} { return symbol(OclSym.MULT); }
{DIVIDE} { return symbol(OclSym.DIVIDE); }
{MINUS} { return symbol(OclSym.MINUS); }

"if" { return symbol(OclSym.IF); }
"then" { return symbol(OclSym.THEN); }
"else" { return symbol(OclSym.ELSE); }
"endif" { return symbol(OclSym.ENDIF); }
"pre" { return symbol(OclSym.PRE); }
"implies" { return symbol(OclSym.IMPLIES); }
"post" { return symbol(OclSym.POST); }
"set" { return symbol(OclSym.SET); }
"true" { return symbol(OclSym.TRUE); }
"false" { return symbol(OclSym.FALSE); }

{DOTDOT} { return symbol(OclSym.DOTDOT); }
{COLON} { return symbol(OclSym.COLON); }
{COMMA} { return symbol(OclSym.COMMA); }
{LPAREN} { return symbol(OclSym.LPAREN); }
{RPAREN} { return symbol(OclSym.RPAREN); }
{DCOLON} { return symbol(OclSym.DCOLON); }
{TYPE} { return symbol(OclSym.TYPE, yytext()); }
{ID} { System.out.println(" ID= "+yytext());return symbol(OclSym.ID, yytext()); }
{STRING} { return symbol(OclSym.STRING, yytext()); }
{NUMERO} { /*return*/ symbol(OclSym.NUMERO, new Integer(yytext()));}
{WhiteSpace} { }
{LineTerminator} { }
