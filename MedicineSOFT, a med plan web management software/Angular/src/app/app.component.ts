//root comp of the app
import { Component } from '@angular/core';
import { Router } from '@angular/router';

import { AuthenticationService } from './_services/authentication.service';
import { User } from './_models/user';

@Component({ selector: 'app', templateUrl: 'app.component.html' })
export class AppComponent {
    //user is observable
    user: User;

    constructor(
        private router: Router,
        private authenticationService: AuthenticationService
    ) {
        //it subscribes to user from authentication serv to see the log in status to hide the nav bar accordingly
        this.authenticationService.user.subscribe(x => this.user = x);
    }

    logout() {
        this.authenticationService.logout();
    }
}