package com.workfront.quiz.service;

import com.workfront.quiz.dto.user.PasswordChangingDto;
import com.workfront.quiz.dto.user.UserInfoDto;
import com.workfront.quiz.dto.user.UserRegistrationDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {

    UserInfoDto findById(Long id);

    Page<UserInfoDto> searchByName(String name, Pageable pageable);

    Page<UserInfoDto> getAllUsers(Pageable pageable);

    void remove(Long id);

    UserInfoDto update(UserInfoDto user);  //TODO or void?

    void updatePassword(PasswordChangingDto passwordChangingDto);

    UserInfoDto register(UserRegistrationDto registrationDto);
}
