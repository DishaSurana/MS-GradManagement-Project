package com.accolite_ms.gradManagement.service.ServiceImplementation;

import com.accolite_ms.gradManagement.Repos.InstituteDao;
import com.accolite_ms.gradManagement.model.Institute;
import com.accolite_ms.gradManagement.service.InstituteService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InstituteServiceImpl implements InstituteService {

    @Autowired
    InstituteDao instituteDao;

    private static final Logger LOGGER = LogManager.getLogger(InstituteServiceImpl.class);

    public Boolean saveInstitute(Institute institute){
        try {
            this.instituteDao.saveInstitute(institute);
            return true;
        }
        catch (Exception e){
            LOGGER.error(e);
            return false;
        }
    }

    public List<Institute> getAllInstitutes(){
        try {
            return this.instituteDao.getAllInstitutes();
        }
        catch (Exception e){
            LOGGER.error(e);
            return null;
        }
    }

    public Institute getInstituteById(Long id){
        try {
            return this.instituteDao.getInstituteById(id);
        }
        catch (Exception e){
            LOGGER.error(e);
            return null;
        }
    }

    public Institute getInstituteByName(String instituteName){
        try {
            return this.instituteDao.getInstituteByName(instituteName);
        }
        catch (Exception e){
            LOGGER.error(e);
            return null;
        }
    }

    public Boolean updateInstitute(Institute institute){
        try {
            this.instituteDao.updateInstitute(institute);
            return true;
        }
        catch (Exception e){
            LOGGER.error(e);
            return false;
        }
    }

    public Boolean deleteInstitute(Long id){
        try {
            this.instituteDao.deleteInstituteById(id);
            return true;
        }
        catch (Exception e){
            LOGGER.error(e);
            return false;
        }
    }
}
