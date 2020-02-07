package com.workfront.quiz.dto.question;

import com.workfront.quiz.entity.AnswerEntity;
import com.workfront.quiz.entity.QuestionEntity;
import com.workfront.quiz.entity.TopicEntity;

import java.util.List;
import java.util.Objects;

public class QuestionDto {

    private Long id;

    private TopicEntity topic;

    private String text;

    private List<AnswerEntity> answers;

    public QuestionEntity toEntity(){
        QuestionEntity question = new QuestionEntity();
        question.setText(this.text);
        question.setTopic(this.topic);
        question.setAnswers(this.answers);

        return question;
    }

    public static QuestionDto mapFromEntity(QuestionEntity question){

        QuestionDto questionDto = new QuestionDto();

        questionDto.setId(question.getId());
        questionDto.setText(question.getText());
        questionDto.setTopic(question.getTopic());
        questionDto.setAnswers(question.getAnswers());

        return questionDto;
    }

    @Override
    public String toString() {
        return "QuestionDto{" +
                "id=" + id +
                ", topic=" + topic +
                ", text='" + text + '\'' +
                ", answers=" + answers +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof QuestionDto)) return false;
        QuestionDto that = (QuestionDto) o;
        return Objects.equals(getId(), that.getId()) &&
                Objects.equals(getTopic(), that.getTopic()) &&
                Objects.equals(getText(), that.getText()) &&
                Objects.equals(getAnswers(), that.getAnswers());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTopic(), getText(), getAnswers());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TopicEntity getTopic() {
        return topic;
    }

    public void setTopic(TopicEntity topic) {
        this.topic = topic;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<AnswerEntity> getAnswers() {
        return answers;
    }

    public void setAnswers(List<AnswerEntity> answers) {
        this.answers = answers;
    }
}
