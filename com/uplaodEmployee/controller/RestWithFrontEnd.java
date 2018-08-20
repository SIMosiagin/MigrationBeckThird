package uplaodEmployee.controller;


import uplaodEmployee.dao.implementation.SkillDescriptionDao;
import uplaodEmployee.entity.MappingWithExcel;
import uplaodEmployee.entity.SkillDescription;
import uplaodEmployee.entity.SkillGroup;
import uplaodEmployee.service.intefaces.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin("http://localhost:4200")

@RestController
public class RestWithFrontEnd {

    @Autowired
    EmployeeServise employeeDao;

    @Autowired
    EmployeeSkillsService employeeSkillsService;

    @Autowired
    SkillDescriptionService skillDescriptionService;

    @Autowired
    SkillGroupService skillGroupService;

    @Autowired
    SkillValueService skillValueService;

    @Autowired
    MappingWithExcelService mappingWithExcelService;



    @RequestMapping(value = "/chousedSheet/{id}",method = RequestMethod.GET)
    public ArrayList<ArrayList<Object>> setSheet(@PathVariable("id") Integer id){


        RestTemplate restTemplate = new RestTemplate();
        ArrayList<ArrayList<Object>> skillsExcel = restTemplate.getForObject("http://localhost:8080/chousedSheet/" + id, ArrayList.class);

        List<MappingWithExcel> dbSkills = mappingWithExcelService.findAll();
        List<SkillDescription> dbReadySkills = skillDescriptionService.findAll();

        for (ArrayList<Object> skExcel: skillsExcel) {
            for (MappingWithExcel skilldesc: dbSkills){
                if (skilldesc.getSkillDescription().getSkillGroup() != null){
                    if (skExcel.get(1).toString().equalsIgnoreCase(skilldesc.getExcelField()) &&
                            skExcel.get(2).toString().equalsIgnoreCase(skilldesc.getExcelGroup())){
                        skExcel.add(skilldesc.getSkillDescription());
                        skExcel.add(skilldesc.getId());
                    }
                }
                else {
                    if (skExcel.get(1).toString().equalsIgnoreCase(skilldesc.getExcelField())){
                        skilldesc.getSkillDescription().setSkillGroup(new SkillGroup());
                        skExcel.add(skilldesc.getSkillDescription());
                        skExcel.add(skilldesc.getId());
                    }
                }
            }
            if (skExcel.size() == 3){
                for (SkillDescription skilldesc: dbReadySkills){
                    if (skilldesc.getSkillGroup() != null){
                        if (skExcel.get(1).toString().equalsIgnoreCase(skilldesc.getName()) &&
                                skExcel.get(2).toString().equalsIgnoreCase(skilldesc.getSkillGroup().getName())){
                            skExcel.add(skilldesc);
                        }
                    }
                    else {
                        if (skExcel.get(1).toString().equalsIgnoreCase(skilldesc.getName())){
                            skilldesc.setSkillGroup(new SkillGroup());
                            skExcel.add(skilldesc);
                        }
                    }
                }
                if (skExcel.size() == 3) {
                    SkillDescription tmpSd = new SkillDescription(new SkillGroup());
                    if (skExcel.get(1) != null) tmpSd.setName(skExcel.get(1).toString());
                    if (skExcel.get(2) != null) tmpSd.getSkillGroup().setName(skExcel.get(2).toString());
                    skExcel.add(tmpSd);
                }
            }
        }
        return skillsExcel;
    }

    @RequestMapping(value = "/uploadFieldMapWithValidation", method = RequestMethod.POST)
    public void uploadFieldMapWithValidation(@RequestBody String arrayMap[][]){
        try{
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.postForObject("http://localhost:8080/uploadFieldMapWithValidation", arrayMap, ArrayList.class);
        }
        catch (Exception e){
            System.out.println(e);
        }

        mappingWithExcelService.parseMappingTransit(arrayMap);

    }


