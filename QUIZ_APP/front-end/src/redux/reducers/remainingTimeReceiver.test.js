import React from "react";
import remainingTimeReceiver from '../reducers/remainingTimeReceiver';
import { updateTimer } from "../actions";

describe('remainingTimeReceiver reducer tests', () => {
    let initialState = {};

    test('new state to match initialState', () => {
        let action = {};
        let newState = remainingTimeReceiver(initialState, action);
        expect(newState).toBe(initialState);
    });

    test('newState to match initialState + mockData', () => {
        let mockData =  { remainingTime: 150 }

        let action = updateTimer(mockData);
        let newState = remainingTimeReceiver(initialState, action);
        expect(newState).toMatchObject({...initialState, ...action.payload});
    });
});