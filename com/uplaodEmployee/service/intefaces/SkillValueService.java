package uplaodEmployee.service.intefaces;

import uplaodEmployee.entity.SkillValue;

import java.util.List;

public interface SkillValueService {
    public void save(SkillValue obj);

    public List<SkillValue> findAll();

    //    public ArrayList<Employee> findByFieldString(String field, String value);
//
    public SkillValue findById(int id);

    public void deleteById(int id);

//    public void update(SkillValue newObj,SkillValue oldObj);

    public void saveOrUpdate(SkillValue obj);
}
