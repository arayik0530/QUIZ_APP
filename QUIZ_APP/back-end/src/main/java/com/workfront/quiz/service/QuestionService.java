package com.workfront.quiz.service;

import com.workfront.quiz.dto.question.CreateQuestionDto;
import com.workfront.quiz.dto.question.QuestionDto;
import com.workfront.quiz.entity.AnswerEntity;
import com.workfront.quiz.entity.QuestionEntity;
import com.workfront.quiz.entity.TopicEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Collection;
import java.util.List;

public interface QuestionService {

    QuestionDto findById(Long id);

    Page<QuestionDto> searchByText(String text, Pageable pageable);

    Page<QuestionDto> getAllQuestions(Pageable pageable);

    Page<QuestionDto> getQuestionsByTopic(TopicEntity topic, Pageable pageable);

    void remove(Long id);

    void update(QuestionDto question);

    Collection<QuestionEntity> generateQuestions(Long topicId);

    void create(CreateQuestionDto question);
}
