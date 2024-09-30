package eapli.base.takenexammanagement.lprog;

import eapli.base.enrollmentmanagement.exception.ErrorInFileException;
import eapli.base.exammanagement.domain.Exam;
import eapli.base.exammanagement.lprog.ExamMain;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ResolutionMain {

    public static void main(String[] args) throws IOException {
        Exam exam = ExamMain.parseWithVisitor("files/exam.txt");
        System.out.println(parseWithVisitor("files/correct_resolution.txt", exam));
        System.out.println(parseWithVisitor("files/incorrect_resolution.txt", exam));
        System.out.println(parseWithVisitor("files/incomplete_resolution.txt", exam));
    }

    public static float parseWithVisitor(final String resolutionFilePath, Exam exam) {
        try {
            FileInputStream fileInputStream = new FileInputStream(resolutionFilePath);
            ResolutionLexer lexer = new ResolutionLexer(CharStreams.fromStream(fileInputStream));
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            ResolutionParser parser = new ResolutionParser(tokens);
            EvalVisitor visitor = new EvalVisitor();

            ParseTree tree = parser.resolution();
            visitor.registerExam(exam);
            visitor.visit(tree);

            return visitor.getGrade();
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException("The file doesn't exists!");
        } catch (RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        } catch (Exception e) {
            throw new ErrorInFileException("The resolution does not follow the required format!.");
        }
    }
}
