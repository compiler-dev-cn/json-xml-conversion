package com.cdc.jxc.parse;

import com.cdc.jxc.lex.ILexer;
import org.jetbrains.annotations.NotNull;

public abstract class BaseParser implements IParser {

    protected ILexer lexer;

    public BaseParser(@NotNull ILexer lexer) {
        this.lexer = lexer;
    }
}
