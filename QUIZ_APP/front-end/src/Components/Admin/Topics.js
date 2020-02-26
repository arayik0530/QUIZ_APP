import React,{useEffect} from 'react'
import TextField from '@material-ui/core/TextField';
import '../../Css/Topics.css';
import AddIcon from '@material-ui/icons/Add';
import IconButton from '@material-ui/core/IconButton';
import Autocomplete from '@material-ui/lab/Autocomplete';
import CheckIcon from '@material-ui/icons/Check';
import { makeStyles } from '@material-ui/core/styles';
const useStyles = makeStyles({
  root: {
   width:300
  }
  
});

export default function Topics()
{  const classes =useStyles();
  const [Topic, SetTopic] = React.useState(null); 
  const [Topics,SetTopics]=React.useState([]);
  const [NewTopic,SetNewTopic]=React.useState("")
  const [NewUpdateTopic,SetNewUpdateTopic]=React.useState("")
  useEffect(  ()=>{
    async function getData()
    { 
      
   let response = await fetch("http://localhost:8090/api/topic/all",{
     method:"GET",
     headers:{
      "Content-Type":"application/json",
      "Authorization": "Bearer_ "+localStorage.getItem("token")
     }
   });
   let  x = await response.json();
   SetTopics(x.content);
  
  
  
   
   
  }
  getData();
  },[]);
  const AddTopic = async ()=>{
    let response = await fetch("http://localhost:8090/api/topic/create",{
      method:"POST",
      headers:{
       "Content-Type":"application/json",
       "Authorization": "Bearer_ "+localStorage.getItem("token")
      },
      body:JSON.stringify({
        "title":NewTopic
      })
    });
    
  
  }
  const UpdateTopic = async ()=>{
    console.log(NewUpdateTopic)
    let response = await fetch("http://localhost:8090/api/topic/update",{
      method:"PUT",
      headers:{
       "Content-Type":"application/json",
       "Authorization": "Bearer_ "+localStorage.getItem("token")
      },
      body:JSON.stringify({title:NewUpdateTopic,id:Topic.id})
    });
    console.log(response)
  }
    return(
        <div >
            <h1>Edit Topics</h1>
            <div className='AddTopic'>
            <h3>Add Topic</h3>
            <TextField onChange={(e)=>SetNewTopic(e.target.value)} value={NewTopic}  size="medium" variant="standard"></TextField>
            <IconButton onClick={AddTopic}> <AddIcon></AddIcon></IconButton>
            <h3>Update Topic</h3>
            <div className="search">
            <Autocomplete
            classes={{inputRoot:classes.root}}
      id="combo-box-demo"
      options={Topics}
      getOptionLabel={option => option.title}
      value={Topic}
      onChange={(event, newValue) => {
        SetTopic(newValue);
        
      }}
      style={{marginBottom:10}}
      renderInput={params => <TextField {...params} label="Topic" variant="outlined" />}
    />
       </div>
       
            <TextField  value={NewUpdateTopic} onChange={(e)=>{SetNewUpdateTopic(e.target.value)}} size="medium" variant="standard"></TextField>
           
            <IconButton onClick={UpdateTopic}><CheckIcon></CheckIcon></IconButton>
            </div>
        </div>
    )
}