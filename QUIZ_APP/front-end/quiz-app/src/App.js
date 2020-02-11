import React from 'react';

import Quiz from "./Quiz";
class App extends React.Component {

    constructor(props) {
        super(props);
        this.state = {};
    }



    render() {
        return (
            <>
                <Quiz />
            </>
        );
    }
}

export default App;
