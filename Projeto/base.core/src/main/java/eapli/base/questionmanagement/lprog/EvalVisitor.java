package eapli.base.questionmanagement.lprog;

import eapli.base.questionmanagement.domain.*;
import org.springframework.data.util.Pair;

import java.util.*;

public class EvalVisitor extends ExamQuestionBaseVisitor<Object> {

    private final Set<Question> questions;

    public EvalVisitor() {
        this.questions = new HashSet<>();
    }

    public Iterable<Question> questions() {
        return this.questions;
    }

    @Override
    public Object visitExamQuestion(ExamQuestionParser.ExamQuestionContext ctx) {
        for (ExamQuestionParser.QuestionContext questionContext : ctx.question()) {
            this.questions.add(visitQuestion(questionContext));
        }
        return null;
    }

    @Override
    public Question visitQuestion(ExamQuestionParser.QuestionContext ctx) {
        return (Question) visit(ctx.getChild(0));
    }

    @Override
    public Matching visitMatching(ExamQuestionParser.MatchingContext ctx) {
        String text = visitPhrase(ctx.phrase());

        Map<String, String> matching1 = new HashMap<>();
        Map<String, String> matching2 = new HashMap<>();
        for (ExamQuestionParser.PairContext pairCtx : ctx.pair()) {
            Pair<String, String> left = visitPairLeft(pairCtx);
            matching1.put(left.getFirst(), left.getSecond());

            Pair<String, String> right = visitPairRight(pairCtx);
            matching2.put(right.getFirst(), right.getSecond());
        }

        StringBuilder correctAnswer = new StringBuilder();
        Map<String, String> matchingAssociations = new HashMap<>();
        for (ExamQuestionParser.MatchingOptionCorrectionContext matchingCtx : ctx.matchingOptionCorrection()) {
            Pair<String, String> matchingOption = visitMatchingOptionCorrection(matchingCtx);
            matchingAssociations.put(matchingOption.getFirst(), matchingOption.getSecond());
            correctAnswer.append(matchingCtx.getText());
        }

        float score = convertToFloat(ctx.FLOAT().getText());
        String feedback = ctx.feedback().phrase() != null ? visitFeedback(ctx.feedback()) : "";

        QuestionScore questionScore = QuestionScore.valueOf(score);
        Answer answer = new Answer(correctAnswer.toString(), feedback);

        return new Matching(text, questionScore, answer, matching1, matching2, matchingAssociations);
    }

    @Override
    public Pair<String, String> visitMatchingOptionCorrection(ExamQuestionParser.MatchingOptionCorrectionContext ctx) {
        String numericalOption = ctx.NUMERICAL_OPTION().getText().trim();
        String alfabeticalOption = ctx.ALPHABETICAL_OPTION().getText().trim();

        return Pair.of(numericalOption, alfabeticalOption);
    }

    public Pair<String, String> visitPairLeft(ExamQuestionParser.PairContext ctx) {
        String numericalOption = ctx.NUMERICAL_OPTION().getText().trim();
        String text = ctx.phrase(0).getText().trim();

        return Pair.of(numericalOption, text);
    }

    public Pair<String, String> visitPairRight(ExamQuestionParser.PairContext ctx) {
        String alfabeticalOption = ctx.ALPHABETICAL_OPTION().getText().trim();
        String text = ctx.phrase(1).getText().trim();
        return Pair.of(alfabeticalOption, text);
    }

    @Override
    public MultipleChoice visitMultipleChoice(ExamQuestionParser.MultipleChoiceContext ctx) {
        String text = visitPhrase(ctx.phrase());

        Map<String, String> options = new HashMap<>();
        for (ExamQuestionParser.OptionContext optionCtx : ctx.option()) {
            String[] option = visitOption(optionCtx).split("\\)");
            options.put(option[0].trim(), option[1].trim());
        }

        float score = convertToFloat(ctx.FLOAT().getText());
        String correctAnswer = ctx.CHAR().getText();
        String feedback = ctx.feedback().phrase() != null ? visitFeedback(ctx.feedback()) : "";

        QuestionScore questionScore = QuestionScore.valueOf(score);
        Answer answer = new Answer(correctAnswer, feedback);

        return new MultipleChoice(text, questionScore, answer, options);
    }

