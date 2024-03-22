import Book from './Book'

export default function Library(props){
    let successData=[
        {name:'강호동',age:25},
        {name:'유재석',age:35},
        {name:'신동엽',age:45}
    ]
    return(
        <div>
           {
            successData.map((data)=>{
                return(<Book name={data.name} age={data.age}/>)
            })
           }
        </div>
    );
}