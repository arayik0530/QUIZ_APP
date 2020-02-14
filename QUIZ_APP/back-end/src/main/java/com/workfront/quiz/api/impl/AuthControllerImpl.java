package com.workfront.quiz.api.impl;

import com.workfront.quiz.api.AuthController;
import com.workfront.quiz.dto.user.LoginRequestDto;
import com.workfront.quiz.dto.user.UserInfoDto;
import com.workfront.quiz.dto.user.UserRegistrationDto;
import com.workfront.quiz.entity.ConfirmationTokenEntity;
import com.workfront.quiz.entity.UserEntity;
import com.workfront.quiz.repository.ConfirmationTokenRepository;
import com.workfront.quiz.repository.UserRepository;
import com.workfront.quiz.security.jwt.JwtTokenProvider;
import com.workfront.quiz.security.jwt.JwtUser;
import com.workfront.quiz.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/api/auth/")
@CrossOrigin(value="*")
public class AuthControllerImpl implements AuthController {

    private UserService userService;
    private AuthenticationManager authenticationManager;
    private JwtTokenProvider jwtTokenProvider;

    public AuthControllerImpl(UserService userService, AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
    }
    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ConfirmationTokenRepository confirmationTokenRepository;

    @Override
    @PostMapping("register")
    public void register(@RequestBody UserRegistrationDto registrationDto) {

        UserInfoDto user= userService.register(registrationDto);
        ConfirmationTokenEntity confirmationToken = new ConfirmationTokenEntity();
        confirmationToken.setUser(userRepository.findByEmail(user.getEmail()).get());
        confirmationTokenRepository.save(confirmationToken);
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(user.getEmail());
        msg.setText("To confirm your account, please click here : "
                +"http://localhost:8090/api/auth/confirm-account?token="+confirmationToken.getText());
        msg.setSubject("Email Confirmation");
        javaMailSender.send(msg);
    }

    @RequestMapping(value="/confirm-account", method= {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView confirmUserAccount(ModelAndView modelAndView, @RequestParam("token")String confirmationToken)
    {
        ConfirmationTokenEntity token = confirmationTokenRepository.findByText(confirmationToken);

        if(token != null)
        {
            UserEntity user = userRepository.findByEmail(token.getUser().getEmail()).get();
            user.setActive(true);
            userRepository.save(user);
            modelAndView.setViewName("accountVerified");
        }
        else
        {
            modelAndView.addObject("message","The link is invalid or broken!");
            modelAndView.setViewName("error");
        }

        return modelAndView;
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