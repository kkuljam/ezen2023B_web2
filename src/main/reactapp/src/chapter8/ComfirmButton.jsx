import { useState } from "react"
export default function ConfirmButton(props){
    
    const [isConfirmed,setIsConfirmed]=useState(false)
    
    function handleConfirm(){
        //console.log(prevIsConfirmed)
        setIsConfirmed((prevIsConfirmed)=>!prevIsConfirmed)
    }
    return (<>
        <button onClick={handleConfirm} disabled={isConfirmed}>
            {isConfirmed ? '확인됨' : '확인하기'}
        </button>
    </>)
}