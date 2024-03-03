package sdk;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.antlr.v4.runtime.tree.TerminalNode;

import antlr.WParser;
import antlr.WParserBaseListener;
import antlr.WParser.ExpressionContext;
import antlr.WParser.PairContext;
import handlers.ContextErrorHandler;
import handlers.ExpressionHandler;
import handlers.LiteralHandler;
import handlers.MathOperationsHandler;
import sdk.enums.DoubleuTypes;
import sdk.enums.Scopes;

public class DoubleuListener extends WParserBaseListener {
    // Controle de escopo
    private Scopes scope = Scopes.MAIN;
    private boolean isOnWhileScope = false;
    private boolean isOnDoWhileScope = false;
    private boolean isOnIfStatement = false;

    // Tabelas de símbolos para variáveis e funções
    private Map<String, DoubleuVariable> identifiersDeclared = new HashMap<>();
    private Map<Integer, DoubleuFunction> dFunctions = new HashMap<>();
    private Map<String, DoubleuObject> dObjects = new HashMap<>();

    // Erros
    public ContextErrorHandler errorHandler = new ContextErrorHandler();

    // Úteis
    public ExpressionHandler expressionHandler = new ExpressionHandler(identifiersDeclared);

    private void enterScope(Scopes scope) {
        this.scope = scope;
    }

    private boolean isOnFunctionScope() {
        return scope == Scopes.FUNCTION;
    }

    @Override
    public void enterProgram(WParser.ProgramContext ctx) {
        enterScope(Scopes.MAIN);
    }

    private boolean variableAlreadyExists(String variableName) {
        return identifiersDeclared.containsKey(variableName) && identifiersDeclared.get(variableName).scope == scope;
    }

    @Override
    public void enterDeclaration(WParser.DeclarationContext ctx) {
        String variableType = ctx.typeSpecifier().getText();
        String variableName = ctx.ID().getText();

        if (variableAlreadyExists(variableName)) {
            errorHandler.addDuplicatedVariableError(ctx, variableName);
            return;
        }

        if (ctx.expression() != null) {
            var expressionType = expressionHandler.inferType(ctx.expression());

            if (!expressionType.equals(DoubleuTypes.UNKNOWN.toString()) && !variableType.equals(expressionType)) {
                errorHandler.addTypeConversionError(ctx, expressionType, variableType);
                return;
            }
        }

        identifiersDeclared.put(variableName, new DoubleuVariable(variableType, variableName, scope));
    }

    @Override
    public void enterAssignment(WParser.AssignmentContext ctx) {
        var variableName = ctx.ID().getText();
        if (!variableAlreadyExists(variableName)) {
            errorHandler.addNotDeclaredVariableError(ctx, variableName);
            return;
        }

        var variable = identifiersDeclared.get(variableName);
        if (ctx.expression() != null) {
            var expressionType = expressionHandler.inferType(ctx.expression());
            if (!variable.type.equals(expressionType)) {
                errorHandler.addTypeConversionError(ctx, expressionType, variable.type);
            }
        }
    }

    @Override
    public void enterDeclarationConditionalExpression(WParser.DeclarationConditionalExpressionContext ctx) {
        var variableName = ctx.getParent().getChild(1).getText();
        DoubleuVariable variable = identifiersDeclared.get(variableName);

        String trueBranchType = expressionHandler.inferType(ctx.expression(0));
        String conditionType = expressionHandler.inferType(ctx.expression(1));
        String falseBranchType = expressionHandler.inferType(ctx.expression(2));

        if (!conditionType.equals(DoubleuTypes.BOOLEAN.toString())){
            errorHandler.addTypeConversionError(ctx, conditionType, DoubleuTypes.BOOLEAN.toString());
            return;
        }

        if (!trueBranchType.equals(falseBranchType) && !trueBranchType.equals(variable.type)){
            errorHandler.addTypeConversionError(ctx, trueBranchType, variable.type);
            return;
        }

        if (!trueBranchType.equals(variable.type)) {
            errorHandler.addTypeConversionError(ctx, trueBranchType, variable.type);
        }

        if (!falseBranchType.equals(variable.type)) {
            errorHandler.addTypeConversionError(ctx, trueBranchType, variable.type);
        }
    }

