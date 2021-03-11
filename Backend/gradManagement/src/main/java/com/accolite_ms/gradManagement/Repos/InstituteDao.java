package com.accolite_ms.gradManagement.Repos;

import com.accolite_ms.gradManagement.model.Institute;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Component
public interface InstituteDao {

    void saveInstitute(Institute institute) throws Exception;

    List<Institute> getAllInstitutes() throws Exception;

    Institute getInstituteById(Long id) throws Exception;

    Institute getInstituteByName(String instituteName) throws Exception;

    void updateInstitute(Institute institute) throws Exception;

    void deleteInstituteById(Long id) throws Exception;
}
