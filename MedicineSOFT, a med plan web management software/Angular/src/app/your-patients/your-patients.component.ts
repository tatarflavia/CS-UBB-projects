import { AfterViewInit, Component, ElementRef, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Organisation } from '@app/_models/organisation';
import { OrganisationBelongings } from '@app/_models/organisationBelongings';
import { User } from '@app/_models/user';
import { AuthenticationService } from '@app/_services/authentication.service';
import { OrganisationService } from '@app/_services/organisation.service';
import { OrganisationBelongingService } from '@app/_services/organisationBelonging.service';
import { UserService } from '@app/_services/user.service';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-your-patients',
  templateUrl: './your-patients.component.html',
  styleUrls: ['./your-patients.component.css']
})
export class YourPatientsComponent implements OnInit,AfterViewInit {
  

  patientOrganisationsDict:Map<Organisation,User>

  orgBelongByDoctor:Array<OrganisationBelongings>;
  orgsByDoctor:Array<Organisation>;
  
  constructor(private elementRef: ElementRef,private organisationBelongingService:OrganisationBelongingService,private organisationService:OrganisationService,private userService:UserService,private authenticationService:AuthenticationService,private router:Router) {
    
   }
  ngAfterViewInit(): void {
    this.elementRef.nativeElement.ownerDocument.body.style.backgroundColor = '#d6e0f5';
  }

   

  ngOnInit():void{
    this.getOrganisationsByDoctorId();
    this.getOrgsByDoctorId();
    //this.populateDict();
  }

  getOrganisationsByDoctorId(){
    this.organisationService.getAllOrganisationsByDoctorId(this.authenticationService.userValue.uId).subscribe(
      (orgs)=>{
        this.orgsByDoctor=orgs.map(function (org){
          return org;
        })

      }
    )
  }




  getOrgsByDoctorId(){
     this.organisationBelongingService.getAllOrganisationBelongingsByDoctorId(this.authenticationService.userValue.uId).subscribe(
      (orgs)=>{
        this.patientOrganisationsDict=new Map<Organisation,User>();
        this.orgBelongByDoctor=orgs.map(function (org){
          return org;
        })
        for(let org of this.orgBelongByDoctor){
          this.organisationService.getOrganisationById(org.bOrganisationId).subscribe((key)=>{
            this.userService.getUserById(org.bPatientId).subscribe((value)=>{
              this.patientOrganisationsDict.set(key,value);
            })
          })
          
        }
      }
    );
    
   }

   populateDict():void{
    this.patientOrganisationsDict=new Map<Organisation,User>();
    this.orgBelongByDoctor.forEach(function (orgBel){
      this.patientOrganisationsDict.set(this.organisationService.getOrganisationById(orgBel.bOrganisationId),
                                        this.userService.getUserById(orgBel.bPatientId)
      );
    });
   }



  visitPage(uId:number):void {
    //visit page for details of medication
    this.router.navigate(["/patient-details",uId])
  }

}
