package com.accolite_ms.gradManagement.controller;

import com.accolite_ms.gradManagement.model.Skill;
import com.accolite_ms.gradManagement.service.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import java.util.List;

@RestController
@RequestMapping("/skill")
public class SkillController {

    @Autowired
    private SkillService skillService;

    @CrossOrigin("http://localhost:4200")
    @Produces({"application/xml","application/json"})
    @GetMapping("/all")
    public ResponseEntity<List<Skill>> getAllSkills(){
        List<Skill> skillsList = skillService.getAllSkills();
        return new ResponseEntity<>(skillsList, HttpStatus.OK);
    }

    @CrossOrigin("http://localhost:4200")
    @Produces({"application/xml","application/json"})
    @GetMapping("/{id}")
    public ResponseEntity<Skill> getSkillById(@PathVariable("id") Long id){
        Skill skill = skillService.getSkill(id);
        HttpStatus status = (skill==null) ? HttpStatus.EXPECTATION_FAILED : HttpStatus.OK;
        return new ResponseEntity<>(skill, status);
    }

    @CrossOrigin("http://localhost:4200")
    @Produces({"application/xml","application/json"})
    @GetMapping("/name/{skillName}")
    public ResponseEntity<Skill> getSkillBySkillName(@PathVariable("skillName") String skillName){
        Skill skill = skillService.getSkill(skillName);
        HttpStatus status = (skill==null) ? HttpStatus.EXPECTATION_FAILED : HttpStatus.OK;
        return new ResponseEntity<>(skill, status);
    }

    @CrossOrigin("http://localhost:4200")
    @Consumes({"application/xml","application/json"})
    @PostMapping("/add")
    public ResponseEntity<?> addSkill(@RequestBody Skill skill){
        Boolean success = skillService.saveSkill(skill);
        HttpStatus status = !success ? HttpStatus.EXPECTATION_FAILED : HttpStatus.CREATED;
        return new ResponseEntity(status);
    }

    @CrossOrigin("http://localhost:4200")
    @Consumes({"application/xml","application/json"})
    @PutMapping("/update")
    public ResponseEntity<?> updateSkill(@RequestBody Skill skill){
        Boolean success = skillService.updateSkill(skill);
        HttpStatus status = !success ? HttpStatus.EXPECTATION_FAILED : HttpStatus.OK;
        return new ResponseEntity<>(status);
    }

    @CrossOrigin("http://localhost:4200")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteSkillById(@PathVariable("id") Long id){
        Boolean success = skillService.deleteSkill(id);
        HttpStatus status = !success ? HttpStatus.EXPECTATION_FAILED : HttpStatus.OK;
        return new ResponseEntity<>(status);
    }

//    @CrossOrigin("http://localhost:4200")
//    @DeleteMapping("/delete/name/{skillName}")
//    public ResponseEntity<?> deleteSkillBySkillName(@PathVariable("skillName") String skillName){
//        Boolean success = skillService.deleteSkill(skillName);
//        HttpStatus status = !success ? HttpStatus.EXPECTATION_FAILED : HttpStatus.OK;
//        return new ResponseEntity<>(status);
//    }
}
