package eapli.base.questionmanagement.lprog;

import eapli.base.enrollmentmanagement.exception.ErrorInFileException;
import eapli.base.questionmanagement.domain.Question;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ExamQuestionMain {

    public static void main(String[] args) throws IOException {
        parseWithVisitor("files/exam_question.txt");
    }

    public static Iterable<Question> parseWithVisitor(final String pathToFile) {
        try {
            FileInputStream fileInputStream = new FileInputStream(pathToFile);
            ExamQuestionLexer lexer = new ExamQuestionLexer(CharStreams.fromStream(fileInputStream));
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            ExamQuestionParser parser = new ExamQuestionParser(tokens);

            ParseTree tree = parser.examQuestion();
            EvalVisitor visitor = new EvalVisitor();
            visitor.visit(tree);

            return visitor.questions();
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException("The file doesn't exists!");
        } catch (Exception e) {
            throw new ErrorInFileException("The questions does not follow the required format!.");
        }
    }
}