    @Override
    public void enterAssignmentConditionalExpression(WParser.AssignmentConditionalExpressionContext ctx) {
        var variableName = ctx.getParent().getChild(0).getText();
        DoubleuVariable variable = identifiersDeclared.get(variableName);

        String trueBranchType = expressionHandler.inferType(ctx.expression(0));
        String conditionType = expressionHandler.inferType(ctx.expression(1));
        String falseBranchType = expressionHandler.inferType(ctx.expression(2));

        if (!conditionType.equals(DoubleuTypes.BOOLEAN.toString())){
            errorHandler.addTypeConversionError(ctx, conditionType, DoubleuTypes.BOOLEAN.toString());
            return;
        }

        if (!trueBranchType.equals(falseBranchType) && !trueBranchType.equals(variable.type)){
            errorHandler.addTypeConversionError(ctx, trueBranchType, variable.type);
            return;
        }

        if (!trueBranchType.equals(variable.type)) {
            errorHandler.addTypeConversionError(ctx, trueBranchType, variable.type);
        }

        if (!falseBranchType.equals(variable.type)) {
            errorHandler.addTypeConversionError(ctx, trueBranchType, variable.type);
        }
    }

    @Override
    public void enterReturnConditionalExpression(WParser.ReturnConditionalExpressionContext ctx) {
        var lastFunction = getLastFunctionDeclared();

        String trueBranchType = expressionHandler.inferType(ctx.expression(0));
        String conditionType = expressionHandler.inferType(ctx.expression(1));
        String falseBranchType = expressionHandler.inferType(ctx.expression(2));

        if (!conditionType.equals(DoubleuTypes.BOOLEAN.toString())){
            errorHandler.addTypeConversionError(ctx, conditionType, DoubleuTypes.BOOLEAN.toString());
            return;
        }

        if (!trueBranchType.equals(falseBranchType) && !trueBranchType.equals(lastFunction.type)){
            errorHandler.addTypeConversionError(ctx, trueBranchType, lastFunction.type);
            return;
        }

        if (!trueBranchType.equals(lastFunction.type)) {
            errorHandler.addTypeConversionError(ctx, trueBranchType, lastFunction.type);
        }

        if (!falseBranchType.equals(lastFunction.type)) {
            errorHandler.addTypeConversionError(ctx, trueBranchType, lastFunction.type);
        }
    }

    @Override
    public void enterFunctionDeclaration(WParser.FunctionDeclarationContext ctx) {
        enterScope(Scopes.FUNCTION);

        var functionName = ctx.functionDeclarator().ID().getText();
        var returnType = ctx.functionTypeSpecifier().getText();

        if (identifiersDeclared.containsKey(functionName)) {
            var function = getFunctionAlreadyDeclaredByName(functionName);
            errorHandler.addDuplicatedFunctionError(ctx, function.getSignatureString());
            return;
        }

        dFunctions.put(dFunctions.size(), new DoubleuFunction(functionName, returnType));
        identifiersDeclared.put(functionName, new DoubleuVariable(returnType, functionName, scope));
    }

    @Override
    public void exitFunctionDeclaration(WParser.FunctionDeclarationContext ctx) {
        // remover as variáveis que foram declaradas dentro da função
        List<DoubleuVariable> variablesToRemove = new ArrayList<DoubleuVariable>();
        for (DoubleuVariable variable : identifiersDeclared.values()) {
            if (variable.scope == Scopes.FUNCTION)
                variablesToRemove.add(variable);
        }

        for (DoubleuVariable variable : variablesToRemove)
            identifiersDeclared.remove(variable.name, variable);

        enterScope(Scopes.MAIN);
    }

    @Override
    public void enterReturnStatement(WParser.ReturnStatementContext ctx) {
        if (!isOnFunctionScope()) {
            errorHandler.addReturnOutsideFunctionScopeError(ctx);
            return;
        }

        if (ctx.expression() != null) {
            var lastFunction = getLastFunctionDeclared();
            var functionReturnType = lastFunction.type;

            String expressionType = expressionHandler.inferType(ctx.expression());

            if (!expressionType.equals(functionReturnType)) {
                errorHandler.addTypeConversionError(ctx, expressionType, functionReturnType);
            }
        }
    }

    @Override
    public void enterJumpStatement(WParser.JumpStatementContext ctx) {
        if (!isOnWhileScope && !isOnDoWhileScope) {
            if (ctx.Break() != null) {
                errorHandler.addBreakOutsideLoopError(ctx);
                return;
            }

            if (ctx.Continue() != null) {
                errorHandler.addContinueOutsideLoopError(ctx);
                return;
            }
        }
    }

