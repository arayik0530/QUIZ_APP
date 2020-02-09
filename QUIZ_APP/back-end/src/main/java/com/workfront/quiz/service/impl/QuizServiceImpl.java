package com.workfront.quiz.service.impl;

import com.workfront.quiz.dto.question.QuestionDto;
import com.workfront.quiz.dto.quiz.QuizDto;
import com.workfront.quiz.entity.*;
import com.workfront.quiz.repository.QuizRepository;
import com.workfront.quiz.repository.UpComingQuizRepository;
import com.workfront.quiz.repository.UserRepository;
import com.workfront.quiz.security.jwt.JwtUser;
import com.workfront.quiz.service.QuestionService;
import com.workfront.quiz.service.QuizService;
import com.workfront.quiz.service.util.exception.QuizNotFoundException;
import com.workfront.quiz.service.util.exception.UpcomingQuizNotFoundException;
import com.workfront.quiz.service.util.exception.UserNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class QuizServiceImpl implements QuizService {

    private QuizRepository quizRepository;

    private QuestionService questionService;

    private UpComingQuizRepository upComingQuizRepository;
    private UserRepository userRepository;

    public QuizServiceImpl(QuizRepository quizRepository, QuestionService questionService,
                           UpComingQuizRepository upComingQuizRepository, UserRepository userRepository) {
        this.quizRepository = quizRepository;
        this.questionService = questionService;
        this.upComingQuizRepository = upComingQuizRepository;
        this.userRepository = userRepository;
    }

    @Override
    public QuizDto findById(Long id) {
        Optional<QuizEntity> byId = quizRepository.findById(id);
        if (byId.isPresent()) {
            return QuizDto.mapFromEntity(byId.get());
        } else {

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
    @Transactional
    public void remove(Long id) {
        Optional<QuizEntity> byId = quizRepository.findById(id);
        if (byId.isPresent()) {
            quizRepository.deleteById(id);
        } else {
            throw new QuizNotFoundException(id);
        }
    }

    @Override
    @Transactional
    public void update(QuizDto quiz) {
        Optional<QuizEntity> byId = quizRepository.findById(quiz.getId());
        if (byId.isPresent()) {
            QuizEntity quizEntity = byId.get();
            quiz.toEntity(quizEntity);
            quizRepository.save(quizEntity);
        } else {
            throw new QuizNotFoundException(quiz.getId());
        }
    }

    @Override
    @Transactional
    public Collection<QuestionDto> generateQuiz(Long upComingQuizId) {
        //TODO vercumem enq topic id-n quiz-ic
        //TODO jnjum enq upcomingquiz entity-n
        //TODO stexcum enq QuizEntity
        ///TODO vercum enq harcer random-ov DB-ic, kcum enq quiz-in,
        //TODO veradarcnum enq et harcer@
        UpcomingQuizEntity upcomingQuizEntity = upComingQuizRepository.findById(upComingQuizId)
                .orElseThrow(() -> new UpcomingQuizNotFoundException(upComingQuizId));
        Collection<QuestionEntity> questionEntities = questionService
                .generateQuestions(upcomingQuizEntity.getTopic().getId());
        JwtUser authentication = (JwtUser) SecurityContextHolder.getContext().getAuthentication();
        UserEntity userEntity = userRepository.findById(authentication.getId()).orElseThrow(UserNotFoundException);
        QuizEntity quizEntity = new QuizEntity();
        quizEntity.setUser(userEntity);
        //TODO mnacac set()-er@
        for (QuestionEntity questionEntity : questionEntities ) {
            QuizQuestionEntity quizQuestionEntity = new QuizQuestionEntity();
            quizQuestionEntity.setQuiz(quizEntity);
            quizQuestionEntity.setQuestion(questionEntity);
            //TODO save in repo
        }
    }
}
