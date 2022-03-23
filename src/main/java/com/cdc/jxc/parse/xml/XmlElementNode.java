package com.cdc.jxc.parse.xml;

import com.cdc.jxc.parse.ast.CompositeNodeBase;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class XmlElementNode extends CompositeNodeBase {

    private XmlNameNode mName;

    private List<XmlAttributeNode> mAttributes = new ArrayList<>();

    private static final XmlNameNode IMPLICIT_ATTRIBUTE_NAME = new XmlNameNode("#text");

    public XmlElementNode(@NotNull XmlNameNode elementName) {
        this.mName = elementName;
    }

    public void setAttributes(@NotNull Collection<XmlAttributeNode> attributes) {
        mAttributes.clear();
        mAttributes.addAll(attributes);
    }

    @NotNull
    public void addImplicitTextAttribute(@NotNull String value) {
        XmlAttributeValueNode valueNode = new XmlAttributeValueNode(value);
        mAttributes.add(new XmlAttributeNode(IMPLICIT_ATTRIBUTE_NAME, valueNode));
    }

}
