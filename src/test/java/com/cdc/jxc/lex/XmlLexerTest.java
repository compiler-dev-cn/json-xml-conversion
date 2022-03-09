package com.cdc.jxc.lex;

import com.cdc.jxc.TestResources;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class XmlLexerTest {

    @Test
    public void readTokens() throws IOException {
        File xmlFile = new File(TestResources.getXmlDir(), "simple.xml");
        FileReader reader = new FileReader(xmlFile);
        XmlLexerImpl impl = new XmlLexerImpl(reader);
        while (true) {
            ITokenType type = impl.nextToken();
            if (type == null) {
                return;
            }
            System.out.println(String.format("{%s, %s, %d, %d}",
                    type.name(),
                    impl.yytext(),
                    impl.yyline(),
                    impl.yycolumn()));
        }
    }
}
