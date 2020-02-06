package com.workfront.quiz.repository;

import com.workfront.quiz.entity.QuestionEntity;
import com.workfront.quiz.entity.TopicEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface QuestionRepository extends JpaRepository<QuestionEntity, Long> {

    @Query(value = "from QuestionEntity as question where question.text like ?1%")
    Page<QuestionEntity> searchByText(String text, Pageable pageable);

    Page<QuestionEntity> findAllByTopic(TopicEntity topic, Pageable pageable); //TODO jshtel ashxatuma te che

    @Query(value = "from QuestionEntity as question where question.topic = ?1")
    QuestionEntity generateQuestion(TopicEntity topic);
}
