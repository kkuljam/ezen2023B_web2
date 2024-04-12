package ezenweb.controller;

import ezenweb.model.Dto.BoardDto;
import ezenweb.model.Dto.MemberDto;
import ezenweb.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/member")
@CrossOrigin("http://localhost:3000") // * 요청 근원지를 교차 허용
public class MemberController {
    @Autowired
    private MemberService memberService;

    @PostMapping("/signup/post.do") // 1. 회원가입
    public boolean doSignupPost( @RequestBody MemberDto memberDto){
        return memberService.doSignupPost( memberDto );
    }

    // 스프링 시큐리티로 인한 로그인/ 로그아웃 사용 안함=================
   /* @PostMapping("/login/post.do") // 2. 로그인
    public boolean doLoginPost( MemberDto memberDto ){
        return memberService.doLoginPost( memberDto );
    }

    @GetMapping("/logout/get.do") // 3. 로그아웃
    public boolean doLogOutGet( ){
        return memberService.doLogOutGet();
    }
*/
    @GetMapping("/login/info/get.do") // 4. 내정보
    public MemberDto doLoginInfo( ){
        return memberService.doLoginInfo();
    }

    @GetMapping("/find/memail/get.do")
    public boolean doFindEmail( String memail){
        return memberService.getFindMemail( memail );
    }

    @GetMapping("/find/myboard/get.do")
    public List<BoardDto> findByMyBoardList( ){
        return memberService.findByMyBoardList(  );
    }
}
