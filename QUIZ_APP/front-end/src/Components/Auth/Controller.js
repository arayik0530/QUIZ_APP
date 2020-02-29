import React from 'react';
import Navbar from './Navbar';
import Main from './Main';
import {UpdateUserContext,UserContext} from '../../Contexts/user';
import { IdContext, UpdateIdContext } from '../../Contexts/idcontext';
import { UpdateResultContext, ResultContext } from '../../Contexts/ResultContext';
export default function  Controller() { 
     const [Result,SetResult]=React.useState(null);
     const [loggedInUser, setLoggedInUser] = React.useState(null);
     const [id, setId] = React.useState(null);
  
    return(
        <UpdateUserContext.Provider value={setLoggedInUser}>
        <UserContext.Provider value={loggedInUser}>
          <IdContext.Provider value={id}>
            <UpdateIdContext.Provider value={setId}>
              <UpdateResultContext.Provider value={SetResult}>
                <ResultContext.Provider value={Result}>
            {(loggedInUser ?loggedInUser:localStorage.getItem("UserContext"))? <Navbar /> : <Main />}
            </ResultContext.Provider>
            </UpdateResultContext.Provider>
            </UpdateIdContext.Provider>
          </IdContext.Provider>
          
        </UserContext.Provider>
      </UpdateUserContext.Provider>
    )
  }