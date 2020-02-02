package com.workfront.quiz.api;

import com.workfront.quiz.entity.UserEntity;

public interface UserController {
    UserEntity getById(Long id);

}
