import React,{useEffect} from 'react';
import Autocomplete from '@material-ui/lab/Autocomplete';
import TextField from '@material-ui/core/TextField';
import DateFnsUtils from '@date-io/date-fns';
import Button from '@material-ui/core/Button';
import { makeStyles } from '@material-ui/core/styles';

import {
    MuiPickersUtilsProvider,
    KeyboardTimePicker,
    KeyboardDatePicker,
  } from '@material-ui/pickers';
import '../../Css/Quiz.css';

const useStyles = makeStyles({
    root: {
     width:300
    }
    
  });
export default function Quiz()
{   const classes=useStyles();
    const [Duration,SetDuration]=React.useState(null);
    const [questionCount,SetquestionCount]=React.useState(null)
    const [User,SetUser]=React.useState({});
    const [selectedDate, setSelectedDate] = React.useState( new Date());
    const [Users,SetUsers]=React.useState([]);
    const [Topic,SetTopic]=React.useState(null);
    const [Topics,SetTopics]=React.useState([]);
   
     useEffect(()=>{
      async function getData()
      { 
        
     let response = await fetch("http://localhost:8090/api/topic/all",{
       method:"GET",
       headers:{
        "Content-Type":"application/json",
        "Authorization": "Bearer_ "+localStorage.getItem("token")
       }
     });
     let  x = await response.json();
     SetTopics(x.content);
     response = await fetch("http://localhost:8090/api/user/all-users",{
      method:"GET",
      headers:{
       "Content-Type":"application/json",
       "Authorization": "Bearer_ "+localStorage.getItem("token")
      }
     })
    x=await response.json();
    SetUsers(x);
    console.log(Users);
     
     
     
    }
    getData();

     },[]);
    const handleAdd = async () =>{
      let year=selectedDate.getFullYear(),
      month=selectedDate.getMonth()+1,
      day=selectedDate.getDate();
         if(month<10)month="0"+month;
         if(day<10)day='0'+day;

         let deadline=(year+"-"+month+"-"+day);
      let response = await fetch("http://localhost:8090/api/quiz/create-upcoming-quiz/",
      { method:"POST",
        headers:{
          "Content-Type":"application/json",
          "Authorization": "Bearer_ "+localStorage.getItem("token")
        },
        body:JSON.stringify(
          { "deadline":deadline, 
           "durationInMinutes": Duration,
          "questionCount": questionCount,
          "topicId": Topic.id,
          "userId": User.id

          }
        )

      })
    }
    return(
       <div>
           <h1>Edit Quizes</h1>
           <div className="quizmain">
               <h2>Create an upcoming quiz</h2>
               <Autocomplete
            classes={{inputRoot:classes.root}}
      id="combo-box-demo"
      options={Users}
      getOptionLabel={option => option.email}
      value={User}
      onChange={(event, newValue) => {
        SetUser(newValue);
        
      }}
      style={{marginBottom:10}}
      renderInput={params => <TextField {...params} label="User" variant="outlined" />}
    />
       <Autocomplete
            classes={{inputRoot:classes.root}}
      id="combo-box-demo"
      options={Topics}
      getOptionLabel={option => option.title}
      value={Topic}
      onChange={(event, newValue) => {
        SetTopic(newValue);
        
      }}
      style={{marginBottom:10}}
      renderInput={params => <TextField {...params} label="Topic" variant="outlined" />}
    />
    <MuiPickersUtilsProvider  utils={DateFnsUtils}>
       <KeyboardDatePicker
          margin="normal"
          id="date-picker-dialog"
          label="Deadline"
          format="MM/dd/yyyy"
          value={selectedDate}
          onChange={setSelectedDate}
          KeyboardButtonProps={{
            'aria-label': 'change date',
          }}
        />
        </MuiPickersUtilsProvider>
         <br></br>
         <br></br>
         <TextField style={{marginRight:10}} placeholder="Duration in minutes" value={Duration} onChange={(e)=>SetDuration(e.target.value)} ></TextField>  
         <TextField  placeholder="Questions count" value={questionCount} onChange={(e)=>SetquestionCount(e.target.value)} ></TextField>  
         <br></br>
           <Button onClick={handleAdd} style={{marginTop:50}} variant='outlined' color='primary' >Assign quiz</Button>
           </div>
           
         
       </div>
                  
    )
}