package com.cdc.jxc.lex;

public enum CommonTokenType implements ITokenType {

    SKIP("skip");

    private String name;

    CommonTokenType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
