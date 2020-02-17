export const SELECT_ANSWER = 'SELECT_ANSWER';

const initialState = {
   question: "What is true about...",
    answers: [
        "Some answer...",
        "Some answer...",
        "Some answer...",
        "Some answer...",
    ]
};

export default function(state = initialState, action){
    return state;
}