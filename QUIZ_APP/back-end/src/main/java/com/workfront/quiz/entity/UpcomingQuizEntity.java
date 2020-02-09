package com.workfront.quiz.entity;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

public class UpcomingQuizEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private UserEntity user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "topic_id")
    private TopicEntity topic;

    @Column(name = "expected_duration")
    private Integer duration;

    @Column(name = "deadline")
    private LocalDateTime deadline;

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

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public LocalDateTime getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDateTime deadline) {
        this.deadline = deadline;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UpcomingQuizEntity)) return false;
        UpcomingQuizEntity that = (UpcomingQuizEntity) o;
        return Objects.equals(getId(), that.getId()) &&
                Objects.equals(getUser(), that.getUser()) &&
                Objects.equals(getTopic(), that.getTopic()) &&
                Objects.equals(getDuration(), that.getDuration()) &&
                Objects.equals(getDeadline(), that.getDeadline());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUser(), getTopic(), getDuration(), getDeadline());
    }

    @Override
    public String toString() {
        return "UpcomingQuizEntity{" +
                "id=" + id +
                ", user=" + user +
                ", topic=" + topic +
                ", duration=" + duration +
                ", deadline=" + deadline +
                '}';
    }
}
