import React, { useEffect } from 'react';
import UserField from './UserField';
import Button from '@material-ui/core/Button';
const x="<<",y=">>";
let index=0;
function Search_List()
{   
    
    let [state,Setstate]= React.useState(null);
    useEffect(()=>{
        let url = new URL("http://localhost:8090/api/user/search")
    url.search = new URLSearchParams({text:localStorage.getItem("input")})
    fetch(url)
    .then((response) => {
      return response.json();
    })
    .then((myJson) => {
    console.log(myJson);
    
      let x= myJson.content.map  ((a)=>{ return <UserField name={a.firstName} surname={a.lastName} email={a.email}></UserField>})
     Setstate(x);
    });
  


    }
    ,[]);
    const  handleClick= async (e,i)=>
    {   
        if((index+i)>=0)
        {
        index=index+i;
        console.log(index);
        let url = new URL("http://localhost:8090/api/user/search")
        url.search = new URLSearchParams({text:localStorage.getItem("input"),page:index})
        let x= await fetch(url);
        let myJson=await x.json();
         if(myJson.content.length!=0)
         {
        let y = myJson.content.map  ((a)=>{ return <UserField name={a.firstName} surname={a.lastName} email={a.email}></UserField>})
         Setstate(y);}
        
    }
    
        
    }
return(
    <div className='find-main-container'>
    {state}
    <div className="bottom-navbar">
    <Button onClick={(e)=>handleClick(e,-1)} variant="contained"  color="primary">{x}</Button>
    <Button onClick={(e)=>handleClick(e,1)} variant="contained"  color="primary">{y}</Button>
    </div>
    </div>
)
}
export default Search_List;