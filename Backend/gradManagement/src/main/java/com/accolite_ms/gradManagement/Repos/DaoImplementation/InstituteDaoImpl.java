package com.accolite_ms.gradManagement.Repos.DaoImplementation;

import com.accolite_ms.gradManagement.Repos.InstituteDao;
import com.accolite_ms.gradManagement.model.Institute;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class InstituteDaoImpl implements InstituteDao {

    private final Configuration connectionVar  = new Configuration().configure()
            .addAnnotatedClass(Institute.class);

    private final SessionFactory sessionFactoryVar = connectionVar.buildSessionFactory();

    public void saveInstitute(Institute institute) throws Exception{
        Session session = sessionFactoryVar.openSession();
        Institute checkInstitute = this.getInstituteByName(institute.getName());
        if(institute.equals(checkInstitute))
            return;
        Transaction tx = session.beginTransaction();
        session.save(institute);
        tx.commit();
        session.close();
    }

    public List<Institute> getAllInstitutes() throws Exception {
        Session session = sessionFactoryVar.openSession();
        Transaction tx = session.beginTransaction();
        Query allInstituteQuery = session.createQuery("from Institute " );
        allInstituteQuery.setCacheable(true);
        List<Institute> allInstitutes = (List<Institute>) allInstituteQuery.getResultList();
        tx.commit();
        session.close();
        return allInstitutes;
    }

    public Institute getInstituteById(Long id) throws Exception{
        Session session = sessionFactoryVar.openSession();
        Transaction tx = session.beginTransaction();
        Institute institute = (Institute)session.get(Institute.class, id);
        tx.commit();
        session.close();
        return institute;
    }

    public Institute getInstituteByName(String instituteName) throws Exception{
        Session session = sessionFactoryVar.openSession();
        Transaction tx = session.beginTransaction();
        Query instituteByNameQuery = session.createQuery("from Institute where name = '" + instituteName +"'");
        instituteByNameQuery.setCacheable(true);
        Institute institute = (Institute) instituteByNameQuery.uniqueResult();
        tx.commit();
        session.close();
        return institute;
    }

    public void updateInstitute(Institute newInstitute) throws Exception{
        Session session = sessionFactoryVar.openSession();
        Institute updateInstitute = this.getInstituteById(newInstitute.getId());
        updateInstitute.setName(newInstitute.getName());
        updateInstitute.setAddress(newInstitute.getAddress());
        Transaction tx = session.beginTransaction();
        session.update(updateInstitute);
        tx.commit();
        session.close();
    }

    public void deleteInstituteById(Long id) throws Exception{
        Session session = sessionFactoryVar.openSession();
        Transaction tx = session.beginTransaction();
        Institute institute = session.get(Institute.class, id);
        if(institute != null)
            session.delete(institute);
        tx.commit();
        session.close();
    }

}
