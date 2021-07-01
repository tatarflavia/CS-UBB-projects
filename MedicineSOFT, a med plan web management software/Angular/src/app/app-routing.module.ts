import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AddDoctorComponent } from './add-doctor/add-doctor.component';
import { AddPrescriptionComponent } from './add-prescription/add-prescription.component';
import { AddReviewComponent } from './add-review/add-review.component';

import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { MedicationDetailsComponent } from './medication-details/medication-details.component';
import { MedicationsComponent } from './medications/medications.component';
import { PatientDetailsComponent } from './patient-details/patient-details.component';
import { PrescriptionDetailsComponent } from './prescription-details/prescription-details.component';
import { PrescriptionsComponent } from './prescriptions/prescriptions.component';
import { RegisterComponent } from './register/register.component';
import { ReviewsComponent } from './reviews/reviews.component';
import { SearchPatientComponent } from './search-patient/search-patient.component';
import { YourPatientsComponent } from './your-patients/your-patients.component';
import { AuthGuard } from './_helpers/auth.guard';

const routes: Routes = [
    //homeroute is secured by passing AuthGuard
    { path: '', component: HomeComponent, canActivate: [AuthGuard] },
    { path: 'medications', component: MedicationsComponent, canActivate: [AuthGuard] },
    { path: 'reviews', component: ReviewsComponent, canActivate: [AuthGuard] },
    { path: 'medication-details/:id', component: MedicationDetailsComponent, canActivate: [AuthGuard] },
    { path: 'add-review/:pId/:mId', component: AddReviewComponent, canActivate: [AuthGuard] },
    { path: 'add-prescription/:patientId', component: AddPrescriptionComponent, canActivate: [AuthGuard] },
    { path: 'add-doctor', component: AddDoctorComponent, canActivate: [AuthGuard] },
    { path: 'prescriptions', component: PrescriptionsComponent, canActivate: [AuthGuard] },
    { path: 'patients', component: YourPatientsComponent, canActivate: [AuthGuard] },
    { path: 'search-patient', component: SearchPatientComponent, canActivate: [AuthGuard] },
    { path: 'patient-details/:id', component: PatientDetailsComponent, canActivate: [AuthGuard] },
    { path: 'prescription-details/:id', component: PrescriptionDetailsComponent, canActivate: [AuthGuard] },
    { path: 'login', component: LoginComponent },
    {path: 'register', component:RegisterComponent},
    // otherwise redirect to home
    { path: '**', redirectTo: '' }
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})
export class AppRoutingModule { }
