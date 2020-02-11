package com.workfront.quiz.entity;

import com.workfront.quiz.entity.enums.UserRole;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "first_name", nullable = false, length = 100)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 100)
    private String lastName;

    @Column(name = "e_mail", nullable = false, unique = true)
    private String email;

    @Column(name = "phone", unique = true, length = 15)
    private String phone;

    @Column(name = "password", nullable = false)
    private String password;

    @OneToOne
    private ImageEntity profileImage;

    @OneToOne
    private SmallImageEntity smallImage;

    @Column(name = "active", nullable = false)
    private Boolean active = Boolean.FALSE;

    @ElementCollection(targetClass = UserRole.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<UserRole> roles = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = {CascadeType.REMOVE})
    private List<UpcomingQuizEntity> upcommingQuizes;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = {CascadeType.REMOVE})
    private List<QuizEntity> quizes;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserEntity)) return false;
        UserEntity that = (UserEntity) o;
        return isActive() == that.isActive() &&
                Objects.equals(getId(), that.getId()) &&
                Objects.equals(getFirstName(), that.getFirstName()) &&
                Objects.equals(getLastName(), that.getLastName()) &&
                Objects.equals(getEmail(), that.getEmail()) &&
                Objects.equals(getPhone(), that.getPhone()) &&
                Objects.equals(getPassword(), that.getPassword()) &&
                Objects.equals(getProfileImage(), that.getProfileImage()) &&
                Objects.equals(getSmallImage(), that.getSmallImage()) &&
                Objects.equals(getRoles(), that.getRoles()) &&
                Objects.equals(getQuizes(), that.getQuizes());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getFirstName(), getLastName(), getEmail(), getPhone(), getPassword(), getProfileImage(), getSmallImage(), isActive(), getRoles(), getQuizes());
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", password='" + password + '\'' +
                ", profileImage=" + profileImage +
                ", smallImage=" + smallImage +
                ", active=" + active +
                ", roles=" + roles +
                ", quizes=" + quizes +
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ImageEntity getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(ImageEntity profileImage) {
        this.profileImage = profileImage;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Set<UserRole> getRoles() {
        return roles;
    }

    public void setRoles(Set<UserRole> roles) {
        this.roles = roles;
    }

    public List<QuizEntity> getQuizes() {
        return quizes;
    }

    public void setQuizes(List<QuizEntity> quizes) {
        this.quizes = quizes;
    }

    public SmallImageEntity getSmallImage() {
        return smallImage;
    }

    public void setSmallImage(SmallImageEntity smallImage) {
        this.smallImage = smallImage;
    }
}
