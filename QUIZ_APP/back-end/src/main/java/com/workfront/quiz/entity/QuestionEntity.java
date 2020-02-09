package com.workfront.quiz.entity;

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
    private TopicEntity topic;


    @OneToMany(mappedBy = "question", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
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

    public TopicEntity getTopic() {
        return topic;
    }

    public void setTopic(TopicEntity topic) {
        this.topic = topic;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof QuestionEntity)) return false;
        QuestionEntity that = (QuestionEntity) o;
        return Objects.equals(getId(), that.getId()) &&
                Objects.equals(getText(), that.getText()) &&
                Objects.equals(getTopic(), that.getTopic()) &&
                Objects.equals(getAnswers(), that.getAnswers());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getText(), getTopic(), getAnswers());
    }

    @Override
    public String toString() {
        return "QuestionEntity{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", topic=" + topic +
                ", answers=" + answers +
                '}';
    }
}
