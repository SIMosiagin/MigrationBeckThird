package uplaodEmployee.service.implementation;


import uplaodEmployee.dao.intefaces.SkillGroupIntDao;
import uplaodEmployee.dao.intefaces.SkillValueIntDao;
import uplaodEmployee.entity.SkillGroup;
import uplaodEmployee.entity.SkillValue;
import uplaodEmployee.service.intefaces.SkillGroupService;
import uplaodEmployee.service.intefaces.SkillValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service("SkillValueService")
@Transactional
public class SkillValueServiceImpl implements SkillValueService {

    @Autowired
    private SkillValueIntDao skillValueIntDao;

    public void save(SkillValue obj) {
        skillValueIntDao.save(obj);
    }

    public List<SkillValue> findAll() {
        return skillValueIntDao.findAll();
    }

//    public ArrayList<Employee> findByFieldString(String field, String value) {
//        return employeeDao.;
//    }

    public SkillValue findById(int id) {
        return skillValueIntDao.findById(id);
    }

    public void deleteById(int id) {
        skillValueIntDao.deleteById(id);
    }

//    public void update(SkillValue newObj, SkillValue oldObj) {
//        skillValueIntDao.update(newObj, oldObj);
//    }

    public void saveOrUpdate (SkillValue object){
        skillValueIntDao.saveOrUpdate(object);
    }
}
