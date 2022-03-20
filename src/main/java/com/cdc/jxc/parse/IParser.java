package com.cdc.jxc.parse;

import com.cdc.jxc.parse.ast.ASTNode;

import java.io.IOException;

public interface IParser {

    ASTNode parse() throws IOException;
}
