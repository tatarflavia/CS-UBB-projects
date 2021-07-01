import { AfterViewInit, Component, ElementRef, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { Medication } from '@app/_models/medication';
import { MedList } from '@app/_models/medList';
import {Location} from '@angular/common';
import { Prescription } from '@app/_models/prescription';
import { User } from '@app/_models/user';
import { AuthenticationService } from '@app/_services/authentication.service';
import { MedicationService } from '@app/_services/medication.service';
import { MedListService } from '@app/_services/medList.service';
import { PrescriptionService } from '@app/_services/prescriptio.service';
import { ReviewService } from '@app/_services/review.service';
import { UserService } from '@app/_services/user.service';
import { first, switchMap } from 'rxjs/operators';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-add-review',
  templateUrl: './add-review.component.html',
  styleUrls: ['./add-review.component.css']
})
export class AddReviewComponent implements OnInit,AfterViewInit {
  loggedUser:User;
  addReviewForm:FormGroup;
  loading = false;
  submitted = false;
  error1 = '';
  prescription:Prescription;
  medListOfPrescription:Array<MedList>;
  medication:Medication;
  medListMember:MedList;
  medId:number;
  columnsToDisplay = ['mlMedicationName', 'mlHowOften'];
  userService:UserService;
  medications:Array<Medication>;

  inputFilterForm: FormGroup;
  error = '';

  constructor(private elementRef: ElementRef,private formBuilder: FormBuilder,
    private route: ActivatedRoute,
    private router: Router,
    private reviewService:ReviewService,
    private authenticationService:AuthenticationService,private prescriptionService:PrescriptionService,private medListService:MedListService,
    private medicationService:MedicationService,userServ:UserService,private location:Location,private _snackBar: MatSnackBar) {
    this.medication=null;
    this.prescription=null;
    this.medListMember=null;
    this.userService=userServ;
    this.loggedUser=this.authenticationService.userValue;
    this.addReviewForm=this.formBuilder.group({
      rMedicationName: ['', Validators.required],
      rMedicationId:['',Validators.required],
      rReactionObserved: ['', Validators.required],
      rOverallFeel:['',Validators.required],
      rMedQuantity: ['', Validators.required],
      rTakingMotive: ['', Validators.required],
      rMedStartDate: ['', Validators.required],
      rMedEndDate: ['', Validators.required]
    });

  
      
     }
     ngAfterViewInit(): void {
      this.elementRef.nativeElement.ownerDocument.body.style.backgroundColor = ' #e9f8fb';
    }

