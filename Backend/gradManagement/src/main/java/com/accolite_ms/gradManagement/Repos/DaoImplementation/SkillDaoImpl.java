package com.accolite_ms.gradManagement.Repos.DaoImplementation;

import com.accolite_ms.gradManagement.Repos.SkillDao;
import com.accolite_ms.gradManagement.model.Skill;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SkillDaoImpl implements SkillDao {

    private final Configuration connectionVar  = new Configuration().configure()
            .addAnnotatedClass(Skill.class);

    private final SessionFactory sessionFactoryVar = connectionVar.buildSessionFactory();

    public void saveSkill(Skill skill) throws Exception{
        Session session = sessionFactoryVar.openSession();
        Skill checkSkill = this.getSkillByName(skill.getSkillName());
        if(skill.equals(checkSkill))
            return;
        Transaction tx = session.beginTransaction();
        session.save(skill);
        tx.commit();
        session.close();
    }

    public Skill getSkillById(Long id) throws Exception{
        Session session = sessionFactoryVar.openSession();
        Transaction tx = session.beginTransaction();
        Skill skill = (Skill)session.get(Skill.class, id);
        tx.commit();
        session.close();
        return skill;
    }

    public List<Skill> getAllSkills() throws Exception{
        Session session = sessionFactoryVar.openSession();
        Transaction tx = session.beginTransaction();
        Query allSkillQuery = session.createQuery("from Skill " );
        allSkillQuery.setCacheable(true);
        List<Skill> allSkills = (List<Skill>) allSkillQuery.getResultList();
        tx.commit();
        session.close();
        return allSkills;
    }

    public Skill getSkillByName(String skillName) throws Exception{
        Session session = sessionFactoryVar.openSession();
        Transaction tx = session.beginTransaction();
        Query skillByNameQuery = session.createQuery("from Skill where skillName = '" + skillName +"'");
        skillByNameQuery.setCacheable(true);
        Skill skill = (Skill) skillByNameQuery.uniqueResult();
        tx.commit();
        session.close();
        return skill;
    }

    public void updateSkill(Skill newSkill) throws Exception{
        Session session = sessionFactoryVar.openSession();
        Skill updateSkill = this.getSkillById(newSkill.getId());
        updateSkill.setSkillName(newSkill.getSkillName());
        Transaction tx = session.beginTransaction();
        session.update(updateSkill);
        tx.commit();
        session.close();
    }

    public void deleteSkillById(Long id) throws Exception{
        Session session = sessionFactoryVar.openSession();
        Transaction tx = session.beginTransaction();
        Skill skill = (Skill)session.get(Skill.class, id);
        if(skill != null)
            session.delete(skill);
        tx.commit();
        session.close();
    }

//    public void deleteSkillBySkillName(String skillName) throws Exception{
//        Session session = sessionFactoryVar.openSession();
//        Skill skill = this.getSkillByName(skillName);
//        Transaction tx1 = session.beginTransaction();
//        session.delete(skill);
//        tx1.commit();
//        session.close();
//    }

}
