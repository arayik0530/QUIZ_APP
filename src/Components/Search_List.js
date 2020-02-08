import React, { useEffect } from 'react';
import UserField from './UserField'
function Search_List()
{   let NumberofPages=0,NumberofElements=0;
    const [state,Setstate]= React.useState(null);
    useEffect(()=>{
        let url = new URL("http://localhost:8090/api/user/search")
    url.search = new URLSearchParams({text:localStorage.getItem("input")})
    fetch(url)
    .then((response) => {
      return response.json();
    })
    .then((myJson) => {
    console.log(myJson);
     NumberofPages=myJson.totalPages;
      NumberofElements=myJson.pageable.pageSize;
      let x= myJson.content.map  ((a)=>{ return <UserField name={a.firstName} surname={a.lastName} email={a.email}></UserField>})
     Setstate(x);
    });
  


    }
    ,[]);
return(
    <div className='find-main-container'>
    {state}
    </div>
)
}
export default Search_List;