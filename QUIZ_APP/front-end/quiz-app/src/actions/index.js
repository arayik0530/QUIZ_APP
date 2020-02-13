import {CHANGE_QUESTION, SELECT_ANSWER} from "../constants";

let nextQuestionId = 0;

export function changeQuestion(direction){
    return ({
        type: CHANGE_QUESTION,
        direction
    })
}

export function selectAnswers(...ids){
    type: SELECT_ANSWER,
        ids:
}

