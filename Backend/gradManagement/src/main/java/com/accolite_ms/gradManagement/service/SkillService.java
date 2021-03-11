package com.accolite_ms.gradManagement.service;

import com.accolite_ms.gradManagement.model.Skill;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SkillService {

    Boolean saveSkill(Skill skill);

    List<Skill> getAllSkills();

    Skill getSkill(Long id);

    Skill getSkill(String skillName);

    Boolean updateSkill(Skill skill);

    Boolean deleteSkill(Long id);

//    Boolean deleteSkill(String skillName);

}
