package com.cdc.jxc.exception;

import com.cdc.jxc.lex.Position;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * thrown during the syntax parsing phase
 */
public class SyntaxException extends RuntimeException {

    public SyntaxException(@NotNull String message, @Nullable Position position) {
        this(message, position, null);
    }

    public SyntaxException(@NotNull String message, @Nullable Position position,  @Nullable Throwable cause) {
        super(constructMessage(message, position), cause);
    }

    @NotNull
    private static String constructMessage(@NotNull String message, @Nullable Position position) {
        StringBuilder sb = new StringBuilder();
        if (position != null) {
            sb.append(position);
        }
        sb.append(message);
        return sb.toString();
    }
}
