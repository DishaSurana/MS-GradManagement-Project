package com.accolite_ms.gradManagement.service;

import com.accolite_ms.gradManagement.model.Candidate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface CandidateService {
    
    Boolean saveCandidate(Candidate grad);

    List<Candidate> getAllCandidates();

    Candidate getCandidateById(String grad_id);

    Boolean updateCandidate(Candidate grad);

    Boolean deleteCandidate(String grad_id);

    List getTrendByLocation();

    List getTrendByInstitute();

    List getTrendBySkill();

    List getTrendByDegree();

    List getAllCandidatesByInstitute(Long id);

//    List getAllCandidatesByLocation(String location);

}
