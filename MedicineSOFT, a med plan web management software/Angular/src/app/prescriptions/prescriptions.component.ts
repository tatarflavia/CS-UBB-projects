import { AfterViewInit, Component, ElementRef, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Prescription } from '@app/_models/prescription';
import { AuthenticationService } from '@app/_services/authentication.service';
import { PrescriptionService } from '@app/_services/prescriptio.service';
import { UserService } from '@app/_services/user.service';

@Component({
  selector: 'app-prescriptions',
  templateUrl: './prescriptions.component.html',
  styleUrls: ['./prescriptions.component.css']
})
export class PrescriptionsComponent implements OnInit, AfterViewInit  {

  pendingPrescriptions:Array<Prescription>;
  currentPrescriptions:Array<Prescription>;
  finishedPrescriptions:Array<Prescription>;
  dismissedPrescriptions:Array<Prescription>;
  userService:UserService;
 
  panelOpenStateFinished = false;
  panelOpenStateCurrent = false;
  panelOpenStateDismissed = false;
  panelOpenStateNotStarted = false;
  constructor(private elementRef: ElementRef,private prescriptionService:PrescriptionService,private authenticationService:AuthenticationService,private router:Router,private userServ:UserService) {
    this.userService=userServ;
   }
  ngAfterViewInit(): void {
    this.elementRef.nativeElement.ownerDocument.body.style.backgroundColor = '#d6e0f5';
  }

  ngOnInit(): void {
    this.getPendingPrescriptions();
    this.getCurrentPrescriptions();
    this.getFinishedPrescriptions();
    this.getDismissedPrescriptions();
  }

  getPendingPrescriptions():void{
    this.prescriptionService.getPrescriptionsByTypeByUserId("Not Started",this.authenticationService.userValue.uId).subscribe(
      (prescriptions)=>{
        this.pendingPrescriptions=prescriptions.map(function (prescription){
          return prescription;

        })
      }
    )
  }
  getCurrentPrescriptions():void{
    this.prescriptionService.getPrescriptionsByTypeByUserId("Ongoing",this.authenticationService.userValue.uId).subscribe(
      (prescriptions)=>{
        this.currentPrescriptions=prescriptions.map(function (prescription){
          return prescription;

        })
      }
    )
  }
  getDismissedPrescriptions():void{
    this.prescriptionService.getPrescriptionsByTypeByUserId("Dismissed",this.authenticationService.userValue.uId).subscribe(
      (prescriptions)=>{
        this.dismissedPrescriptions=prescriptions.map(function (prescription){
          return prescription;

        })
      }
    )
  }
  getFinishedPrescriptions():void{
    this.prescriptionService.getPrescriptionsByTypeByUserId("Finished",this.authenticationService.userValue.uId).subscribe(
      (prescriptions)=>{
        this.finishedPrescriptions=prescriptions.map(function (prescription){
          return prescription;

        })
      }
    )
  }
  visitPage(pId:number):void {
    //visit page for details of medication
    this.router.navigate(["/prescription-details",pId])
  }

}
