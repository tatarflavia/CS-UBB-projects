import { AfterViewInit, Component, ElementRef, OnInit, Pipe, PipeTransform } from '@angular/core';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { MedList } from '@app/_models/medList';
import { Prescription } from '@app/_models/prescription';
import { User } from '@app/_models/user';
import { AuthenticationService } from '@app/_services/authentication.service';
import { PrescriptionService } from '@app/_services/prescriptio.service';
import { UserService } from '@app/_services/user.service';
import { Observable } from 'rxjs';
import { switchMap } from 'rxjs/operators';
import {Location} from '@angular/common';
import { OrganisationBelongings } from '@app/_models/organisationBelongings';
import { OrganisationBelongingService } from '@app/_services/organisationBelonging.service';
import { OrganisationService } from '@app/_services/organisation.service';
import { Organisation } from '@app/_models/organisation';
import { Disease } from '@app/_models/disease';
import { DiseaseService } from '@app/_services/disease.service';
import { AddDiseaseDialog } from '@app/_dialogs/add-disease.dialog';
import { MatDialog } from '@angular/material/dialog';
import { SavePacientDialog } from '@app/_dialogs/save-pacient.dialog';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-patient-details',
  templateUrl: './patient-details.component.html',
  styleUrls: ['./patient-details.component.css']
})

export class PatientDetailsComponent implements OnInit,AfterViewInit {
  prescriptionsForPacientByDoctor:Array<Prescription>;
  orgBelongingsForDoctorPatient:Array<OrganisationBelongings>;
  patient:User;
  isYourPatient:Boolean;
  organisationsPatientIsEnrolled:Array<Organisation>;
  patientDiseases:Array<Disease>;
  prescCardStateOpened=false;
  organisationsPatientIsNotEnrolledInForDoctor:Array<Organisation>;
  constructor(private elementRef: ElementRef,private organisationBelongingService:OrganisationBelongingService,private _snackBar: MatSnackBar,public dialog: MatDialog,private diseaseService:DiseaseService,private organisationService:OrganisationService,private orgBelongingsService:OrganisationBelongingService,private location:Location,private route:ActivatedRoute,private prescriptionService:PrescriptionService,private authenticationService:AuthenticationService,private router:Router,private userService:UserService) { 
    this.isYourPatient=false;
  }

  ngAfterViewInit(): void {
    this.elementRef.nativeElement.ownerDocument.body.style.backgroundColor = ' #e9f8fb';
  }

  ngOnInit(): void {
    this.route.params.pipe(
      switchMap(
        (params:Params) => this.userService.getUserById(+params['id'])
      )).subscribe(user=>{
      this.patient=user;
      this.getDiseasesForPatient();
      this.getOrganisationsPatientIsNotEnrolledInForDoctor();
      this.getOrganisationBelongingsForDoctorPatient();
      this.getPrescriptionsForPacientByDoctor();
    });
      
  }

  
  openOrganisationDialog(): void {
    const dialogRef = this.dialog.open(SavePacientDialog, {
      width: '500px',
      maxHeight: '90vh',
      panelClass: 'my-dialog',
      data: this.organisationsPatientIsNotEnrolledInForDoctor
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
      this.organisationBelongingService.savePatientToOrganisation(this.authenticationService.userValue.uId,result.oId,this.patient.uId).subscribe(
        res=>{
          let varSnack=this._snackBar.open("You successfully saved the patient "+this.patient.uName+" to the organisation "+result.oName,"OK",{horizontalPosition: "center",
          verticalPosition: "top"});
          varSnack.afterDismissed().subscribe(()=>window.location.reload());
          //window.location.reload();
          //this.openSnackBar("You successfully saved the patient "+this.patient.uName+" to the organisation "+result.oName,"OK");
        },(error=>{this.openSnackBar("Unsuccessfull. "+error,"OK");})
      )
      
      
    }),(error=>{});}

    openSnackBar(message: string, action: string) {
      this._snackBar.open(message, action,{horizontalPosition: "center",
      verticalPosition: "top",});
    }


    

  openDiseaseDialog(): void {
    const dialogRef = this.dialog.open(AddDiseaseDialog, {
      width: '600px',
      maxHeight: '90vh',
      panelClass: 'my-dialog',
      data: this.patient.uId
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
      //let varSnack=this._snackBar.open("You successfully added the disease for patient "+this.patient.uName,"OK",{horizontalPosition: "center",
       //   verticalPosition: "top"});
       //   varSnack.afterDismissed().subscribe(()=>window.location.reload());
     
      
    });

  }

  getOrganisationsPatientIsNotEnrolledInForDoctor(){
    this.organisationService.getOrganisationsByDoctorIdNotPatientI(this.authenticationService.userValue.uId,this.patient.uId).subscribe(
      (organisations)=>{
        this.organisationsPatientIsNotEnrolledInForDoctor=organisations.map(function (organisation){return organisation;});
        
      }
    )
  }

  getOrganisationBelongingsForDoctorPatient(){
    this.orgBelongingsService.getAllOrganisationBelongingsByDoctorIdPatientId(this.authenticationService.userValue.uId,this.patient.uId).subscribe(
      (orgs)=>{
        this.orgBelongingsForDoctorPatient=orgs.map(function (org){return org;});
        this.organisationsPatientIsEnrolled=new Array<Organisation>();
        if(this.orgBelongingsForDoctorPatient.length!==0){
          this.isYourPatient=true;
          for(var i=0;i<this.orgBelongingsForDoctorPatient.length;i++){
              this.organisationService.getOrganisationById(this.orgBelongingsForDoctorPatient[i].bOrganisationId).subscribe(org=>{this.organisationsPatientIsEnrolled.push(org);})
          }
        }
      }
    )
  }

  getDiseasesForPatient(){
      this.diseaseService.getDiseasesByUserId(this.patient.uId).subscribe(
        (diseases)=>{
          this.patientDiseases=diseases.map(function (dis){return dis;});
        }
      )
  }

  

  



  getPrescriptionsForPacientByDoctor():void{
    this.prescriptionService.getAllPrescriptionsForPacientId(this.patient.uId,this.authenticationService.userValue.uId).subscribe(
      (prescriptions)=>{
        this.prescriptionsForPacientByDoctor=prescriptions.map(function (prescription){
          return prescription;

        })
      }
    )
  }

  visitLastPage() {
    this.location.back();
  }
  visitPage(pId):void {
    //visit page for details of medication
    this.router.navigate(["/prescription-details",pId])
  }

}
