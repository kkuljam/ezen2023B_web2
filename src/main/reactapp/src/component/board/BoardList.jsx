import axios from "axios"
import { useEffect, useState } from "react"
import MediaCard from "./MediaCard";
import { Pagination } from "@mui/material";

export default function BoardList (props){

    //1. useState 변수
    const [pageDto , setPageDto]=useState( { 
        page:1 , count:0, data: []
    } );

    const getBoard=()=>{

        const info={page: pageDto.page, view:2}
        axios.get('/board/get.do',{params: info})
        .then((r)=> {
            //서버로 받은 데이터를 setState 넣어주면 재렌더링
            console.log(r);
            setPageDto(r.data);
        })
        .catch(error=>{console.log(error)})
    }

    useEffect(()=>{getBoard()},[pageDto.page])

    const handleChange = (e, value) => {
        pageDto.page=value;
        setPageDto({...pageDto});
    }

    return(<>
         <div style={ { display:"flex" , flexWrap : "wrap"} } >
        {
            pageDto.data.map((board)=>{
                return(
                    <div>
                        <MediaCard board={board} getBoard={getBoard}/>
                    </div>
                )
            })
        }
        </div>
        <Pagination count={pageDto.count} page={pageDto.page} onChange={handleChange} />
    </>)
}

//count : 전체 페이지수
//page : 현재 페이지수
//onChange : 콜백함수