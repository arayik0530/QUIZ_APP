package com.workfront.quiz;

import com.workfront.quiz.service.scheduler.QuizDurationChecker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class Bootstrap implements CommandLineRunner {
    private QuizDurationChecker quizDurationChecker;

    public Bootstrap(QuizDurationChecker quizDurationChecker) {
        this.quizDurationChecker = quizDurationChecker;
    }


    public static void main(String[] args) {
        SpringApplication.run(Bootstrap.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        quizDurationChecker.checkDuration();
    }
}
