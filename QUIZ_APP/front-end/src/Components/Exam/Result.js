import React from 'react';
export default function Result(props) {
  
    return (
        <>
            <div style={{marginTop: '5vh', marginLeft: '10vw'}}>
                <h3>Topic: {props.topic} <br/> Percent: {props.successPercent}</h3>
                <h5>Start: {props.startTime.replace('T', ' ').substring(0, 19)} - End: {props.endTime.replace('T', ' ').substring(0, 19)}</h5>
                <br/>
                <br/>
                <ol>
                    {props.quizQuestions.map((quizQuestion, index) => {
                        const answeredIs = [];
                        quizQuestion.answers.map(ans => answeredIs.push(ans.id));
                        return (
                            <li key={index}>
                                <b>{quizQuestion.questionDto.text}</b>
                                <ol>
                                    {quizQuestion.questionDto.answers.map((answer, i) => (
                                        <li key={i}>
                                            <p style = {{color: answeredIs.includes(answer.id) ? "blue" : "grey"}}>{answer.text}</p>
                                        </li>
                                    ))}
                                </ol>
                            </li>)
                        }
                    )}
                </ol>
            </div>
        </>
    );
}