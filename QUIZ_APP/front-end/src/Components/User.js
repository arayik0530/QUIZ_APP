import React,{useEffect,useContext, useState} from 'react';
import '../Css/styles.css';
import { useRef } from 'react';
import Button from '@material-ui/core/Button';
import EditIcon from '@material-ui/icons/Edit';
import { Dialog } from '@material-ui/core';
import LockIcon from '@material-ui/icons/Lock';
import LanguageIcon from '@material-ui/icons/Language';
import TextField from '@material-ui/core/TextField';
import image from '../Image/user-male.jpg';
import {UserContext} from '../Contexts/user';
import QuizItem from './QuizItem.js';
import IconButton from '@material-ui/core/IconButton';
import SecurityIcon from '@material-ui/icons/Security';

function User() {
    // eslint-disable-next-line react-hooks/rules-of-hooks
    
    return (
        <div className="main-container"  >
         
            <GeneralInfo ></GeneralInfo>
            <ActiveExams></ActiveExams>

        </div>
    );

}
function UserGeneral()
{ let [state,Setstate]= useState({firstName:"",lastName:"",email:"",phone:""});
const User = useContext(UserContext);
useEffect(()=>{
    async function getdata()
    {
        let response = await fetch("http://localhost:8090/api/user/",{
            method:"GET",
            headers:{
               "Content-Type":"application/json",
               "Authorization": "Bearer_ "+localStorage.getItem("token")
            }
        });
        let x= await response.json();
        Setstate(x)

    }
    getdata();
},[]);
 const uploadData = async ()=>{
     console.log("asd")
     let response = await fetch("http://localhost:8090/api/user/update",{
         method:"PUT",
         headers:{
            "Content-Type":"application/json",
            "Authorization": "Bearer_ "+localStorage.getItem("token")
         },
         body:JSON.stringify({
            "firstName":state.firstName,
            "lastName":state.lastName,
            "email":state.email,
            "phone":state.phone,
            "imageId":null,
            "id":User.id
         })
    
         
     });
    
 }
return ( <div style={{paddingRight:"20px"}}>
<div className="about-container-list-item"><TextField helperText="Name" fullWidth="true" variant="filled"  onChange={(e)=>Setstate({...state,firstName:e.target.value})} value={state.firstName}></TextField></div>
<div className="about-container-list-item"><TextField  helperText="Surname" fullWidth="true" variant="filled"  onChange={(e)=>Setstate({...state,lastName:e.target.value})}  value={state.lastName}></TextField></div>
<div className="about-container-list-item"><TextField   helperText="Email" fullWidth="true" variant="filled"   onChange={(e)=>Setstate({...state,email:e.target.value})} value={state.email}></TextField></div>
<div className="about-container-list-item"><TextField   helperText="Phone" fullWidth="true" variant="filled"   onChange={(e)=>Setstate({...state,phone:e.target.value})} value={state.phone}></TextField></div>
<Button onClick={uploadData} color="primary" variant="contained">Change</Button>
</div>)
}
function UserSecurity()
{const User = useContext(UserContext);
    let [state,Setstate]=useState({});
    
    const uploadData=  async ()=>{
        
        let response = await fetch("http://localhost:8090/api/user/change-password",{
            method:"PUT",
            headers:{
                "Content-Type":"application/json",
                "Authorization": "Bearer_ "+localStorage.getItem("token")
            },
            body:JSON.stringify({
                "email":User.email,
                "oldPassword":state.oldPassword,
                "newPassword":state.newPassword
            })
        });
       
        if(response.status=="400")
        {
            //alert wrong password
        }
        else{
            //alert password changed successfully
        }
    }
    return ( <div style={{paddingRight:"20px"}}>
<div className="about-container-list-item"><TextField helperText="Old Password" fullWidth="true" variant="filled"  onChange={(e)=>Setstate({...state,oldPassword:e.target.value})} value={state.oldPassword}></TextField></div>
<div className="about-container-list-item"><TextField  helperText="New Password" fullWidth="true" variant="filled"  onChange={(e)=>Setstate({...state,newPassword:e.target.value})}  value={state.newPassword}></TextField></div>

<Button onClick={uploadData} color="primary" variant="contained">Change</Button>
</div>)
    
}
function GeneralInfo(props) {
  let [state,Setstate]=useState({info:<UserGeneral></UserGeneral>})
   
    return (
        <div className="general-row">
            <div className='image-container-row' >
                <div className="image-container" >
                    <img src={image} alt='userImage' className='image' ></img>
                    <div className="middle">
                        <Button  color='primary' variant='contained' >Upload</Button>
                    </div>
                </div>
                <input type="file"  style={{ display: "none" }} ></input>
                

            </div>
            <div className='about-container-row'>
            <IconButton onClick={()=>Setstate({info:<UserGeneral></UserGeneral>})} color='primary'><LanguageIcon></LanguageIcon></IconButton>
            <IconButton onClick={()=>Setstate({info:<UserSecurity></UserSecurity>})} color="primary"><SecurityIcon></SecurityIcon></IconButton>
            {state.info}
            </div>
        </div>
    );
}



function ActiveExams() {
     
    let [state,Setstate]=useState([]);
    let [passedExams,SetpassedExams]=useState([]);
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

    return (
        <div className="activeExams-container">
            <h1 >Active Exams</h1>
            <div className="activeExamsList-container">
    {state.map((x)=>{ return <QuizItem props={[x.topic,x.deadline]}></QuizItem>})}
            </div>
            <h1 >Passed Exams</h1>
            <div className="activeExamsList-container">
            {passedExams.map((x)=>{ return <div className="activeExamsList-container-Item"><div className='title'>{x.topic}<br></br>{x.successPercent}</div></div>})}
                </div>
        </div>
    )
}
export default User;