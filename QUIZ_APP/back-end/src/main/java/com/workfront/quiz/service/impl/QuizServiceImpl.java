package com.workfront.quiz.service.impl;

import com.workfront.quiz.dto.answer.AnswerDto;
import com.workfront.quiz.dto.question.QuestionDto;
import com.workfront.quiz.dto.quiz.*;
import com.workfront.quiz.entity.*;
import com.workfront.quiz.repository.*;
import com.workfront.quiz.service.QuestionService;
import com.workfront.quiz.service.QuizService;
import com.workfront.quiz.service.UserService;
import com.workfront.quiz.service.scheduler.QuizDurationChecker;
import com.workfront.quiz.service.util.exception.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class QuizServiceImpl implements QuizService {
    private UserService userService;

    private QuizRepository quizRepository;

    private QuestionService questionService;
    private TopicRepository topicRepository;

    private UpComingQuizRepository upComingQuizRepository;
    private UserRepository userRepository;
    private QuizQuestionRepository quizQuestionRepository;
    private  QuizDurationChecker quizDurationChecker;

    public QuizServiceImpl(UserService userService, QuizRepository quizRepository, QuestionService questionService,
                           UpComingQuizRepository upComingQuizRepository, UserRepository userRepository,
                           QuizQuestionRepository quizQuestionRepository, QuizDurationChecker quizDurationChecker) {
        this.userService = userService;
        this.quizRepository = quizRepository;
        this.questionService = questionService;
        this.upComingQuizRepository = upComingQuizRepository;
        this.userRepository = userRepository;
        this.quizQuestionRepository = quizQuestionRepository;
        this.quizDurationChecker = quizDurationChecker;
    }

    @Override
    public QuizDto findById(Long id) {
        QuizEntity quizEntity = quizRepository.findById(id).orElseThrow(() -> new QuizNotFoundException(id));
        return QuizDto.mapFromEntity(quizEntity);

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
        quizRepository.findById(id).orElseThrow(() -> new QuizNotFoundException(id));
        quizRepository.deleteById(id);
    }

    @Override
    public Page<QuizDtoShortInfo> getQuizesByUserId(Long userId, Pageable pageable) {
        UserEntity userEntity = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));

        Page<QuizEntity> allByUser = quizRepository.findAllByUser(userEntity, pageable);
        return allByUser.map(QuizDtoShortInfo::mapFromEntity);
    }


    @Override
    @Transactional
    public QuestionDto generateQuiz(Long upComingQuizId) {
        int startId = 0;
        UpcomingQuizEntity upcomingQuizEntity = upComingQuizRepository.findById(upComingQuizId)
                .orElseThrow(() -> new UpcomingQuizNotFoundException(upComingQuizId));

        List<QuestionEntity> questionEntities = questionService
                .generateQuestions(upcomingQuizEntity.getTopic().getId(), upcomingQuizEntity.getCount());

        Long userId = userService.getMe();

        UserEntity userEntity = userRepository.findById(userId).orElseThrow(
                () -> new UserNotFoundException(userId));

        QuizEntity quizEntity = new QuizEntity();
        quizEntity.setUser(userEntity);
        quizEntity.setDuration(upcomingQuizEntity.getDurationInMinutes());
        quizEntity.setTopic(upcomingQuizEntity.getTopic());

        quizRepository.save(quizEntity);
        quizDurationChecker.addQuizToCheckingList(quizEntity);

        for (QuestionEntity questionEntity : questionEntities) {
            QuizQuestionEntity quizQuestionEntity = new QuizQuestionEntity();
            quizQuestionEntity.setQuiz(quizEntity);
            quizQuestionEntity.setQuestion(questionEntity);
            quizQuestionRepository.save(quizQuestionEntity);
            quizEntity.getQuizQuestions().add(quizQuestionEntity);
        }

        quizRepository.save(quizEntity);
        upComingQuizRepository.delete(upcomingQuizEntity);

//TODO need to split his method

        QuestionDto questionDto = QuestionDto.mapFromEntity(quizEntity.getQuizQuestions().get(startId).getQuestion());
        if (quizEntity.getQuizQuestions().size() > startId + 1) {
            questionDto.setNextQuestionId(quizEntity.getQuizQuestions().get(++startId).getId());
        }
        questionDto.setQuizId(quizEntity.getId());

        return questionDto;//TODO check this, if quizQuestions is empty, throw exception
    }

    @Override
    public PastQuizInfoDto getQuizInfo(Long quizId) {
        PastQuizInfoDto pastQuizInfoDto = new PastQuizInfoDto();
        QuizEntity quizEntity = quizRepository.findById(quizId)
                .orElseThrow(() -> new QuizNotFoundException(quizId));

        pastQuizInfoDto.setId(quizEntity.getId());
        pastQuizInfoDto.setTopic(quizEntity.getTopic().getTitle());
        pastQuizInfoDto.setStartTime(quizEntity.getStartTime());
        pastQuizInfoDto.setEndTime(quizEntity.getEndTime());
        pastQuizInfoDto.setSuccessPercent(quizEntity.getSuccessPercent());

        for (QuizQuestionEntity quizQuestionEntity : quizEntity.getQuizQuestions()) {
            pastQuizInfoDto.getQuizQuestions()
                    .add(QuizQuestionDto.mapFromEntity(quizQuestionEntity));
        }
        return pastQuizInfoDto;
    }

    @Override
    public Page<UpcomingQuizDto> getUpcomingQuizes(Long userId, Pageable pageable) {
        UserEntity userEntity = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
        Page<UpcomingQuizEntity> allByUser = upComingQuizRepository.findAllByUser(userEntity, pageable);
        return allByUser.map(UpcomingQuizDto::mapFromEntity);
    }

    @Override
    @Transactional
    public void createUpcomingQuiz(UpcomingQuizCreationDto quizCreationDto) {
        UpcomingQuizEntity upcomingQuizEntity = new UpcomingQuizEntity();

        UserEntity userEntity = userRepository.findById(quizCreationDto.getUserId())
                .orElseThrow(() -> new UserNotFoundException(quizCreationDto.getUserId()));

        TopicEntity topicEntity = topicRepository.findById(quizCreationDto.getTopicId())
                .orElseThrow(() -> new TopicNotFoundException(quizCreationDto.getTopicId()));

        upcomingQuizEntity.setUser(userEntity);
        upcomingQuizEntity.setTopic(topicEntity);

        upcomingQuizEntity.setDeadline(quizCreationDto.getDeadline().atStartOfDay());
        upcomingQuizEntity.setCount(quizCreationDto.getQuestionCount());
        upcomingQuizEntity.setDurationInMinutes(quizCreationDto.getDurationInMinutes());
        upComingQuizRepository.save(upcomingQuizEntity);
        userEntity.getUpcomingQuizes().add(upcomingQuizEntity);
        userRepository.save(userEntity);
    }

    @Override
    public QuestionDto getNextQuestion(Long nextQuizQuestionId) {

        QuizQuestionEntity quizQuestionEntity = quizQuestionRepository.findById(nextQuizQuestionId)
                .orElseThrow(() -> new QuizQuestionNotFoundException(nextQuizQuestionId));

        QuestionDto questionDto = QuestionDto.mapFromEntity(quizQuestionEntity.getQuestion());

        QuizEntity quiz = quizQuestionEntity.getQuiz();

        if (Boolean.TRUE.equals(quiz.getIsFinished())) {
            throw new QuizFinishedException();
        }

        int offsetOfQuestion = quiz.getQuizQuestions().indexOf(quizQuestionEntity);
        if (quiz.getQuizQuestions().size() > offsetOfQuestion + 1) {
            Long nextId;
            if((null == quizQuestionEntity.getGivenAnswers()) || (quizQuestionEntity.getGivenAnswers().isEmpty())) //TODO esi em poxel
                nextId = quiz.getQuizQuestions().get(++offsetOfQuestion).getId();
            else
                nextId = quiz.getQuizQuestions().get(offsetOfQuestion).getId();
            questionDto.setNextQuestionId(nextId);
            return questionDto;
        }
        return questionDto;
    }

    @Override
    @Transactional
    public void computePercentage(Long quizId) {
        QuizEntity quizEntity = quizRepository.findById(quizId).orElseThrow(() -> new QuizNotFoundException(quizId));

        List<QuizQuestionEntity> quizQuestions = quizEntity.getQuizQuestions();
        int answersCount = quizQuestions.size();//for computing percentage
        double userScoreCount = 0;
        for (QuizQuestionEntity quizQuestion : quizQuestions) {
            QuestionEntity question = quizQuestion.getQuestion();

            int trueAnswersCount = getTrueAnswersCount(question.getAnswers());
            int usersTrueAnswers = getTrueAnswersCount(quizQuestion.getGivenAnswers());
            double answerScore = (double) usersTrueAnswers / trueAnswersCount;
            userScoreCount += answerScore;
        }
        Double percent = (userScoreCount / quizQuestions.size()) * 100;
        quizEntity.setSuccessPercent(percent);
        quizRepository.save(quizEntity);
    }

    private int getTrueAnswersCount(List<AnswerEntity> answers) {
        int count = 0;
        for (AnswerEntity answerEntity : answers) {
            if (answerEntity.getIsRight()) {
                count++;
            }
        }
        return count;
    }


    @Override
    @Transactional
    public void finishQuiz(Long quizId) {
        QuizEntity quizEntity = quizRepository.findById(quizId).orElseThrow(() -> new QuizNotFoundException(quizId));
        quizEntity.setIsFinished(Boolean.TRUE);
        computePercentage(quizId);
    }

    @Override
    public void failQuiz(Long upcomingQuizId) {
        UpcomingQuizEntity upcomingQuizEntity = upComingQuizRepository.findById(upcomingQuizId)
                .orElseThrow(() -> new UpcomingQuizNotFoundException(upcomingQuizId));


        UserEntity userEntity = userRepository.findById(userService.getMe()).orElseThrow(
                () -> new UserNotFoundException(userService.getMe()));

        QuizEntity quizEntity = new QuizEntity();
        quizEntity.setUser(userEntity);
        quizEntity.setDuration(upcomingQuizEntity.getDurationInMinutes());
        quizEntity.setTopic(upcomingQuizEntity.getTopic());

        QuizEntity savedQuiz = quizRepository.save(quizEntity);
        finishQuiz(savedQuiz.getId());
        upComingQuizRepository.deleteById(upcomingQuizId);
    }

    @Override
    @Transactional
    public void answerToQuestion(Long questionId, List<AnswerDto> answerDtos) {//TODO esi em poxel
        QuizQuestionEntity quizQuestion;
        Optional<QuizQuestionEntity> byId = quizQuestionRepository.findById(questionId);
        if(byId.isPresent()){
            quizQuestion = byId.get();

            QuestionEntity question = quizQuestion.getQuestion();

            List<AnswerEntity> answers = question.getAnswers();

            for (AnswerEntity answer: answers){
                for (AnswerDto answerDto: answerDtos) {
                    if(answer.getText().equalsIgnoreCase(answerDto.getText())){
                        quizQuestion.getGivenAnswers().add(answer);
                    }
                }
            }
        }
        else
            throw new QuizQuestionNotFoundException(questionId);

        quizQuestionRepository.save(quizQuestion);
    }
}