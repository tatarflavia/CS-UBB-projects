import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import {HttpClientModule} from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { StudentsComponent } from './students/students.component';
import { StudentsListComponent } from './students/students-list/students-list.component';
import {StudentService} from './students/shared/student.service';
import { ProblemsComponent } from './problems/problems.component';
import { ProblemsListComponent } from './problems/problems-list/problems-list.component';
import {ProblemService} from './problems/shared/problem.service';
import { GradesComponent } from './grades/grades.component';
import { GradesListComponent } from './grades/grades-list/grades-list.component';
import {GradeService} from './grades/shared/grade.service';
import { StudentsNewComponent } from './students/students-new/students-new.component';
import {FormsModule} from '@angular/forms';

@NgModule({
  declarations: [
    AppComponent,
    StudentsComponent,
    StudentsListComponent,
    ProblemsComponent,
    ProblemsListComponent,
    GradesComponent,
    GradesListComponent,
    StudentsNewComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [StudentService, ProblemService, GradeService],
  bootstrap: [AppComponent]
})
export class AppModule { }
