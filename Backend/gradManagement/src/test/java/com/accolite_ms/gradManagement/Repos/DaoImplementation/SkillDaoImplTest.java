package com.accolite_ms.gradManagement.Repos.DaoImplementation;

import com.accolite_ms.gradManagement.Repos.SkillDao;
import com.accolite_ms.gradManagement.model.Skill;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SkillDaoImplTest {

    @Autowired
    SkillDao skillDao;

    Skill skill;

    @Before()
    public void setUp() throws Exception{
        this.getAllSkillsTest();
    }

    @Test
    @Transactional
    public void getAllSkillsTest() throws Exception{

        List<Skill> result = skillDao.getAllSkills();
        Assert.assertNotNull(result);
        skill = result.get(0);
    }

    @Test
    @Transactional
    public void getSkillByIdTest() throws Exception{

        Skill resultSkill = skillDao.getSkillById(skill.getId());
        Assert.assertEquals(skill,resultSkill);
    }

    @Test
    @Transactional
    public void getSkillByNameTest() throws Exception{

        Skill resultSkill = skillDao.getSkillByName(skill.getSkillName());
        Assert.assertEquals(skill,resultSkill);

    }

    @Test
    @Transactional
    public void updateSkillTest() throws Exception{

        String skillName = skill.getSkillName();
        skill.setSkillName("DummySkill");
        skillDao.updateSkill(skill);
        Assert.assertEquals("DummySkill", skillDao.getSkillById(skill.getId()).getSkillName());

        skill.setSkillName(skillName);
        skillDao.updateSkill(skill);
        Assert.assertEquals(skillName, skillDao.getSkillById(skill.getId()).getSkillName());

    }

    @Test
    @Transactional
    public void deleteSkillTest() throws Exception{
        skillDao.deleteSkillById(-1L);
    }

    @Test
    @Transactional
    public void saveSkillTest() throws Exception{
        skillDao.saveSkill(skill);
        Assert.assertEquals(skill, skillDao.getSkillById(skill.getId()));
    }

    @Test
    @Transactional
    public void saveDeleteSkillTest() throws Exception{
        Skill tempSkill = new Skill("DummySkill");
        skillDao.saveSkill(tempSkill);
        Assert.assertEquals(tempSkill, skillDao.getSkillById(tempSkill.getId()));
        skillDao.deleteSkillById(tempSkill.getId());
        Assert.assertNull(skillDao.getSkillById(tempSkill.getId()));
    }
}
