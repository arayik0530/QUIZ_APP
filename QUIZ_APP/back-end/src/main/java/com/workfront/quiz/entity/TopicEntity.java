package com.workfront.quiz.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "topics")
@Data
public class TopicEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "topic", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private List<QuestionEntity> questions;

}
