package com.workfront.quiz.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "passed_quiz_questions")
public class PassedQuizQuestionsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "quiz_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private PassedQuizEntity passedQuiz;

    @ManyToOne
    @JoinColumn(name = "question_id", nullable = false, updatable = false)
    private QuestionEntity questionEntity;


    @ManyToOne
    @JoinColumn(name = "answer_id", nullable = false, updatable = false)
    private AnswerEntity answerEntity;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PassedQuizEntity getPassedQuiz() {
        return passedQuiz;
    }

    public void setPassedQuiz(PassedQuizEntity passedQuiz) {
        this.passedQuiz = passedQuiz;
    }

    public QuestionEntity getQuestionEntity() {
        return questionEntity;
    }

    public void setQuestionEntity(QuestionEntity questionEntity) {
        this.questionEntity = questionEntity;
    }

    public AnswerEntity getAnswerEntity() {
        return answerEntity;
    }

    public void setAnswerEntity(AnswerEntity answerEntity) {
        this.answerEntity = answerEntity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PassedQuizQuestionsEntity)) return false;
        PassedQuizQuestionsEntity that = (PassedQuizQuestionsEntity) o;
        return Objects.equals(getId(), that.getId()) &&
                Objects.equals(getPassedQuiz(), that.getPassedQuiz()) &&
                Objects.equals(getQuestionEntity(), that.getQuestionEntity()) &&
                Objects.equals(getAnswerEntity(), that.getAnswerEntity());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getPassedQuiz(), getQuestionEntity(), getAnswerEntity());
    }

    @Override
    public String toString() {
        return "PassedQuizQuestionsEntity{" +
                "id=" + id +
                ", passedQuiz=" + passedQuiz +
                ", questionEntity=" + questionEntity +
                ", answerEntity=" + answerEntity +
                '}';
    }
}
