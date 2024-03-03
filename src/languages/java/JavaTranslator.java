package languages.java;

import java.util.ArrayList;
import java.util.List;

import org.antlr.v4.runtime.tree.TerminalNode;

import antlr.WParser.ArgumentsContext;
import antlr.WParser.ArrayDeclarationContext;
import antlr.WParser.ArrayLiteralContext;
import antlr.WParser.AssignmentConditionalExpressionContext;
import antlr.WParser.AssignmentContext;
import antlr.WParser.DeclarationConditionalExpressionContext;
import antlr.WParser.DeclarationContext;
import antlr.WParser.DoWhileLoopContext;
import antlr.WParser.ExpressionContext;
import antlr.WParser.FunctionCallContext;
import antlr.WParser.FunctionDeclarationContext;
import antlr.WParser.IfStatementContext;
import antlr.WParser.InputStatementContext;
import antlr.WParser.JumpStatementContext;
import antlr.WParser.ObjectLiteralAssignmentContext;
import antlr.WParser.ObjectLiteralContext;
import antlr.WParser.OutputStatementContext;
import antlr.WParser.PairContext;
import antlr.WParser.PrimaryExpressionContext;
import antlr.WParser.ProgramContext;
import antlr.WParser.ReturnConditionalExpressionContext;
import antlr.WParser.ReturnStatementContext;
import languages.java.enumerators.JavaImports;
import languages.java.enumerators.JavaOperators;
import languages.java.enumerators.JavaTypes;
import languages.java.objects.JavaScanner;
import languages.java.objects.JavaSystem;
import sdk.enums.DoubleuLogicalOperators;
import sdk.enums.DoubleuTypes;
import sdk.enums.Scopes;
import antlr.WParser;
import antlr.WParserBaseListener;

public class JavaTranslator extends WParserBaseListener {
    public StringBuilder mainCode = new StringBuilder();

    // Controle de funções
    private StringBuilder methodCode = new StringBuilder();
    private List<StringBuilder> methodCodes = new ArrayList<StringBuilder>();

    // Controle de objetos
    private StringBuilder classCode = new StringBuilder();
    private List<StringBuilder> classCodes = new ArrayList<StringBuilder>();

    // Controle de escopo
    private boolean isOnFunctionScope = false;
    private boolean isOnMainScope = false;
    private boolean isOnObjectScope = false;
    private boolean isOnTernaryExpression = false;
    private boolean isOnArgumentsDeclaration = false;

    // Controle de imports
    private boolean alreadyHasImportScanner = false;

    // Controle de variáveis únicas por escopo
    private boolean alreadyHaveScannerOnMainScope = false;
    private boolean alreadyHaveScannerOnFunctionScope = false;

    private void resetClassCode() {
        classCode = new StringBuilder();
    }

    private void resetMethodCode() {
        methodCode = new StringBuilder();
    }

    private void enterScope(Scopes scope) {
        if (scope == Scopes.FUNCTION) {
            isOnMainScope = false;
            isOnFunctionScope = true;
        }

        if (scope == Scopes.MAIN) {
            isOnMainScope = true;
            isOnFunctionScope = false;
        }
    }

    private String translateType(String type) {
        if (type.equals(DoubleuTypes.INTEGER.toString())) {
            return JavaTypes.INT.toString();
        } else if (type.equals(DoubleuTypes.STRING.toString())) {
            return JavaTypes.STRING.toString();
        } else if (type.equals(DoubleuTypes.DECIMAL.toString())) {
            return JavaTypes.FLOAT.toString();
        } else if (type.equals(DoubleuTypes.BOOLEAN.toString())) {
            return JavaTypes.BOOLEAN.toString();
        } else {
            return type;
        }
    }

    private void addTextOnIndex(int index, String text) {
        if (isOnFunctionScope) {
            methodCode.insert(index, "\n" + text);
        }

        if (isOnMainScope) {
            mainCode.insert(index, "\n" + text);
        }
    }

    private void addText(String text) {
        if (isOnFunctionScope) {
            methodCode.append(text);
        }

        if (isOnMainScope) {
            var teste = mainCode.toString();
            mainCode.append(text);
        }
    }

    private void addTextAndWhiteSpace(String text) {
        if (isOnFunctionScope) {
            methodCode.append(text + " ");
        }

        if (isOnMainScope) {
            var teste = mainCode.toString();
            mainCode.append(text + " ");
        }
    }

