package com.workfront.quiz.entity;


import javax.persistence.*;

@Entity
@Table(name = "images")
public class ImageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;



    @Column(name = "picture")
    @Lob
    @Basic(fetch = FetchType.LAZY)
    private byte[] picture;

}
