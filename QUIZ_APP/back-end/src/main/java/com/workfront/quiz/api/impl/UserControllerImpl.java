package com.workfront.quiz.api.impl;

import com.workfront.quiz.api.UserController;
import com.workfront.quiz.dto.PasswordChangingDto;
import com.workfront.quiz.dto.UserInfoDto;
import com.workfront.quiz.dto.UserRegistrationDto;
import com.workfront.quiz.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user/")
@CrossOrigin(value = "*")
public class UserControllerImpl implements UserController {
    private UserService userService;

    public UserControllerImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    @GetMapping("{id}")
    public UserInfoDto getById(@PathVariable Long id) {
        return userService.findById(id);
    }

    @Override
    @GetMapping("search")
    public Page<UserInfoDto> search(@RequestParam String text, @PageableDefault Pageable pageable) {
        return userService.searchByName(text, pageable);
    }

    @Override
    @DeleteMapping("{id}")
    public void remove(@PathVariable Long id) {
        userService.remove(id);
    }

    @Override
    @PutMapping("update")
    public UserInfoDto update(@RequestBody UserInfoDto userInfoDto) {
        return userService.update(userInfoDto);
    }

    @Override
    @PutMapping("change-password")
    public void changePassword(@RequestBody PasswordChangingDto passwordChangingDto) {
        userService.updatePassword(passwordChangingDto);
    }

    @Override
    @PostMapping("register")
    public UserInfoDto register(@RequestBody UserRegistrationDto registrationDto) {
       return userService.register(registrationDto);
    }
}
