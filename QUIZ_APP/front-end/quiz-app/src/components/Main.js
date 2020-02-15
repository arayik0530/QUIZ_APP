import React from 'react';
import {useState} from 'react';
import SingleChoice from "./SingleChoice";
import MultipleChoice from "./MultipleChoice";
import { connect } from 'react-redux'


function Main(props) {
    const {isMulti} = props;
    return (
        <>
            { isMulti ? <MultipleChoice/> : <SingleChoice/>}
        </>
    );
}

const mapStateToProps = (state, ownProps) => ({
    isMulti: state.isMulti
})

export default connect(mapStateToProps, null)(Main);
