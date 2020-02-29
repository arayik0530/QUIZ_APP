import {FINISH, INITIAL_DATA_READY, SELECT_ANSWERS, UPDATE_TIMER} from "./actionTypes";


export const selectAnswers = (selectedAnswers) => ({
    type: SELECT_ANSWERS,
    payload: {
        selectedAnswers
    }
});

export const getInitialState = (data) => ({
        type: INITIAL_DATA_READY,
        payload: {
            questionId: data.quizQuestionId,
            text: data.text,
            answers: data.answers,
            isMulti: data.isMultiAnswer,
            nextQuestionId: data.nextQuizQuestionId,
            questionNumber: data.questionNumber,
            selectedAnswersOfCurrentQuestion: [],
            remainingTime: data.remainingTime,
            countOfQuestions: data.countOfQuestions
        }
    });

export const updateTimer = (remainingTime) => ({
    type: UPDATE_TIMER,
    payload: {
        remainingTime
    }
});

export const finisher = (result) => ({
    type: FINISH,
    payload: {
        result
    }
});

