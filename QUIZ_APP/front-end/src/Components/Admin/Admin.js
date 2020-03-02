import React from "react";
import Question from './Question';
import Topics from './Topics';
import Quiz from './Quiz';
import Button from '@material-ui/core/Button'
import Alert from '../Alert/Alert';
import '../../Css/Admin.css';

export default function Admin()
{ const [state,Setstate]= React.useState(0);
    const [open, setOpen] = React.useState(false);
    const [severity,Setseverity]=React.useState("");
      const handleClose = () => {
          setOpen(false);
        };
    const [message,Setmessage]=React.useState("");
    const chooseComponent=()=>{
        if(state==0)
        {return <Topics Setmessage={(e)=>Setmessage(e)} Setseverity={(e)=>Setseverity(e)} setOpen={(e)=>setOpen(e)}></Topics>}
        if(state==1)
        {return <Question></Question>}
        if(state==2)
        {return <Quiz></Quiz>}
    }
    return(
        <div className="main">
           
            <div className="control">
           
            <Button onClick={()=>Setstate(0)} style={{fontSize:25,marginTop:15}} fullWidth={true} variant="contained" color="primary">Topics</Button>
            <Button onClick={()=>Setstate(1)} style={{fontSize:25,marginTop:15}} fullWidth={true}  variant="contained" color="primary">Questions</Button>
            <Button onClick={()=>Setstate(2)} style={{fontSize:25,marginTop:15}} fullWidth={true}  variant="contained" color="primary">Quiz</Button>
            <Alert open={open} message={message} handleClose={handleClose} severity={severity}></Alert>

            </div>
            
         <div className='workarea'>{chooseComponent()}  </div>
        </div>
    )
}