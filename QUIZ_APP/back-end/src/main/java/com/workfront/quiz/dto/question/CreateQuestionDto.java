package com.workfront.quiz.dto.question;

import com.workfront.quiz.dto.answer.CreateAnswerDto;
import com.workfront.quiz.entity.AnswerEntity;
import com.workfront.quiz.entity.QuestionEntity;
import com.workfront.quiz.entity.TopicEntity;

import java.util.List;
import java.util.Objects;

public class CreateQuestionDto {

    private Long id;

    private String topic;

    private String text;

    private List<CreateAnswerDto> createAnswerDtoList;

    public QuestionEntity toEntity(){
        QuestionEntity question = new QuestionEntity();

        List<AnswerEntity> answers = question.getAnswers();

        for(int i = 0; i < answers.size(); ++i){
            answers.get(i).setRight(this.createAnswerDtoList.get(i).getRight());
            answers.get(i).setText(this.createAnswerDtoList.get(i).getText());
        }

        question.setText(this.text);
        TopicEntity topicEntity = new TopicEntity();
        topicEntity.setName(this.text);
        question.setTopic(topicEntity);

        question.setAnswers(answers);

        return question;
    }

    public static CreateQuestionDto mapFromEntity(QuestionEntity question){

        CreateQuestionDto createQuestionDto = new CreateQuestionDto();

        createQuestionDto.setText(question.getText());

        List<CreateAnswerDto> createAnswerDtoList1 = createQuestionDto.getCreateAnswerDtoList();
        for(int i = 0; i < createAnswerDtoList1.size(); ++i){
            createAnswerDtoList1.get(i).setRight(question.getAnswers().get(i).getRight());
            createAnswerDtoList1.get(i).setText(question.getAnswers().get(i).getText());

        }

        createQuestionDto.setCreateAnswerDtoList(createAnswerDtoList1);

        return createQuestionDto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }


    public List<CreateAnswerDto> getCreateAnswerDtoList() {
        return createAnswerDtoList;
    }

    public void setCreateAnswerDtoList(List<CreateAnswerDto> createAnswerDtoList) {
        this.createAnswerDtoList = createAnswerDtoList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CreateQuestionDto)) return false;
        CreateQuestionDto that = (CreateQuestionDto) o;
        return Objects.equals(getId(), that.getId()) &&
                Objects.equals(getTopic(), that.getTopic()) &&
                Objects.equals(getText(), that.getText()) &&
                Objects.equals(getCreateAnswerDtoList(), that.getCreateAnswerDtoList());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTopic(), getText(), getCreateAnswerDtoList());
    }

    @Override
    public String toString() {
        return "CreateQuestionDto{" +
                "id=" + id +
                ", topic='" + topic + '\'' +
                ", text='" + text + '\'' +
                ", createAnswerDtoList=" + createAnswerDtoList +
                '}';
    }
}
