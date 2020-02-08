package com.workfront.quiz.api.impl;

import com.workfront.quiz.api.AuthController;
import com.workfront.quiz.dto.user.LoginRequestDto;
import com.workfront.quiz.dto.user.UserInfoDto;
import com.workfront.quiz.dto.user.UserRegistrationDto;
import com.workfront.quiz.security.jwt.JwtTokenProvider;
import com.workfront.quiz.security.jwt.JwtUser;
import com.workfront.quiz.service.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth/")
public class AuthControllerImpl implements AuthController {
    private UserService userService;
    private AuthenticationManager authenticationManager;
    private JwtTokenProvider jwtTokenProvider;

    public AuthControllerImpl(UserService userService, AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    @PostMapping("register")
    public void register(@RequestBody UserRegistrationDto registrationDto) {
        userService.register(registrationDto);
    }

    @Override
    @PostMapping("login")
    public String login(@RequestBody LoginRequestDto loginRequestDto) {
        String email = loginRequestDto.getEmail();
        UserInfoDto userInfoDto = userService.findByEmail(email);

        JwtUser jwtUser = (JwtUser) authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        email, loginRequestDto.getPassword()))
                .getPrincipal();

        return jwtTokenProvider.createToken(jwtUser.getId(), jwtUser.getUsername(), userInfoDto.getRoles());

    }
}
