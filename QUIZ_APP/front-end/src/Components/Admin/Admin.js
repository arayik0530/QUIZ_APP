import React from "react";
import '../../Css/Admin.css';
import Question from './Question';
import Topics from './Topics';
import Quiz from './Quiz';
import Button from '@material-ui/core/Button'

export default function Admin()
{ const [state,Setstate]= React.useState(<Topics></Topics>)
    return(
        <div className="main">
           
            <div className="control">
           
            <Button onClick={()=>Setstate(<Topics></Topics>)} style={{fontSize:25,marginTop:15}} fullWidth={true} variant="contained" color="primary">Topics</Button>
            <Button onClick={()=>Setstate(<Question></Question>)} style={{fontSize:25,marginTop:15}} fullWidth={true}  variant="contained" color="primary">Questions</Button>
            <Button onClick={()=>Setstate(<Quiz></Quiz>)} style={{fontSize:25,marginTop:15}} fullWidth={true}  variant="contained" color="primary">Quiz</Button>
           
            </div>
            
         <div className='workarea'>{state}  </div>
        </div>
    )
}