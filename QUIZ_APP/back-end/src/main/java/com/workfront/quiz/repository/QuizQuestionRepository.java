package com.workfront.quiz.repository;

import com.workfront.quiz.entity.QuizEntity;
import com.workfront.quiz.entity.QuizQuestionEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface QuizQuestionRepository extends CrudRepository<QuizQuestionEntity, Long> {
    QuizQuestionEntity findFirstByQuizOrderById(QuizEntity quizEntity);
}
