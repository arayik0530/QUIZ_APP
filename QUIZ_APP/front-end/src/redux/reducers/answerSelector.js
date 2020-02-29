import { SELECT_ANSWERS } from "../actionTypes";

const initialState = {};

export default function (state = initialState, action) {
    switch (action.type) {
        case SELECT_ANSWERS: {
            const { selectedAnswers } = action.payload;
            return {
                ...state,
                selectedAnswersOfCurrentQuestion : selectedAnswers
            };
        }

        default: return state;
    }
}