    private void addNewLine() {
        if (isOnFunctionScope) {
            methodCode.append("\n");
        }

        if (isOnMainScope) {
            var teste = mainCode.toString();
            mainCode.append("\n");
        }
    }

    private void addSemiColon() {
        if (isOnFunctionScope) {
            methodCode.append(";");
        }

        if (isOnMainScope) {
            mainCode.append(";");
        }
    }

    private void addImport(JavaImports jimport) {
        mainCode.insert(19, "\nimport " + jimport.toString() + ";\n");
    }

    private void addTernary(String condition, String trueText, String falseText) {
        addTextAndWhiteSpace(condition);
        addTextAndWhiteSpace("?");
        addTextAndWhiteSpace(trueText);
        addTextAndWhiteSpace(":");
        addText(falseText);
    }

    @Override
    public void enterProgram(ProgramContext ctx) {
        enterScope(Scopes.MAIN);
        addText("""
                package resources;
                public class Program {
                    public static void main(String[] args) { """);
        addNewLine();
    }

    @Override
    public void exitProgram(ProgramContext ctx) {
        addText("}");
        addNewLine();

        for (StringBuilder methodCode : methodCodes) {
            addText(methodCode.toString());
            addNewLine();
        }

        for (StringBuilder classCode : classCodes) {
            addText(classCode.toString());
            addNewLine();
        }

        addText("}");
    }

    @Override
    public void enterDeclaration(DeclarationContext ctx) {
        var translatedType = translateType(ctx.typeSpecifier().getText());
        var variableName = ctx.ID().getText();

        if (ctx.Assign() != null) {
            addTextAndWhiteSpace(translatedType);
            addTextAndWhiteSpace(variableName);
            addTextAndWhiteSpace(ctx.Assign().getText());

        } else {
            addTextAndWhiteSpace(translatedType);
            addText(variableName);
        }
    }

    @Override
    public void exitDeclaration(DeclarationContext ctx) {
        if (!isOnArgumentsDeclaration) {
            addSemiColon();
            addNewLine();
        }
    }

    @Override
    public void enterAssignment(AssignmentContext ctx) {
        var variableName = ctx.ID().getText();

        addTextAndWhiteSpace(variableName);
        addTextAndWhiteSpace(ctx.Assign().getText());

        if (ctx.expression() != null) {
            addText(getExpressionValue(ctx.expression()));
        }
    }

    @Override
    public void exitAssignment(AssignmentContext ctx) {
        addSemiColon();
        addNewLine();
    }

    @Override
    public void enterIfStatement(IfStatementContext ctx) {
        addTextAndWhiteSpace(ctx.If().getText());
    }

    @Override
    public void exitIfStatement(IfStatementContext ctx) {
    }

    @Override
    public void enterCondition(WParser.ConditionContext ctx) {
        addText("(");
    }

    @Override
    public void exitCondition(WParser.ConditionContext ctx) {
        addTextAndWhiteSpace(")");
    }

    @Override
    public void enterElseStatement(WParser.ElseStatementContext ctx) {
        addTextAndWhiteSpace(ctx.Else().getText());
    }

    @Override
    public void exitElseStatement(WParser.ElseStatementContext ctx) {
    }

    @Override
    public void enterContextProgram(WParser.ContextProgramContext ctx) {
        if (isOnFunctionScope) {
            methodCode.append("{");
            methodCode.append("\n");
        } else {
            addText("{");
            addNewLine();
        }
    }

    @Override
    public void exitContextProgram(WParser.ContextProgramContext ctx) {
        if (isOnFunctionScope) {
            methodCode.append("}");
            methodCode.append("\n");
        } else {
            addText("}");
            addNewLine();
        }
    }

    @Override
    public void enterReturnConditionalExpression(ReturnConditionalExpressionContext ctx) {
        isOnTernaryExpression = true;

        var trueBranchText = getExpressionValue(ctx.expression(0));
        var conditionalBranchText = getExpressionValue(ctx.expression(1));
        var falseBranchText = getExpressionValue(ctx.expression(2));

        addTernary(conditionalBranchText, trueBranchText, falseBranchText);
    }

    @Override
    public void exitReturnConditionalExpression(WParser.ReturnConditionalExpressionContext ctx) {
        isOnTernaryExpression = false;
    }

