package ezenweb.model.Dto;


import ezenweb.model.entity.MemberEntity;
import ezenweb.service.MemberService;
import lombok.*;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Setter@Getter@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MemberDto  extends BaseTimeDto{

    private int mno;
    private String memail;
    private String mpassword;
    private String mname;
    private String mrol;

    // - dto 엔티티로 변환하는 메소드
    public MemberEntity toEntity(){
        return MemberEntity.builder()
                .mno(this.mno)
                .mname(this.mname)
                .memail(this.memail)
                .mrol(this.mrol)
                .mpassword(this.mpassword)
                .build();
        // this ?? : 해당 메소드를 호출한 인스턴스
    }
}
