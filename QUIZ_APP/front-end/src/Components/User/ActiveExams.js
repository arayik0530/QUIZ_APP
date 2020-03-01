import React,{useEffect,useContext, useState} from 'react';
import '../../Css/styles.css';
import Button from '@material-ui/core/Button';
import LanguageIcon from '@material-ui/icons/Language';
import TextField from '@material-ui/core/TextField';
import {UserContext} from '../../Contexts/user';
import QuizItem from './QuizItem.js';
import IconButton from '@material-ui/core/IconButton';
import SecurityIcon from '@material-ui/icons/Security';
import Alert from '../Alert/Alert';
import {UpdateIdContext} from '../../Contexts/idcontext';
  import {UpdateResultContext} from '../../Contexts/ResultContext';
import {
    useHistory
  } from "react-router-dom";

export default function ActiveExams() {
    let history = useHistory();
    let [state,Setstate]=useState([]);
    
    let [passedExams,SetpassedExams]=useState([]);
    const UpdateResult=useContext(UpdateResultContext);
    const UpdateId = useContext(UpdateIdContext);
    useEffect(()=>{
        async function getdata()
        {
        let response = await fetch("http://localhost:8090/api/quiz/upcoming/own",{
            method:"GET",
            headers:{
                "Content-Type":"application/json",
                "Authorization": "Bearer_ "+localStorage.getItem("token")
              }
        });

        let x = await response.json();
        
     Setstate(x.content);
      response =await fetch("http://localhost:8090/api/quiz/own",{
        method:"GET",
        headers:{
            "Content-Type":"application/json",
            "Authorization": "Bearer_ "+localStorage.getItem("token")
          }
    });
    x= await response.json();
    SetpassedExams(x.content);
    }
    getdata();

    },[]);
  const OpenUpcomingExam=(id)=>{
     
    UpdateId(id);
    history.push("/exams");
  }
  const OpenPassedExam= async (id)=>{
      let response = await fetch(`http://localhost:8090/api/quiz/${id}`,{
          method:"GET",
          headers:{
            "Content-Type":"application/json",
            "Authorization": "Bearer_ "+localStorage.getItem("token")
          }
      });
      let x= await response.json();
      
      UpdateResult(x);
     history.push('/result');
     
  }
    return (
        
        <div className="activeExams-container">
            <h1 >Active Exams</h1>
            <div className="activeExamsList-container">
    {state && state.map((x)=>{ return <QuizItem onClick={()=>OpenUpcomingExam(x.id)} props={[x.topic,x.deadline]}></QuizItem>})}
            </div>
            <h1 >Passed Exams</h1>
            <div className="activeExamsList-container">
            {passedExams && passedExams.map((x)=>{ return <QuizItem onClick={()=>OpenPassedExam(x.id)} props={[x.topic,x.successPercent+"%"]}></QuizItem>})}
                </div>
             
        </div>
     
   

    )

}