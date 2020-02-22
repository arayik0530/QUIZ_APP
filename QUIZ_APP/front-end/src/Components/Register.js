import React from 'react';
import '../Css/styles.css';
import icon from '../Image/quiz.png';
import TextField from '@material-ui/core/TextField';
import Button from "@material-ui/core/Button";
import Login from './Login';
import {
    BrowserRouter as Router,
    Switch,
    Route,
    Link
  } from "react-router-dom";
  import {postData} from '../utlis/utils.js';
function Register_comp()
{   const [state,Setstate]=React.useState({
  email:"",
  password:"",
  firstName:"",
  lastName:""
});
    const HandleRegister = async ()=>
    {
         let response = await postData("http://localhost:8090/api/auth/register",state)
         if(response.status=="200")
         {
           alert("Registration was successfull")
         }
         else 
         {
           alert("Something went wrong please try again")
         }
       
    }
    const HandleBack =()=>
    {
      window.location.href='/'
    }
    return (
        <div className="register-main">
             <img width='50px' src={icon}></img>
            <h2 style={{color:"#1976d2",textDecoration:"underline"}}>Register</h2>
            <TextField onChange={(e)=>{Setstate({...state,firstName:e.target.value})}} style={{backgroundColor:'whitesmoke'}} label="Name" variant="outlined"></TextField>
            <br/>
            <br/>
            <TextField  onChange={(e)=>{Setstate({...state,lastName:e.target.value})}}  style={{backgroundColor:'whitesmoke'}}  label="Surname" variant="outlined"></TextField>
            <br/>
            <br/>
            <TextField  onChange={(e)=>{Setstate({...state,email:e.target.value})}} style={{backgroundColor:'whitesmoke'}}  required label="email" variant="outlined"></TextField>
            <br/>
            <br/>
            <TextField  onChange={(e)=>{Setstate({...state,password:e.target.value})}} style={{backgroundColor:'whitesmoke'}}  required label="password" type='password' variant="outlined"></TextField>
         <br></br>
         <br></br>
         <Button style={{marginRight:'10px'}} onClick={HandleBack} color='primary' variant='contained'>Go Back</Button>
           <Button onClick={HandleRegister} color='primary' variant='contained'>Register</Button>
        </div>
    )

}
function Register()
{ return(
    <Router>
        
        <div>
        <Switch>
          <Route exact path="/">
            <Login />
          </Route>
          <Route exact  path="/register">
            <Register_comp />
          </Route>
        </Switch>
        </div>
        
          </Router>

);

}
export default Register;