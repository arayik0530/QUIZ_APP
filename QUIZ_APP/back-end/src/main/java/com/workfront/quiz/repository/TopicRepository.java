package com.workfront.quiz.repository;

import com.workfront.quiz.entity.TopicEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TopicRepository extends JpaRepository<TopicEntity, Long> {

    @Query(value = "from TopicEntity as topic where topic.name like ?1%")
    Page<TopicEntity> searchByName(String name, Pageable pageable);
}
