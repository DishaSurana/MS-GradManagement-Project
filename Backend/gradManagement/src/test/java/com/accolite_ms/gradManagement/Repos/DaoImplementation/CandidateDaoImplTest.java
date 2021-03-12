package com.accolite_ms.gradManagement.Repos.DaoImplementation;

import com.accolite_ms.gradManagement.Repos.CandidateDao;
import com.accolite_ms.gradManagement.model.Candidate;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CandidateDaoImplTest {

    @Autowired
    CandidateDao candidateDao;

    Candidate grad, grad1;

    @Before
    public void setUp() throws Exception{
        this.getAllGradsTest();
    }

    @Test
    public void getAllGradsTest() throws Exception{
        List<Candidate> candidateList = candidateDao.getAllCandidates();
        Assert.assertNotNull(candidateList);
        grad = candidateList.get(0);
        grad1 = candidateList.get(1);
    }

    @Test
    public void getGradByIdTest() throws Exception{
        Assert.assertEquals(grad, candidateDao.getCandidateById(grad.getGrad_id()));
    }

    @Test
    public void saveGradTest() {
        try {
            candidateDao.saveCandidate(grad);
        }
        catch (Exception e){
            Assert.assertEquals("Grad already present", e.getMessage());
        }
    }

    @Test
    public void deleteAndSaveGradTest() throws Exception{
        candidateDao.deleteCandidateById(grad1.getGrad_id());
        Assert.assertNull(candidateDao.getCandidateById(grad1.getGrad_id()));
        candidateDao.saveCandidate(grad1);
        Assert.assertEquals(grad1, candidateDao.getCandidateById(grad1.getGrad_id()));
    }

    @Test
    public void updateGradTest() throws Exception{
        String gradName = grad.getName();
        grad.setName("Dummy");
        candidateDao.updateCandidate(grad);
        Assert.assertEquals("Dummy",candidateDao.getCandidateById(grad.getGrad_id()).getName());

        grad.setName(gradName);
        candidateDao.updateCandidate(grad);
        Assert.assertEquals(gradName, candidateDao.getCandidateById(grad.getGrad_id()).getName());
    }

    @Test
    public void getTrendTest() throws Exception{

        Assert.assertNotNull(candidateDao.getTrendBy("location"));
        Assert.assertNotNull(candidateDao.getTrendBy("institute"));
        Assert.assertNotNull(candidateDao.getTrendBy("degree"));

    }

    @Test
    public void getGradsByInstituteTest() throws Exception{
        List<Candidate> list = candidateDao.getAllCandidatesByInstitute(grad.getInstitute().getId());
        for(Candidate c: list){
            Assert.assertEquals(c.getInstitute(), grad.getInstitute());
        }
    }


}
