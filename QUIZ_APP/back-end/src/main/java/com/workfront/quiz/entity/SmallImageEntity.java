package com.workfront.quiz.entity;

import javax.persistence.*;

@Entity
@Table(name = "small_images")
public class SmallImageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;


    @Column(name = "picture")
    @Lob
    @Basic(fetch = FetchType.LAZY)
    private byte[] picture;

}
