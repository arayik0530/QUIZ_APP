package com.workfront.quiz.dto;

import com.workfront.quiz.entity.UserEntity;

public class UserInfoDto {
    public static  UserInfoDto mapFromEntity(UserEntity userEntity) {
        UserInfoDto userInfoDto = new UserInfoDto();
        userInfoDto.id = userEntity.getId();
        userInfoDto.email = userEntity.getEmail();
        userInfoDto.lastName = userEntity.getLastName();
        userInfoDto.firstName = userEntity.getFirstName();
        userInfoDto.role = userEntity.getRole().toString();
        return userInfoDto;
    }

    public UserEntity toEntity(UserEntity userEntity) {
        userEntity.setEmail(this.email);
        userEntity.setFirstName(this.firstName);
        userEntity.setLastName(this.lastName);
        return userEntity;
    }

    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private String role;


    @Override
    public String toString() {
        return "UserInfoDto{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", role='" + role + '\'' +
                '}';
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
