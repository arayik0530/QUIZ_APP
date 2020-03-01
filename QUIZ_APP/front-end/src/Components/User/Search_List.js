import React, { useEffect,useContext } from 'react';
import UserField from './UserField';
import Button from '@material-ui/core/Button';
import {useHistory} from 'react-router-dom';
import {UserContext} from '../../Contexts/user';
const x = "<<", y = ">>";
let index = 0;

function Search_List(props) {
   
  const User = useContext(UserContext);

  let [state, Setstate] = React.useState(null);
  let images=[];
  
  useEffect(() => {
    
    let url = new URL("http://localhost:8090/api/user/search")
    url.search = new URLSearchParams({  text: props.input })
    fetch(url,{
      method:"GET",
      headers:{
      "Content-Type":"application/json",
      "Authorization": "Bearer_ "+localStorage.getItem("token")
    }})
      .then((response) => {
        return response.json();
      })
      .then((myJson) => {
        myJson.content.forEach(element => {
          async function getdata()
{
let   response= await fetch(`http://localhost:8090/api/user/image/${element.id}`,
{
    method:"GET",
    headers:{
      "Content-Type":"application/x-www-form-urlencoded",
        "Authorization": "Bearer_ "+localStorage.getItem("token")
    }
})

 let  x=  await response.blob();
 images.push(URL.createObjectURL(x)) ;


}
         getdata().then(()=>{  let x = myJson.content.map( (a,index) => { return <UserField name={a.firstName} surname={a.lastName} email={a.email} image={images[0]}  ></UserField> })
         Setstate(x);});
        
        });
        
      });



  }
    , []);
  const handleClick = async (e, i) => {
    window.scrollTo({
      top: 0,
      left: 0,
      behavior: 'smooth'
    });
    if ((index + i) >= 0) {
      let k = index + i;

      let url = new URL("http://localhost:8090/api/user/search")
      url.search = new URLSearchParams({ text: props.input, page: k })
      let x = await fetch(url,{
        method:"GET",
        headers:{
        "Content-Type":"application/json",
        "Authorization": "Bearer_ "+localStorage.getItem("token")
      }});
      let myJson = await x.json();
      if (myJson.content.length != 0) {
        images=[];
        myJson.content.forEach( async (element)=>{
          let   response= await fetch(`http://localhost:8090/api/user/image/${element.id}`,
          {
              method:"GET",
              headers:{
                "Content-Type":"application/x-www-form-urlencoded",
                  "Authorization": "Bearer_ "+localStorage.getItem("token")
              }
          });
          
           let  x=  await response.blob();
           images.push(URL.createObjectURL(x)) ;
        });
        
        let y = myJson.content.map((a,index) => { return <UserField name={a.firstName} surname={a.lastName} email={a.email} image={images[index]}></UserField> })
        Setstate(y);

        index = k;
      }



    }


  }
  return (
    <div className='find-main-container'>
      {state}
      <div className="bottom-navbar">
        <Button onClick={(e) => handleClick(e, -1)} variant="contained" color="primary">{x}</Button>
        <Button onClick={(e) => handleClick(e, 1)} variant="contained" color="primary">{y}</Button>
      </div>
    </div>
  )
}
export default Search_List;