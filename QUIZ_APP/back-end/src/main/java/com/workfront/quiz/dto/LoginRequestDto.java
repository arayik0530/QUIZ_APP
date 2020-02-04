package com.workfront.quiz.dto;

import com.workfront.quiz.entity.UserEntity;

public class LoginRequestDto {
    public UserEntity toEntity() {
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(this.email);
        userEntity.setPassword(this.password);
        return userEntity;
    }

    private String email;
    private String password;

    @Override
    public String toString() {
        return "LoginRequestDto{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
