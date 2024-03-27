package ezenweb.controller;

import ezenweb.model.Dto.MemberDto;
import ezenweb.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/member")
@CrossOrigin("http://localhost:3000") // * 요청 근원지를 교차 허용
public class MemberController {
    @Autowired
    private MemberService memberService;

    @PostMapping("/signup/post.do") //1. 회원가입
    public boolean doSignupPost(@RequestBody MemberDto memberDto){
        System.out.println("memberDto = " + memberDto);
        return memberService.doSignupPost(memberDto);
    }

    @PostMapping("/login/post.do")//2. 로그인
    public boolean doLoginPost(MemberDto memberDto){
        return memberService.doLoginPost(memberDto);
    }
    @GetMapping("/logout/get.do") //3. 로그아웃
    public boolean doLogoutget(){
        return memberService.doLogoutget();
    }
    @GetMapping("/login/info/get.do")
    public MemberDto doLoginInfo(){
        return memberService.doLoginInfo();
    }
}
