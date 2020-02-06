package com.workfront.quiz.dto.user;

import com.workfront.quiz.entity.UserEntity;

public class UserRegistrationDto {
   public UserEntity toEntity() {
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(this.email);
        userEntity.setPassword(this.password);
        userEntity.setFirstName(this.firstName);
        userEntity.setLastName(this.lastName);
        return userEntity;
    }

    private String email;

    private String password;

    private String firstName;

    private String lastName;

    @Override
    public String toString() {
        return "UserRegistrationDto{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
