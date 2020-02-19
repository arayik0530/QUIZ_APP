import React from 'react';
import { Provider } from 'react-redux';
import store from "./redux/store";
import Main from "../../quiz-page/src/components/Main";

function App(props) {

    return (
        <Provider store={store}>
            <Main />
        </Provider>
    );
}

export default App;
