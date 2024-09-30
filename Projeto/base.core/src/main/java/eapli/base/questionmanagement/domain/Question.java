package eapli.base.questionmanagement.domain;

import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import eapli.framework.validations.Preconditions;

import javax.persistence.*;

/**
 * Representa a classe abstrata Question.
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING)
public abstract class Question implements AggregateRoot<String> {

    private static final long serialVersionUID = 1L;

    @Version
    private Long version;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long questionId;

    @Column(nullable = false)
    private String text;

    @Column(nullable = false)
    private QuestionScore score;

    @OneToOne(optional = false, cascade = CascadeType.ALL)
    private Answer answer;

    @Column(name = "type", insertable = false, updatable = false)
    private String type;

    public Question(final String text, final QuestionScore score, final Answer answer) {
        Preconditions.noneNull(text, score, answer, "Question must not contain null or empty attributes");

        this.text = text;
        this.score = score;
        this.answer = answer;
    }

    protected Question() {
        // for ORM only
    }

    public String typeText() {
        return this.typeQuestionToString() + this.text();
    }

    public String text() {
        return text;
    }

    public abstract String typeQuestionToString();

    public Float score() {
        return score.score();
    }

    public void merge(Question question) {
        if (question == null) {
            throw new IllegalArgumentException("Cannot merge with a question null");
        }

        this.text = question.text;
        this.score = question.score;
        this.answer = question.answer;
    }

    public boolean isCorrectAnswer(String answer) {
        return this.answer.isCorrectAnswer(answer);
    }

    public String correctAnswer() {
        return this.answer.correctAnswer();
    }

    public String questionFeedback() {
        return "Question: " + text + "\n" + "Feedback: " + answer.feedback() + "\n";
    }

    @Override
    public boolean sameAs(Object other) {
        if (!(other instanceof Question)) {
            return false;
        }

        final Question that = (Question) other;
        if (this == that) {
            return true;
        }

        return text.equals(that.text) && score.equals(that.score) && answer.sameAs(that.answer);
    }

    @Override
    public boolean equals(final Object other) {
        return DomainEntities.areEqual(this, other);
    }

    @Override
    public int hashCode() {
        return DomainEntities.hashCode(this);
    }

    @Override
    public String identity() {
        return this.text;
    }

    @Override
    public String toString() {
        return "-- Question --\n" + "Text: " + this.text + "\n"
                + this.score.toString() + "\n"
                + this.answer.toString() + "\n";
    }
}
