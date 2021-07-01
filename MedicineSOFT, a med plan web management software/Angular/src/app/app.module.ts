//root module of the app
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule,ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { NgSelectModule } from '@ng-select/ng-select';
import { MatSliderModule } from '@angular/material/slider';


import { AppComponent } from './app.component';
import { AppRoutingModule } from './app-routing.module';
import { MatTableModule } from '@angular/material/table'  ;
import {MatDialogModule, MAT_DIALOG_DEFAULT_OPTIONS} from '@angular/material/dialog';


import { BasicAuthInterceptor } from './_helpers/basic-auth.interceptor';
import {ErrorInterceptor} from './_helpers/error.interceptor';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component'
;
import { AddReviewComponent } from './add-review/add-review.component'
;
import { ReviewsComponent } from './reviews/reviews.component'
;
import { ReviewDetailsComponent } from './review-details/review-details.component'
;
import { MedicationsComponent } from './medications/medications.component';
import { MedicationDetailsComponent } from './medication-details/medication-details.component';;
import { PrescriptionsComponent } from './prescriptions/prescriptions.component'
;
import { YourPatientsComponent } from './your-patients/your-patients.component';
import {MatSnackBarModule} from '@angular/material/snack-bar';
import {MatGridListModule} from '@angular/material/grid-list';

import { SearchPatientComponent } from './search-patient/search-patient.component';
import { PatientDetailsComponent } from './patient-details/patient-details.component';
import { AddPrescriptionComponent } from './add-prescription/add-prescription.component';;
import { ChangeStatusDismissedDialog, ChangeStatusNotStartedDialog, ChangeStatusOngoingDialog, PrescriptionDetailsComponent } from './prescription-details/prescription-details.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {MatExpansionModule} from '@angular/material/expansion';
import { GetDoctorPipe } from './_pipes/getDoctorPipe';
import { GetMedicationPipe } from './_pipes/getMedicationPipe';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatButtonModule } from '@angular/material/button';
import { MatRippleModule } from '@angular/material/core';
import {MatSelectModule} from '@angular/material/select';
import {MatInputModule} from '@angular/material/input';
import { ReviewDetailsDialog } from './_dialogs/review-details.dialog';
import { GetReviewPipe } from './_pipes/getReviewPipe';
import { AddDiseaseDialog } from './_dialogs/add-disease.dialog';
import { SavePacientDialog } from './_dialogs/save-pacient.dialog';
import { AddDoctorComponent } from './add-doctor/add-doctor.component';
import {ScrollingModule} from '@angular/cdk/scrolling';
import {MatIconModule} from '@angular/material/icon';
@NgModule({
    imports: [
        BrowserModule,
        ReactiveFormsModule,
        FormsModule,
        HttpClientModule,
        AppRoutingModule,
        NgSelectModule,
        BrowserAnimationsModule,
        MatSliderModule,
        MatExpansionModule,
        MatTableModule,
        MatDialogModule,
        MatFormFieldModule,
        MatButtonModule,
        MatInputModule,
        MatRippleModule,
        MatSelectModule,
        MatSnackBarModule,
        MatGridListModule,
        ScrollingModule,
        MatIconModule,
        
    ],
    declarations: [
        AppComponent,
        HomeComponent,
        LoginComponent,
        RegisterComponent ,
        AddReviewComponent ,
        ReviewsComponent ,
        ReviewDetailsComponent ,
        MedicationsComponent,
        MedicationDetailsComponent,
        PatientDetailsComponent,
        SearchPatientComponent,
        YourPatientsComponent,
        PrescriptionsComponent,
        AddPrescriptionComponent,
        PrescriptionDetailsComponent,
        GetDoctorPipe,
        GetMedicationPipe,
        GetReviewPipe,
        ChangeStatusNotStartedDialog,
        ChangeStatusOngoingDialog,
        ChangeStatusDismissedDialog,
        ReviewDetailsDialog,
        AddDiseaseDialog,
        SavePacientDialog,
        AddDoctorComponent,
    ],
    providers: [
        { provide: HTTP_INTERCEPTORS, useClass: BasicAuthInterceptor, multi: true },
        { provide: HTTP_INTERCEPTORS, useClass: ErrorInterceptor, multi: true },
        {provide: MAT_DIALOG_DEFAULT_OPTIONS, useValue: {hasBackdrop: false}},

        
    ],
    bootstrap: [AppComponent]
})
export class AppModule { }