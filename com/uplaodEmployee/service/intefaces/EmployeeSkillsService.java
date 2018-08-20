package uplaodEmployee.service.intefaces;

import uplaodEmployee.entity.Employee;
import uplaodEmployee.entity.EmployeeSkills;
import uplaodEmployee.entity.SkillDescription;

import java.util.List;

public interface EmployeeSkillsService {

    public void save(EmployeeSkills obj);

    public List<EmployeeSkills> findAll();

    //    public ArrayList<Employee> findByFieldString(String field, String value);
//
    public EmployeeSkills findById(int id);

    public void deleteById(int id);

    public void update(EmployeeSkills newObj,EmployeeSkills oldObj);

    public void saveOrUpdate(EmployeeSkills obj);

    public EmployeeSkills findByEmployeeAndSkill(Employee employee, SkillDescription skillDescription);
}
