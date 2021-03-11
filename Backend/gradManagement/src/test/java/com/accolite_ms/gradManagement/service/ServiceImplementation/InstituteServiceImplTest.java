package com.accolite_ms.gradManagement.service.ServiceImplementation;


import com.accolite_ms.gradManagement.Repos.InstituteDao;
import com.accolite_ms.gradManagement.model.Institute;
import com.accolite_ms.gradManagement.service.InstituteService;
import org.junit.Assert;
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
public class InstituteServiceImplTest {

    @Autowired
    InstituteService instituteService;

    @MockBean
    InstituteDao instituteDao;

    //Testing object
    Institute institute, institute1;

    @Before
    public void setUp() throws Exception {
        institute = new Institute("University of Delhi", "Delhi, India");
        institute.setId(1L);
        institute1 = new Institute("Mumbai University", "Mumbai, India");
        institute1.setId(2L);
    }

    @Test
    public void getInstituteByIdTest() throws Exception{
        Long id = 1L;

        //when no exception- institute found
        Mockito.when(instituteDao.getInstituteById(id))
                .thenReturn(institute);
        Assert.assertEquals(instituteService.getInstituteById(id), institute);

        //when exception- institute not found
        Mockito.when(instituteDao.getInstituteById(id))
                .thenThrow(new Exception())
                .thenReturn(null);
        Assert.assertEquals(instituteService.getInstituteById(id), null);


    }

    @Test
    public void getInstituteByNameTest() throws Exception{
        String name = "University of Delhi";

        //when no exception- institute found
        Mockito.when(instituteDao.getInstituteByName(name))
                .thenReturn(institute);
        Assert.assertEquals(instituteService.getInstituteByName(name), institute);

        //when exception- institute not found
        Mockito.when(instituteDao.getInstituteByName(name))
                .thenThrow(new Exception())
                .thenReturn(null);
        Assert.assertEquals(instituteService.getInstituteByName(name), null);

    }

    @Test
    public void getAllInstitutesTest() throws Exception{
        List<Institute> instituteList = new ArrayList(){
            {
                add(institute);
                add(institute1);
            }
        };
        //when no exception- institute list returned
        Mockito.when(instituteDao.getAllInstitutes())
                .thenReturn(instituteList);
        Assert.assertEquals(instituteService.getAllInstitutes(), instituteList);

        //when exception- institute list not found
        Mockito.when(instituteDao.getAllInstitutes())
                .thenThrow(new Exception())
                .thenReturn(null);
        Assert.assertEquals(instituteService.getAllInstitutes(), null);
    }

    @Test
    public void saveInstituteTest() throws Exception{

        //when no exception
        Mockito.doNothing().when(instituteDao).saveInstitute(institute);
        Assert.assertTrue(instituteService.saveInstitute(institute));

        //when exception
        Mockito.doThrow(new Exception())
                .when(instituteDao).saveInstitute(institute);
        Assert.assertFalse(instituteService.saveInstitute(institute));

    }

    @Test
    public void updateInstituteTest() throws Exception{

        //when no exception
        Mockito.doNothing().when(instituteDao).updateInstitute(institute);
        Assert.assertTrue(instituteService.updateInstitute(institute));

        //when exception
        Mockito.doThrow(new Exception())
                .when(instituteDao).updateInstitute(institute);
        Assert.assertFalse(instituteService.updateInstitute(institute));

    }

    @Test
    public void deleteInstituteTest() throws Exception{

        //when no exception- institute id = 1
        Mockito.doNothing().when(instituteDao).deleteInstituteById(institute.getId());
        Assert.assertTrue(instituteService.deleteInstitute(institute.getId()));

        //when exception
        Mockito.doThrow(new Exception())
                .when(instituteDao).deleteInstituteById(institute.getId());
        Assert.assertFalse(instituteService.deleteInstitute(institute.getId()));

    }

}
