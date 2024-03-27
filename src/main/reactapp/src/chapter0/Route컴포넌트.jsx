import { BrowserRouter, Link, Route, Routes } from "react-router-dom"
import JSX선언 from "../chapter3/1_jsx"
import Library from "../chapter3/Library"
import Clock from "../chapter4/Clock1"

export default function Route컴포넌트 (props){


    return(<>
        <BrowserRouter>
        <div id="wrap" style={{display : 'flex'}}>
            <고정형컴포넌트 />
                <Routes>
                    <Route path='/chapter3/jsx선언' element={<JSX선언/>}/>
                    <Route path='/chapter3/library' element={<Library/>}/>
                    <Route path='/chapter3/clock' element={<Clock/>}/>
                </Routes>
        </div>
        </BrowserRouter> 
    </>)
}

function 고정형컴포넌트 (props){
    return(<> 
        <div>
            <ul>
                <li><a href="/chapter3/jsx선언">jsx 선언</a></li>
            </ul>
            <ul>
                <li> <Link to= "/chapter3/jsx선언">jsx 선언 </Link> </li>
                <li> <Link to= "/chapter3/library">Library </Link> </li>
                <li> <Link to= "/chapter3/clock">Clock </Link> </li>
            </ul>
        </div>
    </>)
}


