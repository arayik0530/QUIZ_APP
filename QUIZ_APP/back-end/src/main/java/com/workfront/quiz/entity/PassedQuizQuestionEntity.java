package com.workfront.quiz.entity;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "passed_quiz_questions")
public class PassedQuizQuestionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "quiz_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private QuizEntity quiz;

    @OneToOne
    @JoinColumn(name = "question_id", nullable = false, updatable = false)
    private QuestionEntity question;

    @OneToMany(mappedBy = "quizQuestion")
    private List<AnswerEntity> givenAnswers;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public QuizEntity getQuiz() {
        return quiz;
    }

    public void setQuiz(QuizEntity quiz) {
        this.quiz = quiz;
    }

    public QuestionEntity getQuestion() {
        return question;
    }

    public void setQuestion(QuestionEntity question) {
        this.question = question;
    }

    public List<AnswerEntity> getGivenAnswers() {
        return givenAnswers;
    }

    public void setGivenAnswers(List<AnswerEntity> givenAnswers) {
        this.givenAnswers = givenAnswers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PassedQuizQuestionEntity)) return false;
        PassedQuizQuestionEntity that = (PassedQuizQuestionEntity) o;
        return Objects.equals(getId(), that.getId()) &&
                Objects.equals(getQuiz(), that.getQuiz()) &&
                Objects.equals(getQuestion(), that.getQuestion()) &&
                Objects.equals(getGivenAnswers(), that.getGivenAnswers());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getQuiz(), getQuestion(), getGivenAnswers());
    }

    @Override
    public String toString() {
        return "PassedQuizQuestionEntity{" +
                "id=" + id +
                ", quiz=" + quiz +
                ", question=" + question +
                ", givenAnswers=" + givenAnswers +
                '}';
    }
}
