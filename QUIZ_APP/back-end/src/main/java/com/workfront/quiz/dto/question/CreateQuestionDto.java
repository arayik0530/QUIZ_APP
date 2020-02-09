package com.workfront.quiz.dto.question;

import com.workfront.quiz.dto.answer.CreateAnswerDto;
import com.workfront.quiz.dto.topic.TopicDto;
import com.workfront.quiz.entity.AnswerEntity;
import com.workfront.quiz.entity.QuestionEntity;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class CreateQuestionDto {

    private Long id;

    private String topic;

    private String text;

    private List<CreateAnswerDto> createAnswerDtoList;
    private List<AnswerEntity> answerEntities;

    public QuestionEntity toEntity(){

        QuestionEntity question = new QuestionEntity();
        answerEntities =
        createAnswerDtoList
                            .stream()
                            .map(CreateAnswerDto::toEntity)
                            .collect(Collectors.toList());

        question.setAnswers(this.answerEntities);

        TopicDto topicDto = new TopicDto();
        topicDto.setTitle(this.topic);

        question.setTopic(topicDto.toEntity());
        question.setText(this.text);

        return question;
    }
}
