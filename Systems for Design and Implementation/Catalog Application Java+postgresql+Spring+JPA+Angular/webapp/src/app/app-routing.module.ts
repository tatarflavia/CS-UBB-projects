import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {StudentsComponent} from './students/students.component';
import {ProblemsComponent} from './problems/problems.component';
import {GradesComponent} from './grades/grades.component';
import {StudentsNewComponent} from './students/students-new/students-new.component';


const routes: Routes = [
  {path: 'students', component: StudentsComponent},
  {path: 'problems', component: ProblemsComponent},
  {path: 'grades', component: GradesComponent},
  {path: 'students/new', component: StudentsNewComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
