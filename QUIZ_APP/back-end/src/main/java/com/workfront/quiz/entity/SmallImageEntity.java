package com.workfront.quiz.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Data
@Table(name = "small_images")
public class SmallImageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;


    @Column(name = "picture")
    @Lob
    @Basic
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private byte[] picture;

}
