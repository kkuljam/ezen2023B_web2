
import React from "react";

const user= {name:'강호동', age : 10};

function formatName(user){
    return user.name+' '+user.age;
}
function JSX선언(props){

    let name='유재석'; // 변수
    return(
        <div>
            안녕하세요 리액트 공간.<br/>
            저는 {name}입니다.<br/> 
            {formatName(user)}
            
        </div>
    )
}

export default JSX선언;