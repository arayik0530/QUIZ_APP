import React from "react";
import { render, unmountComponentAtNode } from "react-dom";
import { act } from "react-dom/test-utils";
import User from './User';
jest.mock('./ActiveExams',()=>{
  return function ActiveExams(){
  return(
  <div></div>
  );
}
})
let container = null;

beforeEach(() => {
  // setup a DOM element as a render target
  container = document.createElement("div");
  document.body.appendChild(container);
});
afterEach(() => {
    // cleanup on exiting
    unmountComponentAtNode(container);
    container.remove();
    container = null;
  });
  
  it("Displays user information", async ()=>{
      const fakeUser={
        email: "poghosyan26@gmail.com",
        firstName: "Ruben",
        id: 0,
        imageId: 0,
        lastName: "Poghosyan",
        phone: "0000",
        roles: [
          "USER"
        ]
      }
      localStorage.setItem("UserContext",JSON.stringify(fakeUser))
    
      
      jest.spyOn(global, "fetch").mockImplementation(() =>
      Promise.resolve({
        json: () => Promise.resolve(fakeUser)
      })
    );
   
    await act( async ()=>{
        render(<User/>,container)

    });
    expect(container.querySelector("[id='Name']").getAttribute("value")).toBe(fakeUser.firstName);
    expect(container.querySelector("[id='Surname']").getAttribute("value")).toBe(fakeUser.lastName);
    expect(container.querySelector("[id='Email']").getAttribute("value")).toBe(fakeUser.email);
  
    global.fetch.mockRestore();
  });