package com.workfront.quiz.api;

import com.workfront.quiz.dto.PasswordChangingDto;
import com.workfront.quiz.dto.UserInfoDto;
import com.workfront.quiz.dto.UserRegistrationDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestParam;

public interface UserController {

    UserInfoDto getById(Long id);

    Page<UserInfoDto> search(String text, Pageable pageable);

    void remove(Long id);

    UserInfoDto update(UserInfoDto userInfoDto);

    void changePassword(PasswordChangingDto passwordChangingDto);

    UserInfoDto register(UserRegistrationDto registrationDto);
}
