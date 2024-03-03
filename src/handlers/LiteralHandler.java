package handlers;

import antlr.WParser;
import sdk.enums.DoubleuTypes;

public final class LiteralHandler {
    public static String inferType(WParser.LiteralContext ctx) {
        if (ctx.INT() != null) {
            return DoubleuTypes.INTEGER.toString();
        } else if (ctx.DECIMAL() != null) {
            return DoubleuTypes.DECIMAL.toString();
        } else if (ctx.STRING() != null) {
            return DoubleuTypes.STRING.toString();
        } else if (ctx.True() != null || ctx.False() != null) {
            return DoubleuTypes.BOOLEAN.toString();
        }

        // Caso nenhum tipo seja correspondido, retorne um tipo padrão ou trate o erro
        return DoubleuTypes.UNKNOWN.toString();
    }
}
