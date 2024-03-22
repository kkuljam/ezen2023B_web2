export default function Toolbar(props){
    //1. props 상태변수
    //const[isLoggedIn,onClickLoigin,onClickLogout]=props
    
    return(<>
        <div>
            {
                props.isLoggedIn && <span>환영합니다.</span>
            }
            {
               props.isLoggedIn ? 
                (<button onClick={props.onClickLogout}>로그아웃</button>)
                :
                (<button onClick={props.onClickLoigin}>로그인</button>)
            }
        </div>
    </>);
}