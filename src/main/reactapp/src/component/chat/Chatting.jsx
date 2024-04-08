import { useState,useRef } from "react";

export default function Chatting (props){
    //1. 해당 컴포넌트가 렌더링 될 때 소켓 재렌더링 방지 useRef
        //useRef(초기값) : {current : 값}
        // 컴포넌트가 렌더링시 참조값을 고정할 수 있다
    let clientSocket = useRef(null);
    //2. Ref 참조가 없으면
    if( !clientSocket.current){
    // (클라이언트) 웹소켓 구현================
        //1. new WebSocket(서버소켓url);
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
            msgList.push(e.data)    
            setMsgList([...msgList])
        }
            // 클라이언트소켓이 open 발생했을 때 콜백함수 정의
        clientSocket.current.onopen=(e)=>{console.log("open 발생했을 때");}
    }

        const onSend=()=>{
            //2. 연결된 서버소켓에게 메시지 보내기
            clientSocket.current.send(msgInput); // 입력받은 데이터를 서버소켓에게 보내기
        }
       

        //4. 연결 종료
        // clientSocket.close();
    //========================================

    //- 채팅 내용 입력창
    const [msgInput, setMsgInput]=useState('');
    //채팅 창의 내용물들
    const [msgList , setMsgList]=useState([]);

    return(<>
        <div>
            <h3>채팅방</h3>
            <div>
                {
                    msgList.map((msg)=>{
                        return<div>{msg}</div>
                    })
                }
            </div>
            <textarea value={msgInput} onChange={(e)=>{setMsgInput(e.target.value)}}></textarea>
            <button type="button" onClick={onSend} >전송</button>
        </div>
    </>)
}