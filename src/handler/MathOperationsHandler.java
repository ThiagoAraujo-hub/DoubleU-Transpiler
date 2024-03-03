package handler;

import sdk.enums.DoubleuLogicalOperators;
import sdk.enums.DoubleuMathOperators;
import sdk.enums.DoubleuTypes;

public final class MathOperationsHandler {
    public static boolean isMathOperationTypesCompatible(String leftType, String rightType, String operator) {
        if (someSideIsUnknown(leftType, rightType)){
            return false;
        } else if (MathOperationsHandler.isSumOperator(operator)) {
            if (MathOperationsHandler.someSideIsBoolean(leftType, rightType)) {
                return false;
            } else {
                return true;
            }
        } else if (MathOperationsHandler.isMathOperator(operator)) {
            return MathOperationsHandler.sidesAreNumbers(leftType, rightType);
        } else {
            return leftType.equals(rightType);
        }
    }

    public static boolean someSideIsUnknown(String leftType, String rightType){
        return leftType.equals(DoubleuTypes.UNKNOWN.toString()) || leftType.equals(DoubleuTypes.UNKNOWN.toString());
    }

    public static boolean someSideIsBoolean(String leftType, String rightType) {
        return leftType.equals(DoubleuTypes.BOOLEAN.toString()) || rightType.equals(DoubleuTypes.BOOLEAN.toString());
    }

    public static boolean isSumOperator(String operator) {
        return operator.equals(DoubleuMathOperators.PLUS.toString());
    }

    public static boolean isMathOperator(String operator) {
        return operator.equals(DoubleuMathOperators.MINUS.toString())
                || operator.equals(DoubleuMathOperators.MULT.toString())
                || operator.equals(DoubleuMathOperators.DIV.toString())
                || operator.equals(DoubleuMathOperators.MOD.toString());
    }

    private static boolean operandIsNumber(String operandType) {
        return operandType.equals(DoubleuTypes.INTEGER.toString())
                || operandType.equals(DoubleuTypes.DECIMAL.toString());
    }

    public static boolean sidesAreNumbers(String leftType, String rightType) {
        return operandIsNumber(leftType) && operandIsNumber(rightType);
    }

    public static boolean sameTypes(String leftType, String rightType) {
        return leftType.equals(rightType);
    }

    public static boolean someSideIsString(String leftType, String rightType) {
        return leftType.equals(DoubleuTypes.STRING.toString()) || rightType.equals(DoubleuTypes.STRING.toString());
    }

    public static String determineResultType(String leftType, String rightType, String operator) {
        if (someSideIsUnknown(leftType, rightType)){
            return DoubleuTypes.UNKNOWN.toString();
        } else if (operator.equals(DoubleuMathOperators.PLUS.toString())) {
            if (sidesAreNumbers(leftType, rightType) && sameTypes(leftType, rightType)) {
                return leftType;
            } else if (sidesAreNumbers(leftType, rightType) && !sameTypes(leftType, rightType)) {
                return DoubleuTypes.DECIMAL.toString();
            } else {
                return DoubleuTypes.STRING.toString();
            }
        } else if (operator.equals(DoubleuMathOperators.MINUS.toString())
                || operator.equals(DoubleuMathOperators.MULT.toString())
                || operator.equals(DoubleuMathOperators.DIV.toString())
                || operator.equals(DoubleuMathOperators.MOD.toString())) {

            if (leftType.equals(DoubleuTypes.INTEGER.toString()) && rightType.equals(DoubleuTypes.INTEGER.toString())) {
                return DoubleuTypes.INTEGER.toString();
            } else {
                return DoubleuTypes.DECIMAL.toString();
            }
        }

        return DoubleuTypes.BOOLEAN.toString();
    }

    public static boolean isUnaryOperatorApplicable(String operandType, String operator) {
        if (operator.equals(DoubleuLogicalOperators.NOT.toString())) {
            return operandType.equals(DoubleuTypes.BOOLEAN.toString());
        }

        return operandIsNumber(operandType);
    }
}
