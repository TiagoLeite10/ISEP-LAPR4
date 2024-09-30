package eapli.base.takenexammanagement.domain;

import eapli.base.BuilderHelper;
import eapli.base.clientusermanagement.domain.BirthDate;
import eapli.base.clientusermanagement.domain.Student;
import eapli.base.clientusermanagement.domain.TaxPayerNumber;
import eapli.base.exammanagement.domain.*;
import eapli.base.questionmanagement.domain.Answer;
import eapli.base.questionmanagement.domain.Question;
import eapli.base.questionmanagement.domain.QuestionScore;
import eapli.base.questionmanagement.domain.ShortAnswer;
import eapli.base.usermanagement.domain.BaseRoles;
import eapli.base.usermanagement.domain.UserBuilderHelper;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;


/**
 * Classe para realizar testes à classe TakenExamBuilder.
 */
public class TakenExamBuilderTest {

    //Nota do exame
    private final Float GRADE = 20F;

    //Variaveis para a criação do exame
    private final String TITLE = "Exame sobre jogadores";

    private final Calendar OPENING_DATE_TIME = BuilderHelper.buildDateTime(3000, 7, 26, 0, 0);

    private final Calendar CLOSING_DATE_TIME = BuilderHelper.buildDateTime(3000, 7, 29, 23, 59);

    private final Header HEADER = new HeaderBuilder().withDescription("Primeiro exame sobre jogadores").withTypeNote("on-submission").
            withTypeFeedback("after-closing").build();

    private final ExamSection SECTION = buildExamSection();

    //Variaveis para a criação do aluno
    private final SystemUser SYSTEM_USER = UserBuilderHelper.builder().with("maria", "Password1",
            "Maria", "Rocha", "maria@gmail.com").withRoles(BaseRoles.STUDENT).build();

    private final TaxPayerNumber TAX_PAYER_NUMBER = TaxPayerNumber.valueOf("254588182");

    private final BirthDate BIRTH_DATE = BirthDate.valueOf(BuilderHelper.buildDate(2005, Calendar.JANUARY, 25));

    /**
     * Método para contruir uma secção de um exame.
     *
     * @return A secção do exame construída.
     */
    private ExamSection buildExamSection() {
        List<Question> questions = new ArrayList<>();
        questions.add(new ShortAnswer("Em que ano nasceu Cristiano Ronaldo?", QuestionScore.valueOf(20F),
                new Answer("1985", "Cristiano Ronaldo nasceu em 1985 na Madeira")));

        return new ExamSection(SectionDescription.valueOf("Jogadores de outra galáxia"), questions);
    }

    /**
     * Método para contruir um exame.
     *
     * @return Um exame contruido.
     */
    private Exam buildExam() {
        return new ExamBuilder().with(TITLE, OPENING_DATE_TIME, CLOSING_DATE_TIME, HEADER).withSection(SECTION).build();
    }

    /**
     * Método para contruir um aluno.
     *
     * @return Um aluno construído.
     */
    private Student buildStudent() {
        return new Student(SYSTEM_USER, TAX_PAYER_NUMBER, BIRTH_DATE);
    }

    /**
     * Teste para garantir que é possível construir um exame feito com a nota, exame e aluno.
     */
    @Test
    public void ensureCanBuildTakenExamWithStudentGradeAndExam() {
        final TakenExam subject = new TakenExamBuilder().with(buildStudent(), GRADE, buildExam()).build();
        assertNotNull(subject);
    }

    /**
     * Teste para garantir que não é possível construir um exame formativo com o título nulo.
     */
    @Test
    public void ensureCannotBuildTakenExamWithNullStudent() {
        assertThrows(IllegalArgumentException.class, () -> new TakenExamBuilder().withStudent(null).withGrade(GRADE).withExam(buildExam()).build());
    }

    /**
     * Teste para garantir que não é possível construir um exame formativo sem título.
     */
    @Test
    public void ensureCannotBuildTakenExamWithoutStudent() {
        assertThrows(IllegalArgumentException.class, () -> new TakenExamBuilder().withGrade(GRADE).withExam(buildExam()).build());
    }

    /**
     * Teste para garantir que não é possível construir um exame formativo com a data de abertura nula.
     */
    @Test
    public void ensureCannotBuildTakenExamWithNullGrade() {
        assertThrows(IllegalArgumentException.class, () -> new TakenExamBuilder().withStudent(buildStudent()).withGrade(null).withExam(buildExam()).build());
    }

    /**
     * Teste para garantir que não é possível construir um exame formativo com a data de fecho nula.
     */
    @Test
    public void ensureCannotBuildTakenExamWithoutGrade() {
        assertThrows(IllegalArgumentException.class, () -> new TakenExamBuilder().withStudent(buildStudent()).withExam(buildExam()).build());
    }

    /**
     * Teste para garantir que não é possível construir um exame formativo com o cabeçalho nulo.
     */
    @Test
    public void ensureCannotBuildTakenExamWithNullExam() {
        assertThrows(IllegalArgumentException.class, () -> new TakenExamBuilder().withStudent(buildStudent()).withGrade(GRADE).withExam(null).build());
    }

    /**
     * Teste para garantir que não é possível construir um exame formativo sem cabeçalho.
     */
    @Test
    public void ensureCannotBuildTakenExamWithoutExam() {
        assertThrows(IllegalArgumentException.class, () -> new TakenExamBuilder().withStudent(buildStudent()).withGrade(GRADE).build());
    }

}
