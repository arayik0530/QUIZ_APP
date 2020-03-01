import React from 'react';
import '../../Css/styles.css';
const UserField=({name,surname,email,image})=>

{
   console.log("image",image)
  return (<div className="UserField">
      <div className="UserFieldImage"><img className="UserFieldImage" src={image} alt='userImage'></img></div>
      
      <div className="UserFieldAbout">
  <div>{name} {surname}</div>
  <div>{email}</div>
  </div>
</div>)
}
export default UserField;