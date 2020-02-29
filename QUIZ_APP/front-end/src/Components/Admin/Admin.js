import React from "react";
import '../../Css/Admin.css';
import Question from './Question';
import Topics from './Topics';
import Quiz from './Quiz';
import Button from '@material-ui/core/Button'

export default function Admin()
{ const [state,Setstate]= React.useState(0);
    const chooseComponent=()=>{
        if(state==0)
        {return <Topics></Topics>}
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
           
            </div>
            
         <div className='workarea'>{chooseComponent()}  </div>
        </div>
    )
}