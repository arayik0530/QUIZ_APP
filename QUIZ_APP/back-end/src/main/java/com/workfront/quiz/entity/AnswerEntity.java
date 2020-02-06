package com.workfront.quiz.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "answers")
public class AnswerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "text", nullable = false)
    private String text;

    @Column(name = "is_right", nullable = false)
    private Boolean isRight;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id")
    private QuestionEntity question;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "passed_quiz_question_id")
    private PassedQuizQuestionEntity quizQuestion;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Boolean getRight() {
        return isRight;
    }

    public void setRight(Boolean right) {
        isRight = right;
    }

    public QuestionEntity getQuestion() {
        return question;
    }

    public void setQuestion(QuestionEntity question) {
        this.question = question;
    }

    public PassedQuizQuestionEntity getQuizQuestion() {
        return quizQuestion;
    }

    public void setQuizQuestion(PassedQuizQuestionEntity quizQuestion) {
        this.quizQuestion = quizQuestion;
    }

    @Override
    public String toString() {
        return "AnswerEntity{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", isRight=" + isRight +
                ", question=" + question +
                ", quizQuestion=" + quizQuestion +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AnswerEntity)) return false;
        AnswerEntity that = (AnswerEntity) o;
        return Objects.equals(getId(), that.getId()) &&
                Objects.equals(getText(), that.getText()) &&
                Objects.equals(isRight, that.isRight) &&
                Objects.equals(getQuestion(), that.getQuestion()) &&
                Objects.equals(getQuizQuestion(), that.getQuizQuestion());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getText(), isRight, getQuestion(), getQuizQuestion());
    }
}
