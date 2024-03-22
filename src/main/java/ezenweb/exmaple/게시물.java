package ezenweb.exmaple;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class 게시물 {
    private int 번호;
    private String 제목;
    private String 내용;
    // 작성자 회원객체
    private 회원 작성자;
}
