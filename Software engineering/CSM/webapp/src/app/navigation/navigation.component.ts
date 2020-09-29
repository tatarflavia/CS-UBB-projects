import { Component, OnInit } from '@angular/core';
import {UserService} from "../service/user-service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-navigation',
  templateUrl: './navigation.component.html',
  styleUrls: ['./navigation.component.css']
})
export class NavigationComponent implements OnInit {

  constructor(private userService: UserService, private router: Router) { }

  ngOnInit(): void {
  }

  logOut(): void {
    this.userService.forgetUser();
    this.router.navigateByUrl("");
  }

}
