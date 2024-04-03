package ezenweb.service;

import ezenweb.model.Dto.BoardDto;
import ezenweb.model.Dto.MemberDto;
import ezenweb.model.Dto.PageDto;
import ezenweb.model.entity.BoardEntity;
import ezenweb.model.entity.GalleryEntity;
import ezenweb.model.entity.MemberEntity;
import ezenweb.model.entity.ReplyEntity;
import ezenweb.model.repository.BoardEntityRepository;
import ezenweb.model.repository.GalleryEntityRepository;
import ezenweb.model.repository.MemberEntityRepository;
import ezenweb.model.repository.ReplyEntityRepository;
import jakarta.transaction.Transactional;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.lang.reflect.Member;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class BoardService {
    @Autowired
    private BoardEntityRepository boardEntityRepository;
    @Autowired
    private MemberEntityRepository memberEntityRepository;
    @Autowired
    private ReplyEntityRepository replyEntityRepository;
    @Autowired
    private GalleryEntityRepository galleryEntityRepository;
    @Autowired
    private MemberService memberService;
    @Autowired
    private FileService fileService;

    // 1. C
    @Transactional
    public boolean postBoard(BoardDto boardDto) { //  ======= 테스트 ==========
        System.out.println(" service boardDto = " + boardDto);
        MemberDto loginDto = memberService.doLoginInfo();
        if (loginDto == null) return false;
        // 1. 로그인된 회원 엔티티 찾기
        Optional<MemberEntity> optionalMemberEntity = memberEntityRepository.findById(loginDto.getMno());
        System.out.println("memberEntityRepository.findById(loginDto.getMno()) = " + memberEntityRepository.findById(loginDto.getMno()));
        // 2. 찾은 엔티티가 존재하지 않으면 실패;
        if (!optionalMemberEntity.isPresent()) return false;
        System.out.println("true");
        // 3. 엔티티 꺼내기
        MemberEntity memberEntity = optionalMemberEntity.get();
        // - 글쓰기

        BoardEntity saveBoard = boardEntityRepository.save(boardDto.toEntity());
        // - FK 대입
        if (saveBoard.getBno() >= 1) { // 글쓰기를 성공했으면
            saveBoard.setMemberEntity(memberEntity);

            boardDto.getUploadList().forEach((uploadfile)->{
                GalleryEntity saveGallery= GalleryEntity.builder()
                        .bimg(fileService.FileUpload(uploadfile))
                        .boardEntity(saveBoard)
                        .build();
                galleryEntityRepository.save(saveGallery);
            });
            return true;
        }
        return false;
    }

    // 2. R
    @Transactional
    public PageDto getBoard(int page, int view) {


        //1  Pageable 인터페이스 이용한 페이징처리
            //Pageabled은 page 순서 0부터 시작하기 때문에  page가 1페이지일때 0페이지로 변환하기 위해 -1 처리
        Pageable pageable= PageRequest.of(page-1,view);

            //1. 페이징처리된 엔티티 호출
        Page<BoardEntity> boardEntityPage =boardEntityRepository.findAll(pageable);
            //-- List 아닌 page 타입일 때 list 동일한 메소드 사용하고 추가 기능
                //1. 전체 페이지수
        System.out.println("boardEntityPage.getTotalPages() = " + boardEntityPage.getTotalPages());
        int count=boardEntityPage.getTotalPages();
                //2. 전체 게시물수
        System.out.println("boardEntityPage.getTotalElements() = " + boardEntityPage.getTotalElements());
            //2. 엔티티를 Dto 변환
        List<Object> data=boardEntityPage.stream().map(((boardEntity)->{
            return boardEntity.toDto();
        })).collect(Collectors.toList());
        //2. 페이지 Dto 반환 값 구성
            //1.
        PageDto pageDto = PageDto.builder()
                .data(data)
                .page(page)
                .count(count)
                .build();
        return pageDto;
        //==========================1=========================
       /* // 1. 리포지토리를 이용한 모든 엔티티( 테이블에 매핑 하기전 엔티티 )를 호출
        List<BoardEntity> result = boardEntityRepository.findAll();
        // 2. Entity ---> Dto 변환한다
        List<BoardDto> boardDtoList = new ArrayList<>();
        // 1. 꺼내온 entity 을 순회한다
        for (int i = 0; i < result.size(); i++) {
            // 2. 하나씩 enriry 꺼낸다
            BoardEntity boardEntity = result.get(i);
            // 3. 해당 엔티티를 dto로 변환한다.
            BoardDto boardDto = boardEntity.toDto();
                //------ 게시물 안에 게시물 사진
                List<String > bimgList=new ArrayList<>();
                for(int j=0; j<boardEntity.getGalleryEntityList().size(); j++){
                    GalleryEntity galleryEntity=boardEntity.getGalleryEntityList().get(j);
                    String bimg=galleryEntity.getBimg();
                    bimgList.add(bimg);
                }
                boardDto.setBimgList(bimgList);
            // 4. 변환된 dto를 리스트에 담는다.
            boardDtoList.add(boardDto);
        }
        return boardDtoList;*/
        //==========================2=========================
    }

    // 3. U
    @Transactional
    public boolean putBoard() {
        BoardEntity boardEntity = boardEntityRepository.findById(1).get();
        boardEntity.setBcontent("JPA수정테스트중");
        return false;
    }

    // 4. D
    @Transactional
    public boolean deleteBoard(int bno) {
        //Cannot delete or update a parent row: a foreign key constraint fails : 참조키 오류
        MemberDto loginDto= memberService.doLoginInfo();
        if(loginDto==null){
            return false;
        }
        System.out.println(loginDto.getMno());
        List<Object>reslut=boardEntityRepository.findByBnoAndMno(bno,loginDto.getMno() );
        System.out.println("List 반환"+reslut);
        if( ! reslut.isEmpty()){
            boardEntityRepository.deleteById(bno);
            return true;
        }
        return false;
    }
     /* MemberEntity memberEntity= loginDto.toEntity();
        BoardEntity boardEntity=BoardEntity.builder()
                .bno(bno)
                .build();*/
       /* BoardDto boardDto=new BoardDto();
        boardDto.setBno(bno);
        boardDto.toEntity();*/
}
//}
//    //1.c
//    @Transactional
//    public boolean postBoard(BoardDto boardDto){
//      //======= 테스트====
//        //1. 회원가입
//            //1. 엔티티 객체 작성
//        MemberEntity memberEntity = MemberEntity.builder()
//                .memail("qwe@qwe.com")
//                .mpassword("1234")
//                .mname("유재석")
//                .build();
//            //2. 해당 엔티티를 db 에 저장할 수 있도록 조작
//        MemberEntity saveMemberEntity=memberEntityRepository.save(memberEntity);
//
//        //2. 회원가입된 회원으로 글스기
//            //1. 엔티티 객체 생성
//        BoardEntity boardEntity=BoardEntity.builder()
//                .bcontent("게시물글입니다")
//                .build();
//            //2.**** 글쓴이[fk 대입]
//        boardEntity.setMemberEntity(saveMemberEntity); // 회원 엔티티 대입
//            //3. 조작
//        BoardEntity saveBoardEntity=boardEntityRepository.save(boardEntity);
//
//        //3. 해당 글에 댓글 작성
//            //1. 엔티티 객체 생성
//        ReplyEntity replyEntity =ReplyEntity.builder()
//                .rcontent("댓글입니다.")
//                .build();
//            //2.******** [fk대입1 작성자]
//        replyEntity.setMemberEntity(saveMemberEntity);
//            //2.******** [fk대입2 게시물번호]
//        replyEntity.setBoardEntity(saveBoardEntity);
//            //2.********
//        replyEntityRepository.save(replyEntity);
//
//
//        return false;
//    }
//    //2.R
//    @Transactional
//    public List<BoardDto> getBoard(){
//        List<BoardEntity>result=boardEntityRepository.findAll();
//        System.out.println("result"+result);
//        return null;
//    }
//    //3.U
//    @Transactional
//    public boolean putBoard(){
//        BoardEntity boardEntity= boardEntityRepository.findById(1).get();
//        boardEntity.setBcontent("JPA 수정테스트중");
//        return false;
//    }
//    //4.D
//    @Transactional
//    public boolean deleteBoard(){
//        boardEntityRepository.deleteById(1);
//        return false;
//    }
//}
 /* //1.엔티티 객체 생성
        BoardEntity boardEntity=BoardEntity.builder()
                .bno(1)
                .bcontent("JPA테스트중")
                .build();
        //2. 리포지토리를 이용한 엔티티를 테이블에 대입
        boardEntityRepository.save(boardEntity); //insert*/