package com.cdc.jxc.lex;

import org.jetbrains.annotations.NotNull;

import java.io.*;

public class XmlLexer implements ILexer {

    private final XmlLexerImpl mImpl;

    private ITokenType mCurrentTokenType;

    public XmlLexer(@NotNull File inputFile) throws FileNotFoundException {
        this.mImpl = new XmlLexerImpl(new FileReader(inputFile));
    }

    public XmlLexer(@NotNull String input) {
        this.mImpl = new XmlLexerImpl(new StringReader(input));
    }

    @Override
    public ITokenType nextToken() throws IOException {
        this.mCurrentTokenType = mImpl.nextToken();
        return this.mCurrentTokenType;
    }

    @Override
    public ITokenType tokenType() {
        return this.mCurrentTokenType;
    }

    @Override
    public String tokenText() {
        return this.mImpl.yytext();
    }

    @Override
    public Position tokenPosition() {
        return new Position(mImpl.yyline(), mImpl.yycolumn());
    }

    @Override
    public boolean eof() {
        return this.mImpl.yyatEOF();
    }
}
