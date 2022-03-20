package com.cdc.jxc.parse.ast;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class CompositeNodeBase implements CompositeASTNode {

    private final List<ASTNode> mChildren = new ArrayList<>();

    @Override
    public void addChild(@NotNull ASTNode child) {
        this.mChildren.add(child);
    }

    @Override
    public ASTNode firstChild(@NotNull Class<?> type) {
        for (ASTNode child : mChildren) {
            if (child.getClass() == type) {
                return child;
            }
        }
        return null;
    }
}
