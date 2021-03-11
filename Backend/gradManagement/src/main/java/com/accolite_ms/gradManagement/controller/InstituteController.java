package com.accolite_ms.gradManagement.controller;

import com.accolite_ms.gradManagement.model.Institute;
import com.accolite_ms.gradManagement.model.Skill;
import com.accolite_ms.gradManagement.service.InstituteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Consumes;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import java.util.List;

@RestController
@RequestMapping("/institute")
public class InstituteController {
    
    @Autowired
    InstituteService instituteService;

    @CrossOrigin("http://localhost:4200")
    @Produces({"application/xml","application/json"})
    @GetMapping("/all")
    public ResponseEntity<List<Institute>> getAllInstitutes(){
        List<Institute> instituteList = instituteService.getAllInstitutes();
        return new ResponseEntity<>(instituteList, HttpStatus.OK);
    }

    @CrossOrigin("http://localhost:4200")
    @Produces({"application/xml","application/json"})
    @GetMapping("/{id}")
    public ResponseEntity<Institute> getInstitute(@PathVariable("id") Long id){
        Institute institute = instituteService.getInstituteById(id);
        HttpStatus status = (institute==null) ? HttpStatus.EXPECTATION_FAILED : HttpStatus.OK;
        return new ResponseEntity<>(institute,status);
    }

    @CrossOrigin("http://localhost:4200")
    @Produces({"application/xml","application/json"})
    @GetMapping("/name/{instituteName}")
    public ResponseEntity<Institute> getInstituteByName(@PathVariable("instituteName") String instituteName){
        Institute institute = instituteService.getInstituteByName(instituteName);
        HttpStatus status = (institute==null) ? HttpStatus.EXPECTATION_FAILED : HttpStatus.OK;
        return new ResponseEntity<>(institute, status);
    }

    @CrossOrigin("http://localhost:4200")
    @Consumes({"application/xml","application/json"})
    @PostMapping("/add")
    public ResponseEntity<?> addInstitute(@RequestBody Institute institute){
        Boolean success = instituteService.saveInstitute(institute);
        HttpStatus status = !success ? HttpStatus.EXPECTATION_FAILED : HttpStatus.CREATED;
        return new ResponseEntity<>(status);
    }

    @CrossOrigin("http://localhost:4200")
    @Consumes({"application/xml","application/json"})
    @PutMapping("/update")
    public ResponseEntity<?> updateInstitute(@RequestBody Institute institute){
        Boolean success = instituteService.updateInstitute(institute);
        HttpStatus status = !success ? HttpStatus.EXPECTATION_FAILED : HttpStatus.OK;
        return new ResponseEntity<>(status);
    }

    @CrossOrigin("http://localhost:4200")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteInstitute(@PathVariable("id") Long id){
        Boolean success = instituteService.deleteInstitute(id);
        HttpStatus status = !success ? HttpStatus.EXPECTATION_FAILED : HttpStatus.OK;
        return new ResponseEntity<>(status);
    }
}
