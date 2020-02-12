import React from 'react';

class App extends React.Component {

    constructor(props) {
        super(props);
        this.state = {patasxan: 0};
    }

    dataFetcher = async () => {
        let url = "http://localhost:8080/api/user/1";
        let response = await fetch(url, {
            headers: {
                Authorization: 'Bearer_eyJhbGciOiJIUzI1NiJ9.eyJyb2xlcyI6WyJbVVNFUiwgQURNSU5dIl0sImlkIjoxLCJleHAiOjEwMDAxNTgxMzYxMDcyLCJpYXQiOjE1ODEzNjEwNzIsImVtYWlsIjoiYWRtaW5AYWRtaW4uY29tIn0.vpCzgA71ukArQrmDeiTEt7JFwhMqAH2LxmMxuaslsk4'
            }
        });

        let json = await response.json();

        this.setState({
            patasxan: json.firstName,
        })
    };



    render() {
        return (
            <>
                <button onClick={() => this.dataFetcher()}>knopka</button>
                <p>{this.state.patasxan}</p>
            </>
        );
    }
}

export default App;
