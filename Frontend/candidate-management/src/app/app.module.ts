import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { SocialAuthServiceConfig, SocialLoginModule } from 'angularx-social-login';
import { GoogleLoginProvider } from 'angularx-social-login';
import { HttpClientModule } from '@angular/common/http';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ChartsModule } from 'ng2-charts';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatMenuModule } from '@angular/material/menu';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatListModule } from '@angular/material/list';
import { MatTableModule } from '@angular/material/table';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { MatCardModule } from '@angular/material/card';
import { MatSelectModule } from '@angular/material/select';
import { MatNativeDateModule } from '@angular/material/core';
// import { MatCarouselModule } from '@ngmodule/material-carousel';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AuthLoginGuard } from './services/auth-login.guard';
import { UserService } from './services/user.service';
import { HomeComponent } from './components/home/home.component';
import { SkillSetComponent } from './components/skill-set/skill-set.component';
import { GradComponent } from './components/grad/grad.component';
import { TrendComponent } from './components/trend/trend.component';
import { CandidateFormComponent } from './components/candidate-form/candidate-form.component';
import { SkillFormComponent } from './components/skill-form/skill-form.component';
import { InstituteFormComponent } from './components/institute-form/institute-form.component';
import { InstituteComponent } from './components/institute/institute.component';


const materialAr = [
  BrowserAnimationsModule,
  MatToolbarModule,
  BrowserModule,
  MatMenuModule,
  MatButtonModule,
  MatIconModule,
  MatListModule,
  MatTableModule,
  MatFormFieldModule,
  MatInputModule,
  MatDatepickerModule,
  MatSnackBarModule,
  MatCardModule,
  MatSelectModule,
  MatNativeDateModule
];

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    SkillSetComponent,
    GradComponent,
    TrendComponent,
    CandidateFormComponent,
    SkillFormComponent,
    InstituteFormComponent,
    InstituteComponent,
  ],
  imports: [
    AppRoutingModule,
    SocialLoginModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    materialAr,
    ChartsModule
  ],
  providers: [
    {
      provide: 'SocialAuthServiceConfig',
      useValue: {
        autoLogin: false,
        providers: [
          {
            id: GoogleLoginProvider.PROVIDER_ID,
            provider: new GoogleLoginProvider(
              '305325987714-b6rbv4vrt5beah34e441fvpaca03vgf9.apps.googleusercontent.com'
            )
          }
        ]
      } as SocialAuthServiceConfig,
    },
    AuthLoginGuard,
    UserService
  ],
  exports: [materialAr],
  bootstrap: [AppComponent]
})
export class AppModule { }
