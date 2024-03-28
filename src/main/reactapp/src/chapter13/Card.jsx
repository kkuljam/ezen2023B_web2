export default function Card(props){
    const {title, backgroundColor, children}=props;
    return(
        <div
            style={{
                margin:8,
                padding:8,
                borderRadius:8,
                backgroundColor: backgroundColor || 'white'
            }}
        >
            {title &&<h1>{title}</h1>}
            {children}
        </div>
    );
}