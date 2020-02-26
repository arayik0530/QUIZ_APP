import React,{useEffect} from 'react';
import IconButton from '@material-ui/core/IconButton';
import AddIcon from '@material-ui/icons/Add';
import TextField from '@material-ui/core/TextField'
import Switch from '@material-ui/core/Switch';
import Button from '@material-ui/core/Button';
import { makeStyles } from '@material-ui/core/styles';
import Autocomplete from '@material-ui/lab/Autocomplete';
import '../../Css/Questions.css';

const useStyles = makeStyles({
    root: {
     width:300
    }
    
  });
export default function Question() {
    const classes =useStyles();
    const [Topic, SetTopic] = React.useState(null); 
    const [Topics,SetTopics]=React.useState([{}]);
    const [text,Settext]=React.useState("");
    const [state,Setstate]=React.useState([]);

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
    
    const AddNew= () =>
    {   let x = [...state];
        x.push({text:"",isRight:false});
        Setstate(x);
        

    }
    const handleChange=(e,index)=>
    {  let x= [...state];
        x[index].text=e.target.value;
      Setstate(x);
      
    }
    const toggleIsRight=(e,index)=>
    {  let x= [...state];
        x[index].isRight=e.target.checked;
        Setstate(x)
        
    }
    const HandleAdd = () => {
        let isMultiAnswer=false;
        let total=0;
        state.forEach((x)=>{if(x.isRight)total=total+1})
        if(total>=2)isMultiAnswer=true;
        Settext("");
        Setstate([]);

    }
    return (
        <div>
            <h1>Questions</h1>
            <h2 style={{textAlign:"center"}}>Add a new question:</h2>
        <div className="addnew">
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
              
                <div>
                    <label >Question:</label>

                    <textarea value={text} onChange={(e)=>Settext(e.target.value)} placeholder=" Type your question here..." id="question" 
                        rows="5" cols="33">
                       
                    </textarea>
                </div>
                <div style={{marginLeft:10,marginBottom:50}}>
                    <label >Answers: <IconButton onClick={AddNew}><AddIcon></AddIcon></IconButton></label>
                   
                    <div className="answers">     
                    {
                    state.map((x,index)=>{  return (<div><TextField style={{marginBottom:10}} value={x.text}  onChange={(e)=>handleChange(e,index)} ></TextField>
                    <Switch value={x.isRight} onChange={(e)=>toggleIsRight(e,index)} color="primary"/>
                  </div>
                  )
                })
                    }
                    </div>
                   
                </div>
              <Button onClick={HandleAdd} variant="outlined" color="primary"> Add Question</Button>
                </div>
        </div>
    )
}