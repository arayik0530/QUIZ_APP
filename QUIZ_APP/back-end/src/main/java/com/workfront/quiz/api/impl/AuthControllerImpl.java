package com.workfront.quiz.api.impl;

import com.workfront.quiz.api.AuthController;
import com.workfront.quiz.dto.user.LoginRequestDto;
import com.workfront.quiz.dto.user.UserInfoDto;
import com.workfront.quiz.dto.user.UserRegistrationDto;
import com.workfront.quiz.entity.ConfirmationTokenEntity;
import com.workfront.quiz.entity.UserEntity;
import com.workfront.quiz.security.jwt.JwtTokenProvider;
import com.workfront.quiz.security.jwt.JwtUser;
import com.workfront.quiz.service.ConfirmationTokenService;
import com.workfront.quiz.service.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/api/auth/")
public class AuthControllerImpl implements AuthController {

    private UserService userService;
    private AuthenticationManager authenticationManager;
    private JwtTokenProvider jwtTokenProvider;
    private ConfirmationTokenService confirmationTokenService;

    public AuthControllerImpl(UserService userService, AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, ConfirmationTokenService confirmationTokenService) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.confirmationTokenService = confirmationTokenService;
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

    @GetMapping("confirm-account/{confirmationToken}") //TODO jshtel aranc MODELANDVIEW chilini?
    public ModelAndView confirmUserAccount(ModelAndView modelAndView, @PathVariable String confirmationToken)
    {
        ConfirmationTokenEntity token = confirmationTokenService.findByConfirmationToken(confirmationToken);

        if(token != null)
        {
            String email = token.getUser().getEmail();

            UserEntity user = userService.findByEmail(email).toEntity();
            user.setActive(true);
            userService.update(UserInfoDto.mapFromEntity(user));
            modelAndView.setViewName("accountVerified");
        }
        else
        {
            modelAndView.addObject("message","The link is invalid or broken!");
            modelAndView.setViewName("error");
        }

        return modelAndView;
    }
}
