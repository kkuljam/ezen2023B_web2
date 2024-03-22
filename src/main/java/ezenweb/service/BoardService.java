package ezenweb.service;

import ezenweb.model.entity.BoardEntity;
import ezenweb.model.repository.BoardEntityRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class BoardService {
    @Autowired
    private BoardEntityRepository boardEntityRepository;
    //1.c
    @Transactional
    public boolean postBoard(){
        //1.엔티티 객체 생성
        BoardEntity boardEntity=BoardEntity.builder()
                .bno(1)
                .btitle("JPA테스트중")
                .build();
        //2. 리포지토리를 이용한 엔티티를 테이블에 대입
        boardEntityRepository.save(boardEntity); //insert
        return false;
    }
    //2.R
    @Transactional
    public List<Objects> getBoard(){
        List<BoardEntity>result=boardEntityRepository.findAll();
        return null;
    }
    //3.U
    @Transactional
    public boolean putBoard(){
        BoardEntity boardEntity= boardEntityRepository.findById(1).get();
        boardEntity.setBtitle("JPA 수정테스트중");
        return false;
    }
    //4.D
    @Transactional
    public boolean deleteBoard(){
        boardEntityRepository.deleteById(1);
        return false;
    }
}
