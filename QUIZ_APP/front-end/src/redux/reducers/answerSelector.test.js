import React from "react";
import answerSelector from '../reducers/answerSelector';
import { selectAnswers } from "../actions";

describe('answerSelector reducer tests', () => {
    let initialState = {};

    test('new state to match initialState', () => {
        let action = {};
        let newState = answerSelector(initialState, action);
        expect(newState).toBe(initialState);
    });

    test('newState.selectedAnswersOfCurrentQuestion to match mockData', () => {
        let mockData = [1, 15];

        let action = selectAnswers(mockData);
        let newState = answerSelector(initialState, action);
        expect(newState.selectedAnswersOfCurrentQuestion).toMatchObject(mockData);
    });
});