package uplaodEmployee.controller;


import uplaodEmployee.entity.SkillDescription;
import uplaodEmployee.entity.SkillGroup;
import uplaodEmployee.service.intefaces.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@CrossOrigin("http://localhost")

@RestController
public class RestWithFirstApp {


    @Autowired
    EmployeeSkillsService employeeSkillsService;

    @Autowired
    SkillDescriptionService skillDescriptionService;

    @Autowired
    SkillGroupService skillGroupService;

    @Autowired
    SkillValueService skillValueService;




    @RequestMapping(value = "/testUpdate")
    public String testUpdate(){



        SkillGroup skillGroup2 = new SkillGroup();
        skillGroup2.setName("Languages");
        //skillGroup.setId(18);
        skillGroup2.setDescription("aslkdjlk");
        skillGroupService.saveOrUpdate(skillGroup2);

        SkillDescription skillDescription = new SkillDescription();
        skillDescription.setSkillGroup(skillGroup2);
        skillDescription.setName("Java SE");

        SkillDescription skillDescription2 = new SkillDescription();
        skillDescription2.setSkillGroup(skillGroup2);
        skillDescription2.setName("Java EE");
//





        return "done";
    }
}
