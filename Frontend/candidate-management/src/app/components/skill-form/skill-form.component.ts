import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute, Router } from '@angular/router';
import { Skill } from 'src/app/models/Skill';
import { SkillService } from 'src/app/services/skill.service';

@Component({
  selector: 'app-skill-form',
  templateUrl: './skill-form.component.html',
  styleUrls: ['./skill-form.component.scss']
})
export class SkillFormComponent implements OnInit {

  skillForm = new FormGroup({
    skillName: new FormControl('')
  });
  mode = "Add";
  id = '';

  constructor(
    private route: ActivatedRoute,
    private skillService: SkillService,
    private snackBar: MatSnackBar,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.extractSkillId();
    this.getOldSkillValue();
  }

  extractSkillId() {
    this.id = this.route.snapshot.paramMap.get('id');
  }

  getOldSkillValue() {
    if (this.id !== '') {
      this.mode = "Edit";
      this.skillService.getSkillById(this.id)
        .subscribe(data => {
          this.skillForm.setValue({
            skillName: (<Skill>data).skillName
          });
        });
    }
  }

  check_AddSkill(skill) {
    this.skillService.getSkillByName(skill.skillName).subscribe(() => {
      this.openSnackBar("Skill already present!");
    }, error => {
      if (error.status === 417)
        this.addSkill(skill);
    });
  }

  addSkill(skill) {
    this.skillService.addSkill(skill)
      .subscribe(() => {
        this.openSnackBar("Skill created!");
        this.router.navigate(['skill']);
      }, error => {
        this.openSnackBar("Skill can't be created!");
        this.router.navigate(['skill']);
      });
  }

  editSkill(skill) {
    let editSkill = <Skill>{ id: Number(this.id), ...skill };
    this.skillService.updateSkill(editSkill)
      .subscribe(() => {
        this.openSnackBar("Skill updated!");
        this.router.navigate(['skill']);
      }, error => {
        this.openSnackBar("Skill can't be updated!");
        this.router.navigate(['skill']);
      });
  }

  checkEmptySkill(skill): Boolean {
    skill = <Skill>skill;
    skill.skillName = skill.skillName.trim();
    if (skill.skillName === "")
      return false;
    return true;
  }

  onSubmit() {
    let skill = this.skillForm.value;
    if (this.checkEmptySkill(skill)) {
      if (this.mode === "Add")
        this.check_AddSkill(skill);
      else if (this.mode === "Edit")
        this.editSkill(skill);
    }
    else {
      this.openSnackBar("Please fill the form");
    }
  }

  onCancel() {
    this.openSnackBar(this.mode + "ing Skill aborted!");
    this.router.navigate(['skill']);
  }

  openSnackBar(msg) {
    this.snackBar.open(msg, '',
      {
        duration: 3000,
        horizontalPosition: 'right',
        verticalPosition: 'bottom',
        panelClass: ['snack-bar-color']
      }
    );
  }
}
