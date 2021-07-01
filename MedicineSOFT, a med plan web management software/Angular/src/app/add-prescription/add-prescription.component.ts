import { AfterViewInit, Component, ElementRef, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { Prescription } from '@app/_models/prescription';
import { User } from '@app/_models/user';
import { PrescriptionService } from '@app/_services/prescriptio.service';
import { UserService } from '@app/_services/user.service';
import { first, switchMap } from 'rxjs/operators';
import {Location} from '@angular/common';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MedList } from '@app/_models/medList';
import { Medication } from '@app/_models/medication';
import { MedicationService } from '@app/_services/medication.service';
import { AuthenticationService } from '@app/_services/authentication.service';
import { MedListService } from '@app/_services/medList.service';
import { forkJoin, Observable } from 'rxjs';

@Component({
  selector: 'app-add-prescription',
  templateUrl: './add-prescription.component.html',
  styleUrls: ['./add-prescription.component.css']
})
export class AddPrescriptionComponent implements OnInit,AfterViewInit {
  patient:User;
  addPrescriptionForm:FormGroup;
  addMedForm:FormGroup;
  prescription:Prescription;
  medListForPrescription:Array<MedList>;
  loadingPresc = false;
  submittedPresc = false;
  loading = false;
  submitted = false;
  loadingAll = false;
  columnsToDisplay = ['mlMedicationName', 'mlHowOften','eraseVal'];
  error = '';
  errorAddPrescForm='';
  medId:number;
  medications:Array<Medication>;
  constructor(private elementRef: ElementRef,private medListService:MedListService,private authenticationService:AuthenticationService,private medicationService:MedicationService,private _snackBar: MatSnackBar,private location:Location,private formBuilder: FormBuilder,private route:ActivatedRoute,private router:Router,private prescriptionService:PrescriptionService,private userService:UserService) { 
    this.medId=null;
    this.medListForPrescription=new Array<MedList>();
    this.addPrescriptionForm=this.formBuilder.group({
      pDiagnosis: ['', Validators.required],
    });
    this.addMedForm=this.formBuilder.group({
      mlMedicationName:['', Validators.required],
      mlMedicationId:['', Validators.required],
      mlHowOften:['', Validators.required]
    })

  }

  ngAfterViewInit(): void {
    this.elementRef.nativeElement.ownerDocument.body.style.backgroundColor = ' #e9f8fb';
  }



  ngOnInit(): void {
    this.route.params.pipe(
      switchMap(
        (params:Params) => this.userService.getUserById(+params['patientId'])
      )).subscribe(user=>{
      this.patient=user;
      this.getMedicationsFiltered("");
      this.fMed.mlMedicationName.valueChanges.subscribe((value)=>{
      this.errorAddPrescForm="";
      this.medicationService.getAllMedicationsFiltered(value).subscribe(
          (meds)=>{
                this.medications=meds.map(function (med){
                   return med;
                    })
                  },(error)=>{
                    this.errorAddPrescForm=error;
                    this.medications=new Array<Medication>();
                  }
                );});
    });
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

  

  
  change(e:Medication){
    console.log(e.mName)
    this.medId=e.mId;
    this.addMedForm.patchValue({
      mlMedicationName: e.mName, 
      mlMedicationId:e.mId,
  });
  }



  visitLastPage() {
    this.location.back();
  }

  
  // convenience getter for easy access to form fields
  get fPresc() { return this.addPrescriptionForm.controls; }
  // convenience getter for easy access to form fields
  get fMed() { return this.addMedForm.controls; }

  openSnackBar(message:string,action:string) {
    this._snackBar.open(message,action, {
      horizontalPosition: "center",
      verticalPosition: "top",
    });
  }

  eraseElement(medListMember:MedList){
    this.medListForPrescription.forEach((element,index)=>{
      if(element.mlMedicationId==medListMember.mlMedicationId && element.mlMedicationName==medListMember.mlMedicationName && element.mlHowOften==medListMember.mlHowOften){
        this.medListForPrescription.splice(index,1);
      } 
   });
  }

  onSubmitPresc(){}


  onSubmitMed(){
    this.submitted=true;
    console.log(this.addMedForm.invalid)
    if(this.addMedForm.invalid){
      return;
    }
    this.loading=true;
    let medListMemb=new MedList(0,0,this.medId,this.fMed.mlMedicationName.value,this.fMed.mlHowOften.value);
    this.medListForPrescription.push(medListMemb);
    this.openSnackBar("The new med was added","OK");
    this.addMedForm.setValue({
      mlMedicationName:'',
      mlMedicationId:'',
      mlHowOften:''
    })
    this.submitted=false;
    this.loading=false;
  }

  trackByFn(index, item) {
    return index; // or item.id
  }

  
  onSubmitAll() {

    // stop here if form is invalid
    if (this.addPrescriptionForm.invalid) {
      this.openSnackBar("You have not added any diagnosis","OK");
        return;
    }
    if(this.medListForPrescription.length===0){
      this.openSnackBar("You have not added any meds","OK");
        return;
    }

    this.loadingAll = true;
    this.prescriptionService.addPrescription(this.patient.uId,this.authenticationService.userValue.uId,this.fPresc.pDiagnosis.value).pipe(
      first()).subscribe(
        data=>{
          console.log(data);
          console.log(data["pId"]);
          console.log(this.medListForPrescription.length);
          let pId=parseInt(data["pId"]);
          //now add the meds
          try {
              var arrayForFork=[];
              for(var i=0;i<this.medListForPrescription.length;i++){
                let medListMemb=this.medListForPrescription[i];  
                arrayForFork.push(this.medListService.addMedListMemberToPresc(pId,medListMemb.mlMedicationId,medListMemb.mlMedicationName,medListMemb.mlHowOften));
              }
              forkJoin(arrayForFork).pipe(first()).subscribe(
                (result=>{
                  console.log("a ajuns");
                  let varSnack=this._snackBar.open("The prescription was successfully added.","OK",{horizontalPosition: "center",
                      verticalPosition: "top"});
                  varSnack.afterDismissed().subscribe(()=>this.visitLastPage());
                  
                }
              ));


          } catch (error) {
            this.openSnackBar("There was an error while adding the medications","OK");
            return;
          }
         
        },(error=>{
          this.openSnackBar(error,"OK");
          this.loadingAll = false;
        })
      )



    
    
}


  

}