    @Override
    public void enterDeclarationConditionalExpression(DeclarationConditionalExpressionContext ctx) {
        isOnTernaryExpression = true;

        var trueBranchText = getExpressionValue(ctx.expression(0));
        var conditionalBranchText = getExpressionValue(ctx.expression(1));
        var falseBranchText = getExpressionValue(ctx.expression(2));

        addTernary(conditionalBranchText, trueBranchText, falseBranchText);
    }

    @Override
    public void exitDeclarationConditionalExpression(WParser.DeclarationConditionalExpressionContext ctx) {
        isOnTernaryExpression = false;
    }

    @Override
    public void enterAssignmentConditionalExpression(AssignmentConditionalExpressionContext ctx) {
        isOnTernaryExpression = true;

        var trueBranchText = getExpressionValue(ctx.expression(0));
        var conditionalBranchText = getExpressionValue(ctx.expression(1));
        var falseBranchText = getExpressionValue(ctx.expression(2));

        addTernary(conditionalBranchText, trueBranchText, falseBranchText);
    }

    @Override
    public void exitAssignmentConditionalExpression(WParser.AssignmentConditionalExpressionContext ctx) {
        isOnTernaryExpression = false;
    }

    @Override
    public void enterExpression(WParser.ExpressionContext ctx) {
        if (ctx.ID() != null && !isOnTernaryExpression) {
            addText(ctx.ID().getText());
        }
    }

    @Override
    public void exitExpression(WParser.ExpressionContext ctx) {

    }

    @Override
    public void enterBinaryOperator(WParser.BinaryOperatorContext ctx) {
        if (!isOnTernaryExpression) {
            var operator = translateOperator(ctx.getChild(0).getText());
            addText(operator);
        }
    }

    @Override
    public void enterUnaryOperator(WParser.UnaryOperatorContext ctx) {
        if (!isOnTernaryExpression) {
            var operator = ctx.getChild(0).getText();
            String text = operator.equals("not") ? "!" : operator;

            addText(text);
        }
    }

    private String translateOperator(String operator) {
        if (operator.equals(DoubleuLogicalOperators.EQUAL.toString())) {
            return JavaOperators.EQUAL.toString();
        } else if (operator.equals(DoubleuLogicalOperators.DIFFERENT.toString())) {
            return JavaOperators.DIFFERENT.toString();
        } else if (operator.equals(DoubleuLogicalOperators.AND.toString())) {
            return JavaOperators.AND.toString();
        } else if (operator.equals(DoubleuLogicalOperators.OR.toString())) {
            return JavaOperators.OR.toString();
        } else {
            return operator;
        }
    }

    public String getExpressionValue(ExpressionContext ctx) {
        if (ctx.ID() != null) {
            return ctx.ID().getText();
        } else if (ctx.literal() != null) {
            return ctx.literal().getText();
        } else if (ctx.functionCall() != null) {
            var functionName = ctx.functionCall().ID().getText();

            if (ctx.functionCall().arguments() != null) {
                var arguments = ctx.functionCall().arguments();
                var numberOfArgs = arguments.getChildCount();

                if (numberOfArgs == 1) {
                    var argument = getExpressionValue(arguments.expression(0));
                    return functionName + "(" + argument + ")";
                } else {
                    String stringArgs = "";
                    for (int i = 0; i < numberOfArgs; i++) {
                        stringArgs += getExpressionValue(arguments.expression(i));

                        if (i != numberOfArgs - 1) {
                            stringArgs += ", ";
                        }
                    }

                    return functionName + "(" + stringArgs + ")";
                }
            }

            return functionName + "()";
        } else if (ctx.binaryOperator() != null) {
            String leftExpr = getExpressionValue(ctx.expression(0));
            String rightExpr = getExpressionValue(ctx.expression(1));
            String operator = translateOperator(ctx.binaryOperator().getText());

            return leftExpr + " " + operator + " " + rightExpr;
        } else if (ctx.unaryOperator() != null) {
            String operator = ctx.unaryOperator().getText();
            String operand = getExpressionValue(ctx.expression(0));

            return operator + operand;
        } else if (ctx.LeftParen() != null && ctx.RightParen() != null) {
            // Trate a expressão dentro dos parênteses
            return "( " + getExpressionValue(ctx.expression(0)) + " )";
        } else {
            return "";
        }
    }

