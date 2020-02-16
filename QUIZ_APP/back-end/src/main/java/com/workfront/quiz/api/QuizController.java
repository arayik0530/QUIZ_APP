package com.workfront.quiz.api;

import com.workfront.quiz.dto.question.QuestionDto;
import com.workfront.quiz.dto.quiz.PastQuizInfoDto;
import com.workfront.quiz.dto.quiz.QuizDto;
import com.workfront.quiz.dto.quiz.QuizDtoShortInfo;
import com.workfront.quiz.entity.TopicEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.jws.soap.SOAPBinding;
import java.util.Collection;

public interface QuizController {

    PastQuizInfoDto findById(Long id);

    Page<QuizDto> getAllQuizes(Pageable pageable);

    Page<QuizDto> getQuizesByTopic(TopicEntity topic, Pageable pageable);

    void remove(Long id);

    Collection<QuestionDto> startQuiz(Long upComingQuizId);


    Page<QuizDtoShortInfo> getQuizesForAuthenticatedUser(Pageable pageable);

    Page<QuizDtoShortInfo> getQuizesForAuthenticatedUser(Long userId, Pageable pageable);
}
