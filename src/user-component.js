import React from 'react';
import './styles.css';
import { makeStyles, fade } from '@material-ui/core/styles';
import AppBar from '@material-ui/core/AppBar';
import Toolbar from '@material-ui/core/Toolbar';
import Button from '@material-ui/core/Button';
import IconButton from '@material-ui/core/IconButton';
import MenuIcon from '@material-ui/icons/Menu';
import image from './user-male.jpg';
import Drawer from '@material-ui/core/Drawer';
import EditIcon from '@material-ui/icons/Edit';
import TextField from '@material-ui/core/TextField';
import LockIcon from '@material-ui/icons/Lock';
import LanguageIcon from '@material-ui/icons/Language';
import InputBase from '@material-ui/core/InputBase';
import SearchIcon from '@material-ui/icons/Search';
import {useRef} from 'react';
import {
  BrowserRouter as Router,
  Switch,
  Route,
  Link
} from "react-router-dom";
import { Dialog } from '@material-ui/core';
const useStyles = makeStyles(theme => ({
  root: {
    flexGrow: 1,
  },
  menuButton: {
    marginRight: theme.spacing(2),
  },
  title: {
    flexGrow: 1,
    display: 'none',
    [theme.breakpoints.up('sm')]: {
      display: 'block',
    },
  },
  search: {
    position: 'relative',
    borderRadius: theme.shape.borderRadius,
    backgroundColor: fade(theme.palette.common.white, 0.15),
    '&:hover': {
      backgroundColor: fade(theme.palette.common.white, 0.25),
    },
    marginLeft: 0,
    width: '100%',
    [theme.breakpoints.up('sm')]: {
      marginLeft: theme.spacing(1),
      width: 'auto',
    },
  },
  searchIcon: {
    width: theme.spacing(7),
    height: '100%',
    position: 'absolute',
    pointerEvents: 'none',
    display: 'flex',
    alignItems: 'center',
    justifyContent: 'center',
  },
  inputRoot: {
    color: 'inherit',
  },
  inputInput: {
    padding: theme.spacing(1, 1, 1, 7),
    transition: theme.transitions.create('width'),
    width: '100%',
    [theme.breakpoints.up('sm')]: {
      width: 200,
      '&:focus': {
        width: 300,
      },
    },
  },
}));

function TaskBar() {
  const classes =useStyles()
  const [state,Setstate]=React.useState(false);
  const toggleDrawer=(e)=> event => Setstate(e)
    return (
    <Router>
    <div >
      <AppBar position="static" >
        <Toolbar>
          <div className="menu">
        <IconButton style={{padding:"0 10px 0 0"}}  onClick={ toggleDrawer(true)}ge="start"  color="inherit" aria-label="menu">
          
      <MenuIcon />
        
    </IconButton>
    <div className={classes.search}>
            <div className={classes.searchIcon}>
              <SearchIcon />
            </div>
            <InputBase 
              placeholder="Search…"
              classes={{
                root: classes.inputRoot,
                input: classes.inputInput,
              }}
              inputProps={{ 'aria-label': 'search' }}
            />
          </div>
          
    <Drawer open={state} onClose={toggleDrawer(false)}>
     <div className="drawer">
    <Link  style={{ color:'white', textDecoration: 'none' }} to="/">  <Button onClick={toggleDrawer(false)} fullWidth color="primary" >
            Main
              </Button></Link>
              
             
              <Link style={{  color:'white', textDecoration: 'none' }} to="/users">    <Button onClick={toggleDrawer(false)} fullWidth color="primary" >
            User
              </Button> </Link>
              
             
              <Link  style={{ color:'white', textDecoration: 'none' }} to="/">  <Button onClick={toggleDrawer(false)} fullWidth color="primary" >
            Exams
              </Button></Link>
              
             
              <Link  style={{ color:'white', textDecoration: 'none' }} to="/">  <Button onClick={toggleDrawer(false)} fullWidth color="primary" >
            Contact
              </Button></Link>
              
             
              <Link  style={{ color:'white', textDecoration: 'none' }} to="/">  <Button onClick={toggleDrawer(false)} fullWidth color="primary" >
            Logout
              </Button></Link>
              </div>
      </Drawer>
    </div>
         <div className="navbar-menu">
        <Link style={{ color:'white', textDecoration: 'none' }} to="/">  <Button color="inherit" >
            Main
              </Button></Link>
              <Link style={{  color:'white', textDecoration: 'none' }} to="/users">    <Button color="inherit" >
            User
              </Button> </Link>

          <Button color="inherit" >
            Exams
              </Button>

          <Button color="inherit"  >
            Contact
                  </Button>
                  <div className="spacer"></div>
                  <div className={classes.search}>
            <div className={classes.searchIcon}>
              <SearchIcon />
            </div>
            <InputBase
              placeholder="Search…"
              classes={{
                root: classes.inputRoot,
                input: classes.inputInput,
              }}
              inputProps={{ 'aria-label': 'search' }}
            />
          </div>
          <Button color="inherit" >
            Logout
                  </Button>
                  </div>
        </Toolbar>
      </AppBar>
      <Switch>
          
          <Route path="/users">
            <User />
          </Route>
          <Route path="/">
            <Home />
          </Route>
        </Switch>
    </div>
    
    </Router>
  );
}
function User() {
  // eslint-disable-next-line react-hooks/rules-of-hooks


  return (
    <div className="main-container"  >
      
      <GeneralInfo></GeneralInfo>
      <ActiveExams></ActiveExams>

    </div>
  );

}
function Home()
{
  return(
    <div>Hello</div>
  )
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
   
    <div style={{textAlign:'right'}}>
    
    <Button color='primary'>Save</Button>
    </div>
    </div>
  )}
  function Security()
  {
    return(
      <div className="edit-content">
        <br></br>
    <TextField size='medium' label="Password"></TextField>
    <br></br>
    
    <div style={{textAlign:"right"}}>
    
    <Button color='primary'>Save</Button>
    </div>
    </div>
    )
  }
