package com.workfront.quiz.dto.user;

import com.workfront.quiz.entity.UserEntity;
import com.workfront.quiz.entity.ImageEntity;
import lombok.Data;

import java.util.Objects;
import java.util.stream.Stream;

@Data
public class UserInfoDto {
    public static  UserInfoDto mapFromEntity(UserEntity userEntity) {
        UserInfoDto userInfoDto = new UserInfoDto();
        userInfoDto.id = userEntity.getId();
        userInfoDto.email = userEntity.getEmail();
        userInfoDto.lastName = userEntity.getLastName();
        userInfoDto.firstName = userEntity.getFirstName();
        userInfoDto.phone = userEntity.getPhone();
        userInfoDto.active = userEntity.getActive();

        userInfoDto.roles = Stream.of(userEntity.getRoles())
                .map(Object::toString).toArray(String[]::new); // TODO jshtel roles petqa te che?

        userInfoDto.image = userEntity.getProfileImage();// TODO jshtel es togh@
        return userInfoDto;
    }

    public UserEntity toEntity() {
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(this.email);
        userEntity.setFirstName(this.firstName);
        userEntity.setLastName(this.lastName);
        userEntity.setProfileImage(this.image); // TODO jshtel es togh@
        userEntity.setPhone(this.phone); // TODO jshtel es togh@
        userEntity.setActive(this.active);
        return userEntity;
    }

    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private String phone;

    private String[] roles;

    private ImageEntity image;

    private Boolean active;
}
