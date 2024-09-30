package eapli.base.takenexammanagement.lprog;

import eapli.base.exammanagement.domain.Exam;
import eapli.base.questionmanagement.domain.Matching;
import eapli.base.questionmanagement.domain.Question;
import eapli.base.exammanagement.domain.ExamSection;

import java.util.*;

public class EvalVisitor extends ResolutionBaseVisitor<Object> {

    int numberAnswers = 0;

    private Exam exam;

    private List<String> answers = new ArrayList<>();

    public void registerExam(Exam examSolution) {
        exam = examSolution;
    }

    public float getGrade() {
        List<ExamSection> sections = exam.sections();
        int counter = 0, numberQuestions = 0;
        float grade = 0;

        //Calcula o numero de perguntas
        for (int i = 0; i < sections.size(); i++) {
            numberQuestions += sections.get(i).questions().size();
        }

        int questionDiffer = numberQuestions - numberAnswers;

        //Verifica se existe o mesmo numero de respostas que perguntas
        if (questionDiffer == 0) {
            //Percorre as secções
            for (int i = 0; i < sections.size(); i++) {
                List<Question> questions = sections.get(i).questions();
                //Percorre as perguntas
                for (int j = 0; j < questions.size(); j++) {
                    Question question = questions.get(j);
                    //Verifica se a pergunta é do tipo matching
                    if (question instanceof Matching) {
                        //Cria uma lista para as perguntas corretas
                        List<String> correctAnswersList = new ArrayList<>(List.of(question.correctAnswer().split(" ")));
                        //Ordena a lista das perguntas corretas
                        Collections.sort(correctAnswersList);

                        //Cria uma lista para as respostas corretas
                        List<String> answersGivenList = new ArrayList<>(List.of(answers.get(counter).split(" ")));
                        //Ordena a lista das respostas corretas
                        Collections.sort(answersGivenList);

                        //Calcula a nota de um par
                        float individualAnswerScore = question.score() / correctAnswersList.size();

                        //Verifica se o numero de respostas do aluno é adequado
                        int answersDiffer = correctAnswersList.size() - answersGivenList.size();
                        if (answersDiffer != 0) {
                            throw new IllegalStateException((answersDiffer > 0 ? "Insufficient" : "Too many") + " answers in a matching question with the title \"" + question.text() + "\"");
                        }

                        //Validar a numeração da ordem das respostas do utilizador às matching questions
                        for (int k = 0; k < answersGivenList.size(); k++) {
                            String tmp = answersGivenList.get(k);
                            tmp =  tmp.split("\\)")[0];
                            int tmpInteger = Integer.parseInt(tmp);
                            if ((k+1) != tmpInteger)
                                throw new IllegalStateException("Bad numbers representation on the answer of the question \"" + question.text() + "\"");
                        }

                        //Se os pares estiveram corretos aumenta a nota
                        for (int k = 0; k < correctAnswersList.size(); k++) {
                            if (answersGivenList.get(k).compareTo(correctAnswersList.get(k)) == 0) {
                                grade += individualAnswerScore;
                            }
                        }

                    //Se as respostas estiverem corretas aumenta a nota
                    } else if (question.isCorrectAnswer(answers.get(counter))) {
                        grade += question.score();
                    }
                    counter++;
                }
            }
        } else {
            throw new IllegalStateException((questionDiffer > 0 ? "Insufficient" : "Too many") + " answers.");
        }

        return grade;
    }

    @Override
    public Object visitResolution(ResolutionParser.ResolutionContext ctx) {
        for (ResolutionParser.SectionContext cont : ctx.section()) {
            visitSection(cont);
        }

        return null;
    }

    @Override
    public Object visitSection(ResolutionParser.SectionContext ctx) {
        for (ResolutionParser.AnswerContext cont : ctx.answer()) {
            visitAnswer(cont);
        }

        return null;
    }

    @Override
    public Object visitAnswer(ResolutionParser.AnswerContext ctx) {
        visit(ctx.getChild(0));

        return null;
    }

    @Override
    public Object visitAnswerToMatching(ResolutionParser.AnswerToMatchingContext ctx) {
        StringBuilder answer = new StringBuilder();
        numberAnswers++;

        for (ResolutionParser.MatchingOptionContext cont : ctx.matchingOption()) {
            answer.append(visitMatchingOption(cont));
        }

        answer = new StringBuilder(answer.toString().trim());

        answers.add(answer.toString());

        return null;
    }

    @Override
    public String visitMatchingOption(ResolutionParser.MatchingOptionContext ctx) {
        return ctx.ALPHABETICAL_OPTION() != null ? ctx.NUMERICAL_OPTION().toString() + ctx.DASH().toString() + ctx.ALPHABETICAL_OPTION().toString() : ctx.NUMERICAL_OPTION().toString() + ctx.DASH().toString() + " ";
    }

    @Override
    public Object visitAnswerToMultipleChoice(ResolutionParser.AnswerToMultipleChoiceContext ctx) {
        numberAnswers++;
        String answer = ctx.CHAR() != null ? ctx.CHAR().getText() : "";

        answer = answer.trim();
        answers.add(answer);

        return null;
    }

    @Override
    public Object visitAnswerToShortAnswer(ResolutionParser.AnswerToShortAnswerContext ctx) {
        numberAnswers++;
        String answer = visitPhrase(ctx.phrase());

        answer = answer.trim();
        answers.add(answer);

        return null;
    }

    @Override
    public Object visitAnswerToNumerical(ResolutionParser.AnswerToNumericalContext ctx) {
        numberAnswers++;
        String answer = String.valueOf(ctx.FLOAT());

        answer = answer.trim();
        answers.add(answer);

        return null;
    }

    @Override
    public Object visitAnswerToSelectMissingWords(ResolutionParser.AnswerToSelectMissingWordsContext ctx) {
        numberAnswers++;
        String answer = visitPhrase(ctx.phrase());

        answer = answer.trim();
        answers.add(answer);

        return null;
    }

    @Override
    public Object visitAnswerToTrueFalse(ResolutionParser.AnswerToTrueFalseContext ctx) {
        numberAnswers++;
        String answer = String.valueOf(ctx.TRUE_OR_FALSE());

        answer = answer.trim();
        answers.add(answer);

        return null;
    }

    @Override
    public String visitPhrase(ResolutionParser.PhraseContext ctx) {
        return ctx != null ? ctx.getText() : "";
    }

    @Override
    public String visitWords(ResolutionParser.WordsContext ctx) {
        return ctx != null ? ctx.getText() : "";
    }
}