    @Override
    public ShortAnswer visitShortAnswer(ExamQuestionParser.ShortAnswerContext ctx) {
        String text = visitPhrase(ctx.phrase(0));
        float score = convertToFloat(ctx.FLOAT().getText());
        String correctAnswer = visitPhrase(ctx.phrase(1));
        String feedback = ctx.feedback().phrase() != null ? visitFeedback(ctx.feedback()) : "";

        QuestionScore questionScore = QuestionScore.valueOf(score);
        Answer answer = new Answer(correctAnswer, feedback);

        return new ShortAnswer(text, questionScore, answer);
    }

    @Override
    public Numerical visitNumerical(ExamQuestionParser.NumericalContext ctx) {
        String text = visitPhrase(ctx.phrase());
        float score = convertToFloat(ctx.FLOAT(0).getText());
        String correctAnswer = ctx.FLOAT(1).getText();
        float error = convertToFloat(ctx.FLOAT(2).getText());
        String feedback = ctx.feedback().phrase() != null ? visitFeedback(ctx.feedback()) : "";

        QuestionScore questionScore = QuestionScore.valueOf(score);
        Answer answer = new Answer(correctAnswer, feedback);

        return new Numerical(text, questionScore, answer, error);
    }

    @Override
    public SelectMissingWords visitSelectMissingWords(ExamQuestionParser.SelectMissingWordsContext ctx) {
        String text = visitUncompleteText(ctx.uncompleteText());

        Set<String> possibleChoices = new HashSet<>();
        for (ExamQuestionParser.PossibleChoicesContext possibleChoicesCtx : ctx.possibleChoices()) {
            possibleChoices.add(visitPossibleChoices(possibleChoicesCtx));
        }

        float score = convertToFloat(ctx.FLOAT().getText());
        String correctAnswer = visitPhrase(ctx.phrase());
        String feedback = ctx.feedback().phrase() != null ? visitFeedback(ctx.feedback()) : "";

        QuestionScore questionScore = QuestionScore.valueOf(score);
        Answer answer = new Answer(correctAnswer, feedback);

        return new SelectMissingWords(text, questionScore, answer, possibleChoices);
    }

    @Override
    public String visitUncompleteText(ExamQuestionParser.UncompleteTextContext ctx) {
        return ctx.getText();
    }

    @Override
    public String visitPossibleChoices(ExamQuestionParser.PossibleChoicesContext ctx) {
        return visitWords(ctx.words(0));
    }

    @Override
    public TrueFalse visitTrueFalse(ExamQuestionParser.TrueFalseContext ctx) {
        String text = visitPhrase(ctx.phrase());
        float score = convertToFloat(ctx.FLOAT().getText());
        String correctAnswer = ctx.TRUE_OR_FALSE().getText();
        String feedback = ctx.feedback().phrase() != null ? visitFeedback(ctx.feedback()) : "";

        QuestionScore questionScore = QuestionScore.valueOf(score);
        Answer answer = new Answer(correctAnswer, feedback);

        return new TrueFalse(text, questionScore, answer);
    }

    @Override
    public String visitPhrase(ExamQuestionParser.PhraseContext ctx) {
        return ctx.getText();
    }

    @Override
    public String visitOption(ExamQuestionParser.OptionContext ctx) {
        return ctx.getText();
    }

    @Override
    public String visitFeedback(ExamQuestionParser.FeedbackContext ctx) {
        return visitPhrase(ctx.phrase());
    }

    @Override
    public String visitWords(ExamQuestionParser.WordsContext ctx) {
        return ctx.getText();
    }

    public float convertToFloat(String number) {
        return Float.parseFloat(number);
    }
}
