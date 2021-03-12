package com.accolite_ms.gradManagement.controller;

import com.accolite_ms.gradManagement.model.*;
import com.accolite_ms.gradManagement.service.CandidateService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = CandidateController.class)
public class CandidateControllerTest {

    @Autowired
    public MockMvc mockMvc;

    @Autowired
    public ObjectMapper objectMapper;

    @MockBean
    CandidateService candidateService;

    //Testing object
    Candidate grad, grad1;
    Institute institute;

    @Before
    public void setUp() throws Exception {
        //Institute
        institute = new Institute("University of Delhi", "Delhi, India");
        institute.setId(1L);

        //Skill list
        Set<Skill> skillList = new HashSet();
        Skill skill = new Skill("Java");
        skill.setId(1L);
        skillList.add(skill);
        skill.setId(2L);
        skill.setSkillName("C++");
        skillList.add(skill);

        //Candidate Objects
        grad = new Candidate("INT101", "Aayat", "Ashok Vihar,Delhi",
                "aayat101@gmail.com", "8897638472", Degree.MCA,
                institute, JobLocation.MUMBAI, new Date(2020, 1, 3), "Intern",
                "nice", skillList);

        grad1 = new Candidate("INT102", "Ra,", "Mayur Vihar,Delhi",
                "ram102@gmail.com", "9999638472", Degree.MSC_CS,
                institute, JobLocation.BANGALORE, new Date(2021, 2, 21), "Intern",
                "nice", skillList);

        List<Candidate> candidateList = new ArrayList(){
            {
                add(grad);
                add(grad1);
            }
        };
        // get all candidates
        Mockito.when(candidateService.getAllCandidates()).thenReturn(candidateList);
        // get all candidates of Institute(1L, "University of Delhi", "Delhi, India")
        Mockito.when(candidateService.getAllCandidatesByInstitute(1L)).thenReturn(candidateList);
    }

    @Test
    public void getAllGradsTest() throws Exception{

        this.mockMvc.perform(get("http://localhost:8080/candidate/all"))
                .andExpect(status().isOk());
    }

    @Test
    public void getGradByIdTest() throws Exception{

        //get grad by id- grad found
        Mockito.when(candidateService.getCandidateById(grad.getGrad_id())).thenReturn(grad);
        this.mockMvc.perform(get("http://localhost:8080/candidate/"+grad.getGrad_id()))
                .andExpect(status().isOk());

        //get grad by id- grad not found
        Mockito.when(candidateService.getCandidateById("INT100")).thenReturn(null);
        this.mockMvc.perform(get("http://localhost:8080/candidate/INT100"))
                .andExpect(status().isExpectationFailed());
    }

    @Test
    public void saveGradTest() throws Exception {

        //save candidate- no exception
        Mockito.when(candidateService.saveCandidate(grad)).thenReturn(true);
        this.mockMvc.perform(post("http://localhost:8080/candidate/add")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(grad))
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_XML_VALUE)
        ).andExpect(status().isCreated());

        //save candidate- exception
        Mockito.when(candidateService.saveCandidate(grad)).thenReturn(false);
        this.mockMvc.perform(post("http://localhost:8080/candidate/add")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(grad))
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_XML_VALUE)
        ).andExpect(status().isExpectationFailed());

    }

    @Test
    public void deleteInstituteTest() throws Exception{

        //delete candidate - candidate deleted
        Mockito.when(candidateService.deleteCandidate(grad.getGrad_id())).thenReturn(true);
        this.mockMvc.perform(delete("http://localhost:8080/candidate/delete/"+grad.getGrad_id()))
                .andExpect(status().isOk());

        //delete candidate- candidate not deleted
        Mockito.when(candidateService.deleteCandidate("INT100")).thenReturn(false);
        this.mockMvc.perform(delete("http://localhost:8080/candidate/delete/"+"INT100"))
                .andExpect(status().isExpectationFailed());
    }

    @Test
    public void updateGradTest() throws Exception {

        //update candidate- no exception
        Mockito.when(candidateService.updateCandidate(grad)).thenReturn(true);
        this.mockMvc.perform(put("http://localhost:8080/candidate/update")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(grad))
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_XML_VALUE)
        ).andExpect(status().isOk());

        //update candidate- exception
        Mockito.when(candidateService.updateCandidate(grad)).thenReturn(false);
        this.mockMvc.perform(put("http://localhost:8080/candidate/update")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(grad))
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_XML_VALUE)
        ).andExpect(status().isExpectationFailed());

    }

    @Test
    public void getAllGradsByInstituteTest() throws Exception{

        this.mockMvc.perform(get("http://localhost:8080/candidate/institute/"+institute.getId()))
                .andExpect(status().isOk());
    }

    @Test
    public void getTrendByLocationTest() throws Exception{

        List trendList = new ArrayList(){
            {
                add(new ArrayList(){
                    {
                        add(JobLocation.BANGALORE);
                        add(1);
                    }
                });
                add(new ArrayList(){
                    {
                        add(JobLocation.MUMBAI);
                        add(2);
                    }
                });
            }
        };

        Mockito.when(candidateService.getTrendByLocation()).thenReturn(trendList);
        this.mockMvc.perform(get("http://localhost:8080/candidate/trend/location"))
                .andExpect(status().isOk());
    }

    @Test
    public void getTrendByDegreeTest() throws Exception{

        List trendList = new ArrayList(){
            {
                add(new ArrayList(){
                    {
                        add(Degree.MCA);
                        add(1);
                    }
                });
                add(new ArrayList(){
                    {
                        add(Degree.MSC_CS);
                        add(2);
                    }
                });
            }
        };

        Mockito.when(candidateService.getTrendByDegree()).thenReturn(trendList);
        this.mockMvc.perform(get("http://localhost:8080/candidate/trend/degree"))
                .andExpect(status().isOk());
    }

    @Test
    public void getTrendByInstituteTest() throws Exception{

        List trendList = new ArrayList(){
            {
                add(new ArrayList(){
                    {
                        add(institute);
                        add(1);
                    }
                });
            }
        };

        Mockito.when(candidateService.getTrendByInstitute()).thenReturn(trendList);
        this.mockMvc.perform(get("http://localhost:8080/candidate/trend/institute"))
                .andExpect(status().isOk());
    }

    @Test
    public void getTrendBySkillTest() throws Exception{

        Skill skill = new Skill("Java");
        skill.setId(1L);

        List trendList = new ArrayList(){
            {
                new ArrayList(){{
                    add(skill);
                    add(2);
                }};
            }
        };

        Mockito.when(candidateService.getTrendBySkill()).thenReturn(trendList);
        this.mockMvc.perform(get("http://localhost:8080/candidate/trend/skill"))
                .andExpect(status().isOk());
    }

}
