package com.accolite_ms.gradManagement.Repos;

import com.accolite_ms.gradManagement.model.Candidate;
import com.accolite_ms.gradManagement.model.Institute;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface CandidateDao {

    void saveCandidate(Candidate grad) throws Exception;

    List<Candidate> getAllCandidates() throws Exception;

    Candidate getCandidateById(String grad_id) throws Exception;

    void updateCandidate(Candidate grad) throws Exception;

    void deleteCandidateById(String grad_id) throws Exception;

    List getTrendBy(String att) throws Exception;

//    List getAllCandidatesByLocation(String location) throws Exception;

    List getAllCandidatesByInstitute(Long id) throws Exception;
}
