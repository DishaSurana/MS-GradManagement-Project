package com.accolite_ms.gradManagement.controller;

import com.accolite_ms.gradManagement.model.Institute;
import com.accolite_ms.gradManagement.service.InstituteService;
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

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = InstituteController.class)
public class InstituteControllerTest {

    @Autowired
    public MockMvc mockMvc;

    @Autowired
    public ObjectMapper objectMapper;

    @MockBean
    InstituteService instituteService;

    Institute institute;

    @Before
    public void setUp() throws Exception {
        institute = new Institute("University of Delhi", "Delhi, India");
        institute.setId(1L);
        Institute institute1 = new Institute("Mumbai University", "Mumbai, India");
        institute1.setId(2L);

        List<Institute> instituteList = new ArrayList(){
            {
                add(institute);
                add(institute1);
            }
        };
        //get all institutes
        Mockito.when(instituteService.getAllInstitutes()).thenReturn(instituteList);
    }

    @Test
    public void getAllInstitutesTest() throws Exception{

        this.mockMvc.perform(get("http://localhost:8080/institute/all"))
                .andExpect(status().isOk());
    }

    @Test
    public void getInstituteByIdTest() throws Exception{

        //get institute by id- institute found
        Mockito.when(instituteService.getInstituteById(institute.getId())).thenReturn(institute);
        this.mockMvc.perform(get("http://localhost:8080/institute/"+institute.getId()))
                .andExpect(status().isOk());

        //get institute by id- institute not found
        Mockito.when(instituteService.getInstituteById(5L)).thenReturn(null);
        this.mockMvc.perform(get("http://localhost:8080/institute/"+5L))
                .andExpect(status().isExpectationFailed());
    }

    @Test
    public void getInstituteByNameTest() throws Exception{

        //get institute by name- institute found
        Mockito.when(instituteService.getInstituteByName(institute.getName())).thenReturn(institute);
        this.mockMvc.perform(get("http://localhost:8080/institute/name/"+institute.getName()))
                .andExpect(status().isOk());

        //get institute by name- institute not found
        Mockito.when(instituteService.getInstituteByName("DTU")).thenReturn(null);
        this.mockMvc.perform(get("http://localhost:8080/institute/name/DTU"))
                .andExpect(status().isExpectationFailed());
    }

    @Test
    public void saveInstituteTest() throws Exception {

        //save institute- no exception
        Mockito.when(instituteService.saveInstitute(institute)).thenReturn(true);
        this.mockMvc.perform(post("http://localhost:8080/institute/add")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(institute))
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_XML_VALUE)
        ).andExpect(status().isCreated());

        //save institute- exception
        Mockito.when(instituteService.saveInstitute(institute)).thenReturn(false);
        this.mockMvc.perform(post("http://localhost:8080/institute/add")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(institute))
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_XML_VALUE)
        ).andExpect(status().isExpectationFailed());

    }

    @Test
    public void deleteInstituteTest() throws Exception{

        //delete institute - institute deleted
        Mockito.when(instituteService.deleteInstitute(institute.getId())).thenReturn(true);
        this.mockMvc.perform(delete("http://localhost:8080/institute/delete/"+institute.getId()))
                .andExpect(status().isOk());

        //delete institute- institute not deleted
        Mockito.when(instituteService.deleteInstitute(5L)).thenReturn(false);
        this.mockMvc.perform(delete("http://localhost:8080/institute/delete/"+5L))
                .andExpect(status().isExpectationFailed());
    }

    @Test
    public void updateInstituteTest() throws Exception {

        //update institute- no exception
        Mockito.when(instituteService.updateInstitute(institute)).thenReturn(true);
        this.mockMvc.perform(put("http://localhost:8080/institute/update")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(institute))
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_XML_VALUE)
        ).andExpect(status().isOk());

        //update institute- exception
        Mockito.when(instituteService.updateInstitute(institute)).thenReturn(false);
        this.mockMvc.perform(put("http://localhost:8080/institute/update")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(institute))
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_XML_VALUE)
        ).andExpect(status().isExpectationFailed());

    }
}
