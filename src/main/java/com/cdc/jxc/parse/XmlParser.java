package com.cdc.jxc.parse;


import com.cdc.jxc.lex.ILexer;
import com.cdc.jxc.lex.ITokenType;
import com.cdc.jxc.lex.XmlTokenType;
import com.cdc.jxc.parse.ast.ASTNode;
import com.cdc.jxc.parse.ast.VirtualFileNode;
import com.cdc.jxc.parse.xml.XmlElementNode;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

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
        while (!lexer.eof()) {
            ITokenType tokenType = lexer.nextToken();
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

    private void parseElement(@NotNull VirtualFileNode root) {

    }

    private void parseComment(@NotNull VirtualFileNode root) {

    }

    private void parseProcessInstruction(@NotNull VirtualFileNode root) {

    }
}
