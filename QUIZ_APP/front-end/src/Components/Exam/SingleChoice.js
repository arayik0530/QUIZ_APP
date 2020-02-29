import React from 'react';
import clsx from 'clsx';
import {makeStyles} from '@material-ui/core/styles';
import Radio from '@material-ui/core/Radio';
import RadioGroup from '@material-ui/core/RadioGroup';
import FormControlLabel from '@material-ui/core/FormControlLabel';
import FormControl from '@material-ui/core/FormControl';
import FormLabel from '@material-ui/core/FormLabel';
import Button from '@material-ui/core/Button';
import '../../Css/Question.css';
import {selectAnswers} from "../../redux/actions";
import {connect} from "react-redux";

const useStyles = makeStyles({
    root: {
        '&:hover': {
            backgroundColor: 'transparent',
        },
    },
    icon: {
        borderRadius: '50%',
        width: 16,
        height: 16,
        boxShadow: 'inset 0 0 0 1px rgba(16,22,26,.2), inset 0 -1px 0 rgba(16,22,26,.1)',
        backgroundColor: '#f5f8fa',
        backgroundImage: 'linear-gradient(180deg,hsla(0,0%,100%,.8),hsla(0,0%,100%,0))',
        '$root.Mui-focusVisible &': {
            outline: '2px auto rgba(19,124,189,.6)',
            outlineOffset: 2,
        },
        'input:hover ~ &': {
            backgroundColor: '#ebf1f5',
        },
        'input:disabled ~ &': {
            boxShadow: 'none',
            background: 'rgba(206,217,224,.5)',
        },
    },
    checkedIcon: {
        backgroundColor: '#137cbd',
        backgroundImage: 'linear-gradient(180deg,hsla(0,0%,100%,.1),hsla(0,0%,100%,0))',
        '&:before': {
            display: 'block',
            width: 16,
            height: 16,
            backgroundImage: 'radial-gradient(#fff,#fff 28%,transparent 32%)',
            content: '""',
        },
        'input:hover ~ &': {
            backgroundColor: '#106ba3',
        },
    },
});


function StyledRadio(props) {
    const classes = useStyles();

    return (
        <Radio
            className={classes.root}
            disableRipple
            color="default"
            checkedIcon={<span className={clsx(classes.icon, classes.checkedIcon)}/>}
            icon={<span className={classes.icon}/>}
            {...props}
        />
    );
}

function handleChange(e, props) {

    let answers = [];
    answers.push(e.target.value);
    answers = [answers.pop()]
    props.chooseAnswers(answers);
}

function SingleChoice(props) {

    return (
        props.text ? <div className="container">
                {props.answers && (
                    <>
                        {
                            props.answers.length ? (
                                <div className="questionContainer">
                                    <FormControl component="fieldset">
                                        <FormLabel
                                            component="legend">{props.questionNumber}/{props.countOfQuestions} {props.text}</FormLabel>
                                        <br/>
                                        <br/>
                                        <RadioGroup aria-label="question" name="radios"
                                                    value={props.selectedAnswersOfCurrentQuestion[0] || '-1'}>
                                            {
                                                props.answers.map((answer, index) =>
                                                    <FormControlLabel key={index}
                                                                      control={<StyledRadio
                                                                          onChange={e => handleChange(e, props)}
                                                                          value={String(index)}/>}
                                                                      label={answer.text}/>
                                                )
                                            }
                                        </RadioGroup>
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

export default connect(null, mapDispatchToProps)(SingleChoice);