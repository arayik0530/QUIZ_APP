import {FINISH} from "../actionTypes";

const initialState = {
    result: null
};

export default function (state = initialState, action) {
    switch (action.type) {
        case FINISH: {
            return {
                ...state, ...action.payload
            }
        }
        default: return state;
    }
}