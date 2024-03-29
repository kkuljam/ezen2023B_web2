package ezenweb.model.Dto;

import lombok.*;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder @ToString@Getter@Setter
public class BoardDto extends BaseTimeDto{
    private  int bno;
    private String  bcontent;
    private  int bview;
    private int mno_fk;
    private String memail; // 작성자
}
