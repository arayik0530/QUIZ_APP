import { combineReducers } from "redux";
import answerSelector from "./answerSelector";
import initialDataReceiver from "./initialDataReceiver";
import remainingTimeReceiver from "./remainingTimeReceiver";
import finisher from "./finisher";

export default combineReducers({initialDataReceiver, answerSelector, remainingTimeReceiver, finisher});
