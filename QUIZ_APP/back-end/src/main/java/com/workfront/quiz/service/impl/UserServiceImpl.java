package com.workfront.quiz.service.impl;

import com.workfront.quiz.dto.PasswordChangingDto;
import com.workfront.quiz.dto.UserInfoDto;
import com.workfront.quiz.dto.UserRegistrationDto;
import com.workfront.quiz.entity.UserEntity;
import com.workfront.quiz.repository.UserRepository;
import com.workfront.quiz.service.UserService;
import com.workfront.quiz.service.util.exception.UserAlreadyExistsException;
import com.workfront.quiz.service.util.exception.UserNotFoundException;
import com.workfront.quiz.service.util.exception.WrongPasswordException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
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
    public Page<UserInfoDto> searchByName(String name, Pageable pageable) {
        String[] names = name.split(" ");
        Page<UserEntity> users;
        if (names.length >= 2) {
            users = userRepository.searchByName(names[0], names[1], pageable);

        } else {
            users = userRepository.searchByName(name, name, pageable);
        }
        return users.map(userEntity -> UserInfoDto.mapFromEntity(userEntity));
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
    public UserInfoDto update(UserInfoDto user) {
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
            //TODO check password encoding principles
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
            throw new UserAlreadyExistsException();
        }

        UserEntity userEntity = registrationDto.toEntity();
        UserEntity savedEntity = userRepository.save(userEntity);
        return UserInfoDto.mapFromEntity(savedEntity);
    }
}
