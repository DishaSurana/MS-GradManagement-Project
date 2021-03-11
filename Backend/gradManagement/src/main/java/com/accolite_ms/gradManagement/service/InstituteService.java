package com.accolite_ms.gradManagement.service;

import com.accolite_ms.gradManagement.model.Institute;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface InstituteService {

    Boolean saveInstitute(Institute institute);

    List<Institute> getAllInstitutes();

    Institute getInstituteById(Long id);

    Institute getInstituteByName(String instituteName);

    Boolean updateInstitute(Institute institute);

    Boolean deleteInstitute(Long id);
}
