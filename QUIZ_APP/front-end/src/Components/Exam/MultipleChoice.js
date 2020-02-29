import React from 'react';
import {makeStyles} from '@material-ui/core/styles';
import FormLabel from '@material-ui/core/FormLabel';
import FormControl from '@material-ui/core/FormControl';
import FormGroup from '@material-ui/core/FormGroup';
import FormControlLabel from '@material-ui/core/FormControlLabel';
import Checkbox from '@material-ui/core/Checkbox';
import Button from "@material-ui/core/Button";
import '../../Css/Question.css';
import {selectAnswers} from "../../redux/actions";
import {connect} from "react-redux";


const useStyles = makeStyles(theme => ({
    root: {
        display: 'flex',
    },
    formControl: {
        margin: theme.spacing(3),
    },
}));

function handleChange(e, props) {
    const answers = [...props.selectedAnswersOfCurrentQuestion];
    if (e.target.checked) {
        answers.push(e.target.value);
        props.chooseAnswers(answers);
    } else {
        const index = answers.indexOf(e.target.value);
        if (index > -1) {
            answers.splice(index, 1);
        }
        props.chooseAnswers(answers);
    }
}


function MultipleChoice(props) {

    const classes = useStyles();


    return (
        props.text ? <div className="container">
                {props.answers && (
                    <>
                        {
                            props.answers.length ? (
                                <div className="questionContainer">
                                    <FormControl component="fieldset" className={classes.formControl}>
                                        <FormLabel
                                            component="legend">{props.questionNumber}/{props.countOfQuestions} {props.text}</FormLabel>
                                        <br/>
                                        <br/>
                                        <FormGroup>
                                            {
                                                props.answers.map((answer, index) =>
                                                    <FormControlLabel
                                                        key={index}
                                                        control={<Checkbox
                                                            checked={props.selectedAnswersOfCurrentQuestion.indexOf(String(index)) > -1}
                                                            color="primary" onChange={e => handleChange(e, props)}
                                                            value={String(index)}/>}
                                                        label={answer.text}/>
                                                )
                                            }
                                        </FormGroup>
                                    </FormControl>
                                </div>
                            ) : <div>No answers</div>
                        }
                    </>
                )}

            </div>
            : null

    );
}

const mapDispatchToProps = (dispatch, ownProps) => ({
    chooseAnswers: function (selectedAnswers) {
        dispatch(selectAnswers(selectedAnswers))
    }
});

export default connect(null, mapDispatchToProps)(MultipleChoice);
