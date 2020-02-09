import React, { useEffect } from "react"
import TextField from "@material-ui/core/TextField";
import OutlinedInput from '@material-ui/core/OutlinedInput';
import InputLabel from "@material-ui/core/InputLabel";
import InputAdornment from "@material-ui/core/InputAdornment";
import IconButton from '@material-ui/core/IconButton';
import Visibility from '@material-ui/icons/Visibility';
import VisibilityOff from '@material-ui/icons/VisibilityOff';
import EmailIcon from '@material-ui/icons/Email';
import Button from "@material-ui/core/Button";
import icon from '../Image/quiz.png';
import '../Css/styles.css';
function Login()
{ const [values, setValues] = React.useState({
    email:'',
    password: '',
    showPassword: false,
  });
  const handleChange = prop => event => {
    setValues({ ...values, [prop]: event.target.value });
  };
  
  useEffect(() => {
    return () => {
        document.body.style.overflow="scroll"
    }
  }, []);
  useEffect(() => {document.body.style.overflow="hidden" }, []);

    return(
        
      <div className="login">
       <img width='50px' src={icon}></img>
       <h2 style={{color:"#1976d2",textDecoration:"underline"}}>Login</h2>
     

       <div className="login-input"> 
       <div>
      
          <TextField
            id="outlined-adornment-email"
            variant="outlined"
            label="email"
            type="text"
            value={values.email}
            onChange={handleChange('email')}
            endAdornment={
                <InputAdornment position="end"><EmailIcon/></InputAdornment>
             }
            labelWidth={70}
          />
          </div>
          <br></br>
          <br></br>
          <div>
      
          <TextField
          type='password'
            label="Password"
            variant='outlined'
            id="outlined-adornment-password"
            value={values.password}
            onChange={handleChange('password')}
          />
          </div>
          <br></br>
         
      </div>
      <div style={{textAlign:"center"}}>
      <Button color="primary" variant="contained">Login</Button>
      </div>
      
     
      </div>
    )

}
export default Login;