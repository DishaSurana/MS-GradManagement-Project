import { Component, Injectable } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, UrlTree, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { HomeComponent } from '../components/home/home.component';
import { UserService } from './user.service';

@Injectable({
  providedIn: 'root'
})
export class AuthLoginGuard implements CanActivate {

  constructor(private userService: UserService, private router: Router, private snackBar: MatSnackBar) { }

  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {

    const loginStatus = this.userService.isLoggedIn();
    if (!loginStatus) {
      this.openSnackBar("Please Login first!");
      this.router.navigate(['home']);
    }
    return loginStatus;

  }

  openSnackBar(msg) {
    this.snackBar.open(msg, '',
      {
        duration: 2000,
        horizontalPosition: 'right',
        verticalPosition: 'top',
        panelClass: ['snack-bar-color']
      }
    );
  }

}

