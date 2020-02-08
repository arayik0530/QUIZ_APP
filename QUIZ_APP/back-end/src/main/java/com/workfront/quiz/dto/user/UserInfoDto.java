package com.workfront.quiz.dto.user;

import com.workfront.quiz.entity.UserEntity;
import com.workfront.quiz.entity.ImageEntity;

import java.util.Objects;
import java.util.stream.Stream;

public class UserInfoDto {
    public static  UserInfoDto mapFromEntity(UserEntity userEntity) {
        UserInfoDto userInfoDto = new UserInfoDto();
        userInfoDto.id = userEntity.getId();
        userInfoDto.email = userEntity.getEmail();
        userInfoDto.lastName = userEntity.getLastName();
        userInfoDto.firstName = userEntity.getFirstName();
        userInfoDto.phone = userEntity.getPhone();

        userInfoDto.roles = Stream.of(userEntity.getRoles())
                .map(Object::toString).toArray(String[]::new); // TODO jshtel roles petqa te che?

        userInfoDto.image = userEntity.getProfileImage();// TODO jshtel es togh@
        return userInfoDto;
    }

    public UserEntity toEntity(UserEntity userEntity) {
        userEntity.setEmail(this.email);
        userEntity.setFirstName(this.firstName);
        userEntity.setLastName(this.lastName);
        userEntity.setProfileImage(this.image); // TODO jshtel es togh@
        userEntity.setPhone(this.phone); // TODO jshtel es togh@
        return userEntity;
    }

    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private String phone;

    private String[] roles;

    private ImageEntity image;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserInfoDto)) return false;
        UserInfoDto that = (UserInfoDto) o;
        return Objects.equals(getId(), that.getId()) &&
                Objects.equals(getFirstName(), that.getFirstName()) &&
                Objects.equals(getLastName(), that.getLastName()) &&
                Objects.equals(getEmail(), that.getEmail()) &&
                Objects.equals(getPhone(), that.getPhone()) &&
                Objects.equals(getRoles(), that.getRoles()) &&
                Objects.equals(getImage(), that.getImage());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getFirstName(), getLastName(), getEmail(), getPhone(), getRoles(), getImage());
    }

    @Override
    public String toString() {
        return "UserInfoDto{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", roles='" + roles + '\'' +
                ", image=" + image +
                '}';
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public String[] getRoles() {
        return roles;
    }

    public void setRoles(String... roles) {
        this.roles = roles;
    }

    public ImageEntity getImage() {
        return image;
    }

    public void setImage(ImageEntity image) {
        this.image = image;
    }
}
