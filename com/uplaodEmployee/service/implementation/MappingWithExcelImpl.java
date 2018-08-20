package uplaodEmployee.service.implementation;

import uplaodEmployee.dao.implementation.MappingWithExcelDao;
import uplaodEmployee.entity.MappingWithExcel;
import uplaodEmployee.entity.SkillDescription;
import uplaodEmployee.entity.SkillGroup;
import uplaodEmployee.service.intefaces.MappingWithExcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;


@Service("MappingWithExcelService")
@Transactional
public class MappingWithExcelImpl implements MappingWithExcelService {

    @Autowired
    MappingWithExcelDao mappingWithExcelDao;

    public void save(MappingWithExcel object) {
        mappingWithExcelDao.save(object);
    }

    public ArrayList<MappingWithExcel> findAll() {
        return mappingWithExcelDao.findAll();
    }

    public MappingWithExcel findById(int id) {
        return mappingWithExcelDao.findById(id);
    }

    public void deleteById(int id) {
        mappingWithExcelDao.deleteById(id);
    }

    public void saveOrUpdate(MappingWithExcel object) {
        mappingWithExcelDao.saveOrUpdate(object);
    }

    public void deleteAll(){
        mappingWithExcelDao.deleteAll();
    }


    public MappingWithExcel getMappingWithExcel(ArrayList<Object> arrayList) {



        SkillGroup skillGroup = new SkillGroup();
        skillGroup.setName(checkNPEForString(((LinkedHashMap)((LinkedHashMap) arrayList.get(3)).get("skillGroup")).get("name")));
        skillGroup.setDescription(checkNPEForString(((LinkedHashMap)((LinkedHashMap) arrayList.get(3)).get("skillGroup")).get("description")));
        skillGroup.setId(Integer.parseInt(checkNPEForInteger(((LinkedHashMap)((LinkedHashMap) arrayList.get(3)).get("skillGroup")).get("id"))));
        if (skillGroup.getId() == 0) skillGroup.setId(null);

        SkillDescription skillDescription = new SkillDescription();
        skillDescription.setName(checkNPEForString(((LinkedHashMap) arrayList.get(3)).get("name")));
        skillDescription.setDescription(checkNPEForString(((LinkedHashMap) arrayList.get(3)).get("description")));
        skillDescription.setId(Integer.parseInt(checkNPEForInteger(((LinkedHashMap) arrayList.get(3)).get("id"))));
        skillDescription.setSkillGroup(skillGroup);
        if (skillDescription.getId() == 0) skillDescription.setId(null);

        MappingWithExcel mappingWithExcel = new MappingWithExcel();
        mappingWithExcel.setExcelField(checkNPEForString(arrayList.get(1)));
        mappingWithExcel.setExcelGroup(checkNPEForString(arrayList.get(2)));
        mappingWithExcel.setSkillDescription(skillDescription);
        if (arrayList.size() == 5){
            mappingWithExcel.setId(Integer.parseInt(arrayList.get(4).toString()));
        }

        return mappingWithExcel;
    }

    public String checkNPEForString(Object obj){
        if (obj == null) return "";
        else return obj.toString();
    }

    public String checkNPEForInteger(Object obj){
        if (obj == null) return "0";
        else return obj.toString();
    }

    public void parseMappingTransit( String arrayMap[][]){
        ArrayList<MappingWithExcel> mappingWithExcels = mappingWithExcelDao.findAll();

        for (String[] mapSkill: arrayMap){
            MappingWithExcel mappingWithExcel = this.getByNameAndDisc(mappingWithExcels, mapSkill[1], mapSkill[4]);
            if (mappingWithExcel == null){
                System.out.println("Warning didt find mapping with skills");
                System.out.println(mapSkill[1] + ", " + mapSkill[4]);
                continue;
            }
            mappingWithExcel.setTransitField(mapSkill[2]);
            mappingWithExcelDao.saveOrUpdate(mappingWithExcel);
        }
    }

    public MappingWithExcel getByNameAndDisc(ArrayList<MappingWithExcel> mappingWithExcels, String name, String disc){
        for (MappingWithExcel mWE: mappingWithExcels){
            if (mWE.getExcelGroup().equalsIgnoreCase(disc) && mWE.getExcelField().equalsIgnoreCase(name)){
                return mWE;
            }
        }
        return null;
    }
}
