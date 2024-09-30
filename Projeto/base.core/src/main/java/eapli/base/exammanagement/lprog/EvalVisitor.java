package eapli.base.exammanagement.lprog;

import eapli.base.exammanagement.domain.*;
import eapli.base.exammanagement.domain.Exam;
import eapli.base.questionmanagement.domain.QuestionScore;
import eapli.base.questionmanagement.domain.*;
import org.springframework.data.util.Pair;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class EvalVisitor extends ExamBaseVisitor<Object> {

    private Exam exam;

    public EvalVisitor() {
        this.exam = null;
    }

    public Exam getExam() {
        return this.exam;
    }

    @Override
    public Exam visitExam(ExamParser.ExamContext ctx) {
        String title = visitTitle(ctx.title());
        Calendar openingDateTime = visitOpeningDateTime(ctx.title());
        Calendar closingDateTime = visitClosingDateTime(ctx.title());
        Header header = visitHeader(ctx.header());

        ExamBuilder exam = new ExamBuilder().withTitle(title).withDateTime(openingDateTime, closingDateTime).withHeader(header);

        for (ExamParser.SectionContext cont : ctx.section()) {
            exam.withSection(visitSection(cont));
        }

        return this.exam = exam.build();
    }

    @Override
    public String visitTitle(ExamParser.TitleContext ctx) {
        return visitPhrase(ctx.phrase());
    }

    public Calendar visitOpeningDateTime(ExamParser.TitleContext ctx) {
        String openingDate = ctx.DATE(0).getText();
        String openingTime = ctx.TIME(0).getText();

        return convertToCalendar(openingDate, openingTime);
    }

    public Calendar visitClosingDateTime(ExamParser.TitleContext ctx) {
        String closingDate = ctx.DATE(1).getText();
        String closingTime = ctx.TIME(1).getText();

        return convertToCalendar(closingDate, closingTime);
    }

    @Override
    public Header visitHeader(ExamParser.HeaderContext ctx) {
        String typeOfFeedback = visitType(ctx.type(0));
        String typeOfGrade = visitType(ctx.type(1));
        String description = ctx.phrase() != null ? visitPhrase(ctx.phrase()) : "";

        return new HeaderBuilder().withTypeFeedback(typeOfFeedback).withTypeNote(typeOfGrade).withDescription(description).build();
    }

    @Override
    public ExamSection visitSection(ExamParser.SectionContext ctx) {
        String description = ctx.phrase() != null ? visitPhrase(ctx.phrase()) : "";
        ExamSectionBuilder section = new ExamSectionBuilder().withDescription(description);

        for (ExamParser.QuestionContext questionContext : ctx.question()) {
            section.withQuestion(visitQuestion(questionContext));
        }

        return section.build();
    }

    @Override
    public Question visitQuestion(ExamParser.QuestionContext ctx) {
        return (Question) visit(ctx.getChild(0));
    }

    @Override
    public Matching visitMatching(ExamParser.MatchingContext ctx) {
        String text = visitPhrase(ctx.phrase());

        Map<String, String> matching1 = new HashMap<>();
        Map<String, String> matching2 = new HashMap<>();
        for (ExamParser.PairContext pairCtx : ctx.pair()) {
            Pair<String, String> left = visitPairLeft(pairCtx);
            matching1.put(left.getFirst(), left.getSecond());

            Pair<String, String> right = visitPairRight(pairCtx);
            matching2.put(right.getFirst(), right.getSecond());
        }

        StringBuilder correctAnswer = new StringBuilder();
        Map<String, String> matchingAssociations = new HashMap<>();
        for (ExamParser.MatchingOptionCorrectionContext matchingCtx : ctx.matchingOptionCorrection()) {
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
    public Pair<String, String> visitMatchingOptionCorrection(ExamParser.MatchingOptionCorrectionContext ctx) {
        String numericalOption = ctx.NUMERICAL_OPTION().getText().trim();
        String alfabeticalOption = ctx.ALPHABETICAL_OPTION().getText().trim();

        return Pair.of(numericalOption, alfabeticalOption);
    }

    public Pair<String, String> visitPairLeft(ExamParser.PairContext ctx) {
        String numericalOption = ctx.NUMERICAL_OPTION().getText().trim();
        String text = ctx.phrase(0).getText().trim();

        return Pair.of(numericalOption, text);
    }

    public Pair<String, String> visitPairRight(ExamParser.PairContext ctx) {
        String alfabeticalOption = ctx.ALPHABETICAL_OPTION().getText().trim();
        String text = ctx.phrase(1).getText().trim();
        return Pair.of(alfabeticalOption, text);
    }

    @Override
    public MultipleChoice visitMultipleChoice(ExamParser.MultipleChoiceContext ctx) {
        String text = visitPhrase(ctx.phrase());

        Map<String, String> options = new HashMap<>();
        for (ExamParser.OptionContext optionCtx : ctx.option()) {
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
    public ShortAnswer visitShortAnswer(ExamParser.ShortAnswerContext ctx) {
        String text = visitPhrase(ctx.phrase(0));
        float score = convertToFloat(ctx.FLOAT().getText());
        String correctAnswer = visitPhrase(ctx.phrase(1));
        String feedback = ctx.feedback().phrase() != null ? visitFeedback(ctx.feedback()) : "";

        QuestionScore questionScore = QuestionScore.valueOf(score);
        Answer answer = new Answer(correctAnswer, feedback);

        return new ShortAnswer(text, questionScore, answer);
    }

    @Override
    public Numerical visitNumerical(ExamParser.NumericalContext ctx) {
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
    public SelectMissingWords visitSelectMissingWords(ExamParser.SelectMissingWordsContext ctx) {
        String text = visitUncompleteText(ctx.uncompleteText());

        Set<String> possibleChoices = new HashSet<>();
        for (ExamParser.PossibleChoicesContext possibleChoicesCtx : ctx.possibleChoices()) {
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
    public String visitUncompleteText(ExamParser.UncompleteTextContext ctx) {
        return ctx.getText();
    }

    @Override
    public String visitPossibleChoices(ExamParser.PossibleChoicesContext ctx) {
        return visitWords(ctx.words(0));
    }

    @Override
    public TrueFalse visitTrueFalse(ExamParser.TrueFalseContext ctx) {
        String text = visitPhrase(ctx.phrase());
        float score = convertToFloat(ctx.FLOAT().getText());
        String correctAnswer = ctx.TRUE_OR_FALSE().getText();
        String feedback = ctx.feedback().phrase() != null ? visitFeedback(ctx.feedback()) : "";

        QuestionScore questionScore = QuestionScore.valueOf(score);
        Answer answer = new Answer(correctAnswer, feedback);

        return new TrueFalse(text, questionScore, answer);
    }

    @Override
    public String visitPhrase(ExamParser.PhraseContext ctx) {
        return ctx.getText();
    }

    @Override
    public String visitOption(ExamParser.OptionContext ctx) {
        return ctx.getText();
    }

    @Override
    public String visitFeedback(ExamParser.FeedbackContext ctx) {
        return visitPhrase(ctx.phrase());
    }

    @Override
    public String visitType(ExamParser.TypeContext ctx) {
        return ctx.getText();
    }

    @Override
    public String visitWords(ExamParser.WordsContext ctx) {
        return ctx.getText();
    }

    public float convertToFloat(String number) {
        return Float.parseFloat(number);
    }

    public Calendar convertToCalendar(String date, String time) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm");
        Calendar calendar = Calendar.getInstance();

        Date dateTime;
        try {
            dateTime = dateFormat.parse(date + " " + time);
            calendar.setTime(dateTime);
            return calendar;
        } catch (ParseException e) {
            return null;
        }
    }
}
