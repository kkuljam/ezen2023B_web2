package ezenweb.model.entity;

import ezenweb.model.Dto.BoardDto;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "board")
@AllArgsConstructor@NoArgsConstructor
@Getter @Setter@Builder@ToString
public class BoardEntity extends BaseTime {
    @Id // PK
    @GeneratedValue( strategy = GenerationType.IDENTITY) // AUTO_INCREMENT
    private int bno;

    @Column( columnDefinition = "longtext") // longtext
    private String bcontent;

    @Column
    @ColumnDefault("0")     // int , default 0
    private int bview;

    // 단방향 : FK 필드
    @JoinColumn( name="mno_fk")// fk필드명
    @ManyToOne // 해당 필드 참조
    //단방향은 둘중 한곳에 넣어도 됨
    private MemberEntity memberEntity;

    // 양방향 : 게시물fk
    @OneToMany( mappedBy = "boardEntity", cascade = CascadeType.ALL)
    @ToString.Exclude
    @Builder.Default
    private List<ReplyEntity> replyEntityList = new ArrayList<>();

    // 양방향 : 게시물fk
    @OneToMany( mappedBy = "boardEntity",cascade = CascadeType.ALL)
    @ToString.Exclude
    @Builder.Default
    private List<GalleryEntity> GalleryEntityList = new ArrayList<>();

    // - 게시물 출력
    public BoardDto toDto(){
        return  BoardDto.builder()
                .bno( this.bno )
                .bcontent( this.bcontent )
                .bview( this.bview )
                .mno_fk( memberEntity.getMno() )
                .memail( memberEntity.getMemail() )
                .cdate( this.getCdate() )
                .udate( this.getUdate() )
                .bimgList(
                        this.GalleryEntityList.stream().map(
                                (img)->{return img.getBimg();}
                        ).collect(Collectors.toList())
                )
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
