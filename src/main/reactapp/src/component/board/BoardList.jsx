import axios from "axios"
import { useEffect, useState } from "react"
import MediaCard from "./MediaCard";
export default function BoardList (props){

    //1. useState 변수
    const [boardList , setBoardList]=useState( [] );

    console.log(boardList);
    useEffect(()=>{
        axios.get('/board/get.do')
        .then((r)=> {
            //서버로 받은 데이터를 setState 넣어주면 재렌더링
            console.log(r);
            setBoardList(r.data);
        })
        .catch(error=>{console.log(error)})

    },[])

    return(<>
        {
            boardList.map((board)=>{
                return(
                    <div>
                        <MediaCard board={board}/>
                    </div>
                )
            })
        }
      
    </>)
}