package ezenweb.controller;

import ezenweb.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;


@RestController // @Controller + @ResponseBody : 데이터를 주고 받은 REST 역할
@RequestMapping("/board")
public class TBoardController {
   /* @Autowired private BoardService boardService;
    @PostMapping("/post.do")
    public boolean postBoard(){
        return boardService.postBoard();
    }
    @GetMapping("/get.do")
    public List<Objects> getBoard(){
        return boardService.getBoard();
    }
    @PutMapping("/put.do")
    public boolean putBoard(){
        return boardService.putBoard();
    }
    @DeleteMapping("/delete.do")
    public boolean deleteBoard(){
        return boardService.deleteBoard();
    }*/
}
