package com.cdc.jxc.parse.xml;

import com.cdc.jxc.parse.ast.CompositeNodeBase;
import org.jetbrains.annotations.NotNull;

public class XmlAttributeNode extends CompositeNodeBase {

    private final XmlNameNode mName;

    private final XmlAttributeValueNode mValue;

    public XmlAttributeNode(@NotNull XmlNameNode name, @NotNull XmlAttributeValueNode value) {
        this.mName = name;
        this.mValue = value;
    }
}
