package ezenweb.model.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity //해당 클래스와 연동DB내 테이블과 매핑/연결(ORM)
@Table(name="tboard")
@Builder @Setter
@NoArgsConstructor @AllArgsConstructor
public class TBoardEntity {
    /*
    bno	// 게시물 번호
	bcontent	// 내용
	mno	// 작성자
	bdate	// 작성일
	bview	// 조회수
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bno; // 게시물 번호

    @Column(columnDefinition = "longtext" )
    private String bcontent; //내용

    @ColumnDefault("0")
    private  int bview; //조회수

    @Temporal(TemporalType.TIMESTAMP)
    @ColumnDefault("CURRENT_TIMESTAMP")
    private Date bdate; // 작성일

    //================ FK 필드
    @JoinColumn(name="mno_fk") //fk 필드명
    @ManyToOne // 해당 필드 참조
    private MemberEntity memberEntity;



}

