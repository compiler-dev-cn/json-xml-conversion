package com.cdc.jxc.lex;

%%

%{
    int yyline() {
      return this.yyline;
    }

    int yycolumn() {
      return this.yycolumn;
    }
%}

%unicode
%class XmlLexerImpl
%public
%function nextToken
%type ITokenType
%line
%column

WS = [\ \n\r\t\f\u2028\u2029\u0085]
S = {WS}+
DIGIT = [0-9]
ALPHA = [a-zA-Z]
ID = (_|{ALPHA})+(_|{DIGIT}|{ALPHA})*
NAME = ({ID}:)?{ID}

%state PI
%state COMMENT
%state TAG
%state ATTR
%state END_TAG
%%

{S} {return CommonTokenType.SKIP;}

<YYINITIAL> {
"<?" {yybegin(PI); return XmlTokenType.PI_START_TAG;}
"<!--" {yybegin(COMMENT); return XmlTokenType.COMMENT_START_TAG;}
"<" {yybegin(TAG); return XmlTokenType.ELEMENT_START_TAG;}
"</" {yybegin(END_TAG); return XmlTokenType.ELEMENT_END_START_TAG;}
}
<PI> {
"?>" {yybegin(YYINITIAL); return XmlTokenType.PI_END_TAG;}
[^?>]+ {return CommonTokenType.SKIP;}
}

<COMMENT> {
"-->" {yybegin(YYINITIAL); return XmlTokenType.COMMENT_END_TAG;}
[^\-\->]+ {return CommonTokenType.SKIP;}
}

<TAG>{
{NAME} {yybegin(ATTR); return XmlTokenType.NAME;}
"/>" {yybegin(YYINITIAL); return XmlTokenType.ELEMENT_EMPTY_END_TAG;}
">" {yybegin(YYINITIAL); return XmlTokenType.ELEMENT_END_TAG;}
}

<ATTR> {
"/>" {yybegin(YYINITIAL); return XmlTokenType.ELEMENT_EMPTY_END_TAG;}
">" {yybegin(YYINITIAL); return XmlTokenType.ELEMENT_END_TAG;}
{NAME} {return XmlTokenType.NAME;}
"=" {return XmlTokenType.EQ;}
\"[^]\" {return XmlTokenType.ATTRIBUTE_VALUE; }
}

<END_TAG> {
{NAME} {return XmlTokenType.NAME;}
">" {yybegin(YYINITIAL); return XmlTokenType.ELEMENT_END_TAG;}
}
