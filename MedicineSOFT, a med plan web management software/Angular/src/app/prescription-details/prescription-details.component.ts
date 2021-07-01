import { AfterViewInit, Component, ElementRef, Inject, OnInit } from '@angular/core';
import {Location} from '@angular/common';
import { Prescription } from '@app/_models/prescription';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { PrescriptionService } from '@app/_services/prescriptio.service';
import { AuthenticationService } from '@app/_services/authentication.service';
import { switchMap } from 'rxjs/operators';
import { MedList } from '@app/_models/medList';
import { MedListService } from '@app/_services/medList.service';
import { UserService } from '@app/_services/user.service';
import { User } from '@app/_models/user';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ReviewService } from '@app/_services/review.service';
import { ReviewDetailsDialog } from '@app/_dialogs/review-details.dialog';
import { Review } from '@app/_models/review';

@Component({
  selector: 'app-prescription-details',
  templateUrl: './prescription-details.component.html',
  styleUrls: ['./prescription-details.component.css']
})
export class PrescriptionDetailsComponent implements OnInit,AfterViewInit {
  loggedUser:User;
  prescription:Prescription
  medListOfPrescription:Array<MedList>;
  userService:UserService;
  reviewService:ReviewService;
  medListMemberBoolDict:Map<MedList,Boolean>;
  medListMemberReviewDict:Map<MedList,Review>;

  columnsToDisplay = ['mlMedicationName', 'mlHowOften','booleanVal'];
  constructor(private elementRef: ElementRef,private revieService:ReviewService,private _snackBar: MatSnackBar,public dialog: MatDialog,private location:Location,private route:ActivatedRoute,private prescriptionService:PrescriptionService,private authenticationService:AuthenticationService,private router:Router,private medListService:MedListService,private userServ:UserService) { 
    this.loggedUser=this.authenticationService.userValue;
    this.reviewService=revieService;
    this.userService=userServ;
    this.route.params.pipe(
      switchMap(
        (params:Params) => this.prescriptionService.getPrescriptionById(+params['id'])
      )).subscribe(presc=>{
      this.prescription=presc;
      this.getMedListForPrescription();
    });
  }

  openSnackBar(message: string, action: string) {
    this._snackBar.open(message, action,{horizontalPosition: "center",
    verticalPosition: "top",});
  }

  ngOnInit(): void {
    //this.getMedListForPrescription();
  }
 
  ngAfterViewInit(): void {
    this.elementRef.nativeElement.ownerDocument.body.style.backgroundColor = ' #e9f8fb';
  }

  visitLastPage() {
    this.location.back();
  }

  getMedListForPrescription():void{
    this.medListService.getMedListForPrescriptionId(this.prescription.pId).subscribe(
      (medListMembers)=>{
        this.medListOfPrescription=medListMembers.map(function (medListMember){
          return medListMember;
        });
        this.medListMemberBoolDict=new Map<MedList,Boolean>();
        this.medListMemberReviewDict=new Map<MedList,Review>();
        for(let medListMemb of this.medListOfPrescription){
          this.reviewService.checkIfReviewExists(this.prescription.pId,medListMemb.mlMedicationId,this.prescription.pPatientId).subscribe(
            (value)=>{
              this.medListMemberBoolDict.set(medListMemb,value);
              if(value!=false){
                this.reviewService.getReviewByPrescIdMedIdPatientId(this.prescription.pId,medListMemb.mlMedicationId,this.prescription.pPatientId).subscribe(
                  (value)=>{
                  this.medListMemberReviewDict.set(medListMemb,value);}
                )
              }
              else{
                this.medListMemberReviewDict.set(medListMemb,null);
              }
            }
          )         
        }
      }
    )
  }

  openReviewDialog(rId:number): void {
    const dialogRef = this.dialog.open(ReviewDetailsDialog, {
      width: '1000px',
      maxHeight: '90vh',
      panelClass: 'my-dialog',
      data: rId
    });

    
  }

  openDialog(): void {
    console.log("e la open dialog"+this.prescription.pStatus);
    if(this.prescription.pStatus=="Not Started"){
        const dialogRef = this.dialog.open(ChangeStatusNotStartedDialog, {
        width: '250px',
        data: this.prescription.pStatus,
      });
      dialogRef.afterClosed().subscribe(result => {
        console.log('The dialog was closed');
        this.prescriptionService.changeStatusForPrescription(this.prescription.pId,result).subscribe(
          (presc)=>{
            this.prescription=presc;
            this.openSnackBar("You changed the status to: "+result, "OK")
            
            
          }
        )
        
      });
    }
    else if(this.prescription.pStatus=="Ongoing"){
      const dialogRef = this.dialog.open(ChangeStatusOngoingDialog, {
        width: '250px',
        data: this.prescription.pStatus,
      });
      dialogRef.afterClosed().subscribe(result => {
        console.log('The dialog was closed');
        this.prescriptionService.changeStatusForPrescription(this.prescription.pId,result).subscribe(
          (presc)=>{
            this.prescription=presc;
            this.openSnackBar("You changed the status to: "+result, "OK")
           
          }
        )
        
      });
    }
    else if(this.prescription.pStatus=="Dismissed"){
      const dialogRef = this.dialog.open(ChangeStatusDismissedDialog, {
        width: '250px',
        data: this.prescription.pStatus,
      });
      dialogRef.afterClosed().subscribe(result => {
        console.log('The dialog was closed');
        this.prescriptionService.changeStatusForPrescription(this.prescription.pId,result).subscribe(
          (presc)=>{
            this.prescription=presc;
            this.openSnackBar("You changed the status to: "+result, "OK")
            
          }
        )
        
      });
    }
    

    
  }

 



}

@Component({
  selector: 'dialog-change-status-notStarted-dialog',
  templateUrl: 'changeStatusNotStartedDialog.html',
})
export class ChangeStatusNotStartedDialog{

  constructor(
    public dialogRef: MatDialogRef<ChangeStatusNotStartedDialog>,
    @Inject(MAT_DIALOG_DATA) public data: string) {}

  onNoClick(): void {
    console.log("e la no click");
    this.dialogRef.close();
  }

}


@Component({
  selector: 'dialog-change-status-ongoing-dialog',
  templateUrl: 'changeStatusOngoingDialog.html',
})
export class ChangeStatusOngoingDialog{

  constructor(
    public dialogRef: MatDialogRef<ChangeStatusOngoingDialog>,
    @Inject(MAT_DIALOG_DATA) public data: string) {}

  onNoClick(): void {
    console.log("e la no click");
    this.dialogRef.close();
  }

}
@Component({
  selector: 'dialog-change-status-dismissed-dialog',
  templateUrl: 'changeStatusDismissedDialog.html',
})
export class ChangeStatusDismissedDialog{

  constructor(
    public dialogRef: MatDialogRef<ChangeStatusDismissedDialog>,
    @Inject(MAT_DIALOG_DATA) public data: string) {}

  onNoClick(): void {
    console.log("e la no click");
    this.dialogRef.close();
  }

}


