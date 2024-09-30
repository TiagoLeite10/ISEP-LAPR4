package eapli.base.formativeexammanagement.lprog;

import eapli.base.enrollmentmanagement.exception.ErrorInFileException;
import eapli.base.formativeexammanagement.domain.FormativeExam;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FormativeExamMain {

    public static void main(String[] args) throws IOException {
        parseWithVisitor("files/formative_exam.txt");
    }

    public static FormativeExam parseWithVisitor(final String filePath) {
        try {
            FileInputStream fileInputStream = new FileInputStream(filePath);
            FormativeExamLexer lexer = new FormativeExamLexer(CharStreams.fromStream(fileInputStream));
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            FormativeExamParser parser = new FormativeExamParser(tokens);

            ParseTree tree = parser.formativeExam();
            EvalVisitor visitor = new EvalVisitor();
            visitor.visit(tree);

            return visitor.formativeExam();
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException("The file doesn't exists!");
        } catch (Exception e) {
            throw new ErrorInFileException("The formative exam does not follow the required format!.");
        }
    }
}
