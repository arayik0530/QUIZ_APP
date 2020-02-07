package com.workfront.quiz.service.impl;

import com.workfront.quiz.dto.question.QuestionDto;
import com.workfront.quiz.dto.quiz.QuizDto;
import com.workfront.quiz.entity.QuizEntity;
import com.workfront.quiz.entity.TopicEntity;
import com.workfront.quiz.repository.QuizRepository;
import com.workfront.quiz.service.QuizService;
import com.workfront.quiz.service.util.exception.QuizNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class QuizServiceImpl implements QuizService {

    private QuizRepository quizRepository;

    private QuestionServiceImpl questionService;

    public QuizServiceImpl(QuizRepository quizRepository) {
        this.quizRepository = quizRepository;
        this.questionService = questionService;   //TODO jshtel xi a boxoqum
    }

    @Override
    public QuizDto findById(Long id) {
        Optional<QuizEntity> byId = quizRepository.findById(id);
        if(byId.isPresent()){
            return QuizDto.mapFromEntity(byId.get());
        }
        else {

            throw new QuizNotFoundException(id);
        }
    }

    @Override
    public Page<QuizDto> getAllQuizes(Pageable pageable) {
        Page<QuizEntity> quizes = quizRepository.findAll(pageable);
        return quizes.map(QuizDto::mapFromEntity);
    }

    @Override
    public Page<QuizDto> getQuizesByTopic(TopicEntity topic, Pageable pageable) {
        Page<QuizEntity> quizes = quizRepository.findAllByTopic(topic, pageable);
        return quizes.map(QuizDto::mapFromEntity);
    }

    @Override
    public void remove(Long id) {
        Optional<QuizEntity> byId = quizRepository.findById(id);
        if(byId.isPresent()){
            quizRepository.deleteById(id);
        }

        else {
            throw new QuizNotFoundException(id);
        }
    }

    @Override
    public void update(QuizDto quiz) {
        Optional<QuizEntity> byId = quizRepository.findById(quiz.getId());
        if(byId.isPresent()){
            QuizEntity quizEntity = byId.get();
            quiz.toEntity(quizEntity);
            quizRepository.save(quizEntity);
        }
        else {
            throw new QuizNotFoundException(quiz.getId());
        }
    }

    @Override
    public Set<QuestionDto> generateQuestions(TopicEntity topic, int count) {
        Set<QuestionDto> quizquestions = new HashSet<>();
        while (quizquestions.size() != count){
            quizquestions.add(questionService.generateQuestion(topic));
        }

        return quizquestions;
    }
}
