package ezenweb.controller;
import ezenweb.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;


@RestController // @Controller + @ResponseBody : 데이터를 주고 받은 REST 역할
@RequestMapping("/member")
public class TMemberController {
    @PostMapping("/signup")
    public boolean memberSignup(){
        return false;
    }
    @GetMapping("/login")
    public boolean memberLogin(){return false;}
    @GetMapping("/logout")
    public boolean memberLogout(){return false;}
    @GetMapping("/get.do")
    public List<Objects> getmember(){
        return null;
    }
    @PutMapping("/put.do")
    public boolean putmember(){
        return false;
    }
    @DeleteMapping("/delete.do")
    public boolean deletemember(){
        return false;
    }
}
