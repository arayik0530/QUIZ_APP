import React from 'react';

export default function QuizItem(x)
{   
    return( <div className="activeExamsList-container-Item"><div className='title'>{x.props.map((y)=>{return <div>{y}</div> })}</div></div>)
}