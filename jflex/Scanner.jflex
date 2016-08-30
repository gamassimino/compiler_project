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
	public Symbol symbol(String plaintext,int code,Integer number){
    return sf.newSymbol(plaintext,code,new Location("",yyline+1, yycolumn +1,yychar), new Location("",yyline+1,yycolumn+yylength(),yychar),number);
	}
	private ComplexSymbolFactory sf;
%}
%eofval{
  return sf.newSymbol("EOF",sym.EOF);
%eofval}

%%
"bool" { System.out.println("bool"); }
"break" { System.out.println("break"); }
"class" { System.out.println("class"); }
"continue" { System.out.println("continue"); }
"else" { System.out.println("else"); }
// "false" { System.out.println("false"); }
"float" { System.out.println("float"); }
"for" { System.out.println("FOR");}
"if" { System.out.println("if");}
"integer" { System.out.println("INTEGER");}
"return" { System.out.println("RETURN");}
// "true" { System.out.println("TRUE");}
"void" { System.out.println("VOID");}
"while" { System.out.println("WHILE");}
"extern" { System.out.println("EXTERN");}
"and" { System.out.println("AND");}
"or" { System.out.println("OR");}
"not" { System.out.println("NOT");}
"null" { System.out.println("NULL");}


[a-z]* { System.out.println("IDENTIFIER"); }
[A-Z]* { System.out.println("CONST"); }
[A-Z]+[a-z]* { System.out.println("CLASS"); }

// "bool" { return symbol("Bool", sym.BOOL); }
// "break" { return symbol("Break", sym.BREAK); }
// "class" { return symbol("Class", sym.CLASS); }A
// "continue" { return symbol("Continue", sym.CONTINUE); }
// "else" { return symbol("Else", sym.ELSE); }
"false" { return symbol("False", sym.FALSE); }
// "float" { return symbol("Float", sym.FLOAT); }
// "for" { return symbol("For", sym.FOR);}
// "if" { return symbol("If", sym.IF);}
// "integer" { return symbol("Integer", sym.INTEGER);}
// "return" { return symbol("Return", sym.RETURN);}
"true" { return symbol("True", sym.TRUE);}
// "void" { return symbol("Void", sym.VOID);}
// "while" { return symbol("While", sym.WHILE);}
// "extern" { return symbol("Extern", sym.EXTERN);}
// "for" { return symbol("For", sym.FOR); }
// "if" { return symbol("If", sym.IF); }
// "integer" { return symbol("Integer", sym.INTEGER); }
// "return" { return symbol("Return", sym.RETURN); }
// "true" { return symbol("True", sym.TRUE); }
// "void" { return symbol("Void", sym.VOID); }
// "while" { return symbol("While", sym.WHILE); }
// "extern" { return symbol("Extern", sym.EXTERN); }

"//" .* { System.out.println("COMMENT IN ONE LINE"); }
"/*"~"*/" { System.out.println("COMMENT IN SOMES LINE"); }

";" { return symbol("Semicolon",sym.SEMI); }
"+" { return symbol("Plus",sym.PLUS); }
"-" { return symbol("Minus",sym.MINUS); }
"*" { return symbol("Times",sym.TIMES); }
"(" { return symbol("Left Bracket",sym.LPAREN); }
")" { return symbol("Right Bracket",sym.RPAREN); }
[1-9]+[0-9]* { return symbol("Integral Number",sym.NUMBER, new Integer(yytext())); }
// \d+"."\d+ { return symbol("FloatNumber",sym.NUMBERFLOAT, new Float(yytext())); }
[0-9]+"."[0-9]* { System.out.println("Number Point Float"); }
[ \t\r\n\f] { /* ignore white space. */ }
. { System.err.println("Illegal character: "+yytext()); }
