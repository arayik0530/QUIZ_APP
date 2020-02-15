package com.workfront.quiz.service.impl;

import com.workfront.quiz.dto.user.PasswordChangingDto;
import com.workfront.quiz.dto.user.UserInfoDto;
import com.workfront.quiz.dto.user.UserRegistrationDto;
import com.workfront.quiz.entity.ConfirmationTokenEntity;
import com.workfront.quiz.entity.UserEntity;
import com.workfront.quiz.entity.enums.UserRole;
import com.workfront.quiz.repository.ConfirmationTokenRepository;
import com.workfront.quiz.repository.UserRepository;
import com.workfront.quiz.security.jwt.JwtUser;
import com.workfront.quiz.service.UserService;
import com.workfront.quiz.service.util.exception.InvalidTokenException;
import com.workfront.quiz.service.util.exception.UserAlreadyExistsException;
import com.workfront.quiz.service.util.exception.UserNotFoundException;
import com.workfront.quiz.service.util.exception.WrongPasswordException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private ConfirmationTokenRepository tokenRepository;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder,
                           ConfirmationTokenRepository tokenRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenRepository = tokenRepository;
    }

    @Override
    public UserInfoDto findById(Long id) {
        Optional<UserEntity> byId = userRepository.findById(id);
        if (byId.isPresent()) {
            return UserInfoDto.mapFromEntity(byId.get());
        } else {
            throw new UserNotFoundException(id);
        }
    }

    @Override
    public UserInfoDto findByEmail(String email) {
        Optional<UserEntity> byEmail = userRepository.findByEmail(email);
        if (byEmail.isPresent()) {
            return UserInfoDto.mapFromEntity(byEmail.get());
        } else {
            throw new UserNotFoundException(email);
        }
    }

    @Override
    public Page<UserInfoDto> searchByName(String name, Pageable pageable) {
        String[] names = name.split(" ");
        Page<UserEntity> users;
        if (names.length >= 2) {
            users = userRepository.searchByName(names[0], names[1], pageable);

        } else {
            users = userRepository.searchByName(name, name, pageable);
        }
        return users.map(UserInfoDto::mapFromEntity);
    }

    @Override
    public Page<UserInfoDto> getAllUsers(Pageable pageable) {

        Page<UserEntity> users = userRepository.findAll(pageable);

        return users.map(UserInfoDto::mapFromEntity);
    }

    @Override
    public void remove(Long id) {

        Optional<UserEntity> byId = userRepository.findById(id);
        if (byId.isPresent()) {
            userRepository.deleteById(id);
        } else {
            throw new UserNotFoundException(id);
        }

    }

    @Override
    public UserInfoDto update(UserInfoDto user) { //TODO jshtel es method@ sxala ashxatum, mek el imageId loading@ stex
        Optional<UserEntity> byId = userRepository.findById(user.getId());
        if (byId.isPresent()) {

            UserEntity userEntity = byId.get();
            user.toEntity(userEntity);
            userRepository.save(userEntity);

            return UserInfoDto.mapFromEntity(userEntity);
        } else {
            throw new UserNotFoundException(user.getId());
        }

    }

    @Override
    public void updatePassword(PasswordChangingDto passwordChangingDto) {
        Optional<UserEntity> byEmail = userRepository.findByEmail(passwordChangingDto.getEmail());
        if (byEmail.isPresent()) {
            UserEntity userEntity = byEmail.get();
            if (userEntity.getPassword().equals(passwordChangingDto.getOldPassword())) {
                userEntity.setPassword(passwordChangingDto.getNewPassword());
                userRepository.save(userEntity);
            } else {
                throw new WrongPasswordException();
            }

        } else {
            throw new UserNotFoundException(passwordChangingDto.getEmail());
        }
    }

    @Override
    public UserInfoDto register(UserRegistrationDto registrationDto) {
        Optional<UserEntity> byEmail = userRepository.findByEmail(registrationDto.getEmail());
        if (byEmail.isPresent()) {
            throw new UserAlreadyExistsException(registrationDto.getEmail());
        }

        UserEntity userEntity = registrationDto.toEntity();

        userEntity.getRoles().add(UserRole.USER);

        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));

        UserEntity savedEntity = userRepository.save(userEntity);

        return UserInfoDto.mapFromEntity(savedEntity);
    }

    @Override
    public Long getMe() {
        JwtUser user = (JwtUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return user.getId();
    }

    @Override
    @Transactional
    public String generateToken(String email) {
        UserEntity userEntity = userRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException(email));

        ConfirmationTokenEntity confirmationToken = new ConfirmationTokenEntity();
        confirmationToken.setUser(userEntity);
        ConfirmationTokenEntity saved = tokenRepository.save(confirmationToken);
        return saved.getText();
    }

    @Override
    @Transactional
    public void activateByEmailToken(String tokenText) {
        ConfirmationTokenEntity token = tokenRepository.findByText(tokenText).orElseThrow(InvalidTokenException::new);
        token.getUser().setActive(true);
        userRepository.save(token.getUser());
        tokenRepository.delete(token);
    }
}
