import { Component, Inject } from "@angular/core";
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { MatDialogRef, MAT_DIALOG_DATA } from "@angular/material/dialog";
import { MatSnackBar } from "@angular/material/snack-bar";
import { Router } from "@angular/router";
import { MedList } from "@app/_models/medList";
import { Prescription } from "@app/_models/prescription";
import { Review } from "@app/_models/review";
import { User } from "@app/_models/user";
import { AuthenticationService } from "@app/_services/authentication.service";
import { DiseaseService } from "@app/_services/disease.service";
import { MedicationService } from "@app/_services/medication.service";
import { MedListService } from "@app/_services/medList.service";
import { PrescriptionService } from "@app/_services/prescriptio.service";
import { ReviewService } from "@app/_services/review.service";
import { UserService } from "@app/_services/user.service";
import { first } from "rxjs/operators";

@Component({
    selector: 'add-disease-dialog',
    templateUrl: 'add-disease.dialog.html',
  })
  export class AddDiseaseDialog {

    patient:User;
    addDiseaseForm:FormGroup;
    loading = false;
    submitted = false;
    error1 = '';
    constructor(
      public dialogRef: MatDialogRef<AddDiseaseDialog>,
      @Inject(MAT_DIALOG_DATA) public data: number,private authenticationService:AuthenticationService,private userServ:UserService,private _snackBar: MatSnackBar,private diseaseService:DiseaseService,private formBuilder: FormBuilder,private router:Router) {
        this.userServ.getUserById(data).subscribe(
            pati=>{this.patient=pati;}
        );
        this.addDiseaseForm=this.formBuilder.group({
            dName: ['', Validators.required],
            dType:['',Validators.required],
            
          });
        
      }

      


  
    onNoClick(): void {
      this.dialogRef.close();
    }

    
  // convenience getter for easy access to form fields
  get f() { return this.addDiseaseForm.controls; }
  

  openSnackBar(message:string,action:string) {
    this._snackBar.open(message,action, {
      horizontalPosition: "center",
      verticalPosition: "top",
    });
  }

  onSubmit() {
    this.submitted = true;

    // stop here if form is invalid
    if (this.addDiseaseForm.invalid) {
        return;
    }

    this.loading = true;
    this.diseaseService.addDisease(this.patient.uId,this.f.dName.value,this.f.dType.value)
        .pipe(first())
        .subscribe(
            data => {
                this.openSnackBar("The disease was successfully added.","OK");
                let varSnack=this._snackBar.open("You successfully added the disease for patient "+this.patient.uName,"OK",{horizontalPosition: "center",
                    verticalPosition: "top"});
                varSnack.afterDismissed().subscribe(()=>{this.dialogRef.close();window.location.reload();});
                
                
            },
            error => {
                this.error1 = error;
                this.openSnackBar(error,"OK");
                this.loading = false;
            });
}
  
  }
  