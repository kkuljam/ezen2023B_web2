package ezenweb.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="member")
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder@ToString
public class MemberEntity {
    @Id //pk
    @GeneratedValue(strategy = GenerationType.IDENTITY) //auto_increment
    private int mno; // 회원 번호 pk
    private String mid;
    @ToString.Exclude //객체 호출시 해당 필드 제외
    @OneToMany(mappedBy = "memberEntity") // 하나가 다수에게 1:M
    private List<BoardEntity> boardEntitieList=new ArrayList<>();



    //@Column(name="title", length = 10,nullable = false,unique = true,columnDefinition = "longtext")
    //@Column(columnDefinition = "longtext")



}
