package uplaodEmployee.controller;


import uplaodEmployee.entity.*;
import uplaodEmployee.service.intefaces.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@CrossOrigin("http://localhost")

@RestController
public class RestWithSecondApp {

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


    @RequestMapping(value = "/setData", method = RequestMethod.POST)
    void uploadData(@RequestBody List<Map<String, String>> DateFromTransit){

        ArrayList<MappingWithExcel> mappingWithExcel = mappingWithExcelService.findAll();


        for (Map<String, String> m: DateFromTransit) {

            Employee employee = null;
            Object[] arrayOfMap = m.keySet().toArray();
            for (int i = 3; i <arrayOfMap.length; i++){
                if (i == 3){
                    employee = new Employee();
                    employee.parseString(m.get(arrayOfMap[3]).toString());
                    Employee dBEmployee = employeeDao.findByFirstLastName(employee.getFirstName(),employee.getLastName());
                    if (dBEmployee == null) employeeDao.saveOrUpdate(employee);
                    else employee = dBEmployee;
                }
                else {
                    EmployeeSkills employeeSkills = new EmployeeSkills();
                    employeeSkills.setEmployee(employee);
                    employeeSkills.setSkillDescription(getSkillDescriptionByList(mappingWithExcel, arrayOfMap[i].toString()));
                    employeeSkills.setSkillValue(skillValueService.findById((int) Double.parseDouble(m.get(arrayOfMap[i]))));


                    EmployeeSkills dBEmployeeSkills = employeeSkillsService.findByEmployeeAndSkill(employeeSkills.getEmployee(), employeeSkills.getSkillDescription());
                    if (dBEmployeeSkills == null) employeeSkillsService.save(employeeSkills);
                    else    {
                        dBEmployeeSkills.setSkillValue(employeeSkills.getSkillValue());
                        employeeSkillsService.saveOrUpdate(dBEmployeeSkills);

                    }
                }
            }

        }



    }

    private SkillDescription getSkillDescriptionByList(ArrayList<MappingWithExcel>  mappingWithExcel, String skillDiscName){

        SkillDescription skillDescription = null;

            for (MappingWithExcel mWE:mappingWithExcel) {
                try {
                    if(mWE.getTransitField().replaceAll(String.valueOf('"'),"").equalsIgnoreCase(skillDiscName)){
                        skillDescription = mWE.getSkillDescription();
                        break;
                    }
                }
                catch (Exception ex){
                    System.out.println(ex);
                }

            }
        return skillDescription;
    }

//    private SkillValue getSkillValue(String rank){
//        final String zeroRank = "No knowledge or experience";
//        final String firstRank = "Passed training, have surface knowledge, not ready for independent work, need a mentor on the technical part";
//        final String secondRank = "Have experience in the application, ready (a) for independent work as part of the team";
//        final String thirdRank = "Have extensive experience. Can work independently, think creatively and act as an expert (teach others)";
//
//        SkillValue skillValue = new SkillValue();
//        int tempRank = rank == null ? null: (int)Double.parseDouble(rank);
//        skillValue.setRank(tempRank);
//        skillValue.setDescription(tempRank == 0? zeroRank:
//                tempRank == 1? firstRank:
//                        tempRank == 2? secondRank:
//                                tempRank == 3? thirdRank: "undefinded");
//        String name = new String();
//        name = rank == null ? null :
//                tempRank == 0 ? "no knowleage" :
//                        tempRank == 1 ? "junior" :
//                                tempRank == 2 ? "middle":
//                                        tempRank == 3 ? "sen'or": "undefinded";
//        skillValue.setName(name);
//        skillValueService.save(skillValue);
//        return skillValue;
//
//
//    }
}
