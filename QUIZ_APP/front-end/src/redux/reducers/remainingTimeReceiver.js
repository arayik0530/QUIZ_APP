import { UPDATE_TIMER } from "../actionTypes";

const initialState = {};

export default function (state = initialState, action) {
    switch (action.type) {
        case UPDATE_TIMER: {
            return {
                ...state,
                remainingTime: action.payload.remainingTime
            }
        }

        default: return state;
    }
}