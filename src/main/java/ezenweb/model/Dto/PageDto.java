package ezenweb.model.Dto;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;
@SuperBuilder
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PageDto {
    int page;           // 현재 페이지
    int count;          // 총 페이지수
    List<Object>data; // 본문 내용들

}
