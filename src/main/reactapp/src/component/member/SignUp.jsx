import { useState } from "react";
import axios from 'axios';
export default function SignUp(props){
    
    const[memail ,setMemail]=useState('');
    const[mpassword ,setMpassword]=useState('');
    const[mname ,setMname]=useState('');
    
    const onChangeMemail= (e)=>{
        setMemail(e.target.value);
    }
    //3. 전송함수
    const onSignup = (e)=>{
        console.log(memail);
        console.log(mpassword);
        console.log(mname);
        /*
            ContentType : application.json
            axios.HTTP메소드명(url, data).then(응답매개변수=>{응답 로직})
        */
        let info={memail:memail , mpassword:mpassword, mname:mname}
        axios.post("http://localhost:8080/member/signup/post.do" , info)
            .then(response=>{console.log(response)})

    
    }
    
    return(<>
        <form>
            이메일 : <input value={memail} type="text" onChange={onChangeMemail}/> <br/>
            패스워드 : <input type="password" value={mpassword} onChange={(e)=> setMpassword(e.target.value)}/> <br/>
            닉네임 : <input type="text" value={mname} onChange={(e)=> setMname(e.target.value)}/> <br/>
        </form>
        <button type="button" onClick={onSignup} >제출</button>
    </>);
}