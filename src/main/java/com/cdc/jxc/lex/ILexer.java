package com.cdc.jxc.lex;

import java.io.IOException;

public interface ILexer {

    ITokenType nextToken() throws IOException;

    ITokenType tokenType();

    String tokenText();

    Position tokenPosition();

    boolean eof();

}
