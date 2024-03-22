import { useState } from "react";
import Toolbar from "./Toolbar";
export default function LandingPage(props){

    //1. state 상태변수
    const[isLoggedIn,setIsLoggedIn]=useState(false);

    //2. 로그인 클릭함수
    const onClickLoigin = ()=>{
        setIsLoggedIn(true)
    }
    //3. 로그아웃 클릭함수
    const onClickLogout = ()=>{
        setIsLoggedIn(false)
    }

    return(<>
        <div>
            <Toolbar 
                isLoggedIn={isLoggedIn}
                onClickLoigin={onClickLoigin}
                onClickLogout={onClickLogout}
                setIsLoggedIn={setIsLoggedIn}
            />
            <div>소플과 함께하는 리액트 공부!</div>
        </div>
    </>)
}