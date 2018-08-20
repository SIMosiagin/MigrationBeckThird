package uplaodEmployee.entity;


import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "map_excel_skill")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MappingWithExcel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "excel_group")
    private String excelGroup;

    @Column(name = "excel_field")
    private String excelField;

    @Column(name ="transit_field")
    private String transitField;

    @OneToOne
    @JoinColumn(name = "skill", referencedColumnName = "id")
    private SkillDescription skillDescription;
}
