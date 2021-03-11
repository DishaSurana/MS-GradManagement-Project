package com.accolite_ms.gradManagement.service.ServiceImplementation;

import com.accolite_ms.gradManagement.Repos.SkillDao;
import com.accolite_ms.gradManagement.model.Skill;
import com.accolite_ms.gradManagement.service.SkillService;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SkillServiceImplTest {

    @Autowired
    SkillService skillService;

    @MockBean
    private SkillDao skillDao;

    Skill skill;

    @Before
    public void setUp() throws Exception {
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

        //By Skill Name
        Mockito.when(skillDao.getSkillByName(skill.getSkillName()))
                .thenReturn(skill);
        Mockito.when(skillDao.getSkillByName("Angular"))
                .thenThrow(new Exception())
                .thenReturn(null);

        //By Skill id
        Mockito.when(skillDao.getSkillById(skill.getId()))
                .thenReturn(skill);
        Mockito.when(skillDao.getSkillById(4L))
                .thenThrow(new Exception())
                .thenReturn(null);

        //All Skills - no exception
        Mockito.when(skillDao.getAllSkills())
                .thenReturn(allSkills);
    }

    @Test
    public void getSkillByNameTest() throws Exception{
        //whenValidName_thenSkillShouldBeFound
        String name = "Java";
        Skill found = skillService.getSkill(name);
        Assert.assertEquals(found, skill);

        //whenInValidName_thenSkillShouldNotBeFound
        name = "Angular";
        found = skillService.getSkill(name);
        Assert.assertEquals(found, null);
    }

    @Test
    public void getSkillByIdTest() throws Exception{
        //whenValidId_thenSkillShouldBeFound
        Long id = 1L;
        Skill found = skillService.getSkill(id);
        Assert.assertEquals(found, skill);

        //whenInValidId_thenSkillShouldNotBeFound
        id = 4L;
        found = skillService.getSkill(id);
        Assert.assertEquals(found, null);
    }

    @Test
    public void getAllSkillsTest() throws Exception{
        //when no Exception
        List allSkills = skillService.getAllSkills();
        Assert.assertNotNull(allSkills);

        //when Exception
        Mockito.when(skillDao.getAllSkills())
                .thenThrow(new Exception())
                .thenReturn(null);
        allSkills = skillService.getAllSkills();
        Assert.assertNull(allSkills);
    }

    @Test
    public void saveSkillTest() throws Exception{

        //when no exception
        Mockito.doNothing().when(skillDao).saveSkill(skill);
        Assert.assertTrue(skillService.saveSkill(skill));

        //when exception
        Mockito.doThrow(new Exception())
                .when(skillDao).saveSkill(skill);
        Assert.assertFalse(skillService.saveSkill(skill));

    }

    @Test
    public void updateSkillTest() throws Exception{

        //when no exception
        Mockito.doNothing().when(skillDao).updateSkill(skill);
        Assert.assertTrue(skillService.updateSkill(skill));

        //when exception
        Mockito.doThrow(new Exception())
                .when(skillDao).updateSkill(skill);
        Assert.assertFalse(skillService.updateSkill(skill));

    }

    @Test
    public void deleteSkillTest() throws Exception{

        //when no exception- skill id = 1
        Mockito.doNothing().when(skillDao).deleteSkillById(skill.getId());
        Assert.assertTrue(skillService.deleteSkill(skill.getId()));

        //when exception
        Mockito.doThrow(new Exception())
                .when(skillDao).deleteSkillById(skill.getId());
        Assert.assertFalse(skillService.deleteSkill(skill.getId()));

    }

}
