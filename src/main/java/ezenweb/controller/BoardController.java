package ezenweb.controller;

import ezenweb.model.Dto.BoardDto;
import ezenweb.model.entity.BoardEntity;
import ezenweb.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;


@RestController // @Controller + @ResponseBody : 데이터를 주고 받은 REST 역할
@RequestMapping("/board")
public class BoardController {
    @Autowired private BoardService boardService;
    @PostMapping("/post.do")
    public boolean postBoard(BoardDto boardDto){
        return boardService.postBoard(boardDto);
    }
    @GetMapping("/get.do")
    public List<BoardDto> getBoard(){
        return boardService.getBoard();
    }
    @PutMapping("/put.do")
    public boolean putBoard(){
        return boardService.putBoard();
    }
    @DeleteMapping("/delete.do")
    public boolean deleteBoard(){
        return boardService.deleteBoard();
    }
}
