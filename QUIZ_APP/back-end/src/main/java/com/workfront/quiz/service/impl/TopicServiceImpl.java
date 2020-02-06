package com.workfront.quiz.service.impl;

import com.workfront.quiz.dto.topic.TopicDto;
import com.workfront.quiz.entity.TopicEntity;
import com.workfront.quiz.repository.TopicRepository;
import com.workfront.quiz.service.TopicService;
import com.workfront.quiz.service.util.exception.TopicNotFoundException;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class TopicServiceImpl implements TopicService {

    private TopicRepository topicRepository;

    public TopicServiceImpl(TopicRepository topicRepository) {
        this.topicRepository = topicRepository;
    }

    @Override
    public TopicDto findById(Long id) {
        Optional<TopicEntity> byId = topicRepository.findById(id);
        if(byId.isPresent()){
            return TopicDto.mapFromEntity(byId.get());
        }
        else {
            throw new TopicNotFoundException(id);
        }
    }

    @Override
    public Page<TopicDto> searchByName(String name, Pageable pageable) {
        Page<TopicEntity> topics = topicRepository.searchByName(name, pageable);
        return topics.map(TopicDto::mapFromEntity);
    }

    @Override
    public Page<TopicDto> getAllTpoics(Pageable pageable) {

        Page<TopicEntity> topics = topicRepository.findAll(pageable);
        return topics.map(TopicDto::mapFromEntity);
    }

    @Override
    public void remove(Long id) {
        Optional<TopicEntity> byId = topicRepository.findById(id);
        if(byId.isPresent()){
            topicRepository.deleteById(id);
        }

        else {
            throw new TopicNotFoundException(id);
        }
    }

    @Override
    public void update(TopicDto topic) { //TODO id?
        Optional<TopicEntity> byId = topicRepository.findById(topic.getId());
        if(byId.isPresent()){
            TopicEntity topicEntity = byId.get();
            topic.toEntity(topicEntity);
            topicRepository.save(topicEntity);
        }
        else {
            throw new TopicNotFoundException(topic.getId());
        }
    }
}
