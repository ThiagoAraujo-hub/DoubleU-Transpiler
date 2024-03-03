import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

import antlr.WLexer;
import antlr.WParser;
import handler.FileHandler;
import languages.java.JavaTranslator;
import sdk.DoubleuListener;

public class Doubleu {
    public static void main(String[] args) throws Exception {
        CharStream stream = CharStreams.fromFileName("src/resources/code-file.txt");

        WLexer lexer = new WLexer(stream);
        CommonTokenStream tokens = new CommonTokenStream(lexer);

        WParser parser = new WParser(tokens);
        ParseTree tree = parser.program();

        if (parser.getNumberOfSyntaxErrors() == 0) {
            DoubleuListener listener = new DoubleuListener();

            ParseTreeWalker walker = new ParseTreeWalker();
		    walker.walk(listener, tree);

            if (!listener.errorHandler.isEmpty()){
                listener.errorHandler.printErrors();
                return;
            }

            System.out.println("Semantic analysis completed.");

            JavaTranslator javaTranslator = new JavaTranslator();
            walker.walk(javaTranslator, tree);

            System.out.println("Transpiled succesfully.");

            FileHandler.createFile();
            FileHandler.writeToFile(javaTranslator.mainCode.toString());
        }
    }
}