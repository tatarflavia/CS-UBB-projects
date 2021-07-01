import { AfterViewInit, Component, ElementRef, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { Medication } from '@app/_models/medication';
import { AuthenticationService } from '@app/_services/authentication.service';
import { MedicationService } from '@app/_services/medication.service';

@Component({
  selector: 'app-medications',
  templateUrl: './medications.component.html',
  styleUrls: ['./medications.component.css']
})
export class MedicationsComponent implements OnInit,AfterViewInit {
  medications:Array<Medication>;

  inputFilterForm: FormGroup;
  error = '';
  constructor(private elementRef: ElementRef, private formBuilder: FormBuilder,private medicationService:MedicationService,private authenticationService:AuthenticationService,private router:Router) { 
  }

  ngOnInit(): void {
    this.getMedicationsFiltered("");
    //this.searchText="";
    this.inputFilterForm = this.formBuilder.group({
      searchText: [''],
    });
    this.f.searchText.valueChanges.subscribe((value)=>{
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
     

  }

  // convenience getter for easy access to form fields
  get f() { return this.inputFilterForm.controls; }

  

  getMedicationsFiltered(searchStr:string):void{
    this.medicationService.getAllMedicationsFiltered(searchStr).subscribe(
      (meds)=>{
        this.medications=meds.map(function (med){
          return med;

        })
      }
    )
  }

  

  ngAfterViewInit(): void {
    this.elementRef.nativeElement.ownerDocument.body.style.backgroundColor = '#d6e0f5';
  }

  

  visitPage(mId):void {
    //visit page for details of medication
    this.router.navigate(["/medication-details",mId])
  }


}
