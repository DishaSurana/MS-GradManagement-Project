import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute, Router } from '@angular/router';
import { Institute } from 'src/app/models/Institute';
import { InstituteService } from 'src/app/services/institute.service';

@Component({
  selector: 'app-institute-form',
  templateUrl: './institute-form.component.html',
  styleUrls: ['./institute-form.component.scss']
})
export class InstituteFormComponent implements OnInit {

  instituteForm = new FormGroup({
    name: new FormControl('', [Validators.required]),
    address: new FormControl('', [Validators.required])
  });
  mode = "Add";
  id = '';

  constructor(
    public snackBar: MatSnackBar,
    private instituteService: InstituteService,
    private route: ActivatedRoute,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.extractInstituteId();
    this.getOldInstituteValue();
  }

  extractInstituteId() {
    this.id = this.route.snapshot.paramMap.get('id');
  }

  getOldInstituteValue() {
    if (this.id !== '') {
      this.mode = "Edit";
      this.instituteService.getInstituteById(this.id)
        .subscribe(data => {
          this.instituteForm.patchValue({
            name: (<Institute>data).name,
            address: (<Institute>data).address
          });
        });
    }
  }

  check_Add_Institute(institute) {
    this.instituteService.getInstituteByName(institute.name).subscribe(() => {
      this.openSnackBar("Institute already present!");
    }, error => {
      if (error.status === 417)
        this.addInstitute(institute);
    });
  }

  addInstitute(institute) {
    this.instituteService.addInstitute(institute)
      .subscribe(() => {
        this.openSnackBar("Institute created!");
        this.router.navigate(['institute']);
      }, error => {
        this.openSnackBar("Institute can't be created!");
        this.router.navigate(['institute']);
      });
  }

  editInstitute(institute) {
    let editInstitute = <Institute>{ id: Number(this.id), ...institute };
    console.log(editInstitute);
    this.instituteService.updateInstitute(editInstitute)
      .subscribe(() => {
        this.openSnackBar("Institute updated!");
        this.router.navigate(['institute']);
      }, error => {
        this.openSnackBar("Institute can't be updated!");
        this.router.navigate(['institute']);
      });
  }


  checkEmptyInstitute(institute): Boolean {
    institute = <Institute>institute;
    institute.name = institute.name.trim();
    institute.address = institute.address.trim();
    if (institute.name === "" || institute.address === "")
      return false;
    return true;
  }

  onSubmit() {
    let institute = this.instituteForm.value;
    if (this.checkEmptyInstitute(institute)) {
      if (this.mode === "Add")
        this.check_Add_Institute(institute);
      else if (this.mode === "Edit")
        this.editInstitute(institute);
    }
    else {
      this.openSnackBar("Please fill the form!");
    }
  }

  onCancel() {
    this.openSnackBar(this.mode + "ing Institute aborted!");
    this.router.navigate(['institute']);
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
