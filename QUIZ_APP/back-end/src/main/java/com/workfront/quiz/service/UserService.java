package com.workfront.quiz.service;

import com.workfront.quiz.dto.user.PasswordChangingDto;
import com.workfront.quiz.dto.user.UserInfoDto;
import com.workfront.quiz.dto.user.UserRegistrationDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;

public interface UserService {

    UserInfoDto findById(Long id);

    UserInfoDto findByEmail(String email);

    Page<UserInfoDto> searchByName(String name, Pageable pageable);

    Page<UserInfoDto> getAllUsers(Pageable pageable);

    void remove(Long id);

    UserInfoDto update(UserInfoDto user);  //TODO or void?

    void updatePassword(PasswordChangingDto passwordChangingDto);

    UserInfoDto register(UserRegistrationDto registrationDto);

    Long getMe();

    String generateToken(String email);

    @Transactional
    void activateByEmailToken(String token);
}
