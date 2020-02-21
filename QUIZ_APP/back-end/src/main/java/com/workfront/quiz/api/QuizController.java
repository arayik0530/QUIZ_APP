package com.workfront.quiz.api;

import com.workfront.quiz.dto.answer.AnswerDto;
import com.workfront.quiz.dto.question.QuestionDto;
import com.workfront.quiz.dto.quiz.*;
import com.workfront.quiz.entity.TopicEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import javax.jws.soap.SOAPBinding;
import java.util.Collection;
import java.util.List;

public interface QuizController {

    PastQuizInfoDto findById(Long id);

    Page<QuizDto> getAllQuizes(Pageable pageable);

    Page<QuizDto> getQuizesByTopic(TopicEntity topic, Pageable pageable);

    void remove(Long id);

    QuestionDto startQuiz(Long upComingQuizId);

    QuestionDto getQuestion(Long nextQuestionId);

    PastQuizInfoDto finishQuiz( Long quizId);

    void answerToQuestion( Long questionId, List<AnswerDto> answerDtos);

    Page<QuizDtoShortInfo> getQuizesForAuthenticatedUser(Pageable pageable);

    Page<QuizDtoShortInfo> getQuizesForUser(Long userId, Pageable pageable);

    Page<UpcomingQuizDto> getUpcomingQuizForAuthenticatedUser(Pageable pageable);

    Page<UpcomingQuizDto> getUpcomingQuizForUser(Long userId, Pageable pageable);

    void createUpcomingQuizForUser(UpcomingQuizCreationDto upcomingQuizCreationDto);
}
