package com.workfront.quiz.service.scheduler;

import com.workfront.quiz.entity.QuizEntity;
import com.workfront.quiz.repository.QuizRepository;
import com.workfront.quiz.service.QuizService;
import com.workfront.quiz.service.util.exception.QuizNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Component
@RequiredArgsConstructor
public class QuizDurationChecker {
    private static final int MINUTE = 60 * 1000;

    private final QuizService quizService;
    private final QuizRepository quizRepository;

    private List<QuizEntity> quizList = new CopyOnWriteArrayList<>();

    public void addQuizToCheckingList(QuizEntity quizEntity) {
        quizList.add(quizEntity);
    }



    @Async
    public void checkDuration() {
        while (true) {

            try {
                Thread.sleep(MINUTE);
            } catch (InterruptedException e) {

            }
            for (QuizEntity quiz : quizList) {

                LocalDateTime expectedFinishTime = quiz.getStartTime().plusMinutes(quiz.getDuration()).plusMinutes(1L);

                if (LocalDateTime.now().isAfter(expectedFinishTime)) {
                    Long quizId = quiz.getId();
                    quiz = quizRepository.findById(quizId)
                            .orElseThrow(() -> new QuizNotFoundException(quizId));//refreshing information about finish
                    if (quiz.getIsFinished()) {
                        quizList.remove(quiz);
                    } else {
                        quizService.finishQuiz(quizId);
                        quizList.remove(quiz);
                    }
                }

            }


        }
    }
}

