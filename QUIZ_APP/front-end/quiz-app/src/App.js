import React from 'react';


class App extends React.Component{

  constructor(props) {
    super(props);
  }

  dataFetcher = async (url) => {
    const response = await fetch(url);
    const json = await response.json();
  };

  render() {
    return (
        <p>Hello</p>
    );
  }
}

export default App;
