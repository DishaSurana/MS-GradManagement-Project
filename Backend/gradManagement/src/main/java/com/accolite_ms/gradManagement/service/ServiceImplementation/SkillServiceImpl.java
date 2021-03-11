package com.accolite_ms.gradManagement.service.ServiceImplementation;

import com.accolite_ms.gradManagement.Repos.SkillDao;
import com.accolite_ms.gradManagement.model.Skill;
import com.accolite_ms.gradManagement.service.SkillService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SkillServiceImpl implements SkillService {

    @Autowired
    private SkillDao skillDao;

    private static final Logger LOGGER = LogManager.getLogger(SkillServiceImpl.class);

    public Boolean saveSkill(Skill skill){
        try {
            skillDao.saveSkill(skill);
            LOGGER.info("Skill Saved: "+skill);
            return true;
        }
        catch (Exception e){
            LOGGER.error(e);
            return false;
        }
    }

    public Skill getSkill(Long id) {
        try{
            return skillDao.getSkillById(id);
        }
        catch (Exception e){
            LOGGER.error(e);
            return null;
        }
    }

    public Skill getSkill(String skillName) {
        try {
            return skillDao.getSkillByName(skillName);
        }
        catch (Exception e){
            LOGGER.error(e);
            return null;
        }
    }

    public List<Skill> getAllSkills() {
        try {
            return skillDao.getAllSkills();
        }
        catch (Exception e){
            LOGGER.error(e);
            return null;
        }
    }

    public Boolean updateSkill(Skill updatedSkill) {
        try {
            skillDao.updateSkill(updatedSkill);
            return true;
        }
        catch (Exception e){
            LOGGER.error(e);
            return false;
        }
    }

    public Boolean deleteSkill(Long id) {
        try {
            skillDao.deleteSkillById(id);
            return true;
        }
        catch (Exception e){
            LOGGER.error(e);
            return false;
        }
    }

//    public Boolean deleteSkill(String skillName){
//        try {
//            skillDao.deleteSkillBySkillName(skillName);
//            return true;
//        }
//        catch (Exception e){
//            LOGGER.error(e);
//            return false;
//        }
//    }
}
