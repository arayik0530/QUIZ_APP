import React from 'react';
import Navbar from './Navbar';
import renderer from 'react-test-renderer';

it('renders correctly', () => {
    localStorage.setItem("UserContext",JSON.stringify({
        "email": "string",
        "firstName": "string",
        "id": 0,
        "imageId": 0,
        "lastName": "string",
        "phone": "string",
        "roles":['[USER]']
        
      }))
  const tree = renderer
    .create(<Navbar ></Navbar>)
    .toJSON();
  expect(tree).toMatchSnapshot();
});