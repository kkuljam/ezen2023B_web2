package ezenweb.model.Dto;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder @ToString
@Getter
@Setter
public class BaseTimeDto {
    public LocalDateTime cdate;
    public LocalDateTime udate;
}