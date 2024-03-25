package ezenweb.model.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="tmember")
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder@ToString
public class TMemberEntity {
    /*
    mno // 회원번호
	mname // 이름
	mid	//아이디
	mpw	// 비밀번호
	mphone	//전화번호
	memail	//이메일
	mbirth	//생년월일
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int mno; // 회원번호

    @Column(length = 30,unique = true)
    private String mid; // 아이디

    @Column(length = 30)
    private String mpw; //비밀번호

    @Column(length = 20)
    private String mname; // 이름

    @Column(length = 30)
    private String Nikname; // 닉네임

    @Column(length = 50 , unique = true)
    private String memail; //이메일

    @Column(length = 20 , unique = true)
    private String mphone;// 전화번호

    @Column(columnDefinition = "date")
    private String mbirth;	//생년월일

    //양방향 :게시물fk @OneToMany(mappedBy = "해당테이블 fk필드명")
    @OneToMany(mappedBy = "memberEntity")
    @ToString.Exclude // 해당 객체 호출시 해당 필드는 호출하지 않는다.
    @Builder.Default // 빌더패턴 사용해서 객체생성시 해당 필드의 초기값을 빌더 초기값으로 사용
    private List<BoardEntity>boardEntityList=new ArrayList<>();



}
