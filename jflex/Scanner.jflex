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
// "for" { return symbol("For", sym.FOR);}
// "if" { return symbol("If", sym.IF);}
// "integer" { return symbol("Integer", sym.INTEGER);}
// "return" { return symbol("Return", sym.RETURN);}
// "true" { return symbol("True", sym.TRUE);}
// "void" { return symbol("Void", sym.VOID);}
// "while" { return symbol("While", sym.WHILE);}
// "extern" { return symbol("Extern", sym.EXTERN);}

"for" { System.out.println("FOR");}
"if" { System.out.println("if");}
"integer" { System.out.println("INTEGER");}
"return" { System.out.println("RETURN");}
"true" { System.out.println("TRUE");}
"void" { System.out.println("VOID");}
"while" { System.out.println("WHILE");}
"extern" { System.out.println("EXTERN");}

";" { return symbol("Semicolon",sym.SEMI); }
"+" { return symbol("Plus",sym.PLUS); }
"*" { return symbol("Times",sym.TIMES); }
"(" { return symbol("Left Bracket",sym.LPAREN); }
")" { return symbol("Right Bracket",sym.RPAREN); }
[0-9]+ { return symbol("Integral Number",sym.NUMBER, new Integer(yytext())); }
[ \t\r\n\f] { /* ignore white space. */ }
. { System.err.println("Illegal character: "+yytext()); }
