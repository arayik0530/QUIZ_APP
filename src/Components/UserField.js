import React from 'react';

const UserField=({name,surname,email})=>
{
  return (<div className="UserField">
  <div>{name}</div>
  <div>{surname}</div>
  <div>{email}</div>
</div>)
}
export default UserField;