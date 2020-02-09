package com.workfront.quiz.dto.quiz;

import com.workfront.quiz.entity.QuizQuestionEntity;
import com.workfront.quiz.entity.QuizEntity;
import com.workfront.quiz.entity.TopicEntity;
import com.workfront.quiz.entity.UserEntity;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Data
public class QuizDto {

    private Long id;

    private UserEntity user;

    private TopicEntity topic;

    private List<QuizQuestionEntity> questions;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private Double successPercent;



    public QuizEntity toEntity(QuizEntity quiz){
        quiz.setQuizQuestions(this.getQuestions());
        quiz.setTopic(this.getTopic()); //TODO jshtel es tox@ petq a te che?
        quiz.setSuccessPercent(this.getSuccessPercent());
        quiz.setEndTime(this.getEndTime());

        return quiz;
    }

    public static QuizDto mapFromEntity(QuizEntity quiz){
        QuizDto quizDto = new QuizDto();

        quizDto.setId(quiz.getId());
        quizDto.setTopic(quiz.getTopic());
        quizDto.setQuestions(quiz.getQuizQuestions());
        quizDto.setSuccessPercent(quiz.getSuccessPercent());
        quizDto.setStartTime(quiz.getStartTime());
        quizDto.setEndTime(quiz.getEndTime());

        return quizDto;
    }

}