function GeneralInfo() {
  const inputEl = useRef(null);
  const [open, setopen] = React.useState(false);
  const [context, setcontext] = React.useState(Global);
  const handleClick = event => setopen(true)


  const handleClose = () => setopen(false)
const setcontexthandler =(e)=>{
  if(e==0)
    setcontext(Global)
  else setcontext(Security)
}
 function uploadimagehandler()
{
 inputEl.current.click();

}
  return (
    <div className="general-row">
      <div className='image-container-row' >
        <div className="image-container" >
        <img  src={image} alt='userImage' className='image' ></img>
        <div className="middle">
    <Button onClick ={uploadimagehandler} color='primary' variant='contained' >Upload</Button>
    </div>
  </div>
        <input type="file" ref={inputEl} style={{display: "none"}} ></input>
        <Button onClick={handleClick} variant="contained" color="primary" style={{ fontSize: "1.5vw" }}>  <EditIcon></EditIcon>Edit Info</Button>
        <Dialog  maxWidth="xl" open={open} onClose={handleClose}
         
        >
          <div  style={{padding:"2vw 2vw 10vw 1vw"}}>
            <h2 style={{ textAlign: "center" }}>Edit Info</h2>
            <div className="sidebox">
              <Button onClick={()=>setcontexthandler(0)} color='primary'><LanguageIcon></LanguageIcon> General</Button>
              
              <Button onClick={()=>setcontexthandler(1)}  color='primary'><LockIcon></LockIcon> Security</Button>
            </div>
           {context}
           <br></br>
           <Button onClick={handleClose}  color='primary'>Cancel</Button> 
          </div>
         
        </Dialog>

      </div>
      <div className='about-container-row'>
      
          <div className="about-container-list-item"><b>Name:</b> Ruben</div>
          <div className="about-container-list-item"><b>Surname:</b> Poghosyan</div>
          <div className="about-container-list-item"><b>Country:</b>Armenia</div>
          <div className="about-container-list-item"><b>City:</b>Yerevan</div>
          <div className="about-container-list-item"><b>Email:</b> poghosyan26@gmail.com</div>
          <div className="about-container-list-item"><b>Institution:</b> Yerevan State University</div>
          <div className="about-container-list-item"><b>Course:</b>Physics</div>
          <div className="about-container-list-item"><b>Phone:</b>0000000000</div>
      
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
 
export default TaskBar;
