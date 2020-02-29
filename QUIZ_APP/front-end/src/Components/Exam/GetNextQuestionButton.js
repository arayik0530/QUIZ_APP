import {connect} from "react-redux";
import Button from "@material-ui/core/Button";
import React, {useEffect} from "react";
import {getInitialState, selectAnswers, finisher} from "../../redux/actions";
import '../../Css/Question.css';
import {makeGet, makePost} from "../../utils/requestMaker";

const handleButtonClick = async (e, nextQuestionId, saveInitialData, selectedAnswers,
    currentQuestionId, questionNumber, countOfQuestions, answers,
    chooseAnswers, finish) => {
const answerIds = [];
selectedAnswers.forEach((sA) => answerIds.push(answers[sA].id));
makePost(`/api/quiz/${currentQuestionId}/question-answers`, null, JSON.stringify(answerIds))
.then(r => {
if (questionNumber === countOfQuestions) {
makePost(`/api/quiz/finish?quizId=${Number(localStorage.getItem('quizId'))}`, null, null)
.then(res => {
clearInterval(window.timerId);
finish(res);
localStorage.clear();
})
.catch(err => alert(err.message))
} else {
makeGet(`/api/quiz/next-question?nextQuestionId=${nextQuestionId}`, null, null)
.then(res => {
let qNumberIncremented = questionNumber + 1;
localStorage.setItem('questionNumber', String(qNumberIncremented));
localStorage.setItem('questionId', String(res.quizQuestionId));//TODO araj stex talis ei res.quizQuestionId
saveInitialData({
...res,
questionNumber: qNumberIncremented,
countOfQuestions: Number(localStorage.getItem('countOfQuestions')),
remainingTime: Number(localStorage.getItem('remainingTime')),
});
chooseAnswers([]);
})
.catch(err => alert(err.message))
}
})
};
const GetNextQuestionButton = ({
      selectedAnswers, text, questionNumber, countOfQuestions, saveInitialData,
      nextQuestionId, currentQuestionId, answers, chooseAnswers, finish}) => {
useEffect(() => {
window.addEventListener("keydown", handleKeyDown);
return function () {
window.removeEventListener("keydown", handleKeyDown);
}
}, [nextQuestionId, saveInitialData,
selectedAnswers, currentQuestionId, questionNumber, countOfQuestions, answers, chooseAnswers, finish]);
const disabled = !answers ? true : (!selectedAnswers ? true : !selectedAnswers.length);
function handleKeyDown(e) {
if (e.key === "Enter") {
if (!disabled) {
handleButtonClick(e, nextQuestionId, saveInitialData,
selectedAnswers, currentQuestionId, questionNumber, countOfQuestions, answers, chooseAnswers, finish)
.catch(e);
}
}
}
return <div className="nextButtonContainer" style={{display: 'flex', justifyContent: 'center'}}>
{text && <Button
disabled={disabled}
onClick={(e) => handleButtonClick(e, nextQuestionId, saveInitialData,
selectedAnswers, currentQuestionId, questionNumber, countOfQuestions, answers, chooseAnswers, finish)}
color="primary">{(questionNumber !== countOfQuestions) ? "next" : "finish"}
</Button>}
</div>
};
const mapStateToProps = (state) => ({
selectedAnswers: state.answerSelector.selectedAnswersOfCurrentQuestion,
questionNumber: state.initialDataReceiver.questionNumber,
nextQuestionId: state.initialDataReceiver.nextQuestionId,
countOfQuestions: state.initialDataReceiver.countOfQuestions,
text: state.initialDataReceiver.text,
answers: state.initialDataReceiver.answers,
currentQuestionId: state.initialDataReceiver.questionId
});
const mapDispatchToProps = (dispatch, ownProps) => ({
saveInitialData: function (data) {
dispatch(getInitialState(data));
},
chooseAnswers: function (selectedAnswers) {
dispatch(selectAnswers(selectedAnswers));
},
finish: function (result) {
dispatch(finisher(result));
}
});
export default connect(mapStateToProps, mapDispatchToProps)(GetNextQuestionButton);