package com.cdc.jxc.parse.ast;

import org.jetbrains.annotations.NotNull;

public interface CompositeASTNode extends ASTNode {

    void addChild(@NotNull ASTNode child);

    public ASTNode firstChild(@NotNull Class<?> type);
}
