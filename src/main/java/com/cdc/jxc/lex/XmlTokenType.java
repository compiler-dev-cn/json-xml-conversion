package com.cdc.jxc.lex;

public enum XmlTokenType implements ITokenType {
    PI_START_TAG("pi_start_tag"),
    PI_END_TAG("pi_end_tag"),

    COMMENT_START_TAG("comment_start_tag"),
    COMMENT_END_TAG("comment_end_tag"),

    ELEMENT_START_TAG("element_start_tag"),
    ELEMENT_EMPTY_END_TAG("element_empty_end_tag"),
    ELEMENT_END_START_TAG("element_end_start_tag"),
    ELEMENT_END_TAG("element_end_tag"),

    NAME("name"),
    ATTRIBUTE_VALUE("attribute_value"),
    INITIAL_CHARACTER("initial_character"),

    EQ("eq");


    private String name;

    XmlTokenType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
