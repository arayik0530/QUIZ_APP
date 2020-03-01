import React from "react";
import Finisher from '../reducers/finisher';
import {finisher} from "../actions";


describe('Finisher reducer tests', () => {
    let initialState = {
        result: null
    };
    test('new state to match initialState', () => {
        let action = {};
        let newState = Finisher(initialState, action);
        expect(newState).toBe(initialState);
    });

    test('newState.result would match mockData', () => {
        let mockData = {
            "id": 29,
            "topic": "java",
            "startTime": "2020-03-01T00:44:56.324",
            "endTime": "2020-03-01T00:45:13.599",
            "successPercent": 0.0,
            "quizQuestions": [
                {
                    "id": 206,
                    "quizId": 29,
                    "questionDto": {
                        "id": 24,
                        "quizQuestionId": null,
                        "topicId": 2,
                        "text": "How does a WHILE loop start in javascript 4?",
                        "answers": [
                            {
                                "id": 93,
                                "text": "while(i > 0)"
                            },
                            {
                                "id": 94,
                                "text": "while(true)"
                            },
                            {
                                "id": 95,
                                "text": "while(i = from 1 to 10)"
                            },
                            {
                                "id": 96,
                                "text": "while(i = from 1 to 10)"
                            }
                        ],
                        "isMultiAnswer": true,
                        "nextQuizQuestionId": null,
                        "quizId": null
                    },
                    "answers": [{
                        "id": 93,
                        "text": "while(i > 0)"
                    }]
                }
            ]
        }

        let action = finisher(mockData);
        let newState = Finisher(initialState, action);
        expect(newState.result).toMatchObject(mockData);
    });

});
