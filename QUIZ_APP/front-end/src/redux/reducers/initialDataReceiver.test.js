import React from "react";
import initialDataReceiver from '../reducers/initialDataReceiver';
import {getInitialState} from "../actions";

describe('initialDataReceiver reducer tests', () => {
    let initialState = {};

    test('new state to match initialState', () => {
        let action = {};
        let newState = initialDataReceiver(initialState, action);
        expect(newState).toBe(initialState);
    });

    test('newState to match initialState + mockData', () => {
        let mockData =  {
            quizQuestionId: 11,
            text: 'How does hoisting work in js',
            answers: [{id: 125, text: "some answer 1"}, {id: 58, text: "some answer 2"}],
            isMultiAnswer: true,
            nextQuizQuestionId: 12,
            questionNumber: 52,
            selectedAnswersOfCurrentQuestion: [],
            remainingTime: 10,
            countOfQuestions: 10
        }

        let action = getInitialState(mockData);
        let newState = initialDataReceiver(initialState, action);
        expect(newState).toMatchObject({...initialState, ...action.payload});
    });
});