import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { GoogleLoginProvider, SocialAuthService, SocialUser } from 'angularx-social-login';
import { UserService } from './services/user.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'candidate-management';
  user: any;

  constructor(private router: Router, private authService: SocialAuthService, private userService: UserService) { }

  ngOnInit() {
    // this.authService.authState.subscribe(user => {
    this.setUser();
    // });
  }

  setUser() {
    this.user = {
      name: localStorage.getItem("userName"),
      email: localStorage.getItem("email"),
      photoUrl: localStorage.getItem("photoUrl")
    };
  }

  signInWithGoogle(): void {
    this.authService.signIn(GoogleLoginProvider.PROVIDER_ID).then(user => {
      // console.log(user);
      localStorage.setItem("email", user.email);
      localStorage.setItem("userName", user.name);
      localStorage.setItem("photoUrl", user.photoUrl);
      this.setUser();
    });
  }

  signOut(): void {

    this.authService.signOut()
      .catch((e) => {
        console.log("Logged out error:" + e);
      })
      .finally(() => {
        localStorage.removeItem("email");
        localStorage.removeItem("userName");
        localStorage.removeItem("photoUrl");
        this.router.navigate(['home']);
      });
  }

  isSignedIn(): boolean {
    return this.userService.isLoggedIn()
  }

}