    @Override
    public void enterDoWhileLoop(DoWhileLoopContext ctx) {
        addTextAndWhiteSpace("do");
    }

    @Override
    public void exitDoWhileLoop(DoWhileLoopContext ctx) {
        addSemiColon();
        addNewLine();
    }

    @Override
    public void enterWhileCondition(WParser.WhileConditionContext ctx) {
        addTextAndWhiteSpace("while");
        addText("(");
    }

    @Override
    public void exitWhileCondition(WParser.WhileConditionContext ctx) {
        addTextAndWhiteSpace(")");
    }

    @Override
    public void enterJumpStatement(JumpStatementContext ctx) {
        if (ctx.Break() != null) {
            addText("break");
        } else if (ctx.Continue() != null) {
            addText("continue");
        }
    }

    @Override
    public void exitJumpStatement(JumpStatementContext ctx) {
        addSemiColon();
        addNewLine();
    }

    @Override
    public void enterPrimaryExpression(PrimaryExpressionContext ctx) {
    }

    @Override
    public void exitPrimaryExpression(PrimaryExpressionContext ctx) {
        addSemiColon();
        addNewLine();
    }

    @Override
    public void enterObjectLiteralAssignment(ObjectLiteralAssignmentContext ctx) {
        isOnObjectScope = true;

        classCode.append("class ");
        classCode.append(ctx.ID().getText() + " ");
    }

    @Override
    public void exitObjectLiteralAssignment(ObjectLiteralAssignmentContext ctx) {
        var newClassCode = new StringBuilder(classCode.toString());
        classCodes.add(newClassCode);

        resetClassCode();
        isOnObjectScope = false;
    }

    @Override
    public void enterObjectLiteral(ObjectLiteralContext ctx) {
        classCode.append("{\n");
    }

    @Override
    public void exitObjectLiteral(ObjectLiteralContext ctx) {
        classCode.append("}");
    }

    @Override
    public void enterPair(PairContext ctx) {
        classCode.append("public static ");
        classCode.append(translateType(ctx.typeSpecifier().getText()) + " ");
        classCode.append(ctx.ID().getText() + " ");
        classCode.append("= ");
        classCode.append(ctx.literal().getText());
    }

    @Override
    public void exitPair(PairContext ctx) {
        classCode.append(";");
        classCode.append("\n");
    }

    @Override
    public void enterPropertyAccess(WParser.PropertyAccessContext ctx) {
        addText(ctx.ID(0).getText());
        addText(".");
        addText(ctx.ID(1).getText());
    }

    @Override
    public void exitPropertyAccess(WParser.PropertyAccessContext ctx) {
    }

    @Override
    public void enterArrayDeclaration(ArrayDeclarationContext ctx) {
        addText(translateType(ctx.typeSpecifier().getText()));
        addTextAndWhiteSpace("[]");

        addTextAndWhiteSpace(ctx.ID().getText());
        addTextAndWhiteSpace("=");
    }

    @Override
    public void exitArrayDeclaration(ArrayDeclarationContext ctx) {
        addNewLine();
    }

    @Override
    public void enterArrayLiteral(ArrayLiteralContext ctx) {
        addTextAndWhiteSpace("{");
    }

    @Override
    public void exitArrayLiteral(ArrayLiteralContext ctx) {
        addText("}");
        addSemiColon();
    }

    @Override
    public void enterArrayAccess(WParser.ArrayAccessContext ctx) {
        addText(ctx.ID().getText());
        addText("[");
        addText(ctx.INT().getText());
        addText("]");
    }

    @Override
    public void exitArrayAccess(WParser.ArrayAccessContext ctx) {
    }

    @Override
    public void enterFunctionDeclaration(FunctionDeclarationContext ctx) {
        enterScope(Scopes.FUNCTION);

        methodCode.append("public static ");
    }

    @Override
    public void exitFunctionDeclaration(FunctionDeclarationContext ctx) {
        enterScope(Scopes.MAIN);
        alreadyHaveScannerOnFunctionScope = false;

        var newMethodCode = new StringBuilder(methodCode.toString());
        methodCodes.add(newMethodCode);
        resetMethodCode();
    }

    @Override
    public void enterFunctionTypeSpecifier(WParser.FunctionTypeSpecifierContext ctx) {
        if (ctx.typeSpecifier() != null) {
            methodCode.append(translateType(ctx.typeSpecifier().getText()) + " ");
        } else {
            methodCode.append("void ");
        }
    }

