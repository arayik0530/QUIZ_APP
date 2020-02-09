package com.workfront.quiz.entity;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "quiz_questions")
public class QuizQuestionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "quiz_id", nullable = false)
    private QuizEntity quiz;

    @OneToOne
    @JoinColumn(name = "question_id", nullable = false, updatable = false)
    private QuestionEntity question;

   @OneToMany
   @JoinTable(name = "quiz_questions_answers",
           joinColumns = {@JoinColumn(name = "quiz_question_id")},
   inverseJoinColumns = {@JoinColumn(name = "answer_id")})
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
        if (!(o instanceof QuizQuestionEntity)) return false;
        QuizQuestionEntity that = (QuizQuestionEntity) o;
        return Objects.equals(getId(), that.getId()) &&
                Objects.equals(getQuestion(), that.getQuestion()) &&
                Objects.equals(getGivenAnswers(), that.getGivenAnswers());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getQuestion(), getGivenAnswers());
    }

    @Override
    public String toString() {
        return "QuizQuestionEntity{" +
                "id=" + id +
                ", question=" + question +
                ", givenAnswers=" + givenAnswers +
                '}';
    }
}
