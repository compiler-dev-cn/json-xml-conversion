/**
 * lex rule for xml file
 */

%{
#include "xml_lexer.h"
%}

NAME   ((_|[a-zA-Z])+(_|[a-zA-Z]|[0-9])*:)?((_|[a-zA-Z])+(_|[a-zA-Z]|[0-9])*)

%START PI TAG END_TAG ATTR

%%
<INITIAL>"<?" {BEGIN PI; return XML_PI_START_TAG;}
<PI>"?>" {BEGIN INITIAL; return XML_PI_END_TAG;}

<INITIAL>"<" {BEGIN TAG; return XML_ELEMENT_START_TAG;}
<TAG>{NAME} {BEGIN ATTR; return XML_NAME;}
<TAG,ATTR>"/>" {BEGIN INITIAL; return XML_ELEMENT_EMPTY_END_TAG;}
<INITIAL>"</" {BEGIN END_TAG; return XML_ELEMENT_END_START_TAG;}
<END_TAG>{NAME} {return XML_NAME;}
<TAG,ATTR,END_TAG>">" {BEGIN INITIAL; return XML_ELEMENT_END_TAG;}

<ATTR>{NAME} {return XML_NAME;}
<ATTR>"=" {return XML_EQ;}
<ATTR>"\"[^]\"" {return XML_ATTRIBUTE_VALUE;}

%%

int yywrap() {
    return 1;
}

void reset(FILE* in) {
    yyin = in;
}