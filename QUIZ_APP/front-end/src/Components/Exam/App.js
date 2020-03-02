import React, {useEffect,useContext} from 'react';
import SingleChoice from "./SingleChoice";
import MultipleChoice from "./MultipleChoice";
import {connect} from "react-redux";
import '../../Css/Question.css';
import {getInitialState, selectAnswers, updateTimer} from "../../redux/actions";
import {makeGet} from "../../utils/requestMaker";
import GetNextQuestionButton from "./GetNextQuestionButton";
import Result from "./Result";
import {IdContext} from '../../Contexts/idcontext';

function App(props) {
    const {isMulti} = props;
    let initData;
    let remainingTime;
    let upComingQuizId = useContext(IdContext)||localStorage.getItem(); //TODO tiv@ test anelu hamara, id-n stanumenq erb user@ sxmuma upcomingquizi vra
    let questionNumber = 1;
    useEffect(() => {
        
        const questionId = localStorage.getItem('questionId');
        if (questionId == null) {
            makeGet(`/api/quiz/start/${upComingQuizId}`, null, null)
                .then(result => {
                    initData = result;
                })
                .then(res =>
                    makeGet(`/api/quiz/${initData.quizId}/info`, null, null)
                )
                .then(
                    r => {
                        remainingTime = r.durationInMinutes;
                        let countOfQuestions = r.countOfQuestions;
                        localStorage.setItem('questionId', String(initData.quizQuestionId));
                        localStorage.setItem('questionNumber', String(questionNumber));
                        localStorage.setItem('remainingTime', String(remainingTime));
                        localStorage.setItem('countOfQuestions', String(countOfQuestions));
                        localStorage.setItem('quizId', String(initData.quizId));
                        props.saveInitialData({
                            ...initData, questionNumber, countOfQuestions, remainingTime
                        });
                        window.timerId = setInterval(() => {
                            remainingTime = Number(localStorage.getItem('remainingTime')) - 1;
                            localStorage.setItem('remainingTime', String(remainingTime));
                            props.updateTime(remainingTime);
                        }, 60000);
                    }
                )
                .catch(err => alert("Can't start quiz..."));
        } else {
            makeGet(`/api/quiz/next-question?nextQuestionId=${Number(localStorage.getItem('questionId'))}`, null, null)
                .then(res => {
                    props.saveInitialData({
                        ...res,
                        countOfQuestions: Number(localStorage.getItem('countOfQuestions')),
                        remainingTime: Number(localStorage.getItem('remainingTime')),
                        questionNumber: Number(localStorage.getItem('questionNumber')),
                    });
                    window.timerId = setInterval(() => {
                        remainingTime = Number(localStorage.getItem('remainingTime')) - 1;
                        localStorage.setItem('remainingTime', String(remainingTime));
                        props.updateTime(remainingTime);
                    }, 60000);
                })
                .then(r => {
                    props.chooseAnswers([]);
                })
                .catch(err => alert(err.message));
        }
    }, []);
    if (props.result) {
        return (
            <Result {...props.result}/>
        );
    } else {
        return (
            <>
               {(props.remainingTime > 0) ? <p className="timer">Time  {Math.floor(props.remainingTime/60)} : {props.remainingTime%60}</p> :
                        <p className="timer">Time is over</p>}
                        {isMulti ? <MultipleChoice  {...props} /> : <SingleChoice {...props} />}
                        <div style={{display: 'flex', justifyContent: 'center'}}><GetNextQuestionButton/></div>
            </>
        );
    }
}
const mapStateToProps = (state) => {
    return {
        ...state.initialDataReceiver,
        ...state.answerSelector,
        ...state.remainingTimeReceiver,
        ...state.finisher
    }
};
const mapDispatchToProps = (dispatch, ownProps) => ({
    saveInitialData: function (data) {
        dispatch(getInitialState(data))
    },
    updateTime: function (remainingTime) {
        dispatch(updateTimer(remainingTime))
    },
    chooseAnswers: function (selectedAnswers) {
        dispatch(selectAnswers(selectedAnswers))
    }
});
export default connect(mapStateToProps, mapDispatchToProps)(App);