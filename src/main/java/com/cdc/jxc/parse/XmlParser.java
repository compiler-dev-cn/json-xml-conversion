package com.cdc.jxc.parse;


import com.cdc.jxc.exception.SyntaxException;
import com.cdc.jxc.lex.CommonTokenType;
import com.cdc.jxc.lex.ILexer;
import com.cdc.jxc.lex.ITokenType;
import com.cdc.jxc.lex.XmlTokenType;
import com.cdc.jxc.parse.ast.ASTNode;
import com.cdc.jxc.parse.ast.CompositeNodeBase;
import com.cdc.jxc.parse.ast.VirtualFileNode;
import com.cdc.jxc.parse.xml.*;
import com.cdc.jxc.util.TextUtils;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class XmlParser extends BaseParser {

    private final VirtualFileNode mVirtualFileNode = new VirtualFileNode();

    public XmlParser(@NotNull ILexer lexer) {
        super(lexer);
    }

    @Override
    public ASTNode parse() throws IOException {
        return parse(mVirtualFileNode);
    }

    private ASTNode parse(@NotNull VirtualFileNode root) throws IOException {
        while (!eof()) {
            ITokenType tokenType = nextToken();
            if (tokenType == XmlTokenType.PI_START_TAG) {
                parseProcessInstruction(root);
            } else if (tokenType == XmlTokenType.COMMENT_START_TAG) {
                parseComment(root);
            } else if (tokenType == XmlTokenType.ELEMENT_START_TAG) {
                if (root.firstChild(XmlElementNode.class) != null) {
                    throw new IllegalStateException("xml file can only has one root element");
                }
                parseElement(root);
            }
        }
        return root;
    }

    private void parseElement(@NotNull CompositeNodeBase root) throws IOException {
        expectNext(XmlTokenType.NAME);
        XmlNameNode elementName = new XmlNameNode(tokenText());
        ITokenType tokenType = nextToken();
        List<XmlAttributeNode> attributeNodes = new ArrayList<>();
        while (tokenType == XmlTokenType.NAME) {
            XmlNameNode attributeName = new XmlNameNode(tokenText());
            expectNext(XmlTokenType.EQ);
            expectNext(XmlTokenType.ATTRIBUTE_VALUE);
            XmlAttributeValueNode attributeValue = new XmlAttributeValueNode(tokenText());
            attributeNodes.add(new XmlAttributeNode(attributeName, attributeValue));
            tokenType = nextToken();
        }
        XmlElementNode elementNode = new XmlElementNode(elementName);
        elementNode.setAttributes(attributeNodes);

        if (tokenType == XmlTokenType.ELEMENT_END_TAG) {
            root.addChild(elementNode);
        } else if (tokenType == XmlTokenType.ELEMENT_EMPTY_END_TAG) {
            while (nextToken() == CommonTokenType.SKIP);
            if (nextToken() == XmlTokenType.INITIAL_CHARACTER) {
                elementNode.addImplicitTextAttribute(tokenText());
                while (nextToken() == CommonTokenType.SKIP) {}
            }
            //child elements
            while (nextToken() == XmlTokenType.ELEMENT_START_TAG) {
                parseElement(elementNode);
            }
            expectCurrent(XmlTokenType.ELEMENT_END_START_TAG);
            parseElementEndTag(elementName.getName());
            root.addChild(elementNode);
        } else {
            throw new SyntaxException("Unexpected token + " + tokenType, currentPosition());
        }
    }

    private void parseElementEndTag(@NotNull String elementName) throws IOException {
        expectNext(XmlTokenType.NAME);
        String readName = tokenText();
        if (!TextUtils.equals(elementName, readName)) {
            throw new SyntaxException(
                    String.format(
                            "Unexpected element end name %s, expected: %s",
                            readName,
                            elementName),
                    currentPosition());
        }
        expectNext(XmlTokenType.ELEMENT_END_TAG);
    }

    private void parseComment(@NotNull VirtualFileNode root) throws IOException {
        ITokenType tokenType = nextToken();
        if (tokenType == CommonTokenType.SKIP) {
            expectNext(XmlTokenType.COMMENT_END_TAG);
        } else {
            expectCurrent(XmlTokenType.COMMENT_END_TAG);
        }
        root.addChild(new XmlCommentNode());
    }

    private void parseProcessInstruction(@NotNull VirtualFileNode root) throws IOException {
        ITokenType tokenType = nextToken();
        if (tokenType == CommonTokenType.SKIP) {
            expectNext(XmlTokenType.PI_END_TAG);
        } else {
            expectCurrent(XmlTokenType.PI_END_TAG);
        }
        root.addChild(new XmlPINode());
    }
}
