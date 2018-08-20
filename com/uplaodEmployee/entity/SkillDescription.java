package uplaodEmployee.entity;


import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "skill_description")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SkillDescription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Integer id;

    @Column(name = "name")
    String name;

    @Column(name = "description")
    String description;

    @ManyToOne
    @JoinColumn(name = "group_id", referencedColumnName = "id")
    SkillGroup skillGroup;

    public SkillDescription (SkillGroup skillGroup){
        id = null;
        name = null;
        this.skillGroup = skillGroup;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        SkillDescription that = (SkillDescription) object;
        return Objects.equals(name, that.name) &&
                Objects.equals(description, that.description) &&
                Objects.equals(skillGroup, that.skillGroup);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, description, skillGroup);
    }

    public boolean equalsByName(String name){
        if (name.isEmpty()) return false;
        return this.name.toLowerCase().equals(name.toLowerCase());

    }
}
