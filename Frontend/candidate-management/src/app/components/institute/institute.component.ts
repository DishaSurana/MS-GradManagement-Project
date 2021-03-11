import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { InstituteService } from 'src/app/services/institute.service';

@Component({
  selector: 'app-institute',
  templateUrl: './institute.component.html',
  styleUrls: ['./institute.component.scss']
})
export class InstituteComponent implements OnInit {

  institutes: any;

  constructor(private instituteService: InstituteService, public snackBar: MatSnackBar) { }

  ngOnInit(): void {
    this.setAllInstitutes();
  }

  setAllInstitutes() {
    this.instituteService.getAllInstitutes()
      .subscribe(data => this.institutes = data
        , error => console.log("Error", error)
      );
  }

  deleteInstitute(id) {
    this.instituteService.deleteInstitute(id).subscribe(() => this.setAllInstitutes(),
      error => this.openSnackBar("Institute can't be deleted"));
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
