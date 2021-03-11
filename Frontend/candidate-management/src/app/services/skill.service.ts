import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Skill } from '../models/Skill';
import { catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class SkillService {

  constructor(private http: HttpClient) { }

  getAllSkills() {
    return this.http.get("http://localhost:8080/skill/all");
  }

  addSkill(skill: Skill) {
    return this.http.post("http://localhost:8080/skill/add", skill);
  }

  deleteSkill(id) {
    return this.http.delete("http://localhost:8080/skill/delete/" + id);
  }

  updateSkill(skill: Skill) {
    return this.http.put("http://localhost:8080/skill/update", skill);
  }

  getSkillById(id) {
    return this.http.get("http://localhost:8080/skill/" + id);
  }

  getSkillByName(skillName) {
    return this.http.get("http://localhost:8080/skill/name/" + skillName);
  }

}
