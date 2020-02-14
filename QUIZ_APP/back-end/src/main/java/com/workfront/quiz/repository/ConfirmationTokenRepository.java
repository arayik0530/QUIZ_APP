package com.workfront.quiz.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.workfront.quiz.entity.ConfirmationTokenEntity;

public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationTokenEntity, String> {
    ConfirmationTokenEntity findByText(String text);
}