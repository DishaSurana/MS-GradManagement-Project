package com.accolite_ms.gradManagement.service.ServiceImplementation;

import com.accolite_ms.gradManagement.Repos.CandidateDao;
import com.accolite_ms.gradManagement.model.Candidate;
import com.accolite_ms.gradManagement.model.Skill;
import com.accolite_ms.gradManagement.service.CandidateService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CandidateServiceImpl implements CandidateService {

    @Autowired
    CandidateDao candidateDao;

    private static final Logger LOGGER = LogManager.getLogger(CandidateServiceImpl.class);

    public Boolean saveCandidate(Candidate grad){
        try{
            this.candidateDao.saveCandidate(grad);
            return true;
        }
        catch (Exception e){
            LOGGER.error(e);
            return false;
        }
    }

    public List<Candidate> getAllCandidates() {
        try{
            return this.candidateDao.getAllCandidates();
        }
        catch (Exception e){
            LOGGER.error(e);
            return null;
        }
    }

    public Candidate getCandidateById(String grad_id) {
        try{
            return this.candidateDao.getCandidateById(grad_id);
        }
        catch (Exception e){
            LOGGER.error(e);
            return null;
        }
    }

    public Boolean updateCandidate(Candidate grad){
        try{
            this.candidateDao.updateCandidate(grad);
            return true;
        }
        catch (Exception e){
            LOGGER.error(e);
            return false;
        }
    }

    public Boolean deleteCandidate(String grad_id) {
        try{
            this.candidateDao.deleteCandidateById(grad_id);
            return true;
        }
        catch (Exception e){
            LOGGER.error(e);
            return false;
        }
    }

    public List getAllCandidatesByInstitute(Long id) {
        try {
            return this.candidateDao.getAllCandidatesByInstitute(id);
        }
        catch (Exception e) {
            LOGGER.error(e);
            return null;
        }
    }

    public List getTrendByLocation(){
        try{
            return this.candidateDao.getTrendBy("location");
        }
        catch (Exception e) {
            LOGGER.error(e);
            return null;
        }
    }

    public List getTrendByInstitute(){
        try{
            return this.candidateDao.getTrendBy("institute");
        }
        catch (Exception e) {
            LOGGER.error(e);
            return null;
        }
    }

    public List getTrendByDegree(){
        try{
            return this.candidateDao.getTrendBy("degree");
        }
        catch (Exception e) {
            LOGGER.error(e);
            return null;
        }
    }


    public List getTrendBySkill(){

        List<Candidate> allCandidates = this.getAllCandidates();

        Map<Skill, Long> skillCandidateMap = new HashMap<>();
        for(Candidate candidate : allCandidates ){
            for(Skill skill : candidate.getSkillSet())
            {
                if(skillCandidateMap.get(skill) == null)
                    skillCandidateMap.put(skill, 1L);
                else
                    skillCandidateMap.put(skill, skillCandidateMap.get(skill)+1);
            }
        }

        List<List<Object>> result = new ArrayList<>();
        for (Map.Entry<Skill,Long> entry : skillCandidateMap.entrySet())
        {
            result.add(new ArrayList<Object>(){
                {
                    add(entry.getKey());
                    add(entry.getValue());
                }
            });
        }
        return result;
    }

}