    @Override
    public void enterDoWhileLoop(WParser.DoWhileLoopContext ctx) {
        isOnDoWhileScope = true;
    }

    @Override
    public void exitDoWhileLoop(WParser.DoWhileLoopContext ctx) {
        isOnDoWhileScope = true;
    }

    @Override
    public void enterWhileLoop(WParser.WhileLoopContext ctx) {
        isOnWhileScope = true;
    }

    @Override
    public void exitWhileLoop(WParser.WhileLoopContext ctx) {
        isOnWhileScope = false;
    }

    @Override
    public void enterIfStatement(WParser.IfStatementContext ctx) {
        isOnIfStatement = true;
    }

    @Override
    public void exitIfStatement(WParser.IfStatementContext ctx) {
        isOnIfStatement = false;
    }

    @Override
    public void enterCondition(WParser.ConditionContext ctx) {
        var expressionType = expressionHandler.inferType(ctx.expression());
        if (!expressionType.equals("boolean"))
            errorHandler.addTypeConversionError(ctx, expressionType, DoubleuTypes.BOOLEAN.toString());
    }

    @Override
    public void enterArrayDeclaration(WParser.ArrayDeclarationContext ctx) {
        var arrayName = ctx.ID().getText();

        if (variableAlreadyExists(arrayName))
            errorHandler.addDuplicatedArrayError(ctx, arrayName);
    }

    @Override
    public void enterArrayLiteral(WParser.ArrayLiteralContext ctx) {
        var arrayType = ctx.getParent().getChild(2).getText();
        var arrayName = ctx.getParent().getChild(4).getText();

        boolean thereAreErrosOnArrayDeclaration = false;
        for (ExpressionContext expressionCtx : ctx.expression()) {
            var expressionType = expressionHandler.inferType(expressionCtx);
            if (!expressionType.equals(arrayType)) {
                errorHandler.addTypeConversionError(ctx, expressionType, arrayType);
                thereAreErrosOnArrayDeclaration = true;
            }
        }

        if (!thereAreErrosOnArrayDeclaration){
            identifiersDeclared.put(arrayName, new DoubleuVariable(arrayType, arrayName, scope));
        }
    }

    @Override
    public void enterObjectLiteralAssignment(WParser.ObjectLiteralAssignmentContext ctx) {
        String variableName = ctx.ID().getText();
        if (variableAlreadyExists(variableName))
            errorHandler.addDuplicatedObjectError(ctx, variableName);
    }

    @Override
    public void enterObjectLiteral(WParser.ObjectLiteralContext ctx) {
        String dObjectName = ctx.getParent().getChild(1).getText();
        DoubleuObject dObject = new DoubleuObject(dObjectName);

        for (PairContext pairCtx : ctx.pair()) {
            var propertyName = pairCtx.ID().getText();
            var propertyType = pairCtx.typeSpecifier().getText();
            var propertyValueType = LiteralHandler.inferType(pairCtx.literal());

            if (dObject.containsProperty(propertyName)) {
                errorHandler.addDuplicatedPropertyError(ctx, propertyName);
                return;
            } else if (!propertyType.equals(propertyValueType)) {
                errorHandler.addTypeConversionError(ctx, propertyValueType, propertyType);
                return;
            } else {
                dObject.addProperty(new DoubleuProperty(propertyType, propertyName));
            }
        }

        dObjects.put(dObjectName, dObject);
        identifiersDeclared.put(dObjectName, new DoubleuVariable("object", dObjectName, scope));
    }

    @Override
    public void enterPropertyAccess(WParser.PropertyAccessContext ctx) {
        var dObjectName = ctx.ID(0).getText();
        var propertyName = ctx.ID(1).getText();

        if (!identifiersDeclared.containsKey(dObjectName)) {
            errorHandler.addNotDeclaredObjectError(ctx, dObjectName);
        } else {
            var object = dObjects.get(dObjectName);
            if (!object.containsProperty(propertyName)) {
                errorHandler.addNotDeclaredPropertyError(ctx, propertyName);
            }
        }
    }