    @Override
    public void enterFunctionDeclarator(WParser.FunctionDeclaratorContext ctx) {
        methodCode.append(ctx.ID().getText());
    }

    @Override
    public void enterArgumentsDeclarator(WParser.ArgumentsDeclaratorContext ctx) {
        isOnArgumentsDeclaration = true;
        methodCode.append("(");
    }

    @Override
    public void exitArgumentsDeclarator(WParser.ArgumentsDeclaratorContext ctx) {
        methodCode.append(")");
        isOnArgumentsDeclaration = false;
    }

    @Override
    public void enterArgs(WParser.ArgsContext ctx) {
    }

    @Override
    public void exitArgs(WParser.ArgsContext ctx) {
    }

    @Override
    public void enterReturnStatement(ReturnStatementContext ctx) {
        addTextAndWhiteSpace("return");
    }

    @Override
    public void exitReturnStatement(ReturnStatementContext ctx) {
    }

    @Override
    public void enterFunctionCall(FunctionCallContext ctx) {
        if (!isOnTernaryExpression) {
            addText(ctx.ID().getText());
            addText("(");
        }
    }

    @Override
    public void exitFunctionCall(FunctionCallContext ctx) {
        addText(")");
    }

    @Override
    public void enterInputStatement(InputStatementContext ctx) {
        if (!alreadyHasImportScanner) {
            addImport(JavaImports.SCANNER);
            alreadyHasImportScanner = true;
        }

        if (isOnFunctionScope) {
            if (!alreadyHaveScannerOnFunctionScope) {
                var scannerDeclaration = JavaScanner.type + " " + JavaScanner.name + " = new " + JavaScanner.type + "("
                        + JavaSystem.in + ");";
                addTextOnIndex(113, scannerDeclaration);
                alreadyHaveScannerOnFunctionScope = true;
            }

            var variableType = ctx.parent.getChild(0).getText();
            if (variableType.equals(DoubleuTypes.INTEGER.toString())) {
                addText("sc.nextInt()");
            } else if (variableType.equals(DoubleuTypes.DECIMAL.toString())) {
                addText("sc.nextFloat()");
            } else if (variableType.equals(DoubleuTypes.BOOLEAN.toString())) {
                addText("sc.nextBoolean()");
            } else {
                addText("sc.next()");
            }
        }

        if (isOnMainScope) {
            if (!alreadyHaveScannerOnMainScope) {
                var scannerDeclaration = JavaScanner.type + " " + JavaScanner.name + " = new " + JavaScanner.type + "("
                        + JavaSystem.in + ");";
                addTextOnIndex(113, scannerDeclaration);
                alreadyHaveScannerOnMainScope = true;
            }

            var variableType = ctx.parent.getChild(0).getText();
            if (variableType.equals(DoubleuTypes.INTEGER.toString())) {
                addText("sc.nextInt()");
            } else if (variableType.equals(DoubleuTypes.DECIMAL.toString())) {
                addText("sc.nextFloat()");
            } else if (variableType.equals(DoubleuTypes.BOOLEAN.toString())) {
                addText("sc.nextBoolean()");
            } else {
                addText("sc.next()");
            }
        }
    }

    @Override
    public void exitInputStatement(InputStatementContext ctx) {
    }

    @Override
    public void enterOutputStatement(OutputStatementContext ctx) {
        addText("System.out.println");
        addText("(");

        if (ctx.ID() != null) {
            addText(ctx.ID().getText());
        } else if (ctx.STRING() != null) {
            addText(ctx.STRING().getText());
        }

        addText(")");
    }

    @Override
    public void exitOutputStatement(OutputStatementContext ctx) {
        addSemiColon();
        addNewLine();
    }

    @Override
    public void enterLiteral(WParser.LiteralContext ctx) {
        if (!isOnObjectScope && !isOnTernaryExpression) {
            addText(ctx.getChild(0).getText());
        }
    }

    @Override
    public void exitLiteral(WParser.LiteralContext ctx) {

    }

    @Override
    public void visitTerminal(TerminalNode node) {
        var terminalText = node.getText();
        if (terminalText.equals(",") && !isOnObjectScope) {
            addTextAndWhiteSpace(",");
        }
    }
}