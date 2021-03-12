package com.accolite_ms.gradManagement.Repos.DaoImplementation;

import com.accolite_ms.gradManagement.Repos.InstituteDao;
import com.accolite_ms.gradManagement.model.Institute;
import com.accolite_ms.gradManagement.model.Skill;
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
public class InstituteDaoImplTest {

    @Autowired
    InstituteDao instituteDao;

    Institute institute;

    @Before
    public void setUp() throws Exception{
        this.getAllInstitutesTest();
    }

    @Test
    @Transactional
    public void getAllInstitutesTest() throws Exception{
        List<Institute> instituteList = instituteDao.getAllInstitutes();
        Assert.assertNotNull(instituteList);
        institute = instituteList.get(0);
    }

    @Test
    public void getInstituteByIdTest() throws Exception{
        Institute resultInstitute = instituteDao.getInstituteById(institute.getId());
        Assert.assertEquals(institute, resultInstitute);
    }

    @Test
    public void getInstituteByNameTest() throws Exception{
        Institute resultInstitute = instituteDao.getInstituteByName(institute.getName());
        Assert.assertEquals(institute, resultInstitute);
    }

    @Test
    @Transactional
    public void updateInstituteTest() throws Exception{

        String instituteName = institute.getName();
        institute.setName("Dummy");
        instituteDao.updateInstitute(institute);
        Assert.assertEquals("Dummy",instituteDao.getInstituteById(institute.getId()).getName());

        institute.setName(instituteName);
        instituteDao.updateInstitute(institute);
        Assert.assertEquals(instituteName,instituteDao.getInstituteById(institute.getId()).getName());

    }

    @Test
    @Transactional
    public void deleteInstituteTest() throws Exception{
        instituteDao.deleteInstituteById(-1L);
    }

    @Test
    @Transactional
    public void saveInstituteTest() throws Exception{
        instituteDao.saveInstitute(institute);
        Assert.assertEquals(institute, instituteDao.getInstituteById(institute.getId()));
    }

    @Test
    @Transactional
    public void saveDeleteInstituteTest() throws Exception{
        Institute tempInstitute = new Institute("DummyInstitute", "dummyAddress");
        instituteDao.saveInstitute(tempInstitute);
        Assert.assertEquals(tempInstitute, instituteDao.getInstituteById(tempInstitute.getId()));
        instituteDao.deleteInstituteById(tempInstitute.getId());
        Assert.assertNull(instituteDao.getInstituteById(tempInstitute.getId()));
    }
}
