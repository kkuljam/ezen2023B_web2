import { useState } from "react";

export default function NameForm(props){
    //1. 함수 
    function 제출1(){
        let nameInput=document.querySelector('#nameInput').value;
        console.log(nameInput)
    }

    //2. 함수2
    const [value, setValue]=useState('');
        //2. state 변경함수
    const handleChange = (e)=> {setValue(e.target.value);}
    
    const [value2, setValue2]=useState('');
    const handleChange2 = (e)=> {
        setValue2(e.target.value);
        e.preventDefault(); // 브라우저들의 이벤트들을 제거
    } 

    //4.
    const [value3, setValue3]=useState('');
    const handleChange3 = (e)=> {
        setValue3(e.target.value);
       
    } 
    const handleSubmit = (e)=> {
        console.log(value);
        console.log(value2);
        console.log(value3);
    }


    return(<>
        <form>
            이름 : <input id="nameInput" />
            <button type="button" onClick={제출1}>제출1</button>
        </form>
        <form>
            이름 : <input type="text" value={value} onChange={handleChange} />
            요청사항 :
            <textarea value={value2} onChange={handleChange2}/>
            과일을 선택하세요
            <select>
                <option value="apple">사과</option>
                <option value="banana">바나나</option>
                <option value="wetermelon">수박</option>
                <option value="grape">포도</option>
            </select>
            <button type="button" onClick={handleSubmit}>제출2</button>
        </form>
    </>)
}