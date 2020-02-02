package com.workfront.quiz.entity;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "questions")
public class QuestionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "text", nullable = false)
    private String text;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "topic_id", nullable = false)
    private TopicEntity topicEntity;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "question")
    private List<AnswerEntity> answers;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<AnswerEntity> getAnswers() {
        return answers;
    }

    public void setAnswers(List<AnswerEntity> answers) {
        this.answers = answers;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public TopicEntity getTopicEntity() {
        return topicEntity;
    }

    public void setTopicEntity(TopicEntity topicEntity) {
        this.topicEntity = topicEntity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof QuestionEntity)) return false;
        QuestionEntity that = (QuestionEntity) o;
        return Objects.equals(getId(), that.getId()) &&
                Objects.equals(getText(), that.getText()) &&
                Objects.equals(getTopicEntity(), that.getTopicEntity()) &&
                Objects.equals(getAnswers(), that.getAnswers());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getText(), getTopicEntity(), getAnswers());
    }

    @Override
    public String toString() {
        return "QuestionEntity{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", topicEntity=" + topicEntity +
                ", answers=" + answers +
                '}';
    }
}
