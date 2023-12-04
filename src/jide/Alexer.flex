package jide;
import java.io.*;
import static jide.Tokens.*;

%%
%class Alexer
%type Tokens
%line
%column
L=[a-zA-Z_]+
D=[0-9]+|-?(([0-9]+[.]?[0-9]*)|([.][0-9]+))(e[+-]?[0-9]+)?
CA=\"(\\.|[^\"])*\"
CAR='([^'\\]|\\\\[btnfr"'\\"\\\\])'
espacio=[ ]+
espa=[\t]+
esp=[\r]+
salto=[\n]
%{
    public String lexeme;
    Analisis c = new Analisis();
int estado=0;
int nu=0;
%}
%%

"//" .* { /* Ignore */ }
"/*" [^*] ~"*/" | "/*" "*"+ "/" { /* Ignore */ }
{espacio} {/*Ignore*/}
{espa} {/*Ignore*/}
{esp} {/*Ignore*/}
{salto} {/*Ignore*/}
<YYINITIAL> "program" {estado = 1;c.linea=yyline; lexeme=yytext();return program;}
<YYINITIAL> "{" {c.linea=yyline; lexeme=yytext();return open_key;}
<YYINITIAL> "}" {c.linea=yyline; lexeme=yytext();return close_key;}
<YYINITIAL> "print" {c.linea=yyline; lexeme=yytext();return print;}
<YYINITIAL> "(" {c.linea=yyline; lexeme=yytext();return open_parenth;}
<YYINITIAL> ")" {c.linea=yyline; lexeme=yytext();return close_parenth;}
<YYINITIAL> "int" {c.linea=yyline; lexeme=yytext();return integer;}
<YYINITIAL> "float" {c.linea=yyline; lexeme=yytext();return floatType;}
<YYINITIAL> "char" {c.linea=yyline; lexeme=yytext();return character;}
<YYINITIAL> "read" {c.linea=yyline; lexeme=yytext();return read;}
<YYINITIAL> "if" {c.linea=yyline; lexeme=yytext();return ifStr;}
<YYINITIAL> "then" {c.linea=yyline; lexeme=yytext();return then;}
<YYINITIAL> "else" {c.linea=yyline; lexeme=yytext();return elseStr;}
<YYINITIAL> "while" {c.linea=yyline; lexeme=yytext();return whileStr;}
<YYINITIAL> "," {c.linea=yyline; lexeme=yytext();return comma;}
<YYINITIAL> ";" {c.linea=yyline; lexeme=yytext();return semicolon;}
<YYINITIAL> {L}({L}{D})* {c.linea=yyline; lexeme=yytext();return id;}
<YYINITIAL> {D} {c.linea=yyline; lexeme=yytext();return num;}
<YYINITIAL> {CAR}+ {c.linea=yyline; lexeme=yytext();return litcar;}
<YYINITIAL> {CA}+ {c.linea=yyline; lexeme=yytext();return litcad;}
<YYINITIAL> "+" {c.linea=yyline; lexeme=yytext();return plus;}
<YYINITIAL> "-" {c.linea=yyline; lexeme=yytext();return minus;}
<YYINITIAL> "*" {c.linea=yyline; lexeme=yytext();return mult;}
<YYINITIAL> "/" {c.linea=yyline; lexeme=yytext();return div;}
<YYINITIAL> "=" {c.linea=yyline; lexeme=yytext();return equal;}
<YYINITIAL> "<" {c.linea=yyline; lexeme=yytext();return less_than;}
<YYINITIAL> ">" {c.linea=yyline; lexeme=yytext();return greater_than;}
<YYINITIAL> "!=" {c.linea=yyline; lexeme=yytext();return different_to;}
<YYINITIAL> "==" {c.linea=yyline; lexeme=yytext();return equals_to;}
<YYINITIAL> "<=" {c.linea=yyline; lexeme=yytext();return less_or_equals;}
<YYINITIAL> ">=" {c.linea=yyline; lexeme=yytext();return greater_or_equals;}
<YYINITIAL> {L}({L}{D})* {if (estado == 1) { // Si se encontró la palabra clave "Programa"
        c.linea = yyline;
        lexeme = yytext();
        estado = 2;
        return idp; 
    } else { // Si no se ha encontrado "Programa"
        c.linea = yyline;
        lexeme = yytext();
        return id; // Token para identificador normal
    }
}