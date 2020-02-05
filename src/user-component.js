import React from 'react';
import './styles.css';
import { makeStyles, withStyles } from '@material-ui/core/styles';
import AppBar from '@material-ui/core/AppBar';
import Toolbar from '@material-ui/core/Toolbar';
import Button from '@material-ui/core/Button';
import IconButton from '@material-ui/core/IconButton';
import MenuIcon from '@material-ui/icons/Menu';
import image from './user-male.jpg';
import EditIcon from '@material-ui/icons/Edit';
import Popover from '@material-ui/core/Popover';
import TextField from '@material-ui/core/TextField';
import LockIcon from '@material-ui/icons/Lock';
import LanguageIcon from '@material-ui/icons/Language';

const useStyles = makeStyles(theme => ({
  root: {

    flexGrow: 1,
  },
  menuButton: {
    marginRight: theme.spacing(1),
  },
  title: {
    position: "absolute", right: 0
  },
}));
function TaskBar() {
  const classes = useStyles();
  return (
    <div className={classes.root}>
      <AppBar position="static" >
        <Toolbar>
          <IconButton edge="start" className={classes.menuButton} color="inherit" aria-label="menu">
            <MenuIcon />
          </IconButton>

          <Button color="inherit" >
            Main
              </Button>
          <Button color="inherit" >
            User
              </Button>

          <Button color="inherit" >
            Exams
              </Button>

          <Button color="inherit"  >
            Contact
                  </Button>
          <Button color="inherit" className={classes.title}>
            Logout
                  </Button>
        </Toolbar>
      </AppBar>
    </div>
  );
}

function Global(){
  return (
    <div className="edit-content">
    <TextField size='medium' label="Name"></TextField>
    <br></br>
    <TextField size='medium' label="Surname"></TextField>
    <br></br>
    <TextField size='medium' label="Email"></TextField>
    <br></br>
    <TextField size='medium' label="Phone Number"></TextField>
    <br></br>
    <TextField fullWidth label="Institution"></TextField>
    <br></br>
    <br></br>
    <br></br>
    <br></br>
    <br></br>
    <br></br>
    <br></br>
    <br></br>
    <div style={{textAlign:'right'}}>
    
    <Button color='primary'>Save</Button>
    </div>
    </div>
  )}
  function Security()
  {
    return(
      <div className="edit-content">
    <TextField size='medium' label="Password"></TextField>
    <br></br>
    <br></br>
    <br></br>
    <br></br>
    <br></br>
    <br></br>
    <br></br>
    <div style={{textAlign:"right"}}>
    
    <Button color='primary'>Save</Button>
    </div>
    </div>
    )
  }
function GeneralInfo() {
  
  const [open, setopen] = React.useState(false);
  const [context, setcontext] = React.useState(Global);
  const handleClick = event => setopen(true)


  const handleClose = () => setopen(false)
const setcontexthandler =(e)=>{
  if(e==0)
    setcontext(Global)
  else setcontext(Security)
}
  return (
    <div className="general-row">
      <div className='image-container-row' >
        <img src={image} alt='userImage' className='image-container' ></img>
        <Button onClick={handleClick} variant="contained" color="primary" style={{ fontSize: "1.5vw" }}>  <EditIcon></EditIcon>Edit Info</Button>
        <Popover   open={open} onClose={handleClose}
          anchorReference="anchorPosition"
          anchorPosition={{top:50,left:500}}
        >
          <div style={{ padding: "1vw 10vw 10vw 1vw" }}>
            <h2 style={{ textAlign: "center" }}>Edit Info</h2>
            <div className="sidebox">
              <Button onClick={()=>setcontexthandler(0)} color='primary'><LanguageIcon></LanguageIcon> General</Button>
              
              <Button onClick={()=>setcontexthandler(1)}  color='primary'><LockIcon></LockIcon> Security</Button>
            </div>
           {context}
           <br></br>
           <Button onClick={handleClose}  color='primary'>Cancel</Button> 
          </div>
         
        </Popover>

      </div>
      <div className='about-container-row'>
        <ul>
          <li className="about-container-list-item"><b>Name:</b> Ruben</li>
          <li className="about-container-list-item"><b>Surname:</b> Poghosyan</li>
          <li className="about-container-list-item"><b>Country:</b>Armenia</li>
          <li className="about-container-list-item"><b>City:</b>Yerevan</li>
          <li className="about-container-list-item"><b>Email:</b> poghosyan26@gmail.com</li>
          <li className="about-container-list-item"><b>Institution:</b> Yerevan State University</li>
          <li className="about-container-list-item"><b>Course:</b>Physics</li>
          <li className="about-container-list-item"><b>Phone:</b>0000000000</li>
        </ul>
      </div>
    </div>
  );
}
function ActiveExams() {
  return (
    <div className="activeExams-container">
      <h1 >Active Exams</h1>
      <div className="activeExamsList-container">
        <div className="activeExamsList-container-Item"><div className='title'>Physics</div></div>
        <div className="activeExamsList-container-Item"><div className='title'>Mathematics</div></div>
        <div className="activeExamsList-container-Item"><div className='title'>English</div>
        </div>
       
      </div>
    </div>
  )
}
export default function user_component() {
  // eslint-disable-next-line react-hooks/rules-of-hooks


  return (
    <div className="main-container"  >
      <TaskBar></TaskBar>
      <GeneralInfo></GeneralInfo>
      <ActiveExams></ActiveExams>

    </div>
  );

}
