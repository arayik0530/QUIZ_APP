import React from 'react';


class App extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
          user: {}
        };
    }

    dataFetcher = async (url) => {
        const response = await fetch(url);
        const json = await response.json();
        this.setState({
          user: {
            firstName: json.firstName,
            lastName: json.lastName,
          }
        });
    };

    componentDidMount() {
        (async () => {
            await this.dataFetcher("http://localhost:8080/api/user/0");
        })();
    }

    render() {
        return (
            <div>
              <h3>{this.state.user.firstName}</h3>
              <h3>{this.state.user.lastName}</h3>
            </div>
        );
    }
}

export default App;
