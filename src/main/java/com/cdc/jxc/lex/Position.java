package com.cdc.jxc.lex;

public class Position {

    private final int mLine;

    private final int mColumn;

    public Position(int line, int column) {
        this.mLine = line;
        this.mColumn = column;
    }

    public int getLine() {
        return mLine;
    }

    public int getColumn() {
        return mColumn;
    }
}
