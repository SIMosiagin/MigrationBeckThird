package uplaodEmployee.service.intefaces;

import uplaodEmployee.entity.SkillDescription;

import java.util.List;

public interface SkillDescriptionService {
    public void save(SkillDescription obj);

    public List<SkillDescription> findAll();

    //    public ArrayList<Employee> findByFieldString(String field, String value);
//
    public SkillDescription findById(int id);

    public void deleteById(int id);

    public void update(SkillDescription newObj,SkillDescription oldObj);

    public void saveOrUpdate(SkillDescription obj);

    public SkillDescription findByName(String name);
}
