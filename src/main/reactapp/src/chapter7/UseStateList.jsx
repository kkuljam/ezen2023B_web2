import { useState } from "react";

export default function UseStateList(props){
   
    //2. point 상태관리변수
        //1. input 입력된 값을 저장하는 상태관리변수
    let [pointInput, setPointInput]=useState('');
        //2.
    let [pointList, setPointList]=useState([]);

    //1. 등록 버튼 클릭시
    function 등록 (){
        console.log('등록')
        pointList.push(pointInput);
        setPointList([...pointList]); //재렌더링 x
    }; // f end

    function 입력변경(e){
        setPointInput(e.target.value) // 재렌더링
    }
    return(<>
        <div>
            <div>
                <input value={pointInput} type="text" onChange={입력변경} />
                <button type="button" onClick={등록}>등록</button>
            </div>
            <div>
                {
                    pointList.map((point)=>{
                        return (<div>{point}</div>)
                    })
                }
            </div>
        </div>
    
    </>);
} 