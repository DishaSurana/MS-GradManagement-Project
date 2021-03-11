import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Candidate } from '../models/Candidate';

@Injectable({
  providedIn: 'root'
})
export class CandidateService {

  constructor(private http: HttpClient) { }

  getAllCandidates() {
    return this.http.get("http://localhost:8080/candidate/all");
  }

  getCandidateById(grad_id) {
    return this.http.get("http://localhost:8080/candidate/" + grad_id);
  }

  addCandidate(candidate: Candidate) {
    return this.http.post("http://localhost:8080/candidate/add", candidate);
  }

  deleteCandidate(grad_id) {
    return this.http.delete("http://localhost:8080/candidate/delete/" + grad_id);
  }

  updateCandidate(candidate) {
    return this.http.put("http://localhost:8080/candidate/update", candidate);
  }

  //grads by location
  getCandidatesByLocation(location) {
    return this.http.get("http://localhost:8080/candidate/location/" + location);
  }

  //grads by institute
  getCandidatesByInstitute(id) {
    return this.http.get("http://localhost:8080/candidate/institute/" + id);
  }

  //trend
  getCandidateTrend(attribute: String) {
    return this.http.get("http://localhost:8080/candidate/trend/" + attribute);
  }

}
