import * as React from 'react';
import Card from '@mui/material/Card';
import CardActions from '@mui/material/CardActions';
import CardContent from '@mui/material/CardContent';
import CardMedia from '@mui/material/CardMedia';
import Button from '@mui/material/Button';
import Typography from '@mui/material/Typography';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';

export default function MediaCard(props) {
  const onDelete = (e,bno)=>{
    console.log('bno'+bno)
    
    //const navigate=useNavigate(); 

    axios.delete('/board/delete.do',{params:{bno:bno}})
    .then((r)=> {
      console.log(r);
      //1. get 방식
      //window.location.href='/board';
      //2. 라우터 방식
        //1. useNavigate() 훅 / import { useNavigate } from 'react-router-dom';
        //2. navigate=useNavigate(라우터 URL); 
        //navigate('/board');
      //3.
      props.getBoard();
  })
  .catch(error=>{console.log(error)})
  }

    console.log(props);
    return (
    <Card sx={{ maxWidth: 400 }}>
      <CardMedia
        sx={{ height: 140 }}
        image={"/uploadImg/"+props.board.bimgList[0]}
        title="green iguana"
      />
      <CardContent>
        <Typography gutterBottom variant="h5" component="div">
          {props.board.memail}
        </Typography>
        <Typography variant="body2" color="text.secondary">
         {props.board.bcontent}
        </Typography>
      </CardContent>
      <CardActions>
        <Button size="small" onClick={(event)=>onDelete(event, props.board.bno)}>삭제</Button>
        {/* <Button size="small">Learn More</Button> */}
      </CardActions>
    </Card>
  );
}