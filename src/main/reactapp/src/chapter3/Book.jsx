/*
    컴포넌트 만드는 방법
        1. 첫글자를 대문자로 하는 .jsx 파일 생성한다.
        2. 함수형컴포넌트 생성
            1. 
            function 컴포넌트명(props){
               return(jsx형식문법);
            }
            2. 다른 곳에서 해당 파일 import 시 반환할 컴포넌트명시
                -해당 파일을 여러개 함수가 존재 할 수 있으므로
                    export default 해당파일호출시반환컴포넌트명;
            합체:
            export default function 컴포넌트명(props){
               return(jsx형식문법);
                }
    
    컴포넌트를 호출하는 방법
        1. 다른 컴포넌트에서 해당 컴포넌트 호출하는 방법
        import  컴포넌트명  from 컴포넌트파일경로;
*/
export default function Book(props){
    return(
        <div>
            <h1>{props.name}입니다.</h1>
            <h2>나이는 {props.age} 입니다. </h2>
        </div>
    );
}