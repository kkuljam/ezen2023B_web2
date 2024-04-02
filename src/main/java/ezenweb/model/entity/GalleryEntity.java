package ezenweb.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "gallery")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class GalleryEntity extends BaseTime{
    @Id // PK
    private String bimg;

    @JoinColumn(name = "bno_fk") // fk 필드명
    @ManyToOne
    private BoardEntity boardEntity;
}


