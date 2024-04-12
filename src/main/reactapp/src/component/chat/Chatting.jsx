import { useState,useRef, useContext, useEffect } from "react";
import { LoginInfoContext } from "../Index";
import styles from "./Chatting.css"
import Button from '@mui/material/Button';
import Menu from '@mui/material/Menu';
import MenuItem from '@mui/material/MenuItem';
import * as React from 'react';
import { type } from "@testing-library/user-event/dist/type";



export default function Chatting (props){
    //1. 해당 컴포넌트가 렌더링 될 때 소켓 재렌더링 방지 useRef
        //useRef(초기값) : {current : 값}
        // 컴포넌트가 렌더링시 참조값을 고정할 수 있다
    let clientSocket = useRef(null);
    //2. Ref 참조가 없으면
    if( !clientSocket.current){
    // (클라이언트) 웹소켓 구현================
        //1. new WebSocket(서버소켓url); //ws://192.168.17.123:8080/chat  //localhost:8080/chat
        clientSocket.current = new WebSocket('ws://192.168.17.123:8080/chat'); // 비동기 // 서버소켓에게 접속 요청
        //확인
        console.log(clientSocket);
        // onclose // onerror // onmessage// onopen : WebSocket 객체 내 포함된 메소드들
        // 각 메소드 정의
            // 클라이언트소켓이 close 되었을 때 콜백함수 정의
        clientSocket.current.onclose= (e)=>{console.log("close")}
            // 클라이언트소켓이 error 발생했을 때 콜백함수 정의 (* error 이미 발생했고 다음 행동 정의)
        clientSocket.current.onerror= (e)=>{console.log("error")}
            // 클라이언트소켓이 message 받았을 때
        clientSocket.current.onmessage=(e)=>{console.log("message 받았을 때");
            msgList.push(JSON.parse(e.data))    
            setMsgList([...msgList])
        }
            // 클라이언트소켓이 open 발생했을 때 콜백함수 정의
        clientSocket.current.onopen=(e)=>{console.log("open 발생했을 때");}
    }
        const {loginInfo} = useContext  (LoginInfoContext);

        const onSend=()=>{
            let info = {
                msg : msgInput ,                // 작성된 내용
                fromMname :loginInfo.mname,        //현재 로그인 작성자
                date: getTime(),
                type: 'msg'
        }
            //2. 연결된 서버소켓에게 메시지 보내기
                // send() : 데이터 타입 : 텍스트
                    //JSON -> 문자 JSON.stringify (js객체)
                    // 문자 -> JSON : JSON.parse (문자열)
            clientSocket.current.send(JSON.stringify(info)); // 입력받은 데이터를 서버소켓에게 보내기
            setMsgInput('')
        }
       

        //4. 연결 종료
        // clientSocket.close();
    //========================================

    //- 채팅 내용 입력창
    const [msgInput, setMsgInput]=useState('');
    //채팅 창의 내용물들
    const [msgList , setMsgList]=useState([]);
    // 채팅 내용 입력창에서 엔터를 했을 때 / ctrl+엔터
    const activeEnter = (e)=>{
        
        //1. ctrl+ 엔터 했을 때
        if(e.keyCode == 13 && e.ctrlKey){
            setMsgInput(msgInput + '\n' ); 
            return;
        }
        
        //2. 엔터 했을 때
        if(e.keyCode == 13){ // 엔터 눌렀을 때
            onSend();
            return;
        }
    }
    //- 스크롤 자동으로 최하단으로 내리기
    useEffect(()=>{
        let chatcont = document.querySelector('.chatcont')
        console.log(chatcont.scroll)
        console.log(chatcont.scrollTop) // 현재 스크롤 상단 위치
        console.log(chatcont.scrollHeight) // 스크롤 전체 높이 길이
        //2.
        chatcont.scrollTop = chatcont.scrollHeight; // 상단 위치를 초
    })
   
    const [anchorEl, setAnchorEl] = useState(null);
    const open = Boolean(anchorEl);
    const handleClick = (event) => {
      setAnchorEl(event.currentTarget);
    };
    const handleClose = () => {
      setAnchorEl(null);
    };

    // 이모티콘 이미지를 클릭했을 때 전송
    const onEmoSend =(emo)=>{
        let info={
            msg: emo,                   // 이모티콘 식별자
            fromMname : loginInfo.mname,// 작성자
            date: getTime(),
            type : 'emo'                // 메시지 타입 (출력시 식별하기 위해)
        }
        clientSocket.current.send(JSON.stringify(info));
        //- 드롭다운 닫기
        handleClose();
    }

    //- msg 타입에 따른 HTML 반환 함수
    const typeHTML = (m)=>{
        if(m.type=='msg'){
            return <div className="content"> {m.msg}</div>
        }else if(m.type=='emo'){
            return <img src={'/emo/'+m.msg}/>
        }
    }

    function getTime(){
        const date = new Date();
        const hour = date.getHours();
        const min = date.getMinutes();
        return date = `${hour<13?` 오전 ${(hour-12)<10?`0${(hour-12)}`:(hour-12)}`:` 오후 ${(hour-12)<10?`0${(hour-12)}`:(hour-12)}`}시 ${min<10?`0${min}`:min}분`;
    }



    return(<>
        <div>
            <h3>채팅방</h3>
            <div className="chatbox">
                <div className="chatcont">
                    {
                        msgList.map((m)=>{
                            return(<>
                                {
                                    loginInfo.mname == m.fromMname ?
                                    (<div className="rcont">
                                        <div className="subcont">
                                            <div className="date"> {m.date}</div>
                                            {typeHTML(m)}
                                        </div>
                                    </div>
                                    ) :
                                    <div className="lcont">
                                        <img className="pimg" src={'/uploadImg/default.jpg'}/>
                                        <div className="tocont">
                                            <div className="name"> {m.fromMname } </div>
                                            {typeHTML(m)}
                                            <div className="date"> {m.date}</div>
                                        </div>
                                    </div>
                                }
                            </>)
                        })
                    }
                </div>
                <div className="chatbottom">
                    <textarea value={msgInput} onChange={(e)=>{setMsgInput(e.target.value)}}
                        onKeyDown={activeEnter}
                    ></textarea>
                    <button type="button" onClick={onSend} >전송</button>
                </div>
                <div>
                
                    <Button
                        id="basic-button"
                        aria-controls={open ? 'basic-menu' : undefined}
                        aria-haspopup="true"
                        aria-expanded={open ? 'true' : undefined}
                        onClick={handleClick}
                    >
                        이모티콘
                    </Button>
                    <Menu
                        id="basic-menu"
                        anchorEl={anchorEl}
                        open={open}
                        onClose={handleClose}
                        MenuListProps={{
                        'aria-labelledby': 'basic-button',
                        }}
                    >
                        <div style={{height:200, overflow:'scroll'}}>
                        {
                            Array(43).fill().map((v,i)=>{
                                return(<>
                                    <img src={`/emo/emo${i+1}.gif`} onClick={(e)=>onEmoSend(`emo${i+1}.gif`)}/>
                                    {(i+1)% 5==0 && <br/>}
                                </>) 
                            })
                        }
                        </div>
                    </Menu>
                    
                </div>
            </div>
           
        </div>
    </>)
}