//
// Created by imurluck on 2022/3/8.
//
#include "xml/lex.yy.c"

int main() {
    "((_|[a-zA-Z])+(_|[a-zA-Z]|[0-9])*:)?((_|[a-zA-Z])+(_|[a-zA-Z]|[0-9])*)";
    setbuf(stdout, NULL);
    const char* filePath = "/Users/imurluck/compiler/json-xml-conversion/lex/xml/test.xml";
    FILE* fd = fopen(filePath, "r");
    if (fd == NULL) {
        printf("open file failed\n");
        return 0;
    }
    reset(fd);
    while (1) {
        XmlTokenType tokenType = yylex();
        if (!tokenType) {
            return 0;
        }
        printf("{%s, %s}\n", getXmlTokenTypeName(tokenType), yyget_text());
    }
    return 0;
}