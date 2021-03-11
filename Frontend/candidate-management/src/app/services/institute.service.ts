import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Institute } from '../models/Institute';

@Injectable({
  providedIn: 'root'
})
export class InstituteService {

  constructor(private http: HttpClient) { }

  getAllInstitutes() {
    return this.http.get("http://localhost:8080/institute/all");
  }

  deleteInstitute(id) {
    return this.http.delete("http://localhost:8080/institute/delete/" + id);
  }

  addInstitute(institute) {
    return this.http.post("http://localhost:8080/institute/add/", institute);
  }

  updateInstitute(institute: Institute) {
    return this.http.put("http://localhost:8080/institute/update", institute);
  }

  getInstituteById(id) {
    return this.http.get("http://localhost:8080/institute/" + id);
  }

  getInstituteByName(name) {
    return this.http.get("http://localhost:8080/institute/name/" + name);
  }

}
