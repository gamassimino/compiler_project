package Example;

import java_cup.runtime.*;
import java_cup.runtime.ComplexSymbolFactory.Location;
%%
%cup
%line
%column
%char
%class Scanner
%{
	public Scanner(java.io.InputStream r, ComplexSymbolFactory sf){
		this(r);
		this.sf=sf;
	}
	public Symbol symbol(String plaintext,int code){
    return sf.newSymbol(plaintext,code,new Location("",yyline+1, yycolumn +1,yychar), new Location("",yyline+1,yycolumn+yylength(),yychar));
	}
	public Symbol symbol(String plaintext,int code, Integer number){
    return sf.newSymbol(plaintext,code,new Location("",yyline+1, yycolumn +1,yychar), new Location("",yyline+1,yycolumn+yylength(),yychar),number);
	}
	public Symbol symbol(String plaintext,int code, Float number){
    return sf.newSymbol(plaintext,code,new Location("",yyline+1, yycolumn +1,yychar), new Location("",yyline+1,yycolumn+yylength(),yychar),number);
	}
	public Symbol symbol(String plaintext,int code, Boolean bool){
    return sf.newSymbol(plaintext,code,new Location("",yyline+1, yycolumn +1,yychar), new Location("",yyline+1,yycolumn+yylength(),yychar),bool);
	}
	private ComplexSymbolFactory sf;
%}
%eofval{
  return sf.newSymbol("EOF",sym.EOF);
%eofval}

%%
// ### Class Declaration ###
[A-Z]+[a-z]* { System.out.println("CLASS"); }

// ### Const Declaration ###
[A-Z]* { System.out.println("CONST"); }

// ### Others ###
"break" { return symbol("Break", sym.BREAK); }
"class" { return symbol("Class", sym.CLASS); }
"continue" { return symbol("Continue", sym.CONTINUE); }
"extern" { return symbol("Extern", sym.EXTERN);}

// ### Function Return ###
"return" { return symbol("Return", sym.RETURN); }

// ### Conditional Sentences ###
"if" { return symbol("If", sym.IF);}
"else" { return symbol("Else", sym.ELSE); }

// ### Loop Sentences ###
"while" { return symbol("While", sym.WHILE); }
"for" { return symbol("For", sym.FOR);}

// ### Logic Values ###
"false" { return symbol("False", sym.FALSE, false); }
"true" { return symbol("True", sym.TRUE, true); }

// ### Pointer Values ###
"void" { return symbol("Void", sym.VOID); }
"null" { return symbol("Null", sym.NULL); }

// ### Numeric Values ###
[1-9]+[0-9]* { return symbol("Integral Number",sym.NUMBER_I, new Integer(yytext())); }
[0-9]+"."[0-9]+ { return symbol("Float Number",sym.NUMBER_F, new Float(yytext())); }

// ### Types ##
"integer" { return symbol("Integer", sym.INTEGER);}
"bool" { return symbol("Bool", sym.BOOL); }
"float" { return symbol("Float", sym.FLOAT); }

// ### Coments ###
"//" .* { System.out.println("COMMENT IN ONE LINE"); }
"/*"~"*/" { System.out.println("COMMENT IN SOMES LINE"); }

// ### Logic Operators ###
"&&" { return symbol("And", sym.AND); }
"||" { return symbol("Or", sym.OR); }
"!" { return symbol("Not", sym.NOT); }

// ### Relationship Operators ###
"!=" { return symbol("Not Equal To",sym.NOTEQUALTO); }
"==" { return symbol("Equal To",sym.EQUALTO); }
">" { return symbol("Greater",sym.GREATER); }
">=" { return symbol("Greater or Equal",sym.GREATEROREQ); }
"<" { return symbol("Less",sym.LESS); }
"<=" { return symbol("Less or Equal",sym.LESSOREQ); }

// ### End Sentence ###
";" { return symbol("Semicolon",sym.SEMI); }

// ### Numeric Operators ###
"+" { return symbol("Plus",sym.PLUS); }
"-" { return symbol("Minus",sym.MINUS); }
"%" { return symbol("Percentage",sym.PERCENTAGE); }
"/" { return symbol("Divided",sym.DIVIDED); }
"*" { return symbol("Times",sym.TIMES); }

// ### Grouper ###
"(" { return symbol("Left Bracket",sym.LPAREN); }
")" { return symbol("Right Bracket",sym.RPAREN); }

// ### Identifier ###
[a-z]* { System.out.println("IDENTIFIER"); }

[ \t\r\n\f] { /* ignore white space. */ }
. { System.err.println("Illegal character: "+yytext()); }
