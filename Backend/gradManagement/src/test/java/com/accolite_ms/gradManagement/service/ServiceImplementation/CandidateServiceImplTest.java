package com.accolite_ms.gradManagement.service.ServiceImplementation;

import com.accolite_ms.gradManagement.Repos.CandidateDao;
import com.accolite_ms.gradManagement.model.*;
import com.accolite_ms.gradManagement.service.CandidateService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CandidateServiceImplTest {
    
    @Autowired
    CandidateService candidateService;
    
    @MockBean
    CandidateDao candidateDao;
    
    //Testing object
    Candidate grad, grad1;
    Institute institute;
    List<Candidate> candidateList;
    Skill skill, skill1;

    @Before
    public void setUp() throws Exception {
        //Institute
        institute = new Institute("University of Delhi", "Delhi, India");
        institute.setId(1L);

        //Skill list
        Set<Skill> skillList = new HashSet();
        skill = new Skill("Java");
        skill.setId(1L);

        skill1 = new Skill();
        skill1.setId(2L);
        skill1.setSkillName("C++");

        skillList.add(skill);
        skillList.add(skill1);

        //Candidate Objects
        grad = new Candidate("INT101", "Aayat", "Ashok Vihar,Delhi",
                "aayat101@gmail.com", "8897638472", Degree.MCA,
                institute, JobLocation.MUMBAI, new Date(2020, 1, 3), "Intern",
                "nice", skillList);

        grad1 = new Candidate("INT102", "Ra,", "Mayur Vihar,Delhi",
                "ram102@gmail.com", "9999638472", Degree.MSC_CS,
                institute, JobLocation.BANGALORE, new Date(2021, 2, 21), "Intern",
                "nice", skillList);

        candidateList = new ArrayList(){
            {
                add(grad);
                add(grad1);
            }
        };
    }

    @Test
    public void getCandidateByIdTest() throws Exception{
        String id = "INT101";

        //when no exception- candidate found
        Mockito.when(candidateDao.getCandidateById(id))
                .thenReturn(grad);
        Assert.assertEquals(candidateService.getCandidateById(id), grad);

        //when exception- candidate not found
        Mockito.when(candidateDao.getCandidateById(id))
                .thenThrow(new Exception())
                .thenReturn(null);
        Assert.assertEquals(candidateService.getCandidateById(id), null);


    }

    @Test
    public void getAllCandidatesTest() throws Exception{

        //when no exception- candidate list returned
        Mockito.when(candidateDao.getAllCandidates())
                .thenReturn(candidateList);
        Assert.assertEquals(candidateService.getAllCandidates(), candidateList);

        //when exception- candidate list not found
        Mockito.when(candidateDao.getAllCandidates())
                .thenThrow(new Exception())
                .thenReturn(null);
        Assert.assertEquals(candidateService.getAllCandidates(), null);
    }

    @Test
    public void saveCandidateTest() throws Exception{

        //when no exception
        Mockito.doNothing().when(candidateDao).saveCandidate(grad);
        Assert.assertTrue(candidateService.saveCandidate(grad));

        //when exception
        Mockito.doThrow(new Exception())
                .when(candidateDao).saveCandidate(grad);
        Assert.assertFalse(candidateService.saveCandidate(grad));

    }

    @Test
    public void updateCandidateTest() throws Exception{

        //when no exception
        Mockito.doNothing().when(candidateDao).updateCandidate(grad);
        Assert.assertTrue(candidateService.updateCandidate(grad));

        //when exception
        Mockito.doThrow(new Exception())
                .when(candidateDao).updateCandidate(grad);
        Assert.assertFalse(candidateService.updateCandidate(grad));

    }

    @Test
    public void deleteCandidateTest() throws Exception{

        //when no exception- candidate id = 1
        Mockito.doNothing().when(candidateDao).deleteCandidateById(grad.getGrad_id());
        Assert.assertTrue(candidateService.deleteCandidate(grad.getGrad_id()));

        //when exception
        Mockito.doThrow(new Exception())
                .when(candidateDao).deleteCandidateById(grad.getGrad_id());
        Assert.assertFalse(candidateService.deleteCandidate(grad.getGrad_id()));

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
        //When no exception- trend by location = trendList
        Mockito.when(candidateDao.getTrendBy("location")).thenReturn(trendList);
        Assert.assertEquals(candidateService.getTrendByLocation(),trendList);

        //When exception- trend by location = null
        Mockito.when(candidateDao.getTrendBy("location"))
                .thenThrow(new Exception())
                .thenReturn(null);
        Assert.assertEquals(candidateService.getTrendByLocation(),null);

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
        //When no exception- trend by degree = trendList
        Mockito.when(candidateDao.getTrendBy("degree")).thenReturn(trendList);
        Assert.assertEquals(candidateService.getTrendByDegree(),trendList);

        //When exception- trend by degree = null
        Mockito.when(candidateDao.getTrendBy("degree"))
                .thenThrow(new Exception())
                .thenReturn(null);
        Assert.assertEquals(candidateService.getTrendByDegree(),null);

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

        //When no exception- trend by location = trendList
        Mockito.when(candidateDao.getTrendBy("institute")).thenReturn(trendList);
        Assert.assertEquals(candidateService.getTrendByInstitute(),trendList);

        //When exception- trend by location = null
        Mockito.when(candidateDao.getTrendBy("institute"))
                .thenThrow(new Exception())
                .thenReturn(null);
        Assert.assertEquals(candidateService.getTrendByInstitute(),null);

    }

    @Test
    public void getAllCandidatesByInstituteTest() throws Exception{
        List<Candidate> candidateListByInstitute = candidateList;
        Long id = institute.getId();

        //When no exception- all candidates with given institute id = candidateListByInstitute
        Mockito.when(candidateDao.getAllCandidatesByInstitute(id))
                .thenReturn(candidateListByInstitute);
        Assert.assertEquals(candidateService.getAllCandidatesByInstitute(id), candidateListByInstitute);

        //When exception- all candidates with given institute id = null
        Mockito.when(candidateDao.getAllCandidatesByInstitute(id))
                .thenThrow(new Exception())
                .thenReturn(null);
        Assert.assertEquals(candidateService.getAllCandidatesByInstitute(id), null);

    }

    @Test
    public void getTrendBySkillsTest() throws Exception{

        ArrayList trendList = new ArrayList(){
            {
                add(new ArrayList(){{
                    add(skill);
                    add(2L);
                }});
                add(new ArrayList(){{
                    add(skill1);
                    add(2L);
                }});
            }
        };
        // no exception- trend by skills
        Mockito.when(candidateDao.getAllCandidates()).thenReturn(candidateList);
        List trendSkill = candidateService.getTrendBySkill();
        Assert.assertEquals(trendSkill, trendList);

        // exception- trend by skills = null
        Mockito.when(candidateDao.getAllCandidates())
                .thenThrow(new Exception())
                .thenReturn(null);
        trendSkill = candidateService.getTrendBySkill();
        Assert.assertNull(trendSkill);

    }

}
