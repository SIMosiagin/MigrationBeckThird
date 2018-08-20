package uplaodEmployee.service.implementation;


import uplaodEmployee.dao.intefaces.EmployeeIntDao;
import uplaodEmployee.dao.intefaces.EmployeeSkillsIntDao;
import uplaodEmployee.entity.Employee;
import uplaodEmployee.entity.EmployeeSkills;
import uplaodEmployee.entity.SkillDescription;
import uplaodEmployee.service.intefaces.EmployeeServise;
import uplaodEmployee.service.intefaces.EmployeeSkillsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service("EmployeeSkillsService")
@Transactional
public class EmployeeSkillsServiceImpl implements EmployeeSkillsService {

    @Autowired
    private EmployeeSkillsIntDao employeeSkillsIntDao;

    public void save(EmployeeSkills obj) {
        employeeSkillsIntDao.save(obj);
    }

    public List<EmployeeSkills> findAll() {
        return employeeSkillsIntDao.findAll();
    }

//    public ArrayList<Employee> findByFieldString(String field, String value) {
//        return employeeDao.;
//    }

    public EmployeeSkills findById(int id) {
        return employeeSkillsIntDao.findById(id);
    }

    public void deleteById(int id) {
        employeeSkillsIntDao.deleteById(id);
    }

    public void update(EmployeeSkills newObj, EmployeeSkills oldObj) {
        employeeSkillsIntDao.update(newObj, oldObj);
    }

    public void saveOrUpdate (EmployeeSkills object){
        employeeSkillsIntDao.saveOrUpdate(object);
    }

    public EmployeeSkills findByEmployeeAndSkill(Employee employee, SkillDescription skillDescription){
        return employeeSkillsIntDao.findByEmployeeAndSkill(employee, skillDescription);
    }
}
