import { AfterViewInit, Component, ElementRef, Input, OnChanges, OnInit, SimpleChanges } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { User } from '@app/_models/user';
import { AuthenticationService } from '@app/_services/authentication.service';
import { UserService } from '@app/_services/user.service';

@Component({
  selector: 'app-search-patient',
  templateUrl: './search-patient.component.html',
  styleUrls: ['./search-patient.component.css']
})
export class SearchPatientComponent implements OnInit,AfterViewInit {
  //searchText: string;
  patients:Array<User>;
  inputFilterForm: FormGroup;
  error = '';
  constructor(private elementRef: ElementRef, private formBuilder: FormBuilder,private userServ:UserService,private authenticationService:AuthenticationService,private router:Router) { 
  }
  ngAfterViewInit(): void {
    this.elementRef.nativeElement.ownerDocument.body.style.backgroundColor = '#d6e0f5';
  }
  

  ngOnInit(): void {
    this.getUsersFiltered("");
    //this.searchText="";
    this.inputFilterForm = this.formBuilder.group({
      searchText: [''],
    });
    this.f.searchText.valueChanges.subscribe((value)=>{
      this.error="";
      this.userServ.getAllFiltered(value).subscribe(
        (users)=>{
          this.patients=users.map(function (user){
            return user;

          })
    },(error)=>{
      this.error=error;
      this.patients=new Array<User>();
    })});

  }

  // convenience getter for easy access to form fields
  get f() { return this.inputFilterForm.controls; }

  

  getUsersFiltered(searchStr:string):void{
    this.userServ.getAllFiltered(searchStr).subscribe(
      (users)=>{
        this.patients=users.map(function (user){
          return user;

        })
      }
    )
  }

  


}
