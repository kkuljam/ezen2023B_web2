import axios from "axios";

export default function Login (props){

    //1. 로그인 요청 함수
    const onLogin = ()=> {
        //1. 전송할 폼 가져온다
        const loginForm=document.querySelector('#loginForm');
        //2. 데이터폼으로 전환
        const loginFormData=new FormData(loginForm);
        //3. 서버와 통신
        axios.post('/member/login/post.do',loginFormData)
        .then((r)=>{console.log(r)
            if(r.data){
                alert('로그인 성공');
                window.location.href="/";
            }else{
                alert('로그인 실패');
            }
        })
        .catch((e)=>{console.log(e);})
    }

    return(<>
        <form id="loginForm">
            이메일: <input name="memail" type="text"/>
            비밀번호: <input name="mpassword" type="password"/>
            <button type="button" onClick={onLogin}>로그인</button>
        </form>
    </>)
}

export function Logout(){
    axios.get('/member/logout/get.do')
        .then((r)=>{console.log(r)
            if(r){
                alert('로그아웃 성공');
                window.location.href="/";
            }else{
                alert('로그아웃 실패');
            }
        })
        .catch((e)=>{console.log(e);})
}