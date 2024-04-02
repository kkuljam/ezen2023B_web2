package ezenweb.model.Dto;

import ezenweb.model.entity.BoardEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter @SuperBuilder @ToString
public class BoardDto extends BaseTimeDto {
    private int bno;
    private String bcontent;
    private int bview;
    private int mno_fk;     // (memberEntity) 회원 번호
    private String memail; //  (memberEntity) 회원 이메일

    //1. 출력용 게시물 이미지 필드 (파일 이름만 여러개 출력할거라서 )
    private List<String>bimgList= new ArrayList<>();

    //2. 등록용 게시물 이미지 필드
    private List<MultipartFile> uploadList=new ArrayList<>();

    // - 글쓰기
    public BoardEntity toEntity(){
        return BoardEntity.builder()
                .bcontent( this.bcontent )
                .build();
    }
}