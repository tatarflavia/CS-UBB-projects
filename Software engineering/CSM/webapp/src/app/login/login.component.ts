import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {UserService} from "../service/user-service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})

export class LoginComponent implements OnInit {

  errorMessage: string;
  formLogin: FormGroup;
  showRequiredMessage: boolean;

  constructor(private userService: UserService, private router: Router, private formBuilder: FormBuilder) {
    this.formLogin = this.formBuilder.group({
      usernameControl: ['', Validators.required ], passwordControl: ['', Validators.required ]
    });
  }


  ngOnInit(): void {
    this.errorMessage = "";
    this.showRequiredMessage = false;
    console.log("ngOnInit");
  }

  onSubmit() {
    if (this.formLogin.controls['usernameControl'].valid && this.formLogin.controls['passwordControl'].valid) {
      const username = this.formLogin.value["usernameControl"];
      const password = this.formLogin.value["passwordControl"];
      this.userService.loginUser({
        username, password
      }).subscribe(
        (user) => {
          this.userService.rememberUser(user);
          this.router.navigateByUrl("/all-conferences");
        },
        (error) => {
          this.errorMessage = "Your login is invalid. Your password or username is incorrect.";
        }
      );
    } else {
      this.showRequiredMessage = true;
    }
  }
}
