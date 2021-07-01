import { Component, Inject } from "@angular/core";
import { MatDialogRef, MAT_DIALOG_DATA } from "@angular/material/dialog";
import { MedList } from "@app/_models/medList";
import { Prescription } from "@app/_models/prescription";
import { Review } from "@app/_models/review";
import { User } from "@app/_models/user";
import { AuthenticationService } from "@app/_services/authentication.service";
import { MedicationService } from "@app/_services/medication.service";
import { MedListService } from "@app/_services/medList.service";
import { PrescriptionService } from "@app/_services/prescriptio.service";
import { ReviewService } from "@app/_services/review.service";
import { UserService } from "@app/_services/user.service";

@Component({
    selector: 'review-details-dialog',
    templateUrl: 'review-details.dialog.html',
    styleUrls: ['./review-details.dialog.css']
  })
  export class ReviewDetailsDialog {
    review:Review;
    loggedUser:User;
    medicationService:MedicationService;
    userService:UserService;
    prescription:Prescription;
    medListOfPrescription:Array<MedList>;
    columnsToDisplay = ['mlMedicationName', 'mlHowOften'];
  
    constructor(
      public dialogRef: MatDialogRef<ReviewDetailsDialog >,
      @Inject(MAT_DIALOG_DATA) public data: number,private medListService:MedListService,private prescriptionService:PrescriptionService,private authenticationService:AuthenticationService,private reviewService:ReviewService,private medicatiService:MedicationService,private usServ:UserService) {
        this.loggedUser=authenticationService.userValue;
        this.medicationService=medicatiService;
        this.userService=usServ;
        this.prescription=null;
        this.reviewService.getReviewById(data).subscribe(rev=>{
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


  
    onNoClick(): void {
      this.dialogRef.close();
    }
  
  }
  