package com.workfront.quiz.entity;

import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "quizes")
public class QuizEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "user_id", nullable = false)
    @ManyToOne
    private UserEntity user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "topic_id")
    private TopicEntity topic;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "quiz")
    private List<QuizQuestionEntity> quizQuestions;

    @CreatedDate
    @Column(name = "start_time", updatable = false)
    private LocalDateTime startTime;

    @Column(name = "end_time")
    private LocalDateTime endTime;

    @Column(name = "expected_duration")
    private Integer duration;

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

    public List<QuizQuestionEntity> getQuizQuestions() {
        return quizQuestions;
    }

    public void setQuizQuestions(List<QuizQuestionEntity> quizQuestions) {
        this.quizQuestions = quizQuestions;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Double getSuccessPercent() {
        return successPercent;
    }

    public void setSuccessPercent(Double successPercent) {
        this.successPercent = successPercent;
    }

    @Override
    public String toString() {
        return "QuizEntity{" +
                "id=" + id +
                ", user=" + user +
                ", topic=" + topic +
                ", quizQuestions=" + quizQuestions +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", duration=" + duration +
                ", successPercent=" + successPercent +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof QuizEntity)) return false;
        QuizEntity that = (QuizEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(user, that.user) &&
                Objects.equals(topic, that.topic) &&
                Objects.equals(quizQuestions, that.quizQuestions) &&
                Objects.equals(startTime, that.startTime) &&
                Objects.equals(endTime, that.endTime) &&
                Objects.equals(duration, that.duration) &&
                Objects.equals(successPercent, that.successPercent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, topic, quizQuestions, startTime, endTime, duration, successPercent);
    }
}
