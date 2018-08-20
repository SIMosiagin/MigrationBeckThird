package uplaodEmployee.dao.intefaces;

import uplaodEmployee.entity.Employee;
import uplaodEmployee.entity.EmployeeSkills;
import uplaodEmployee.entity.SkillDescription;

import java.util.List;

public interface EmployeeSkillsIntDao {

    public void save(EmployeeSkills object);

    public List<EmployeeSkills> findAll();

    public EmployeeSkills findById(int id);

    public void deleteById(int id);

    public void update(EmployeeSkills oldObj, EmployeeSkills newObj);

    public void saveOrUpdate(EmployeeSkills object);

    public EmployeeSkills findByEmployeeAndSkill(Employee employee, SkillDescription skillDescription);
}
