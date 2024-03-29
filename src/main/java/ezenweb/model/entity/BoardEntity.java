package ezenweb.model.entity;

import ezenweb.model.Dto.BoardDto;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.util.ArrayList;
import java.util.List;

@Entity //해당 클래스와 연동DB내 테이블과 매핑/연결(ORM)
@Table(name="board")
@Builder @Setter
@NoArgsConstructor @AllArgsConstructor
public class BoardEntity extends BaseTime{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bno;

    @Column(columnDefinition = "longtext" )
    private String bcontent;

    @ColumnDefault("0")
    private  int bview;

    //================ FK 필드
    @JoinColumn(name="mno_fk") //fk 필드명
    @ManyToOne // 해당 필드 참조
    private MemberEntity memberEntity;

    //양방향 : 댓글 fk
    @OneToMany(mappedBy = "boardEntity")
    @ToString.Exclude // 해당 객체 호출시 해당 필드는 호출하지 않는다.
    private List<ReplyEntity> replyEntityList=new ArrayList<>();

    //- 게시물 출력
    // -
    public BoardDto toDto(){
        return  BoardDto.builder()
                .bno( this.bno )
                .bcontent( this.bcontent )
                .bview( this.bview )
                .mno_fk( memberEntity.getMno() )
                .memail( memberEntity.getMemail() )
                .cdate( this.getCdate() )
                .udate( this.getUdate() )
                .build();
    }

}
/*
@Id //pk
    @GeneratedValue(strategy = GenerationType.IDENTITY) //auto_increment
    private int bno; // 게시물 번호pk
 //@Column(name="title", length = 10,nullable = false,unique = true,columnDefinition = "longtext")
    //@Column(columnDefinition = "longtext")
//    private String btitle;  // 게시물제목
//    @JoinColumn // fk
//    @ManyToOne // 다수가 하나에게 M:1
//    private MemberEntity memberEntity; // 단방향

*/