     getMedicationsFiltered(searchStr:string):void{
      this.medicationService.getAllMedicationsFiltered(searchStr).subscribe(
        (meds)=>{
          this.medications=meds.map(function (med){
            return med;
  
          })
        }
      )
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
      }
    )
  }





  ngOnInit(): void { 
    
    this.route.params.pipe(
        switchMap(
          (params:Params) => this.prescriptionService.getPrescriptionById(+params['pId'])
        )).subscribe(presc=>{
        this.prescription=presc;
        console.log(this.prescription.pDiagnosis);
        this.getMedListForPrescription();
        this.route.params.pipe(
          switchMap(
            (params:Params) => this.medicationService.getMedicationById(+params['mId'])
          )).subscribe(med=>{
              this.medication=med;
              this.medId=med.mId;
              console.log(this.medication.mName);
              this.medListService.getMedListMemberByPrescIdMedId(this.prescription.pId ,med.mId).subscribe((medListMemb)=>{this.medListMember=medListMemb;
                console.log(this.medListMember.mlHowOften);
                var today = new Date();
                var dd = String(today.getDate()).padStart(2, '0');
                var mm = String(today.getMonth() + 1).padStart(2, '0');
                var yyyy = String(today.getFullYear());
                var today2 = yyyy + '-' +mm + '-' + dd;
                console.log("a ajuns la primul set");
                this.addReviewForm.setValue({
                  rMedicationName:this.medication.mName,
                  rMedicationId:1,
                  rReactionObserved: '',
                  rOverallFeel:'',
                  rMedQuantity: this.medListMember.mlHowOften,
                  rTakingMotive:this.prescription.pDiagnosis,
                  rMedStartDate: this.prescription.pStartDate? this.prescription.pStartDate : today2, 
                  rMedEndDate: this.prescription.pEndDate ? this.prescription.pEndDate :today2, 
                })
              });
              
            })
      },(error=>{
          this.prescription=null;
          this.medListMember=null;
          this.route.params.pipe(
            switchMap(
              (params:Params) => this.medicationService.getMedicationById(+params['mId'])
            )).subscribe(med=>{
                this.medication=med;
                this.medId=med.mId;
                console.log(med.mName);
                  var today = new Date();
                  var dd = String(today.getDate()).padStart(2, '0');
                  var mm = String(today.getMonth() + 1).padStart(2, '0');
                  var yyyy = String(today.getFullYear());
                  var today2 = yyyy + '-' +mm + '-' + dd;
                  console.log("a ajuns la al doilea set");
                  this.addReviewForm.setValue({
                    rMedicationName: this.medication.mName,
                    rMedicationId:1,
                    rReactionObserved: '',
                    rOverallFeel:'',
                    rMedQuantity:'',
                    rTakingMotive: '',
                    rMedStartDate: today2, 
                    rMedEndDate: today2, 
                
                });
              },(error=>{
              this.medication=null;
              this.prescription=null;
              this.medListMember=null;
              var today = new Date();
              var dd = String(today.getDate()).padStart(2, '0');
              var mm = String(today.getMonth() + 1).padStart(2, '0');
              var yyyy = String(today.getFullYear());
              var today2 = yyyy + '-' +mm + '-' + dd;
              this.addReviewForm.setValue({
                rMedicationName: '',
                rMedicationId:'',
                rReactionObserved: '',
                rOverallFeel:'',
                rMedQuantity:'',
                rTakingMotive: '',
                rMedStartDate: today2, 
                rMedEndDate: today2, 
            
            });
              this.getMedicationsFiltered("");
              this.f.rMedicationName.valueChanges.subscribe((value)=>{
                this.error="";
                this.medicationService.getAllMedicationsFiltered(value).subscribe(
                  (meds)=>{
                    this.medications=meds.map(function (med){
                      return med;
            
                    })
                  },(error)=>{
                    this.error=error;
                    this.medications=new Array<Medication>();
                  }
                );});
                      }));
        }));
    
  }

  
  

  change(e){
    console.log(e.value)
    console.log(e.value.mName)
    this.medId=e.value.mId;
    this.addReviewForm.patchValue({
      rMedicationName: e.value.mName, 
  });
  }

  // convenience getter for easy access to form fields
  get f() { return this.addReviewForm.controls; }

  openSnackBar(message:string,action:string) {
    this._snackBar.open(message,action, {
      horizontalPosition: "center",
      verticalPosition: "top",
    });
  }

  onSubmit() {
    this.submitted = true;

    // stop here if form is invalid
    if (this.addReviewForm.invalid) {
        return;
    }

    this.loading = true;
    console.log(this.f.rMedStartDate.value,this.f.rOverallFeel.value);
    var prescId=this.prescription? this.prescription.pId : -100;
    this.reviewService.addReview(this.medId,this.authenticationService.userValue.uId,this.f.rReactionObserved.value,this.f.rOverallFeel.value,prescId,this.f.rMedQuantity.value,this.f.rTakingMotive.value,this.f.rMedStartDate.value,this.f.rMedEndDate.value)
        .pipe(first())
        .subscribe(
            data => {
                this.openSnackBar("The review was successfully added.","OK");
                this.visitLastPage();
            },
            error => {
                this.error1 = error;
                this.openSnackBar(error,"OK");
                this.loading = false;
            });
    
    
    
}

}
