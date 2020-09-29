import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Router} from "@angular/router";
import {UserService} from "../service/user-service";

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {

  errorMessage: string;
  formRegister: FormGroup;
  showRequiredMessage: boolean;

  constructor(private userService: UserService, private router: Router, private formBuilder: FormBuilder) {
    this.formRegister = this.formBuilder.group({
      usernameControl: ['', Validators.required ], passwordControl: ['', Validators.required ]
    });
  }


  ngOnInit(): void {
    this.errorMessage = "";
    this.showRequiredMessage = false;
    console.log("ngOnInit");
  }

  onSubmit() {
    if (this.formRegister.controls['usernameControl'].valid && this.formRegister.controls['passwordControl'].valid) {
      const username = this.formRegister.value["usernameControl"];
      const password = this.formRegister.value["passwordControl"];
      this.userService.registerUser({
        username, password
      }).subscribe(
        (user) => {
          console.log("user " + user);
          this.userService.rememberUser(user);
          this.router.navigateByUrl("/all-conferences");
        },
        (error) => {
          this.errorMessage = "Your registration is invalid. The username already exists.";
        });
    } else {
      this.showRequiredMessage = true;
    }
  }
}
