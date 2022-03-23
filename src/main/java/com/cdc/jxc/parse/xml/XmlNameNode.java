package com.cdc.jxc.parse.xml;

import com.cdc.jxc.parse.ast.LeafNodeBase;
import org.jetbrains.annotations.NotNull;

public class XmlNameNode extends LeafNodeBase {

    private final String mName;

    public XmlNameNode(@NotNull String name) {
        this.mName = name;
    }

    public String getName() {
        return mName;
    }
}
