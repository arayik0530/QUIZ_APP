package com.workfront.quiz.entity;

import java.util.UUID;
import javax.persistence.*;

import lombok.Data;

@Entity
@Data
@Table(name = "confirmation_token")
public class ConfirmationTokenEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private long id;

    @Column(name="text")
    private String text = UUID.randomUUID().toString();


    @OneToOne(targetEntity = UserEntity.class, fetch = FetchType.EAGER) //TODO ardyoq Eager jishta?
    @JoinColumn(nullable = false, name = "user_id")
    private UserEntity user;

}