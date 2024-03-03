// Generated from c:/Users/tlope/Downloads/transpiler/src/antlr/grammar/WParser.g4 by ANTLR 4.13.1
package antlr;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link WParser}.
 */
public interface WParserListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link WParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(WParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link WParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(WParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by {@link WParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement(WParser.StatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link WParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement(WParser.StatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link WParser#typeSpecifier}.
	 * @param ctx the parse tree
	 */
	void enterTypeSpecifier(WParser.TypeSpecifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link WParser#typeSpecifier}.
	 * @param ctx the parse tree
	 */
	void exitTypeSpecifier(WParser.TypeSpecifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link WParser#declaration}.
	 * @param ctx the parse tree
	 */
	void enterDeclaration(WParser.DeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link WParser#declaration}.
	 * @param ctx the parse tree
	 */
	void exitDeclaration(WParser.DeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link WParser#assignment}.
	 * @param ctx the parse tree
	 */
	void enterAssignment(WParser.AssignmentContext ctx);
	/**
	 * Exit a parse tree produced by {@link WParser#assignment}.
	 * @param ctx the parse tree
	 */
	void exitAssignment(WParser.AssignmentContext ctx);
	/**
	 * Enter a parse tree produced by {@link WParser#condition}.
	 * @param ctx the parse tree
	 */
	void enterCondition(WParser.ConditionContext ctx);
	/**
	 * Exit a parse tree produced by {@link WParser#condition}.
	 * @param ctx the parse tree
	 */
	void exitCondition(WParser.ConditionContext ctx);
	/**
	 * Enter a parse tree produced by {@link WParser#ifStatement}.
	 * @param ctx the parse tree
	 */
	void enterIfStatement(WParser.IfStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link WParser#ifStatement}.
	 * @param ctx the parse tree
	 */
	void exitIfStatement(WParser.IfStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link WParser#elseStatement}.
	 * @param ctx the parse tree
	 */
	void enterElseStatement(WParser.ElseStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link WParser#elseStatement}.
	 * @param ctx the parse tree
	 */
	void exitElseStatement(WParser.ElseStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link WParser#contextProgram}.
	 * @param ctx the parse tree
	 */
	void enterContextProgram(WParser.ContextProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link WParser#contextProgram}.
	 * @param ctx the parse tree
	 */
	void exitContextProgram(WParser.ContextProgramContext ctx);
	/**
	 * Enter a parse tree produced by {@link WParser#returnConditionalExpression}.
	 * @param ctx the parse tree
	 */
	void enterReturnConditionalExpression(WParser.ReturnConditionalExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link WParser#returnConditionalExpression}.
	 * @param ctx the parse tree
	 */
	void exitReturnConditionalExpression(WParser.ReturnConditionalExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link WParser#declarationConditionalExpression}.
	 * @param ctx the parse tree
	 */
	void enterDeclarationConditionalExpression(WParser.DeclarationConditionalExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link WParser#declarationConditionalExpression}.
	 * @param ctx the parse tree
	 */
	void exitDeclarationConditionalExpression(WParser.DeclarationConditionalExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link WParser#assignmentConditionalExpression}.
	 * @param ctx the parse tree
	 */
	void enterAssignmentConditionalExpression(WParser.AssignmentConditionalExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link WParser#assignmentConditionalExpression}.
	 * @param ctx the parse tree
	 */
	void exitAssignmentConditionalExpression(WParser.AssignmentConditionalExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link WParser#whileCondition}.
	 * @param ctx the parse tree
	 */
	void enterWhileCondition(WParser.WhileConditionContext ctx);
	/**
	 * Exit a parse tree produced by {@link WParser#whileCondition}.
	 * @param ctx the parse tree
	 */
	void exitWhileCondition(WParser.WhileConditionContext ctx);
	/**
	 * Enter a parse tree produced by {@link WParser#doWhileLoop}.
	 * @param ctx the parse tree
	 */
	void enterDoWhileLoop(WParser.DoWhileLoopContext ctx);
	/**
	 * Exit a parse tree produced by {@link WParser#doWhileLoop}.
	 * @param ctx the parse tree
	 */
	void exitDoWhileLoop(WParser.DoWhileLoopContext ctx);
	/**
	 * Enter a parse tree produced by {@link WParser#whileLoop}.
	 * @param ctx the parse tree
	 */
	void enterWhileLoop(WParser.WhileLoopContext ctx);
	/**
	 * Exit a parse tree produced by {@link WParser#whileLoop}.
	 * @param ctx the parse tree
	 */
	void exitWhileLoop(WParser.WhileLoopContext ctx);
	/**
	 * Enter a parse tree produced by {@link WParser#jumpStatement}.
	 * @param ctx the parse tree
	 */
	void enterJumpStatement(WParser.JumpStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link WParser#jumpStatement}.
	 * @param ctx the parse tree
	 */
	void exitJumpStatement(WParser.JumpStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link WParser#binaryOperator}.
	 * @param ctx the parse tree
	 */
	void enterBinaryOperator(WParser.BinaryOperatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link WParser#binaryOperator}.
	 * @param ctx the parse tree
	 */
	void exitBinaryOperator(WParser.BinaryOperatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link WParser#unaryOperator}.
	 * @param ctx the parse tree
	 */
	void enterUnaryOperator(WParser.UnaryOperatorContext ctx);
	/**
	 * Exit a parse tree produced by {@link WParser#unaryOperator}.
	 * @param ctx the parse tree
	 */
	void exitUnaryOperator(WParser.UnaryOperatorContext ctx);
	/**
	 * Enter a parse tree produced by {@link WParser#primaryExpression}.
	 * @param ctx the parse tree
	 */
	void enterPrimaryExpression(WParser.PrimaryExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link WParser#primaryExpression}.
	 * @param ctx the parse tree
	 */
	void exitPrimaryExpression(WParser.PrimaryExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link WParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterExpression(WParser.ExpressionContext ctx);
	/**
	 * Exit a parse tree produced by {@link WParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitExpression(WParser.ExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link WParser#objectLiteralAssignment}.
	 * @param ctx the parse tree
	 */
	void enterObjectLiteralAssignment(WParser.ObjectLiteralAssignmentContext ctx);
	/**
	 * Exit a parse tree produced by {@link WParser#objectLiteralAssignment}.
	 * @param ctx the parse tree
	 */
	void exitObjectLiteralAssignment(WParser.ObjectLiteralAssignmentContext ctx);
	/**
	 * Enter a parse tree produced by {@link WParser#objectLiteral}.
	 * @param ctx the parse tree
	 */
	void enterObjectLiteral(WParser.ObjectLiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link WParser#objectLiteral}.
	 * @param ctx the parse tree
	 */
	void exitObjectLiteral(WParser.ObjectLiteralContext ctx);
	/**
	 * Enter a parse tree produced by {@link WParser#pair}.
	 * @param ctx the parse tree
	 */
	void enterPair(WParser.PairContext ctx);
	/**
	 * Exit a parse tree produced by {@link WParser#pair}.
	 * @param ctx the parse tree
	 */
	void exitPair(WParser.PairContext ctx);
	/**
	 * Enter a parse tree produced by {@link WParser#propertyAccess}.
	 * @param ctx the parse tree
	 */
	void enterPropertyAccess(WParser.PropertyAccessContext ctx);
	/**
	 * Exit a parse tree produced by {@link WParser#propertyAccess}.
	 * @param ctx the parse tree
	 */
	void exitPropertyAccess(WParser.PropertyAccessContext ctx);
	/**
	 * Enter a parse tree produced by {@link WParser#arrayDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterArrayDeclaration(WParser.ArrayDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link WParser#arrayDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitArrayDeclaration(WParser.ArrayDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link WParser#arrayLiteral}.
	 * @param ctx the parse tree
	 */
	void enterArrayLiteral(WParser.ArrayLiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link WParser#arrayLiteral}.
	 * @param ctx the parse tree
	 */
	void exitArrayLiteral(WParser.ArrayLiteralContext ctx);
	/**
	 * Enter a parse tree produced by {@link WParser#arrayAccess}.
	 * @param ctx the parse tree
	 */
	void enterArrayAccess(WParser.ArrayAccessContext ctx);
	/**
	 * Exit a parse tree produced by {@link WParser#arrayAccess}.
	 * @param ctx the parse tree
	 */
	void exitArrayAccess(WParser.ArrayAccessContext ctx);
	/**
	 * Enter a parse tree produced by {@link WParser#functionDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterFunctionDeclaration(WParser.FunctionDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link WParser#functionDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitFunctionDeclaration(WParser.FunctionDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link WParser#functionTypeSpecifier}.
	 * @param ctx the parse tree
	 */
	void enterFunctionTypeSpecifier(WParser.FunctionTypeSpecifierContext ctx);
	/**
	 * Exit a parse tree produced by {@link WParser#functionTypeSpecifier}.
	 * @param ctx the parse tree
	 */
	void exitFunctionTypeSpecifier(WParser.FunctionTypeSpecifierContext ctx);
	/**
	 * Enter a parse tree produced by {@link WParser#functionDeclarator}.
	 * @param ctx the parse tree
	 */
	void enterFunctionDeclarator(WParser.FunctionDeclaratorContext ctx);
	/**
	 * Exit a parse tree produced by {@link WParser#functionDeclarator}.
	 * @param ctx the parse tree
	 */
	void exitFunctionDeclarator(WParser.FunctionDeclaratorContext ctx);
	/**
	 * Enter a parse tree produced by {@link WParser#argumentsDeclarator}.
	 * @param ctx the parse tree
	 */
	void enterArgumentsDeclarator(WParser.ArgumentsDeclaratorContext ctx);
	/**
	 * Exit a parse tree produced by {@link WParser#argumentsDeclarator}.
	 * @param ctx the parse tree
	 */
	void exitArgumentsDeclarator(WParser.ArgumentsDeclaratorContext ctx);
	/**
	 * Enter a parse tree produced by {@link WParser#args}.
	 * @param ctx the parse tree
	 */
	void enterArgs(WParser.ArgsContext ctx);
	/**
	 * Exit a parse tree produced by {@link WParser#args}.
	 * @param ctx the parse tree
	 */
	void exitArgs(WParser.ArgsContext ctx);
	/**
	 * Enter a parse tree produced by {@link WParser#returnStatement}.
	 * @param ctx the parse tree
	 */
	void enterReturnStatement(WParser.ReturnStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link WParser#returnStatement}.
	 * @param ctx the parse tree
	 */
	void exitReturnStatement(WParser.ReturnStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link WParser#functionCall}.
	 * @param ctx the parse tree
	 */
	void enterFunctionCall(WParser.FunctionCallContext ctx);
	/**
	 * Exit a parse tree produced by {@link WParser#functionCall}.
	 * @param ctx the parse tree
	 */
	void exitFunctionCall(WParser.FunctionCallContext ctx);
	/**
	 * Enter a parse tree produced by {@link WParser#arguments}.
	 * @param ctx the parse tree
	 */
	void enterArguments(WParser.ArgumentsContext ctx);
	/**
	 * Exit a parse tree produced by {@link WParser#arguments}.
	 * @param ctx the parse tree
	 */
	void exitArguments(WParser.ArgumentsContext ctx);
	/**
	 * Enter a parse tree produced by {@link WParser#inputStatement}.
	 * @param ctx the parse tree
	 */
	void enterInputStatement(WParser.InputStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link WParser#inputStatement}.
	 * @param ctx the parse tree
	 */
	void exitInputStatement(WParser.InputStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link WParser#outputStatement}.
	 * @param ctx the parse tree
	 */
	void enterOutputStatement(WParser.OutputStatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link WParser#outputStatement}.
	 * @param ctx the parse tree
	 */
	void exitOutputStatement(WParser.OutputStatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link WParser#literal}.
	 * @param ctx the parse tree
	 */
	void enterLiteral(WParser.LiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link WParser#literal}.
	 * @param ctx the parse tree
	 */
	void exitLiteral(WParser.LiteralContext ctx);
}