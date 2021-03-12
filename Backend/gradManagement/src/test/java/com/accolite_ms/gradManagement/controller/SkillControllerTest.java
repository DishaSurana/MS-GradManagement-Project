package com.accolite_ms.gradManagement.controller;

import com.accolite_ms.gradManagement.model.Skill;
import com.accolite_ms.gradManagement.service.SkillService;
import com.fasterxml.jackson.core.JsonProcessingException;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = SkillController.class)
public class SkillControllerTest {

    @Autowired
    public MockMvc mockMvc;

    @Autowired
    public ObjectMapper objectMapper;

    @MockBean
    SkillService skillService;

    Skill skill;

    @Before
    public void setUp(){
        skill = new Skill("Java");
        skill.setId(1L);
        Skill skill1 = new Skill("C++");
        skill1.setId(2L);
        List<Skill> allSkills = new ArrayList(){
            {
                add(skill);
                add(skill1);
            }
        };

        //getAll skills
        Mockito.when(skillService.getAllSkills()).thenReturn(allSkills);

    }

    @Test
    public void getAllSkillsTest() throws Exception{

        this.mockMvc.perform(get("http://localhost:8080/skill/all"))
                    .andExpect(status().isOk());
    }

    @Test
    public void getSkillByIdTest() throws Exception{

        //get skill by id- skill found
        Mockito.when(skillService.getSkill(skill.getId())).thenReturn(skill);
        this.mockMvc.perform(get("http://localhost:8080/skill/"+skill.getId()))
                .andExpect(status().isOk());

        //get skill by id- skill not found
        Mockito.when(skillService.getSkill(5L)).thenReturn(null);
        this.mockMvc.perform(get("http://localhost:8080/skill/"+5L))
                .andExpect(status().isExpectationFailed());
    }

    @Test
    public void getSkillByNameTest() throws Exception{

        //get skill by name- skill found
        Mockito.when(skillService.getSkill(skill.getSkillName())).thenReturn(skill);
        this.mockMvc.perform(get("http://localhost:8080/skill/name/"+skill.getSkillName()))
                .andExpect(status().isOk());

        //get skill by name- skill not found
        Mockito.when(skillService.getSkill("Angular")).thenReturn(null);
        this.mockMvc.perform(get("http://localhost:8080/skill/name/Angular"))
                .andExpect(status().isExpectationFailed());
    }

    @Test
    public void saveSkillTest() throws Exception {

        //save skill- no exception
        Mockito.when(skillService.saveSkill(skill)).thenReturn(true);
        this.mockMvc.perform(post("http://localhost:8080/skill/add")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(skill))
                        .accept(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_XML_VALUE)
                    ).andExpect(status().isCreated());

        //save skill- exception
        Mockito.when(skillService.saveSkill(skill)).thenReturn(false);
        this.mockMvc.perform(post("http://localhost:8080/skill/add")
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                    .content(objectMapper.writeValueAsString(skill))
                    .accept(MediaType.APPLICATION_JSON_VALUE)
                ).andExpect(status().isExpectationFailed());

    }

    @Test
    public void deleteSkillTest() throws Exception{

        //delete skill - skill deleted
        Mockito.when(skillService.deleteSkill(skill.getId())).thenReturn(true);
        this.mockMvc.perform(delete("http://localhost:8080/skill/delete/"+skill.getId()))
                .andExpect(status().isOk());

        //delete skill- skill not deleted
        Mockito.when(skillService.deleteSkill(5L)).thenReturn(false);
        this.mockMvc.perform(delete("http://localhost:8080/skill/delete/"+5L))
                .andExpect(status().isExpectationFailed());
    }

    @Test
    public void updateSkillTest() throws Exception {

        //update skill- no exception
        Mockito.when(skillService.updateSkill(skill)).thenReturn(true);
        this.mockMvc.perform(put("http://localhost:8080/skill/update")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(skill))
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_XML_VALUE)
        ).andExpect(status().isOk());

        //update skill- exception
        Mockito.when(skillService.updateSkill(skill)).thenReturn(false);
        this.mockMvc.perform(put("http://localhost:8080/skill/update")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(objectMapper.writeValueAsString(skill))
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_XML_VALUE)
        ).andExpect(status().isExpectationFailed());

    }

}
