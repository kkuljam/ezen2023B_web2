package ezenweb.service;

import ezenweb.model.Dto.MemberDto;
import ezenweb.model.entity.MemberEntity;
import ezenweb.model.repository.MemberEntityRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {
    @Autowired
    MemberEntityRepository memberEntityRepository;

    //1. 회원가입
    public int doSignupPost( MemberDto memberDto){
        System.out.println("memberDto = " + memberDto);
        // 중복검사
        List<MemberEntity> memberEntityList= memberEntityRepository.findAll();
        for(int i=0; i<memberEntityList.size(); i++){
            MemberEntity m=memberEntityList.get(i);
            //3. 만약에 아이디가 동일하면(엔티티와 dto)
            if(m.getMemail().equals(memberDto.getMemail())){
                return 0;
            }
        }
        //-- Dao 안니 엔티티 이용한 레코드 저장하는 방법
        //1. 엔티티를 만든다
        //2. 리포지토리 통한 엔티티를 저장한다
        MemberEntity savedEntity =memberEntityRepository.save(memberDto.toEntity());
        if(savedEntity.getMno() > 0) return 1;
        return 2;
    }
    //* 로그인 했다는 증거/기록
    @Autowired private HttpServletRequest request;

    //2. 로그인 (세션 저장)
    public boolean doLoginPost(MemberDto memberDto){
        //1. 리포지토리를 통한 모든 회원엔티티 호출
        List<MemberEntity> memberEntityList= memberEntityRepository.findAll();

        //2. dto와 동일한 아이디/패스워드 찾는다
        for(int i=0; i<memberEntityList.size(); i++){
            MemberEntity m=memberEntityList.get(i);
            //3. 만약에 아이디가 동일하면(엔티티와 dto)
            if(m.getMemail().equals(memberDto.getMemail())){
                //4. 만약에 비밀번호가 동일하면
                if(m.getMpassword().equals((memberDto.getMpassword()))){
                    //5. 세션 저장
                    request.getSession().setAttribute("loginInfo",memberDto);
                    return true;
                }
            }
        }
        return false;
    }

    //3. 로그아웃 (세션 삭제)
    public boolean doLogoutget(){
        request.getSession().setAttribute("loginInfo",null);
        //request.getSession().invalidate();
        return true;
    }

    //4. 현재 로그인 회원정보 호출 (세션 호출)
    public MemberDto doLoginInfo(){
        System.out.println("doLoginInfo() " );
        Object object=request.getSession().getAttribute("loginInfo");
        if(object !=null){
            return (MemberDto)object;// 강제형변환
        }
        return null;
    }
}















