package com.workfront.quiz.entity;

import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "quizes")
public class QuizEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "topic_id")
    private TopicEntity topic;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "quiz")
    private List<PassedQuizQuestionEntity> questions;

    @CreatedDate
    @Column(name = "start_time", updatable = false)
    private LocalDate startTime;

    @Column(name = "end_time")
    private LocalDate endTime;

    @Column(name = "success_percent", updatable = false, scale = 2, precision = 5)
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof QuizEntity)) return false;
        QuizEntity that = (QuizEntity) o;
        return Objects.equals(getId(), that.getId()) &&
                Objects.equals(getUser(), that.getUser()) &&
                Objects.equals(getTopic(), that.getTopic()) &&
                Objects.equals(getQuestions(), that.getQuestions());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUser(), getTopic(), getQuestions());
    }

    @Override
    public String toString() {
        return "QuizEntity{" +
                "id=" + id +
                ", user=" + user +
                ", topic=" + topic +
                ", questions=" + questions +
                '}';
    }
}
