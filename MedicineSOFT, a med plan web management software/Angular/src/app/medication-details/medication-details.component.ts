import { AfterViewInit, Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { Medication } from '@app/_models/medication';
import { MedicationService } from '@app/_services/medication.service';
import { first, switchAll, switchMap } from 'rxjs/operators';
import {Location} from '@angular/common';
import { Disease } from '@app/_models/disease';
import { DiseaseService } from '@app/_services/disease.service';
import { AuthenticationService } from '@app/_services/authentication.service';
import { UserService } from '@app/_services/user.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { BehaviorSubject, forkJoin } from 'rxjs';
import { CdkVirtualScrollViewport } from '@angular/cdk/scrolling';

@Component({
  selector: 'app-medication-details',
  templateUrl: './medication-details.component.html',
  styleUrls: ['./medication-details.component.css']
})
export class MedicationDetailsComponent implements OnInit ,AfterViewInit{
  userDiseases:Array<Disease>;
  medication:Medication;
  personalisedDesc:String;
  personalisedDescSplit:Array<String>;
  loading=false;
  error="";
  itHasNotFound=false;
  strsObservable:BehaviorSubject<Array<String>>;
  newArr:Array<String>;
  constructor(private elementRef: ElementRef,private _snackBar: MatSnackBar,private userService:UserService,private authenticationService:AuthenticationService,private diseaseService:DiseaseService,private route:ActivatedRoute,private router:Router,private medicationService:MedicationService,private location:Location) { 
    
  }

  

  @ViewChild(CdkVirtualScrollViewport) viewport: CdkVirtualScrollViewport;

  trackByFn(index, item) {
    return index; // or item.id
  }

  ngOnInit(): void {
    this.route.params.pipe(
      switchMap(
        (params:Params) => this.medicationService.getMedicationById(+params['id'])
      )).subscribe(med=>{
      this.medication=med;
      this.getDiseasesForLoggedUser();
    });
    
  }

  openSnackBar(message:string,action:string) {
    this._snackBar.open(message,action, {
      horizontalPosition: "center",
      verticalPosition: "top",
    });
  }

  
  ngAfterViewInit(): void {
    this.elementRef.nativeElement.ownerDocument.body.style.backgroundColor = ' #e9f8fb';
  }


  getPersonalisedDesc():void{
    this.loading=true;
    this.userService.getPersonalisedDescForUser(this.authenticationService.userValue.uId,this.medication.mId).subscribe(
      (strs)=>{
        this.personalisedDesc=strs;
        this.personalisedDescSplit=this.personalisedDesc.split("\n\n\n\n\n\n\n\n");
        if(this.personalisedDescSplit[0]==="empty"){
          this.itHasNotFound=true;
          this.personalisedDescSplit.splice(0,1);
        }
        if(this.userDiseases.length===0){
          this.strsObservable = new BehaviorSubject(this.personalisedDescSplit);
          this.loading=false;
        }
        else{
            var arrayForFork=[];
          for(var i=0;i<this.userDiseases.length;i++){
            console.log()

            var str=this.userDiseases[i].dName.substring(0,this.userDiseases[i].dName.length);
            console.log(str);
            //now highlight for every string of the prospectus
            this.newArr=new Array<String>();
            for(var j=0;j<this.personalisedDescSplit.length;j++){
              this.newArr.push(this.personalisedDescSplit[j].replace(new RegExp(str, "gi"), match => {
                console.log("found match");
                return '<mark class=".text-danger" style="background-color: darkred;">' + match + '</mark>';
            })); 
            }
          }
          this.strsObservable = new BehaviorSubject(this.newArr);
          this.loading=false;
        }
        

        // console.log(this.personalisedDescSplit[0])
        // console.log(newArr[0]);

        // forkJoin(arrayForFork).pipe(first()).subscribe(
        //   (result=>{
        //     console.log("a ajuns");
        //     console.log(this.personalisedDescSplit[0])
        //     this.strsObservable = new BehaviorSubject(this.personalisedDescSplit);
        
        //     this.loading=false;
        //   })
        // )


        // //now add highlight for diseases
        // for(var i=0;i<this.userDiseases.length;i++){
        //   var str=this.userDiseases[i].dName.substring(0,this.userDiseases[i].dName.length-1);
        //   //now highlight for every string of the prospectus
        //   for(var j=0;j<this.personalisedDescSplit.length;j++){
        //      this.personalisedDesc[j].replace(new RegExp(str, "gi"), match => {
        //       return '<span class="highlightText">' + match + '</span>';
        //   });
        //   }
        // }
        // console.log(this.personalisedDescSplit[0])
        // this.strsObservable = new BehaviorSubject(this.personalisedDescSplit);
        
        // this.loading=false;


      },(error=>{
        this.openSnackBar(error,"OK");
        this.loading = false;
      })
    )
  }

  getDiseasesForLoggedUser():void{
    this.diseaseService.getDiseasesByUserId(this.authenticationService.userValue.uId).subscribe(
        (diseases)=>{
            this.userDiseases=diseases.map(function (disease){
                return disease;
            });
            this.getPersonalisedDesc();

        }
    )
}
  visitLastPage() {
    this.location.back();
  }

}
