package com.workfront.quiz.dto.topic;

import com.workfront.quiz.entity.TopicEntity;

import java.util.Objects;

public class TopicDto {

    private String name;

    private Long id;


    public TopicEntity toEntity(TopicEntity topic){
        topic.setName(this.name);
        topic.setId(this.id);
        return topic;
    }

    public static TopicDto mapFromEntity(TopicEntity topic){
        TopicDto topicDto = new TopicDto();
        topicDto.id = topic.getId();
        topicDto.name = topic.getName();
        return topicDto;
    }

    @Override
    public String toString() {
        return "TopicDto{" +
                "name='" + name + '\'' +
                ", id=" + id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TopicDto)) return false;
        TopicDto topicDto = (TopicDto) o;
        return Objects.equals(getName(), topicDto.getName()) &&
                Objects.equals(getId(), topicDto.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getId());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
