package uplaodEmployee.entity;


import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "skill_value")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SkillValue {

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Integer id;

    @Column(name = "name")
    String name;

    @Column(name = "description")
    String description;

}
