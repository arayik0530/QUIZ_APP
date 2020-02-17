import React from 'react';
import { makeStyles } from '@material-ui/core/styles';
import FormLabel from '@material-ui/core/FormLabel';
import FormControl from '@material-ui/core/FormControl';
import FormGroup from '@material-ui/core/FormGroup';
import FormControlLabel from '@material-ui/core/FormControlLabel';
import Checkbox from '@material-ui/core/Checkbox';
import Button from "@material-ui/core/Button";
import '../Question.css';

const useStyles = makeStyles(theme => ({
    root: {
        display: 'flex',
    },
    formControl: {
        margin: theme.spacing(3),
    },
}));

export default function CheckboxesGroup() {
    const classes = useStyles();
    const [state, setState] = React.useState({
        gilad: true,
        jason: false,
        antoine: false,
    });

    const handleChange = name => event => {

        alert(event.target.value + " " + event.target.checked);
        //setState({ ...state, [name]: event.target.checked });
    };

    const { some_answer_1, some_answer_2, some_answer_3 } = state;

    return (
        <div className="container">
            <div className="questionContainer">
                <FormControl component="fieldset" className={classes.formControl}>
                    <FormLabel component="legend">2/3 What is true about...</FormLabel>
                    <FormGroup>
                        <FormControlLabel
                            control={<Checkbox color="primary" onChange={handleChange('Some answer...')} value="1" />}
                            label="Some answer..." checked={some_answer_1}
                        />
                        <FormControlLabel
                            control={<Checkbox color="primary" onChange={handleChange('Some answer...')} value="2" />}
                            label="Some answer..." checked={some_answer_2}
                        />
                        <FormControlLabel
                            control={
                                <Checkbox color="primary"  onChange={handleChange('Some answer...')} value="3" />
                            }
                            label="Some answer..." checked={some_answer_3}
                        />
                    </FormGroup>
                </FormControl>
            </div>
            <div style={{display: 'flex'}}>
                <Button color="primary">previous</Button>
                <div style={{flex: 1}}></div>
                <Button color="primary">next</Button>
            </div>
        </div>
    );
}
