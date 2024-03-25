package ezenweb.service;

import ezenweb.model.entity.BoardEntity;
import ezenweb.model.entity.MemberEntity;
import ezenweb.model.entity.ReplyEntity;
import ezenweb.model.repository.BoardEntityRepository;
import ezenweb.model.repository.MemberEntityRepository;
import ezenweb.model.repository.ReplyEntityRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Member;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class BoardService {
    @Autowired
    private BoardEntityRepository boardEntityRepository;
    @Autowired
    private MemberEntityRepository memberEntityRepository;
    @Autowired
    private ReplyEntityRepository replyEntityRepository;
    //1.c
    @Transactional
    public boolean postBoard(){
      //======= 테스트====
        //1. 회원가입
            //1. 엔티티 객체 작성
        MemberEntity memberEntity = MemberEntity.builder()
                .memail("qwe@qwe.com")
                .mpassword("1234")
                .mname("유재석")
                .build();
            //2. 해당 엔티티를 db 에 저장할 수 있도록 조작
        MemberEntity saveMemberEntity=memberEntityRepository.save(memberEntity);

        //2. 회원가입된 회원으로 글스기
            //1. 엔티티 객체 생성
        BoardEntity boardEntity=BoardEntity.builder()
                .bcontent("게시물글입니다")
                .build();
            //2.**** 글쓴이[fk 대입]
        boardEntity.setMemberEntity(saveMemberEntity); // 회원 엔티티 대입
            //3. 조작
        BoardEntity saveBoardEntity=boardEntityRepository.save(boardEntity);

        //3. 해당 글에 댓글 작성
            //1. 엔티티 객체 생성
        ReplyEntity replyEntity =ReplyEntity.builder()
                .rcontent("댓글입니다.")
                .build();
            //2.******** [fk대입1 작성자]
        replyEntity.setMemberEntity(saveMemberEntity);
            //2.******** [fk대입2 게시물번호]
        replyEntity.setBoardEntity(saveBoardEntity);
            //2.********
        replyEntityRepository.save(replyEntity);


        return false;
    }
    //2.R
    @Transactional
    public List<Objects> getBoard(){
        List<BoardEntity>result=boardEntityRepository.findAll();
        System.out.println("result"+result);
        return null;
    }
    //3.U
    @Transactional
    public boolean putBoard(){
        BoardEntity boardEntity= boardEntityRepository.findById(1).get();
        boardEntity.setBcontent("JPA 수정테스트중");
        return false;
    }
    //4.D
    @Transactional
    public boolean deleteBoard(){
        boardEntityRepository.deleteById(1);
        return false;
    }
}
 /* //1.엔티티 객체 생성
        BoardEntity boardEntity=BoardEntity.builder()
                .bno(1)
                .bcontent("JPA테스트중")
                .build();
        //2. 리포지토리를 이용한 엔티티를 테이블에 대입
        boardEntityRepository.save(boardEntity); //insert*/