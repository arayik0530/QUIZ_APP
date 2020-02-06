package com.workfront.quiz.dto.quiz;

import com.workfront.quiz.entity.PassedQuizQuestionEntity;
import com.workfront.quiz.entity.QuizEntity;
import com.workfront.quiz.entity.TopicEntity;
import com.workfront.quiz.entity.UserEntity;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class QuizDto {

    private Long id;

    private UserEntity user;

    private TopicEntity topic;

    private List<PassedQuizQuestionEntity> questions;

    private LocalDate startTime;

    private LocalDate endTime;

    private Double successPercent;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public TopicEntity getTopic() {
        return topic;
    }

    public void setTopic(TopicEntity topic) {
        this.topic = topic;
    }

    public List<PassedQuizQuestionEntity> getQuestions() {
        return questions;
    }

    public void setQuestions(List<PassedQuizQuestionEntity> questions) {
        this.questions = questions;
    }

    public LocalDate getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDate startTime) {
        this.startTime = startTime;
    }

    public LocalDate getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDate endTime) {
        this.endTime = endTime;
    }

    public Double getSuccessPercent() {
        return successPercent;
    }

    public void setSuccessPercent(Double successPercent) {
        this.successPercent = successPercent;
    }

    public QuizEntity toEntity(QuizEntity quiz){
        quiz.setQuestions(this.getQuestions());
        quiz.setUser(this.getUser()); //TODO jshtel es tox@ petq a te che?
        quiz.setTopic(this.getTopic()); //TODO jshtel es tox@ petq a te che?
        quiz.setSuccessPercent(this.getSuccessPercent());
        quiz.setEndTime(this.getEndTime());

        return quiz;
    }

    public static QuizDto mapFromEntity(QuizEntity quiz){
        QuizDto quizDto = new QuizDto();

        quizDto.setId(quiz.getId());
        quizDto.setUser(quiz.getUser());
        quizDto.setTopic(quiz.getTopic());
        quizDto.setQuestions(quiz.getQuestions());
        quizDto.setSuccessPercent(quiz.getSuccessPercent());
        quizDto.setStartTime(quiz.getStartTime());
        quizDto.setEndTime(quiz.getEndTime());

        return quizDto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof QuizDto)) return false;
        QuizDto quizDto = (QuizDto) o;
        return Objects.equals(getId(), quizDto.getId()) &&
                Objects.equals(getUser(), quizDto.getUser()) &&
                Objects.equals(getTopic(), quizDto.getTopic()) &&
                Objects.equals(getQuestions(), quizDto.getQuestions()) &&
                Objects.equals(getStartTime(), quizDto.getStartTime()) &&
                Objects.equals(getEndTime(), quizDto.getEndTime()) &&
                Objects.equals(getSuccessPercent(), quizDto.getSuccessPercent());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUser(), getTopic(), getQuestions(), getStartTime(), getEndTime(), getSuccessPercent());
    }

    @Override
    public String toString() {
        return "QuizDto{" +
                "id=" + id +
                ", user=" + user +
                ", topic=" + topic +
                ", questions=" + questions +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", successPercent=" + successPercent +
                '}';
    }


}
