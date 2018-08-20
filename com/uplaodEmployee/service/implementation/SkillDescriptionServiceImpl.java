package uplaodEmployee.service.implementation;


import uplaodEmployee.dao.intefaces.EmployeeSkillsIntDao;
import uplaodEmployee.dao.intefaces.SkillDescriptionIntDao;
import uplaodEmployee.entity.EmployeeSkills;
import uplaodEmployee.entity.SkillDescription;
import uplaodEmployee.service.intefaces.EmployeeSkillsService;
import uplaodEmployee.service.intefaces.SkillDescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service("SkillDescriptionService")
@Transactional
public class SkillDescriptionServiceImpl implements SkillDescriptionService {

    @Autowired
    private SkillDescriptionIntDao skillDescriptionIntDao;

    public void save(SkillDescription obj) {
        skillDescriptionIntDao.save(obj);
    }

    public List<SkillDescription> findAll() {
        return skillDescriptionIntDao.findAll();
    }

//    public ArrayList<Employee> findByFieldString(String field, String value) {
//        return employeeDao.;
//    }

    public SkillDescription findById(int id) {
        return skillDescriptionIntDao.findById(id);
    }

    public void deleteById(int id) {
        skillDescriptionIntDao.deleteById(id);
    }

    public void update(SkillDescription newObj, SkillDescription oldObj) {
        skillDescriptionIntDao.update(newObj, oldObj);
    }

    public void saveOrUpdate (SkillDescription object){
        skillDescriptionIntDao.saveOrUpdate(object);
    }

    public SkillDescription findByName(String name){
        return skillDescriptionIntDao.findByName(name);
    }
}
