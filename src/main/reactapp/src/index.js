import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import App from './App';
import reportWebVitals from './reportWebVitals';

// 내가 만든 컴포넌트 import(가져오기) 호출
//import 컴포넌트 from 컴포넌트파일경로;
 import JSX선언 from './chapter3/1_jsx';
// const root = ReactDOM.createRoot(document.getElementById('root'));
// root.render(
//   <React.StrictMode>
//     <JSX선언 />
//   </React.StrictMode>
// );

//chapter3실습
import Book from'./chapter3/Book';
import Library from './chapter3/Library';


//chapter4 실습
import Clock from './chapter4/Clock1';

//chapter5 실습
import CommentList from './chapter5/CommentList'

    // {/* <Library /> */}
    // {/* <Clock /> */}
    // {/* <CommentList /> */}

//chapte7 실습
import Counter from './chapter7/Counter';
import UseStateList from './chapter7/UseStateList';
const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <React.StrictMode>
    {/* <Counter /> */}
    <UseStateList />
  </React.StrictMode>
  ); 
//setInterval(함수(),밀리초):밀리초마다 해당 함수 실행
// setInterval(()=>{
//   root.render(<Clock />);
// } ,1000);



// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
