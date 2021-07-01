import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { Review } from '@app/_models/review';
import { User } from '@app/_models/user';
import { AuthenticationService } from '@app/_services/authentication.service';
import { MedicationService } from '@app/_services/medication.service';
import { ReviewService } from '@app/_services/review.service';
import { switchMap } from 'rxjs/operators';
import {Location} from '@angular/common';
import { UserService } from '@app/_services/user.service';
import { Prescription } from '@app/_models/prescription';
import { PrescriptionService } from '@app/_services/prescriptio.service';
import { MedListService } from '@app/_services/medList.service';
import { MedList } from '@app/_models/medList';

@Component({
  selector: 'app-review-details',
  templateUrl: './review-details.component.html',
  styleUrls: ['./review-details.component.less']
})
export class ReviewDetailsComponent implements OnInit {
  review:Review;
  loggedUser:User;
  medicationService:MedicationService;
  userService:UserService;
  prescription:Prescription;
  medListOfPrescription:Array<MedList>;
  columnsToDisplay = ['mlMedicationName', 'mlHowOften'];
  constructor(private medListService:MedListService,private prescriptionService:PrescriptionService,private location:Location,private authenticationService:AuthenticationService,private route:ActivatedRoute,private router:Router,private reviewService:ReviewService,private medicatiService:MedicationService,private usServ:UserService) { 
    this.loggedUser=authenticationService.userValue;
    this.medicationService=medicatiService;
    this.userService=usServ;
    this.prescription=null;
    this.route.params.pipe(
      switchMap(
        (params:Params) => this.reviewService.getReviewById(+params['id'])
      )).subscribe(rev=>{
      this.review=rev;
      if(this.review.rPrescriptionId!==-100 && this.review.rPrescriptionId!==null ){
        this.prescriptionService.getPrescriptionById(this.review.rPrescriptionId).subscribe(
          presc=>{
            this.prescription=presc;
            this.getMedListForPrescription();
          })
      }
    });
  }

  getMedListForPrescription():void{
    this.medListService.getMedListForPrescriptionId(this.prescription.pId).subscribe(
      (medListMembers)=>{
        this.medListOfPrescription=medListMembers.map(function (medListMember){
          return medListMember;
        })
      }
    )
  }

  ngOnInit(): void {
  }

  visitLastPage() {
    this.location.back();
  }

  visitMedPage(mId):void {
    //visit page for details of medication
    this.router.navigate(["/medication-details",mId])
  }

  visiPatientPage(uId):void {
    //visit page for details of medication
    this.router.navigate(["/patient-details",uId])
  }

}
