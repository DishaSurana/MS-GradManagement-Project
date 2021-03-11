package com.accolite_ms.gradManagement.controller;

import com.accolite_ms.gradManagement.model.Candidate;
import com.accolite_ms.gradManagement.service.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Consumes;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/candidate")
public class CandidateController {
    
    @Autowired
    CandidateService candidateService;

    @CrossOrigin("http://localhost:4200")
    @Produces({"application/xml","application/json"})
    @GetMapping("/all")
    public ResponseEntity<List<Candidate>> getAllCandidates(){
        List<Candidate> candidateList = candidateService.getAllCandidates();
        return new ResponseEntity<>(candidateList, HttpStatus.OK);
    }

    @CrossOrigin("http://localhost:4200")
    @Produces({"application/xml","application/json"})
    @GetMapping("/{id}")
    public ResponseEntity<Candidate> getCandidate(@PathVariable("id") String grad_id){
        Candidate candidate = candidateService.getCandidateById(grad_id);
        HttpStatus status = (candidate==null) ? HttpStatus.EXPECTATION_FAILED : HttpStatus.OK;
        return new ResponseEntity<>(candidate,status);
    }

    @CrossOrigin("http://localhost:4200")
    @Consumes({"application/xml","application/json"})
    @PostMapping("/add")
    public ResponseEntity<?> addCandidate(@RequestBody Candidate candidate){
        Boolean success = candidateService.saveCandidate(candidate);
        HttpStatus status = !success ? HttpStatus.EXPECTATION_FAILED : HttpStatus.CREATED;
        return new ResponseEntity<>(status);
    }

    @CrossOrigin("http://localhost:4200")
    @Consumes({"application/xml","application/json"})
    @PutMapping("/update")
    public ResponseEntity<?> updateCandidate(@RequestBody Candidate candidate){
        Boolean success = candidateService.updateCandidate(candidate);
        HttpStatus status = !success ? HttpStatus.EXPECTATION_FAILED : HttpStatus.OK;
        return new ResponseEntity<>(status);
    }

    @CrossOrigin("http://localhost:4200")
    @DeleteMapping("/delete/{grad_id}")
    public ResponseEntity<?> deleteCandidate(@PathVariable("grad_id") String grad_id){
        Boolean success = candidateService.deleteCandidate(grad_id);
        HttpStatus status = !success ? HttpStatus.EXPECTATION_FAILED : HttpStatus.OK;
        return new ResponseEntity<>(status);
    }

    /*
        Get Candidate By
        - Location
        - Institute
    */

//    @CrossOrigin("http://localhost:4200")
//    @GetMapping("/location/{jobLocation}")
//    public ResponseEntity<List> getCandidateByLocation(@PathVariable("jobLocation") String jobLocation){
//        List locationGradList = this.candidateService.getAllCandidatesByLocation(jobLocation);
//        return new ResponseEntity<>(locationGradList, HttpStatus.OK);
//    }

    @CrossOrigin("http://localhost:4200")
    @GetMapping("/institute/{id}")
    public ResponseEntity<List> getCandidateByInstitute(@PathVariable("id") Long id){
        List locationGradList = this.candidateService.getAllCandidatesByInstitute(id);
        return new ResponseEntity<>(locationGradList, HttpStatus.OK);
    }

    /*
        Trend API's
    */
    @CrossOrigin("http://localhost:4200")
    @GetMapping("/trend/{attribute}")
    public ResponseEntity<List> getCandidateTrend(@PathVariable("attribute") String attribute){
        List trendList = null;
        switch (attribute){
            case "location":
            {
                trendList = this.candidateService.getTrendByLocation();
                break;
            }
            case "skill":
            {
                trendList = this.candidateService.getTrendBySkill();
                break;
            }
            case "institute":
            {
                trendList = this.candidateService.getTrendByInstitute();
                break;
            }
            case "degree":
            {
                trendList = this.candidateService.getTrendByDegree();
                break;
            }
            default:
        }
        return new ResponseEntity<>(trendList, HttpStatus.OK);

    }
}
