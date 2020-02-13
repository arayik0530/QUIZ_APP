import React from 'react';
import SingleChoice from "./SingleChoice";
import MultipleChoice from "./MultipleChoice";

class App extends React.Component {

    constructor(props) {
        super(props);
        this.state = {};
    }


    render() {
        return (
            <>
                <SingleChoice/>
                <MultipleChoice/>
                <SingleChoice/>
            </>
        );
    }
}

export default App;
