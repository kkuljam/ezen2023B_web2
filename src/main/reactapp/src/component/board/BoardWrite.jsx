import axios from "axios"
import { useRef } from "react"

export default function BoardWrite (props){
    
    //1. 재렌더링 고정 참조 변수
    const boardWriteFormRef=useRef();
    console.log(boardWriteFormRef)
    
    const onWrite= ( )=>{
        console.log(boardWriteFormRef.current);

        //2.
        //3.
        axios.post('/board/post.do',boardWriteFormRef.current)// axios contentType: mulripart
        .then((r)=> {
            console.log(r)
            if(r.data){
                alert('글쓰기 성공');
                window.location.href="/board";
            }else{
                alert('글쓰기 실패');
            }
        })
        .catch(error=>{console.log(error)})

    }
    
    return(<>
    
        <h3>게시물 쓰기</h3>
        <form ref={boardWriteFormRef}>
            <input name="bcontent" type="type" />
            <input type="file" name="uploadList" multiple accept="imge/*"/>
            <button type="button" onClick={onWrite}>등록</button>
        </form>
    </>)
}