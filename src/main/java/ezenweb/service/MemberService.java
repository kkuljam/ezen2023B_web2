package ezenweb.service;

import ezenweb.model.Dto.MemberDto;
import ezenweb.model.repository.MemberEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
    @Autowired
    MemberEntityRepository memberEntityRepository;
    public boolean doSignupPost( MemberDto memberDto){
        System.out.println("memberDto = " + memberDto);

        //-- Dao 안니 엔티티 이용한 레코드 저장하는 방법
        //1. 엔티티를 만든다
        //2. 리포지토리 통한 엔티티를 저장한다
        memberEntityRepository.save(memberDto.toEntity());
        return false;
    }
}
