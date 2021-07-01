import { AfterViewInit, Component, ElementRef, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { User } from '@app/_models/user';
import { AuthenticationService } from '@app/_services/authentication.service';
import { UserService } from '@app/_services/user.service';

@Component({
  selector: 'app-add-doctor',
  templateUrl: './add-doctor.component.html',
  styleUrls: ['./add-doctor.component.css']
})
export class AddDoctorComponent implements OnInit,AfterViewInit {
  doctors:Array<User>;
  inputFilterForm: FormGroup;
  error = '';
  error2="";
  selectedUser:User;
  loading = false;
  constructor(private elementRef: ElementRef,private _snackBar: MatSnackBar,private formBuilder: FormBuilder,private userServ:UserService,private authenticationService:AuthenticationService,private router:Router) { 
      this.selectedUser=null;
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
          this.doctors=users.map(function (user){
            return user;

          })
    },(error)=>{
      this.error=error;
      this.doctors=new Array<User>();
    })});
  }

  ngAfterViewInit(): void {
    this.elementRef.nativeElement.ownerDocument.body.style.backgroundColor = '#d6e0f5';
  }

  
  // convenience getter for easy access to form fields
  get f() { return this.inputFilterForm.controls; }

  

  getUsersFiltered(searchStr:string):void{
    this.userServ.getAllFiltered(searchStr).subscribe(
      (users)=>{
        this.doctors=users.map(function (user){
          return user;

        })
      }
    )
  }

  change(user:User){
    console.log(user.uName)
    this.selectedUser=user;
    this.error2="";
  }

  openSnackBar(message:string,action:string) {
    this._snackBar.open(message,action, {
      horizontalPosition: "center",
      verticalPosition: "top",
    });
  }

  onSubmitDoctor(){
    if(!this.selectedUser){
      this.openSnackBar("You have not chosen any doctor","OK");
      return;
    }
    this.loading=true;
    this.userServ.addDoctorByDocNumber(this.selectedUser.uDocNumber).subscribe(
      (doctor)=>{
        this.loading=false;
        let varSnack=this._snackBar.open("You successfully added the doctor "+this.selectedUser.uName+" \n with the document number "+this.selectedUser.uDocNumber+" \n  the insured code "+this.selectedUser.uInsuredCode+"\n and the email "+this.selectedUser.uEmail+" in the system.","OK",{horizontalPosition: "center",
        verticalPosition: "top"});
        varSnack.afterDismissed().subscribe(()=>window.location.reload());
      },(error)=>{
        this.openSnackBar("Unsuccessfull. "+error,"OK");
        this.error2=error;
        this.loading=false;

      }
    )

  }




}
