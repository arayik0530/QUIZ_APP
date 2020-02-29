import { INITIAL_DATA_READY } from "../actionTypes";

const initialState = {
    // questionId: data.id,
    // text: data.text,
    // answers: data.answers,
    // isMulti: data.isMultiAnswer,
    // nextQuestionId: data.nextQuizQuestionId,
    // questionNumber: data.questionNumber,
    // selectedAnswersOfCurrentQuestion: [],
    // remainingTime: data.remainingTime,
    // countOfQuestions: data.countOfQuestions
};

export default function (state = initialState, action) {
    switch (action.type) {
        case INITIAL_DATA_READY: {
            return {
                ...state, ...action.payload
            }
        }

        default: return state;
    }
}