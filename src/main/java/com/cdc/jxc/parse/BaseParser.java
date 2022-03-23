package com.cdc.jxc.parse;

import com.cdc.jxc.exception.SyntaxException;
import com.cdc.jxc.lex.ILexer;
import com.cdc.jxc.lex.ITokenType;
import com.cdc.jxc.lex.Position;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;

public abstract class BaseParser implements IParser {

    private ILexer mLexer;

    private Position mCurrentPosition;

    public BaseParser(@NotNull ILexer lexer) {
        this.mLexer = lexer;
    }

    protected void expectNext(@NotNull ITokenType expectedTokenType) throws IOException {
        if (mLexer.eof()) {
            throw new SyntaxException("No more tokens", currentPosition());
        }
        ITokenType nextTokenType = mLexer.nextToken();
        if (nextTokenType != expectedTokenType) {
            throw new SyntaxException(
                    String.format(
                            "Unexpected token type, expected: %s",
                            expectedTokenType), currentPosition());
        }
    }

    protected void expectCurrent(@NotNull ITokenType expectedTokenType) {
        if (mLexer.tokenType() != expectedTokenType) {
            throw new SyntaxException(
                    String.format(
                            "Unexpected token type, expected: %s",
                            expectedTokenType), currentPosition());
        }
    }

    protected boolean eof() {
        return mLexer.eof();
    }

    @NotNull
    protected String tokenText() {
        return mLexer.tokenText();
    }

    @Nullable
    protected ITokenType nextToken() throws IOException {
        mCurrentPosition = mLexer.tokenPosition();
        return mLexer.nextToken();
    }

    @Nullable
    public Position currentPosition() {
        return mCurrentPosition;
    }
}
