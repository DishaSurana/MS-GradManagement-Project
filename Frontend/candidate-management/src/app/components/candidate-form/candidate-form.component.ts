import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute, Router } from '@angular/router';
import { Candidate, checkEmptyCandidate } from 'src/app/models/Candidate';
import { Degree } from 'src/app/models/Degree';
import { Institute } from 'src/app/models/Institute';
import { JobLocation } from 'src/app/models/JobLocation';
import { Skill } from 'src/app/models/Skill';
import { CandidateService } from 'src/app/services/candidate.service';
import { InstituteService } from 'src/app/services/institute.service';
import { SkillService } from 'src/app/services/skill.service';

@Component({
  selector: 'app-candidate-form',
  templateUrl: './candidate-form.component.html',
  styleUrls: ['./candidate-form.component.scss']
})
export class CandidateFormComponent implements OnInit {

  candidateForm = new FormGroup({
    grad_id: new FormControl('', [Validators.required]),
    name: new FormControl('', [Validators.required]),
    address: new FormControl('', [Validators.required]),
    email: new FormControl('', [Validators.required]),
    mobileNumber: new FormControl('', [Validators.required]),
    degree: new FormControl('', [Validators.required]),
    institute: new FormControl('', [Validators.required]),
    location: new FormControl('', [Validators.required]),
    joiningDate: new FormControl('', [Validators.required]),
    description: new FormControl('', [Validators.required]),
    feedback: new FormControl('', [Validators.required]),
    skillSet: new FormControl('', [Validators.required])
  });

  mode = "Add";
  id = '';
  institutes: any = [];
  skills: any = [];
  locations = enumSelector(JobLocation);
  degrees = enumSelector(Degree);

  constructor(
    public snackBar: MatSnackBar,
    private candidateService: CandidateService,
    private instititeService: InstituteService,
    private skillService: SkillService,
    private route: ActivatedRoute,
    private router: Router
  ) { }


  ngOnInit(): void {
    this.id = this.route.snapshot.paramMap.get('id');
    this.setCandidateOldValues();
    this.getInstitutes();
    this.getSkills();
  }

  getSkills() {
    this.skillService.getAllSkills()
      .subscribe(data => {
        this.skills = data;
        // console.log(this.skills);
      }, error => {
        this.openSnackBar("Error: please " + this.mode + " grad later!");
        this.router.navigate(['./grad']);
      });
  }

  getInstitutes() {
    this.instititeService.getAllInstitutes()
      .subscribe(data => {
        this.institutes = data;
        // console.log(this.institutes);
      },
        error => {
          this.openSnackBar("Error: please " + this.mode + " later!");
          this.router.navigate(['./grad']);
        });
  }

  setCandidateForm(candidate) {
    candidate = <Candidate>candidate;
    this.candidateForm.patchValue({
      grad_id: candidate.grad_id.trim(),
      name: candidate.name.trim(),
      address: candidate.address.trim(),
      email: candidate.email.trim(),
      mobileNumber: candidate.mobileNumber.trim(),
      degree: Degree[candidate.degree],
      institute: <Institute>candidate.institute.id,
      location: JobLocation[candidate.location],
      joiningDate: candidate.joiningDate,
      description: candidate.description.trim(),
      feedback: candidate.feedback.trim(),
      skillSet: candidate.skillSet?.map(({ id }) => id)
    });
  }

  setCandidateOldValues() {
    if (this.id !== '') {
      this.mode = "Edit";
      this.candidateService.getCandidateById(this.id)
        .subscribe(data => {
          // console.log(data);
          this.setCandidateForm(data);
        });
    }
  }

  check_Add_Candidate(candidate) {
    this.candidateService.getCandidateById(candidate.grad_id).subscribe(() => {
      this.openSnackBar("Candidate already present!");
    }, error => {
      if (error.status === 417)
        this.addCandidate(candidate);
    });
  }

  addCandidate(candiate) {
    this.candidateService.addCandidate(candiate)
      .subscribe(() => {
        this.openSnackBar("Candidate created!");
        this.router.navigate(['grad']);
      }, error => {
        this.openSnackBar("Candidate can't be created!");
        this.router.navigate(['grad']);
      });
  }

  editCandidate(editCandidate) {
    console.log(editCandidate);
    this.candidateService.updateCandidate(editCandidate)
      .subscribe(() => {
        this.openSnackBar("Candidate updated!");
        this.router.navigate(['grad']);
      }, error => {
        this.openSnackBar("Candidate can't be updated!");
        this.router.navigate(['grad']);
      });
  }

  preSubmitChecks() {
    let candidate = this.candidateForm.value;
    let submitCandidate: Candidate;
    if (checkEmptyCandidate(candidate)) {
      submitCandidate = {
        "grad_id": candidate.grad_id.trim(),
        "name": candidate.name.trim(),
        "address": candidate.address.trim(),
        "email": candidate.email.trim(),
        "mobileNumber": candidate.mobileNumber.trim(),
        "degree": Degree[candidate.degree],
        "institute": <Institute>this.institutes.find(i => i.id === candidate.institute),
        "location": JobLocation[candidate.location],
        "joiningDate": candidate.joiningDate,
        "description": candidate.description.trim(),
        "feedback": candidate.feedback.trim(),
        "skillSet": this.populateSkillSet(candidate.skillSet),
        "isDeleted": false
      }
      return submitCandidate;
    }
    else {
      this.openSnackBar("Please fill the form!");
    }
  }

  populateSkillSet(skillSet) {
    skillSet?.forEach((element, index, array) => {
      array[index] = this.skills.find(j => j.id === element);
    }) // populating skillSet
    return skillSet;
  }

  onSubmit() {
    let candidate = this.preSubmitChecks();
    console.log("Onsubmit: ", candidate);
    if (this.mode === "Add")
      this.check_Add_Candidate(candidate);
    else if (this.mode === "Edit")
      this.editCandidate(candidate);
  }

  onCancel() {
    this.openSnackBar(this.mode + "ing Candidate aborted!");
    this.router.navigate(['grad']);
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

export function enumSelector(definition) {
  return Object.keys(definition)
    .map(key => ({ key: key, value: definition[key] }));
}