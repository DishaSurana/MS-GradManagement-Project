package com.accolite_ms.gradManagement.Repos;

import com.accolite_ms.gradManagement.model.Skill;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Component
public interface SkillDao {

    void saveSkill(Skill skill) throws Exception;

    List<Skill> getAllSkills() throws Exception;

    Skill getSkillById(Long id) throws Exception;

    Skill getSkillByName(String skillName) throws Exception;

    void updateSkill(Skill skill) throws Exception;

    void deleteSkillById(Long id) throws Exception;

//    void deleteSkillBySkillName(String skillName) throws Exception;

}
