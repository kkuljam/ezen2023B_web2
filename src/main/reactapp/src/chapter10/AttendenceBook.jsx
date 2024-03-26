export default function AttendenceBook(props){

    //1. 샘플데이터
    const students=[
        {id:1 , name:'Inje'},
        {id:2 ,name:'Steve'},
        {id:3 ,name:'Bill'},
        {id:4 ,name:'Jeff'},
    ]

    return(<>
            <ul>
                {
                    //JSX 시작
                    students.map((student)=>{
                        return(<><li 
                                key={student.id}
                                id={student.id}
                                className={student.id}
                            >
                                {student.name}
                            </li></>)
                    })
                }
            </ul>
        </>);
}