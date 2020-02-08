import React from 'react';
import '../Css/styles.css';
const UserField=({name,surname,email})=>
{
  return (<div className="UserField">
      <div className="UserFieldImage"></div>
      <div className="spacer"></div>
      <div className="UserFieldAbout">
  <div>{name}</div>
  <div>{surname}</div>
  <div>{email}</div>
  </div>
</div>)
}
export default UserField;