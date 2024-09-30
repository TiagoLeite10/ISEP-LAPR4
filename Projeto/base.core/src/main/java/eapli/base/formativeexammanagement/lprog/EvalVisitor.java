package eapli.base.formativeexammanagement.lprog;

import eapli.base.exammanagement.domain.*;
import eapli.base.formativeexammanagement.domain.FormativeExam;
import eapli.base.formativeexammanagement.domain.FormativeExamBuilder;
import eapli.base.formativeexammanagement.domain.FormativeExamSection;
import eapli.base.formativeexammanagement.domain.FormativeExamSectionBuilder;
import org.springframework.data.util.Pair;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class EvalVisitor extends FormativeExamBaseVisitor<Object> {

    private FormativeExam formativeExam;

    public EvalVisitor() {
        this.formativeExam = null;
    }

    public FormativeExam formativeExam() {
        return this.formativeExam;
    }

    @Override
    public Object visitFormativeExam(FormativeExamParser.FormativeExamContext ctx) {
        String title = visitTitle(ctx.title());
        Calendar openingDateTime = visitOpeningDateTime(ctx.title());
        Calendar closingDateTime = visitClosingDateTime(ctx.title());
        Header header = visitHeader(ctx.header());

        FormativeExamBuilder formativeExam = new FormativeExamBuilder().withTitle(title).withDateTime(openingDateTime, closingDateTime).withHeader(header);

        for (FormativeExamParser.SectionContext cont : ctx.section()) {
            formativeExam.withSection(visitSection(cont));
        }

        return this.formativeExam = formativeExam.build();
    }

    @Override
    public String visitTitle(FormativeExamParser.TitleContext ctx) {
        return visitPhrase(ctx.phrase());
    }

    public Calendar visitOpeningDateTime(FormativeExamParser.TitleContext ctx) {
        String openingDate = ctx.DATE(0).getText();
        String openingTime = ctx.TIME(0).getText();

        return convertToCalendar(openingDate, openingTime);
    }

    public Calendar visitClosingDateTime(FormativeExamParser.TitleContext ctx) {
        String closingDate = ctx.DATE(1).getText();
        String closingTime = ctx.TIME(1).getText();

        return convertToCalendar(closingDate, closingTime);
    }

    @Override
    public Header visitHeader(FormativeExamParser.HeaderContext ctx) {
        String typeOfFeedback = visitType(ctx.type(0));
        String typeOfGrade = visitType(ctx.type(1));
        String description = ctx.phrase() != null ? visitPhrase(ctx.phrase()) : "";

        return new HeaderBuilder().withTypeFeedback(typeOfFeedback).withTypeNote(typeOfGrade).withDescription(description).build();
    }

    @Override
    public FormativeExamSection visitSection(FormativeExamParser.SectionContext ctx) {
        String description = ctx.phrase() != null ? visitPhrase(ctx.phrase()) : "";
        FormativeExamSectionBuilder formativeExamSection = new FormativeExamSectionBuilder().withDescription(description);

        for (FormativeExamParser.TypeQuestionContext typeQuestionCtx : ctx.typeQuestion()) {
            Pair<String, Integer> pair = visitTypeQuestion(typeQuestionCtx);
            formativeExamSection.withQuestionAndRequiredNumber(pair);
        }

        return formativeExamSection.build();
    }

    @Override
    public Pair<String, Integer> visitTypeQuestion(FormativeExamParser.TypeQuestionContext ctx) {
        int number = convertToInteger(ctx.NUMS().getText());
        String typeQuestion = visitQuestion(ctx.question());
        return Pair.of(typeQuestion, number);
    }

    @Override
    public String visitQuestion(FormativeExamParser.QuestionContext ctx) {
        return ctx.getText();
    }

    @Override
    public String visitPhrase(FormativeExamParser.PhraseContext ctx) {
        return ctx.getText();
    }

    @Override
    public String visitType(FormativeExamParser.TypeContext ctx) {
        return ctx.getText();
    }

    @Override
    public String visitWords(FormativeExamParser.WordsContext ctx) {
        return ctx.getText();
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

    public int convertToInteger(String number) {
        return Integer.parseInt(number);
    }
}
