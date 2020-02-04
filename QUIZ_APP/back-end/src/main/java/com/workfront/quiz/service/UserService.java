package com.workfront.quiz.service;

import com.workfront.quiz.dto.PasswordChangingDto;
import com.workfront.quiz.dto.UserInfoDto;
import com.workfront.quiz.dto.UserRegistrationDto;
import com.workfront.quiz.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {

    UserInfoDto findById(Long id);

    Page<UserInfoDto> searchByName(String name, Pageable pageable);

    void remove(Long id);

    UserInfoDto update(UserInfoDto user);

    void updatePassword(PasswordChangingDto passwordChangingDto);

    UserInfoDto register(UserRegistrationDto registrationDto);
}
