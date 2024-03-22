package ezenweb.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity //해당 클래스와 연동DB내 테이블과 매핑/연결(ORM)
@Table(name="board")
@Builder @Setter
@NoArgsConstructor @AllArgsConstructor
public class BoardEntity {
    @Id //pk
    @GeneratedValue(strategy = GenerationType.IDENTITY) //auto_increment
    private int bno; // 게시물 번호
    //@Column(name="title", length = 10,nullable = false,unique = true,columnDefinition = "longtext")
    @Column(columnDefinition = "longtext")
    private String btitle;  // 게시물제목


}
/*

*/
