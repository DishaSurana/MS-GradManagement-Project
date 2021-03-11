import { Component, NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AuthLoginGuard } from './services/auth-login.guard';
import { SkillSetComponent } from './components/skill-set/skill-set.component';
import { HomeComponent } from './components/home/home.component';
import { GradComponent } from './components/grad/grad.component';
import { TrendComponent } from './components/trend/trend.component';
import { InstituteComponent } from './components/institute/institute.component';
import { SkillFormComponent } from './components/skill-form/skill-form.component';
import { CandidateFormComponent } from './components/candidate-form/candidate-form.component';
import { InstituteFormComponent } from './components/institute-form/institute-form.component';

const routes: Routes = [
  { path: '', redirectTo: 'home', pathMatch: 'full' },
  { path: 'home', component: HomeComponent },
  { path: 'skill', component: SkillSetComponent, canActivate: [AuthLoginGuard] },
  { path: 'institute', component: InstituteComponent, canActivate: [AuthLoginGuard] },
  { path: 'grad', component: GradComponent, canActivate: [AuthLoginGuard] },
  { path: 'trend', component: TrendComponent, canActivate: [AuthLoginGuard] },
  { path: 'skill/form/:id', component: SkillFormComponent, canActivate: [AuthLoginGuard] },
  { path: 'grad/form/:id', component: CandidateFormComponent, canActivate: [AuthLoginGuard] },
  { path: 'institute/form/:id', component: InstituteFormComponent, canActivate: [AuthLoginGuard] }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
