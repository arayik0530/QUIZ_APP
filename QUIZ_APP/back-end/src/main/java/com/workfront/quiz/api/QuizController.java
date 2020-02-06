package com.workfront.quiz.api;

import com.workfront.quiz.dto.question.QuestionDto;
import com.workfront.quiz.dto.quiz.QuizDto;
import com.workfront.quiz.entity.TopicEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Set;

public interface QuizController {

    QuizDto findById(Long id);

    Page<QuizDto> getAllQuizes(Pageable pageable);

    Page<QuizDto> getQuizesByTopic(TopicEntity topic, Pageable pageable);

    void remove(Long id);

    void update(QuizDto quiz);

    Set<QuestionDto> startQuiz(TopicEntity topic, int count);
}
