package com.workfront.quiz.service.impl;

import com.workfront.quiz.dto.question.CreateQuestionDto;
import com.workfront.quiz.dto.question.QuestionDto;
import com.workfront.quiz.entity.AnswerEntity;
import com.workfront.quiz.entity.QuestionEntity;
import com.workfront.quiz.entity.TopicEntity;
import com.workfront.quiz.repository.QuestionRepository;
import com.workfront.quiz.service.QuestionService;
import com.workfront.quiz.service.util.exception.InvalidAnswerCountException;
import com.workfront.quiz.service.util.exception.QuestionAlreadyExistException;
import com.workfront.quiz.service.util.exception.QuestionNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionServiceImpl implements QuestionService {

    private QuestionRepository questionRepository;

    public QuestionServiceImpl(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    @Override
    public QuestionDto findById(Long id) {
        Optional<QuestionEntity> byId = questionRepository.findById(id);
        if(byId.isPresent()){
            return QuestionDto.mapFromEntity(byId.get());
        }
        else {
            throw new QuestionNotFoundException(id);
        }
    }

    @Override
    public Page<QuestionDto> searchByText(String text, Pageable pageable) {
        Page<QuestionEntity> questions = questionRepository.searchByText(text, pageable);
        return questions.map(QuestionDto::mapFromEntity);
    }

    @Override
    public Page<QuestionDto> getAllQuestions(Pageable pageable) {
        Page<QuestionEntity> questions = questionRepository.findAll(pageable);
        return questions.map(QuestionDto::mapFromEntity);
    }

    @Override
    public Page<QuestionDto> getQuestionsByTopic(TopicEntity topic, Pageable pageable) {
        Page<QuestionEntity> questions = questionRepository.findAllByTopic(topic, pageable);
        return questions.map(QuestionDto::mapFromEntity);
    }

    @Override
    public void remove(Long id) {
        Optional<QuestionEntity> byId = questionRepository.findById(id);
        if(byId.isPresent()){
            questionRepository.deleteById(id);
        }

        else {
            throw new QuestionNotFoundException(id);
        }
    }

    @Override
    public void update(QuestionDto question) {
        Optional<QuestionEntity> byId = questionRepository.findById(question.getId());
        if(byId.isPresent()){
            QuestionEntity questionEntity = byId.get();
            questionEntity = question.toEntity();
            questionRepository.save(questionEntity);
        }
        else {
            throw new QuestionNotFoundException(question.getId());
        }
    }

    @Override
    public QuestionDto generateQuestion(TopicEntity topic) {
        Optional<QuestionEntity> byTopic = Optional.ofNullable(questionRepository.generateQuestion(topic));
        if (byTopic.isPresent()){
            return QuestionDto.mapFromEntity(byTopic.get());
        }
        else {
            throw new QuestionNotFoundException("Topic: " + topic.toString());
        }
    }

    @Override
    public void create(CreateQuestionDto question, int answerCount) {
        Optional<QuestionEntity> byText = Optional.ofNullable(questionRepository.searchByTextExact(question.getText()));
        if(byText.isPresent()){
            throw new QuestionAlreadyExistException(question.getText());
        }
        else {

            QuestionEntity questionEntity = question.toEntity();
            if(questionEntity.getAnswers().size() != answerCount){
                throw new InvalidAnswerCountException("Current count: " + questionEntity.getAnswers().size());
            }
            questionRepository.save(questionEntity);
        }
    }
}
