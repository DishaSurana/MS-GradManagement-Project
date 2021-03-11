import { Component, OnInit, TemplateRef, ViewChild } from '@angular/core';
import { MatSnackBar, MatSnackBarConfig } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { Skill } from 'src/app/models/Skill';
import { SkillService } from 'src/app/services/skill.service';

@Component({
  selector: 'app-skill-set',
  templateUrl: './skill-set.component.html',
  styleUrls: ['./skill-set.component.scss']
})
export class SkillSetComponent implements OnInit {

  skills: any = [];
  snackBarConf = new MatSnackBarConfig();

  constructor(
    private skillService: SkillService,
    private router: Router,
    public snackBar: MatSnackBar,
  ) { }

  ngOnInit(): void {
    this.getAllSkills();
    this.snackBarConfig();
  }

  snackBarConfig() {
    this.snackBarConf.duration = 3000;
    this.snackBarConf.horizontalPosition = 'right';
    this.snackBarConf.verticalPosition = 'bottom';
    this.snackBarConf.panelClass = ['snack-bar-color'];
  }

  sortSkillsById() {
    this.skills.sort((a, b) => (a.id < b.id ? -1 : 1));
  }

  getAllSkills() {
    this.skillService.getAllSkills().subscribe(data => {
      this.skills = data;
      this.sortSkillsById();
    });
  }

  openSnackBar(msg) {
    this.snackBar.open(msg, '', this.snackBarConf);
  }

  deleteSkill(id) {
    this.skillService.getSkillById(id).subscribe(skillToDelete => {
      if (skillToDelete) {
        this.skillService.deleteSkill(id)
          .subscribe(() => {
            this.openSnackBar("Skill deleted!" + (<Skill>skillToDelete).skillName);
            this.getAllSkills();
          }
            , error => {
              if (error.status === 417) {
                this.openSnackBar("Skill can't be deleted!");
              }
            });
      }
    });

  }

}
