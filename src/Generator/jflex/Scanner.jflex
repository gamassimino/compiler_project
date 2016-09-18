package CodeGenerated;

import ASTClass.BoolLiteral;
import ASTClass.FloatLiteral;
import ASTClass.IntLiteral;
import java_cup.runtime.*;
import java_cup.runtime.ComplexSymbolFactory.Location;
%%
%cup
%line
%column
%char
%public
%class Scanner
%{
  public Scanner(java.io.InputStream r, ComplexSymbolFactory sf){
    this(r);
    this.sf=sf;
  }
  public Symbol symbol(String plaintext,int code){
    return sf.newSymbol(plaintext,code,new Location("",yyline+1, yycolumn +1,yychar), new Location("",yyline+1,yycolumn+yylength(),yychar));
  }
  public Symbol symbol(String plaintext,int code, BoolLiteral bool){
    return sf.newSymbol(plaintext,code,new Location("",yyline+1, yycolumn +1,yychar), new Location("",yyline+1,yycolumn+yylength(),yychar),bool);
  }
  public Symbol symbol(String plaintext,int code, FloatLiteral bool){
    return sf.newSymbol(plaintext,code,new Location("",yyline+1, yycolumn +1,yychar), new Location("",yyline+1,yycolumn+yylength(),yychar),bool);
  }
  public Symbol symbol(String plaintext,int code, IntLiteral bool){
    return sf.newSymbol(plaintext,code,new Location("",yyline+1, yycolumn +1,yychar), new Location("",yyline+1,yycolumn+yylength(),yychar),bool);
  }
  public Symbol symbol(String plaintext,int code, String id){
    return sf.newSymbol(plaintext,code,new Location("",yyline+1, yycolumn +1,yychar), new Location("",yyline+1,yycolumn+yylength(),yychar),id);
  }
  private ComplexSymbolFactory sf;
%}
%eofval{
  return sf.newSymbol("EOF",sym.EOF);
%eofval}

%%
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
"false" { return symbol("False", sym.BOOLEAN_VALUE, new BoolLiteral("false")); }
"true" { return symbol("True", sym.BOOLEAN_VALUE, new BoolLiteral("true")); }

// ### Pointer Values ###
"void" { return symbol("Void", sym.VOID, yytext()); }
// "null" { return symbol("Null", sym.NULL); }

// ### Numeric Values ###
[1-9]+[0-9]* { return symbol("Integral Number",sym.NUMBER_I, new IntLiteral(yytext())); }
[0-9]+"."[0-9]+ { return symbol("Float Number",sym.NUMBER_F, new FloatLiteral(yytext())); }

// ### Types ##
"integer" { return symbol("Integer", sym.INTEGER, yytext());}
"bool" { return symbol("Bool", sym.BOOL, yytext()); }
"float" { return symbol("Float", sym.FLOAT, yytext()); }

// ### Coments ###
"//" .* {  }
"/*"~"*/" {  }

// ### Logic Operators ###
"&&" { return symbol("And", sym.AND); }
"||" { return symbol("Or", sym.OR); }
"!" { return symbol("Not", sym.NOT); }

// ### Assignment
"=" { return symbol("Assign",sym.ASSIGNMENT); }
"+=" { return symbol("Assign and Add",sym.ADDASSIGNMENT); }
"-=" { return symbol("Assign and Substract",sym.SUBASSIGNMENT); }

// ### Relationship Operators ###
"!=" { return symbol("Not Equal To",sym.NOTEQUALTO); }
"==" { return symbol("Equal To",sym.EQUALTO); }
">" { return symbol("Greater",sym.GREATER); }
">=" { return symbol("Greater or Equal",sym.GREATEROREQ); }
"<" { return symbol("Less",sym.LESS); }
"<=" { return symbol("Less or Equal",sym.LESSOREQ); }

// ### End Sentence ###
"," { return symbol("Comma",sym.COMMA); }
";" { return symbol("Semicolon",sym.SEMI); }

// ### Numeric Operators ###
"." { return symbol("Point",sym.POINT); }
"+" { return symbol("Plus",sym.PLUS); }
"-" { return symbol("Minus",sym.MINUS); }
"%" { return symbol("Percentage",sym.PERCENTAGE); }
"/" { return symbol("Divided",sym.DIVIDED); }
"*" { return symbol("Times",sym.TIMES); }

// ### Grouper ###
"(" { return symbol("Left Paren",sym.LPAREN); }
")" { return symbol("Right Paren",sym.RPAREN); }
"{" { return symbol("Left Key",sym.LKEY); }
"}" { return symbol("Right Key",sym.RKEY); }
"[" { return symbol("Left Bracket",sym.LBRACKET); }
"]" { return symbol("Right Bracket",sym.RBRACKET); }

// ### Identifier ###
[a-z]+[a-zA-Z0-9_]* { return symbol("Id", sym.ID, yytext()); }

[ \t\r\n\f] { /* ignore white space. */ }
. { System.err.println("Illegal character: "+yytext()); }
