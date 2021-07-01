import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { first } from 'rxjs/operators';
import { AuthenticationService } from '@app/_services/authentication.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  registerForm: FormGroup;
  loading = false;
  submitted = false;
  returnUrl: string;
  error = '';

  constructor( private formBuilder: FormBuilder,
    private route: ActivatedRoute,
    private router: Router,
    private authenticationService: AuthenticationService
    ) { 
      // redirect to home if already logged in
      if (this.authenticationService.userValue) { 
        this.router.navigate(['/']);
    }
    }

    

 
  

  ngOnInit() {
    this.registerForm = this.formBuilder.group({
      uName: ['', Validators.required],
      uEmail: ['', Validators.required],
      uBirthday:['',Validators.required],
      uInsuredCode: ['', Validators.required],
      uDocNumber: ['', Validators.required],
      uExpirationDate: ['', Validators.required],
      uPassword: ['', Validators.required]
  });

  // get return url from route parameters or default to '/'
  this.returnUrl = this.route.snapshot.queryParams['returnUrl'] || '/';
  }

  // convenience getter for easy access to form fields
  get f() { return this.registerForm.controls; }

  onSubmit() {
    this.submitted = true;

    // stop here if form is invalid
    if (this.registerForm.invalid) {
        return;
    }

    this.loading = true;
    console.log(this.f.uDocNumber.value,this.f.uInsuredCode.value,this.f.uExpirationDate.value);
    this.authenticationService.register(this.f.uName.value,this.f.uEmail.value,this.f.uBirthday.value,this.f.uInsuredCode.value,this.f.uDocNumber.value,this.f.uExpirationDate.value,this.f.uPassword.value)
        .pipe(first())
        .subscribe(
            data => {
                this.router.navigate([this.returnUrl]);
            },
            error => {
                this.error = error;
                this.loading = false;
            });
    
    
    
}

}