    @RequestMapping(value = "/mappedSkills", method = RequestMethod.POST)
    public void setSkills(@RequestBody ArrayList<ArrayList<String>> mappedSkills){
        for (ArrayList<String> skill : mappedSkills) {
            SkillDescription skillDescription = skillDescriptionService.findByName(skill.get(2));
            if (skillDescription == null || skillDescription.getDescription() == null || skillDescription.getSkillGroup() == null){
                if (skillDescription == null){
                    skillDescription = new SkillDescription();
                }
                skillDescription.setDescription(null);
                skillDescription.setName(skill.get(2));
                skillDescription.setSkillGroup(null);

                if (skill.size() > 3 && skillDescription.getDescription() == null){
                    skillDescription.setDescription(skill.get(3));
                }
                if (skill.size() > 5 && skillDescription.getSkillGroup() == null){
                    SkillGroup skillGroup = skillGroupService.findByName(skill.get(5));
                    if (skillGroup == null){
                        skillGroup = new SkillGroup();
                        skillGroup.setName(skill.get(5));
                        skillGroup.setDescription(skill.get(6));
                        skillGroupService.saveOrUpdate(skillGroup);
                        skillDescription.setSkillGroup(skillGroup);
                    }
                    else {
                        skillDescription.setSkillGroup(skillGroup);
                    }
                }
            }
            skillDescriptionService.saveOrUpdate(skillDescription);
        }


//
//
//
//
//        ArrayList<SkillGroup> potentialSkillGroup = new ArrayList<SkillGroup>();
//
//        for (ArrayList<String> skill : mappedSkills) {
//            if (skill.size() < 5) continue;
//            else {
//                SkillGroup skillGroup = new SkillGroup();
//                skillGroup.setName(skill.get(4));
//
//                if (potentialSkillGroup.isEmpty()) potentialSkillGroup.add(skillGroup);
//                for (SkillGroup findDoubles: potentialSkillGroup) {
//                    if (findDoubles.equals(skillGroup)) continue;
//                    else if (skill.size() == 6){
//                        skillGroup.setDescription(skill.get(5));
//                    }
//                    potentialSkillGroup.add(skillGroup);
//                }
//
//                SkillDescription skillDescription = skillDescriptionService.findByName(skill.get(2));
//            }
//        }
//        for (SkillGroup skillGroup: potentialSkillGroup ) {
//            skillGroupService.save(skillGroup);
//        }

    }

    @RequestMapping(value = "/getSkills", method = RequestMethod.POST)
    void uploadExcel(@RequestParam String body[][]) {
        System.out.println(body);

    }

    @RequestMapping(value = "/getSkillGroup",method = RequestMethod.GET)
    public List<SkillGroup> getSkillGroup(){
        return skillGroupService.findAll();
    }

    @RequestMapping(value = "/setOrUpdateSkill", method = RequestMethod.POST)
    public SkillDescription setOrUpdateSkill(@RequestBody SkillDescription mapSkill){
        SkillGroup skillGroup =  skillGroupService.findByName(mapSkill.getSkillGroup().getName());

        if (skillGroup == null){
            skillGroup = new SkillGroup();
        }

        if (!mapSkill.getSkillGroup().getName().isEmpty()){
            skillGroup.setDescription(mapSkill.getSkillGroup().getDescription());
            skillGroup.setName(mapSkill.getSkillGroup().getName());
            skillGroupService.saveOrUpdate(skillGroup);
            mapSkill.setSkillGroup(skillGroup);
            skillDescriptionService.saveOrUpdate(mapSkill);
        }
        else {
            mapSkill.setSkillGroup(null);
            skillDescriptionService.saveOrUpdate(mapSkill);
        }
        return mapSkill;

    }

    @RequestMapping(value = "/setMappingSkills", method = RequestMethod.POST)
    public void setMappingSkills(@RequestBody ArrayList<ArrayList<Object>>  mapSkill){
       // mappingWithExcelService.deleteAll();
        for (ArrayList<Object> arrayList:  mapSkill){
            MappingWithExcel mappingWithExcel =  mappingWithExcelService.getMappingWithExcel(arrayList);
            mappingWithExcelService.saveOrUpdate(mappingWithExcel);
        }
    }

}
