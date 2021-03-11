package com.accolite_ms.gradManagement.Repos.DaoImplementation;

import com.accolite_ms.gradManagement.Repos.CandidateDao;
import com.accolite_ms.gradManagement.model.Candidate;
import com.accolite_ms.gradManagement.model.Institute;
import com.accolite_ms.gradManagement.model.Skill;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CandidateDaoImpl implements CandidateDao {

    private final Configuration connectionVar  = new Configuration().configure()
            .addAnnotatedClass(Institute.class)
            .addAnnotatedClass(Skill.class)
            .addAnnotatedClass(Candidate.class);

    private final SessionFactory sessionFactoryVar = connectionVar.buildSessionFactory();

    public void saveCandidate(Candidate grad) throws Exception{
        Session session = sessionFactoryVar.openSession();
        Transaction tx = session.beginTransaction();
        Candidate checkCandidate = session.get(Candidate.class,grad.getGrad_id());
        if(checkCandidate != null  && grad.equals(checkCandidate)){
            if(checkCandidate.getIsDeleted())
                checkCandidate.setIsDeleted(false);
            else
                throw new Exception("Grad already present");
        }
        else {
            checkCandidate = grad;
        }
        session.save(checkCandidate);
        tx.commit();
        session.close();
    }

    public List<Candidate> getAllCandidates() throws Exception{
        Session session = sessionFactoryVar.openSession();
        Transaction tx = session.beginTransaction();
        Query allCandidatesQuery = session.createQuery("from Candidate where isDeleted is false" );
        allCandidatesQuery.setCacheable(true);
        List<Candidate> allCandidates = (List<Candidate>) allCandidatesQuery.list();
        tx.commit();
        session.close();
        return allCandidates;
    }

    public Candidate getCandidateById(String grad_id) throws Exception{
        Session session = sessionFactoryVar.openSession();
        Transaction tx = session.beginTransaction();
        Candidate grad = (Candidate) session.get(Candidate.class,grad_id);
        tx.commit();
        if(grad!=null && grad.getIsDeleted())
            return null;
        session.close();
        return grad;
    }

    public void updateCandidate(Candidate grad) throws Exception{
        Session session = sessionFactoryVar.openSession();
        Candidate updateGrad = this.getCandidateById(grad.getGrad_id());
        updateGrad.setAll(grad);
        Transaction tx = session.beginTransaction();
        session.update(updateGrad);
        tx.commit();
        session.close();
    }

    public void deleteCandidateById(String grad_id) throws Exception{
        Session session = sessionFactoryVar.openSession();
        Candidate deleteGrad = this.getCandidateById(grad_id);
        deleteGrad.setIsDeleted(true);
        Transaction tx = session.beginTransaction();
        Candidate grad = (Candidate) session.get(Candidate.class,grad_id);
        session.save(deleteGrad);
        tx.commit();
        session.close();
    }

    public List getTrendBy(String attribute) throws Exception {
        Session session = sessionFactoryVar.openSession();
        Transaction tx = session.beginTransaction();
        List result = session.createCriteria(Candidate.class)
                .add(Restrictions.ge("isDeleted", false))
                .setProjection(Projections.projectionList()
                        .add(Projections.groupProperty(attribute))
                        .add(Projections.rowCount())
                ).list();
//        System.out.println(result);
        tx.commit();
        session.close();
        return result;
    }

    public List<Candidate> getAllCandidatesByInstitute(Long id) throws Exception{
        Session session = sessionFactoryVar.openSession();
        Transaction tx = session.beginTransaction();
        Query allCandidatesInstituteQuery = session.createQuery("from Candidate where isDeleted is false and institute= "+id);
        allCandidatesInstituteQuery.setCacheable(true);
        List<Candidate> allCandidates = (List<Candidate>) allCandidatesInstituteQuery.list();
        tx.commit();
        session.close();
        return allCandidates;
    }

//    public List<Candidate> getAllCandidatesByLocation(String location) throws Exception{
//        Session session = sessionFactoryVar.openSession();
//        Transaction tx = session.beginTransaction();
//        Query allCandidatesLocationQuery = session.createQuery("from Candidate where isDeleted is false and location= '"+location +"'" );
//        allCandidatesLocationQuery.setCacheable(true);
//        List<Candidate> allCandidates = (List<Candidate>) allCandidatesLocationQuery.list();
//        tx.commit();
//        session.close();
//        return allCandidates;
//    }


}
