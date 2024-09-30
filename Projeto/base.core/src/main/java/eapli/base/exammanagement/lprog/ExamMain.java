package eapli.base.exammanagement.lprog;

import eapli.base.enrollmentmanagement.exception.ErrorInFileException;
import eapli.base.exammanagement.domain.Exam;
import eapli.base.exammanagement.exception.InvalidScoreException;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ExamMain {

    public static void main(String[] args) throws IOException {
        parseWithVisitor("files/exam.txt");
    }

    public static Exam parseWithVisitor(final String filePath) {
        try {
            FileInputStream fileInputStream = new FileInputStream(filePath);
            ExamLexer lexer = new ExamLexer(CharStreams.fromStream(fileInputStream));
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            ExamParser parser = new ExamParser(tokens);

            ParseTree tree = parser.exam();
            EvalVisitor visitor = new EvalVisitor();
            visitor.visit(tree);

            return visitor.getExam();
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException("The file doesn't exists!");
        } catch (InvalidScoreException e) {
            throw new IllegalArgumentException(e.getMessage());
        } catch (Exception e) {
            throw new ErrorInFileException("The exam does not follow the required format!.");
        }
    }
}
