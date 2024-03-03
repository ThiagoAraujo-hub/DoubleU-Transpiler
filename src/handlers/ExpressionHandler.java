package handlers;

import java.util.Map;

import antlr.WParser.ExpressionContext;
import sdk.DoubleuVariable;
import sdk.enums.DoubleuTypes;

public class ExpressionHandler {
    private Map<String, DoubleuVariable> identifiersDeclared;

    public ExpressionHandler(Map<String, DoubleuVariable> identifiersDeclared) {
        this.identifiersDeclared = identifiersDeclared;
    }

    public String inferType(ExpressionContext ctx) {
        if (ctx.ID() != null) {
            var variableName = ctx.ID().getText();
            if (!identifiersDeclared.containsKey(variableName))
                return DoubleuTypes.UNKNOWN.toString();

            return identifiersDeclared.get(variableName).type;
        } else if (ctx.literal() != null) {
            return LiteralHandler.inferType(ctx.literal());
        } else if (ctx.functionCall() != null) {
            var functionName = ctx.functionCall().ID().getText();
            if (!identifiersDeclared.containsKey(functionName))
                return DoubleuTypes.UNKNOWN.toString();

            return identifiersDeclared.get(functionName).type;
        } else if (ctx.binaryOperator() != null) {
            String leftType = inferType(ctx.expression(0));
            String rightType = inferType(ctx.expression(1));
            String operator = ctx.binaryOperator().getText();

            if (!MathOperationsHandler.isMathOperationTypesCompatible(leftType, rightType, operator)) {
                return DoubleuTypes.UNKNOWN.toString();
            }

            return MathOperationsHandler.determineResultType(leftType, rightType, operator);
        } else if (ctx.unaryOperator() != null) {
            String operandType = inferType(ctx.expression(0));
            String operator = ctx.unaryOperator().getText();

            if (!MathOperationsHandler.isUnaryOperatorApplicable(operandType, operator)) {
                return DoubleuTypes.UNKNOWN.toString();
            }

            return operandType;
        } else if (ctx.LeftParen() != null && ctx.RightParen() != null) {
            return inferType(ctx.expression(0));
        } else if (ctx.propertyAccess() != null) {
            return "object";
        } else if (ctx.arrayAccess() != null) {
            var variableName = ctx.arrayAccess().ID().getText();
            if (!identifiersDeclared.containsKey(variableName))
                return DoubleuTypes.UNKNOWN.toString();

            return identifiersDeclared.get(variableName).type;
        } else {
            return DoubleuTypes.UNKNOWN.toString();
        }
    }
}
