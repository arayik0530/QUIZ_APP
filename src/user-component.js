import React from 'react';
import './styles.css';
import { makeStyles } from '@material-ui/core/styles';
import AppBar from '@material-ui/core/AppBar';
import Toolbar from '@material-ui/core/Toolbar';
import Button from '@material-ui/core/Button';
import IconButton from '@material-ui/core/IconButton';
import MenuIcon from '@material-ui/icons/Menu';
import image from './user-male.jpg';

const useStyles = makeStyles(theme => ({
  root: {

    flexGrow: 1,
  },
  menuButton: {
    marginRight: theme.spacing(1),
  },
  title: {
    flexGrow: 1,
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
            Edit
              </Button>
          <Button color="inherit" >
            Exams
              </Button>

          <Button color="inherit">
            Contact
                  </Button>
          <Button color="inherit">
            Logout
                  </Button>
        </Toolbar>
      </AppBar>
    </div>
  );
}

function GeneralInfo() {
  return (
    <div className="general-row">
      <div className='image-container-row' >
        <img src={image} alt='userImage' className='image-container' ></img>

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
          <li className="about-container-list-item"><b>Phone:</b>Physics</li>
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
          <ul>
            <li>IELTS</li>
            <li>TOEFL</li>
          </ul>
        </div>
        <div className="activeExamsList-container-Item"></div>
        <div className="activeExamsList-container-Item"></div>
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
