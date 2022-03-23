package com.cdc.jxc.parse.xml;

import com.cdc.jxc.parse.ast.LeafNodeBase;
import org.jetbrains.annotations.NotNull;

public class XmlAttributeValueNode extends LeafNodeBase {

    private final String mValueString;

    public XmlAttributeValueNode(@NotNull String valueString) {
        this.mValueString = valueString;
    }
}
