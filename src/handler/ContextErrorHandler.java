package handler;

import java.util.ArrayList;
import java.util.List;

import org.antlr.v4.runtime.ParserRuleContext;

public class ContextErrorHandler {
    private List<String> errors = new ArrayList<String>();

    // error messages
    // duplicated
    private final String variableDuplicatedMessage = "local variable %s duplicated";
    private final String propertyDuplicatedMessage = "local property %s duplicated";
    private final String arrayDuplicatedMessage = "local array %s duplicated.";
    private final String objectDuplicatedMessage = "local object %s duplicated.";
    private final String duplicatedFunctionMessage = "duplicated function %s.";

    // not declared
    private final String notDeclaredVariableMessage = "%s cannot be resolved to a variable";
    private final String notDeclaredObjectMessage = "%s cannot be resolved to an object";
    private final String notDeclaredPropertyMessage = "%s cannot be resolved to a property";
    private final String notDeclaredFunctionMessage = "%s cannot be resolved to a function";

    // type conversion
    private final String typeConversionMessage = "cannot convert from %s to %s";

    // operations
    private final String unaryOperationMessage = "invalid argument to operation %s";
    private final String binaryOperationMessage = "the operator %s is undefined for the argument type(s) %s, %s";

    // arguments
    private final String invalidArgumentsMessage = "the function %s is not applicable to arguments %s";

    // scope
    private final String returnOutsideFunctionScope = "return outside function scope";
    private final String breakOutsideLoop = "break outside loop";
    private final String continueOutsideLoop = "continue outside loop";

    public String getError(int index) {
        return errors.get(index);
    }

    public List<String> getErrors() {
        return errors;
    }

    public void printErrors() {
        for (String error : errors)
            System.err.println(error);
    }

    public boolean isEmpty(){
        return errors.isEmpty();
    }

    public void addError(ParserRuleContext ctx, String msg) {
        errors.add(getErrorLine(ctx) + msg);
    }

    public void addDuplicatedVariableError(ParserRuleContext ctx, String variableName){
        addError(ctx, String.format(variableDuplicatedMessage, variableName));
    }

    public void addDuplicatedPropertyError(ParserRuleContext ctx, String propertyName){
        addError(ctx, String.format(propertyDuplicatedMessage, propertyName));
    }

    public void addDuplicatedArrayError(ParserRuleContext ctx, String arrayName) {
        addError(ctx, String.format(arrayDuplicatedMessage, arrayName));
    }

    public void addDuplicatedObjectError(ParserRuleContext ctx, String objectName) {
        addError(ctx, String.format(objectDuplicatedMessage, objectName));
    }

    public void addDuplicatedFunctionError(ParserRuleContext ctx, String functionName){
        addError(ctx, String.format(duplicatedFunctionMessage, functionName));
    }

    public void addNotDeclaredVariableError(ParserRuleContext ctx, String variableName){
        addError(ctx, String.format(notDeclaredVariableMessage, variableName));
    }

    public void addNotDeclaredObjectError(ParserRuleContext ctx, String objectName){
        addError(ctx, String.format(notDeclaredObjectMessage, objectName));
    }

    public void addNotDeclaredFunctionError(ParserRuleContext ctx, String functionName){
        addError(ctx, String.format(notDeclaredFunctionMessage, functionName));
    }

    public void addNotDeclaredPropertyError(ParserRuleContext ctx, String propertyName){
        addError(ctx, String.format(notDeclaredPropertyMessage, propertyName));
    }

    public void addTypeConversionError(ParserRuleContext ctx, String type1, String type2){
        addError(ctx, String.format(typeConversionMessage, type1, type2));
    }

    public void addUnaryOperationError(ParserRuleContext ctx, String operator){
        addError(ctx, String.format(unaryOperationMessage, operator));
    }

    public void addBinaryOperationError(ParserRuleContext ctx, String leftOperand, String operator, String rightOperand){
        addError(ctx, String.format(binaryOperationMessage, operator, leftOperand, rightOperand));
    }

    public void addReturnOutsideFunctionScopeError(ParserRuleContext ctx){
        addError(ctx, returnOutsideFunctionScope);
    }

    public void addInvalidArgumentsError(ParserRuleContext ctx, String functionSignature, String argumentsSiganature){
        addError(ctx, String.format(invalidArgumentsMessage, functionSignature, argumentsSiganature));
    }

    public void addBreakOutsideLoopError(ParserRuleContext ctx){
        addError(ctx, breakOutsideLoop);
    }

    public void addContinueOutsideLoopError(ParserRuleContext ctx){
        addError(ctx, continueOutsideLoop);
    }

    private String getErrorLine(ParserRuleContext ctx) {
        return "line " + ctx.getStart().getLine() + ":" + ctx.getStop().getCharPositionInLine() + " ";
    }
}
