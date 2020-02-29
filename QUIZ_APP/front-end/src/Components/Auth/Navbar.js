import React,{useContext, useEffect} from 'react';
import IconButton from '@material-ui/core/IconButton';
import MenuIcon from '@material-ui/icons/Menu';
import Drawer from '@material-ui/core/Drawer';
import InputBase from '@material-ui/core/InputBase';
import SearchIcon from '@material-ui/icons/Search';
import Button from '@material-ui/core/Button';
import AppBar from '@material-ui/core/AppBar';
import Toolbar from '@material-ui/core/Toolbar';
import User from '../User/User';
import Admin from '../Admin/Admin';
import App from '../Exam/App';
import '../../Css/styles.css';
import { makeStyles, fade } from '@material-ui/core/styles';
import {
  BrowserRouter as Router,
  Switch,
  Route,
  Link
} from "react-router-dom";
import Search_List from '../User/Search_List';
import {UserContext} from '../../Contexts/user';
import store from "../../redux/store";
import { Provider } from "react-redux";
import {IdContext} from '../../Contexts/idcontext';
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
        width: 250,
      },
    },
  },
}));

function Navbar() {
  //states
  
  const user =JSON.parse(localStorage.getItem("UserContext"));
  const classes = useStyles();
  const [state, Setstate] = React.useState(false);
  const [search, Setsearchstate] = React.useState("");
  const ID=useContext(IdContext);
  const toggleDrawer = (e) => () => Setstate(e)
 
   let x= user.roles[0];
   let roles=[];
   x.substr(0,x.length-1).substr(1).split(',').forEach(x=>{roles.push(x.trim())});
 
 

  return (
    <Router>
      
      <div >
        <AppBar position="static" >
          <Toolbar>
            <div className="menu">
              <IconButton style={{ padding: "0 10px 0 0" }} onClick={toggleDrawer(true)} ge="start" color="inherit" aria-label="menu">

                <MenuIcon />

              </IconButton>
            <div className="spacerphone"></div>
              <div className={classes.search}>
                <div className={classes.searchIcon}>
                  <SearchIcon />
                </div>
                <InputBase
               
                onChange={(e) => Setsearchstate(e.target.value)}
                  placeholder="Search…"
                  classes={{
                    root: classes.inputRoot,
                    input: classes.inputInput,
                  }}
                  inputProps={{ 'aria-label': 'search' }}
                />
              </div>
              <Link  style={{ color: 'white', textDecoration: 'none' }}  to={`/search/${search}`}>    <Button  color="inherit" >
                Search
                </Button> </Link>

              <Drawer open={state} onClose={toggleDrawer(false)}>
                <div className="drawer">
                


                  <Link style={{ color: 'white', textDecoration: 'none' }} to="/">    <Button onClick={toggleDrawer(false)} fullWidth color="primary" >
                    User
                </Button> </Link>


               { roles.includes("ADMIN") && <Link style={{ color: 'white', textDecoration: 'none' }} to="/admin">    <Button onClick={toggleDrawer(false)} fullWidth color="primary" >
                    Admin
                </Button> </Link>}
                { ID && <Link style={{ color: 'white', textDecoration: 'none' }} to="/exams">    <Button color="inherit" >
                Exams
                </Button> </Link>   }  

                  


                  <Link style={{ color: 'white', textDecoration: 'none' }} to="/">  <Button onClick={toggleDrawer(false)} fullWidth color="primary" >
                    Logout
                </Button></Link>
                </div>
              </Drawer>
            </div>
            <div className="navbar-menu">
             
              <Link style={{ color: 'white', textDecoration: 'none' }} to="/">    <Button color="inherit" >
                User
                </Button> </Link>
                
            { roles.includes('ADMIN') &&  <Link style={{ color: 'white', textDecoration: 'none' }} to="/admin">    <Button color="inherit" >
                Admin
                </Button> </Link>}
              { ID && <Link style={{ color: 'white', textDecoration: 'none' }} to="/exams">    <Button color="inherit" >
                Exams
                </Button> </Link>   }  
              
                    <Link style={{ color: 'white', textDecoration: 'none' }} to="/">    <Button onClick={()=>{localStorage.clear();window.location.replace("/");}} color="inherit" >
                Logout
                </Button> </Link>
                    <div className="spacer"></div>
              <div className={classes.search}>
                <div className={classes.searchIcon}>
                  <SearchIcon />
                </div>
                <InputBase
                  onKeyDown={()=>{console.log(this)}}
                  onChange={(e) => Setsearchstate(e.target.value)}
                  placeholder="Search…"
                  classes={{
                    root: classes.inputRoot,
                    input: classes.inputInput,
                  }}
                  inputProps={{ 'aria-label': 'search' }}
                />
              </div>
              
              <Link style={{ color: 'white', textDecoration: 'none' }} to={`/search/${search}`}>    <Button  color="inherit" >
                Search
                </Button> </Link>
            </div>
          </Toolbar>
        </AppBar>
       
        <Switch>

          <Route  exact path="/">
            <User />
          </Route>
           
            <Route path="/admin">
            <Admin></Admin>
            </Route>
            <Route  exact path= {`/search/${search}`}>
            <Search_List input={search} />
          </Route>
          <Route exact path='/exams'>
           <Provider store={store}>
           <App/> 
           </Provider>
           </Route>
        </Switch>
      </div>
    </Router>
  );
}
export default Navbar;