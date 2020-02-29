import React from 'react';
import Navbar from './Navbar';
import Main from './Main';
import {UpdateUserContext,UserContext} from '../../Contexts/user';
import { IdContext, UpdateIdContext } from '../../Contexts/idcontext';
export default function  Controller() { 
   
     const [loggedInUser, setLoggedInUser] = React.useState(null);
     const [id, setId] = React.useState(null);
  
    return(
        <UpdateUserContext.Provider value={setLoggedInUser}>
        <UserContext.Provider value={loggedInUser}>
          <IdContext.Provider value={id}>
            <UpdateIdContext.Provider value={setId}>
            {(loggedInUser ?loggedInUser:localStorage.getItem("UserContext"))? <Navbar /> : <Main />}
            </UpdateIdContext.Provider>
          </IdContext.Provider>
          
        </UserContext.Provider>
      </UpdateUserContext.Provider>
    )
  }