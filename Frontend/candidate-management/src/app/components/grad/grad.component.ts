import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MatTableDataSource } from '@angular/material/table';
import { Skill } from 'src/app/models/Skill';
import { CandidateService } from 'src/app/services/candidate.service';
import { InstituteService } from 'src/app/services/institute.service';

@Component({
  selector: 'app-grad',
  templateUrl: './grad.component.html',
  styleUrls: ['./grad.component.scss']
})

export class GradComponent implements OnInit {

  candidates: any;
  instituteFilterValue: String = "all";
  // locations = enumSelector(JobLocation);
  institutes: any = [];

  grads: MatTableDataSource<any>;

  displayedColumns: any[] = ['grad_id', 'name', 'email', 'address', 'mobileNumber', 'degree', 'location', 'joiningDate', 'description', 'feedback', 'institute', 'skillSet'];

  columnsToDisplay: any[] = this.displayedColumns.slice();

  constructor(
    private candidateService: CandidateService,
    private instititeService: InstituteService,
    public snackBar: MatSnackBar) { }

  ngOnInit(): void {
    this.setAllGrads();
    this.getInstitutes();
  }

  setAllGrads() {
    this.candidateService.getAllCandidates().subscribe(data => {
      this.candidates = data;
      this.grads = new MatTableDataSource(this.candidates);
    });
  }

  getInstitutes() {
    this.instititeService.getAllInstitutes()
      .subscribe(data => {
        this.institutes = data;
        // console.log(this.institutes);
      },
        error => {
          this.openSnackBar("Server Error!");
        });
  }

  filterGradsByInstitute() {
    if (this.instituteFilterValue === "all") {
      this.setAllGrads();
      return;
    }

    this.candidateService.getCandidatesByInstitute(this.instituteFilterValue)
      .subscribe(data => {
        if (data !== null) {
          this.candidates = data;
          this.grads = new MatTableDataSource(this.candidates);
        }
      }, error => {
        this.openSnackBar("Filter By Institute- Server Error!");
      });
  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.grads.filter = filterValue.trim().toLowerCase();
  }

  extractSkills(skillSet) {
    let skills = '';
    let skill: Skill;
    for (let i in skillSet) {
      skill = skillSet[i];
      skills += skill.skillName;
      if (i !== String(skillSet.length - 1))
        skills += ', '
    }
    return skills;
  }

  deleteGrad(grad_id) {
    this.candidateService.deleteCandidate(grad_id)
      .subscribe(() => this.setAllGrads(),
        error => this.openSnackBar("Candiate/Grad can't be deleted"));
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