    @Override
    public void enterExpression(WParser.ExpressionContext ctx) {
        if (ctx.ID() != null) {
            var variableName = ctx.ID().getText();

            if (!identifiersDeclared.containsKey(variableName)) {
                errorHandler.addNotDeclaredVariableError(ctx, variableName);
            }
        }

        if (ctx.binaryOperator() != null) {
            var leftOperandType = expressionHandler.inferType(ctx.expression(0));
            var rightOperandType = expressionHandler.inferType(ctx.expression(1));
            var operator = ctx.binaryOperator().getText();

            if (ctx.expression(0).getChildCount() == 1) {
                if (!MathOperationsHandler.isMathOperationTypesCompatible(leftOperandType, rightOperandType,
                        operator)) {
                    errorHandler.addBinaryOperationError(ctx, leftOperandType, operator, rightOperandType);
                }
            }
        }

        if (ctx.unaryOperator() != null) {
            String operandType = expressionHandler.inferType(ctx.expression(0));
            String operator = ctx.unaryOperator().getText();

            if (!MathOperationsHandler.isUnaryOperatorApplicable(operandType, operator))
                errorHandler.addUnaryOperationError(ctx, operator);
        }
    }

    @Override
    public void enterFunctionCall(WParser.FunctionCallContext ctx) {
        var functionName = ctx.ID().getText();
        if (!identifiersDeclared.containsKey(functionName))
            errorHandler.addNotDeclaredFunctionError(ctx, functionName);
    }

    @Override
    public void enterArguments(WParser.ArgumentsContext ctx) {
        String functionName = ctx.getParent().getChild(0).getText();

        if (identifiersDeclared.containsKey(functionName)) {
            var function = getFunctionAlreadyDeclaredByName(functionName);

            var numberOfArgumentsProvided = ctx.expression().size();
            var numberOfFunctionParameters = function.parameters.size();
            if (numberOfArgumentsProvided != numberOfFunctionParameters) {
                var argumentsSignature = getArgumentsProvidedSignature(ctx);
                if (!argumentsSignature.isEmpty()) {
                    errorHandler.addInvalidArgumentsError(ctx, function.getSignatureString(), argumentsSignature);
                }
            } else {
                for (int i = 0; i < numberOfArgumentsProvided; i++) {
                    var argumentProvidedType = expressionHandler.inferType(ctx.expression(i));
                    var parameterType = function.parameters.get(i).type;

                    if (argumentProvidedType.equals("object")) {
                        var accessProperty = ctx.expression(i).getText();
                        var objectName = accessProperty.split("\\.")[0];
                        var objectProp = accessProperty.split("\\.")[1];

                        var object = dObjects.get(objectName);
                        if (object == null)
                            continue;

                        var property = object.getProperty(objectProp);
                        if (property == null)
                            continue;

                        argumentProvidedType = property.type;
                    }

                    if (!argumentProvidedType.equals(parameterType)) {
                        var argumentsSignature = getArgumentsProvidedSignature(ctx);

                        if (!argumentsSignature.isEmpty()) {
                            errorHandler.addInvalidArgumentsError(ctx, function.getSignatureString(), argumentsSignature);
                            break;
                        }
                    }
                }
            }
        }
    }

    private String getArgumentsProvidedSignature(WParser.ArgumentsContext ctx) {
        String signature = "";
        signature += "(";

        var childCounter = 0;
        var expressionCounter = 0;
        var commaCounter = 0;
        while (childCounter < ctx.getChildCount()) {
            var child = ctx.getChild(childCounter);
            if (child instanceof TerminalNode) {
                signature += child.getText();
                signature += " ";

                commaCounter++;
            } else {
                var expressionType = expressionHandler.inferType(ctx.expression(expressionCounter));

                if (expressionType.equals("object")) {
                    var accessProperty = ctx.expression(expressionCounter).getText();
                    var objectName = accessProperty.split("\\.")[0];
                    var objectProp = accessProperty.split("\\.")[1];

                    var object = dObjects.get(objectName);
                    if (object != null) {
                        var property = object.getProperty(objectProp);

                        if (property != null) {
                            signature += property.type + " ";
                            signature += child.getText();
                        }
                    } else {
                        return "";
                    }
                } else {
                    signature += expressionType + " ";
                    signature += child.getText();
                }

                expressionCounter++;
            }

            childCounter++;
        }

        signature += ")";
        return signature;
    }

    private DoubleuFunction getLastFunctionDeclared() {
        return dFunctions.get(dFunctions.size() - 1);
    }

    private DoubleuFunction getFunctionAlreadyDeclaredByName(String functionName) {
        for (DoubleuFunction function : dFunctions.values()) {
            if (function.name.equals(functionName))
                return function;
        }

        return null;
    }
}
