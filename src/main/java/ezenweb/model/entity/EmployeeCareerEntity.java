package ezenweb.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class EmployeeCareerEntity {
    @Id
    @Column(unique = true )
    private String companyname; // varchar(255),
    private String note; // varchar(255), #무슨 업무를 맡았는지
    @Column(columnDefinition = "longtext")
    private String eimg; //longtext,   #경력 증명서
    @Column(columnDefinition = "date")
    private String start_date;// date,
    @Column(columnDefinition = "date")
    private String end_date;// date,
}
