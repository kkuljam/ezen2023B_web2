package ezenweb.model.entity;

import ezenweb.model.Dto.MemberDto;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="member")
@Setter@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder@ToString
public class MemberEntity extends BaseTime{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int mno;
    @Column(length = 50 , unique = true)
    private String memail;
    private String mpassword;
    @Column(length = 20)
    private String mname;
    @Column(name = "mrol" )
    @ColumnDefault("'user'") //문자 '' , 숫자
    private String mrol;

    //양방향 :게시물fk @OneToMany(mappedBy = "해당테이블 fk필드명")
    @OneToMany(mappedBy = "memberEntity")
    @ToString.Exclude // 해당 객체 호출시 해당 필드는 호출하지 않는다.
    @Builder.Default // 빌더패턴 사용해서 객체생성시 해당 필드의 초기값을 빌더 초기값으로 사용
    private List<BoardEntity>boardEntityList=new ArrayList<>();

    //양방향 : 댓글 fk
    @OneToMany(mappedBy = "memberEntity")
    @ToString.Exclude // 해당 객체 호출시 해당 필드는 호출하지 않는다.
    private List<ReplyEntity> replyEntityList=new ArrayList<>();

    //- 엔티티를 dto로 변환하는 메소드
    public MemberDto toDto(){
        return MemberDto.builder()
                .mno(this.mno)
                .mname(this.mname)
                .memail(this.memail)
                .mrol(this.mrol)
                .mpassword(this.mpassword)
                .build();
    }


}
/* @Id //pk
    @GeneratedValue(strategy = GenerationType.IDENTITY) //auto_increment
    private int mno; // 회원 번호 pk
    private String mid;
    @ToString.Exclude //객체 호출시 해당 필드 제외
    @OneToMany(mappedBy = "memberEntity") // 하나가 다수에게 1:M
    private List<BoardEntity> boardEntitieList=new ArrayList<>();*/



//@Column(name="title", length = 10,nullable = false,unique = true,columnDefinition = "longtext")
//@Column(columnDefinition = "longtext")