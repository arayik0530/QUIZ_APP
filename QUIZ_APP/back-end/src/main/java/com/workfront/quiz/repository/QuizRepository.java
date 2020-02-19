package com.workfront.quiz.repository;

import com.sun.mail.util.QEncoderStream;
import com.workfront.quiz.entity.QuizEntity;
import com.workfront.quiz.entity.TopicEntity;
import com.workfront.quiz.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuizRepository extends JpaRepository<QuizEntity, Long> {

    Page<QuizEntity> findAllByTopic(TopicEntity topic, Pageable pageable);

    Page<QuizEntity> findAllByUser(UserEntity user, Pageable pageable);

}
