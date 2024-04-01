package ezenweb.model.Dto;

import ezenweb.model.entity.BoardEntity;
import lombok.*;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter @SuperBuilder @ToString
public class BoardDto extends BaseTimeDto {
    private int bno;
    private String bcontent;
    private int bview;
    private int mno_fk;     // (memberEntity) 회원 번호
    private String memail; //  (memberEntity) 회원 이메일
    // - 글쓰기
    public BoardEntity toEntity(){
        return BoardEntity.builder()
                .bcontent( this.bcontent )
                .build();
    }
}