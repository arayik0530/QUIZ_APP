package com.workfront.quiz.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "passed_quizes")
public class PassedQuizEntity  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "topic_id")
    private TopicEntity topicEntity;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "passedQuiz")
    private List<PassedQuizQuestionsEntity> questions;


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

    public TopicEntity getTopicEntity() {
        return topicEntity;
    }

    public void setTopicEntity(TopicEntity topicEntity) {
        this.topicEntity = topicEntity;
    }

    public List<PassedQuizQuestionsEntity> getQuestions() {
        return questions;
    }

    public void setQuestions(List<PassedQuizQuestionsEntity> questions) {
        this.questions = questions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PassedQuizEntity)) return false;
        PassedQuizEntity that = (PassedQuizEntity) o;
        return Objects.equals(getId(), that.getId()) &&
                Objects.equals(getUser(), that.getUser()) &&
                Objects.equals(getTopicEntity(), that.getTopicEntity()) &&
                Objects.equals(getQuestions(), that.getQuestions());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUser(), getTopicEntity(), getQuestions());
    }

    @Override
    public String toString() {
        return "PassedQuizEntity{" +
                "id=" + id +
                ", user=" + user +
                ", topicEntity=" + topicEntity +
                ", questions=" + questions +
                '}';
    }
}
