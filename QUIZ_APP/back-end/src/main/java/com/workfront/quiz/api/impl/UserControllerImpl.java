package com.workfront.quiz.api.impl;

import com.workfront.quiz.api.UserController;
import com.workfront.quiz.entity.UserEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user/")
public class UserControllerImpl implements UserController {

    @Override
    @GetMapping("{userId}")
    public UserEntity getById(@PathVariable Long userId) {

        UserEntity artak = new UserEntity();
        artak.setFirstName("Artak");
        artak.setLastName("Gevorgyan");

        return artak;
    }
}
