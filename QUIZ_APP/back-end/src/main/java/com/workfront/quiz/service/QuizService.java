package com.workfront.quiz.service;

import com.workfront.quiz.dto.question.QuestionDto;
import com.workfront.quiz.dto.quiz.QuizDto;
import com.workfront.quiz.entity.TopicEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Set;

public interface QuizService {

    QuizDto findById(Long id);

    Page<QuizDto> getAllQuizes(Pageable pageable);

    Page<QuizDto> getQuizesByTopic(TopicEntity topic, Pageable pageable);

    Set<QuestionDto> generateQuestions(TopicEntity topic, int count);

    void remove(Long id);

    void update(QuizDto quiz);
}
