import {SELECT_ANSWER} from "./actionTypes";

export const selectAnswers = (...id) => ({
    type: SELECT_ANSWER,
    payload: {
        id
    }
});
