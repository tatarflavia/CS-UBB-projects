//used for logging on and out of the app
//it notifies other components about it
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { BehaviorSubject, Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { environment } from '@environments/environment';
import { User } from '@app/_models/user';

@Injectable({ providedIn: 'root' })
export class AuthenticationService {
    private userSubject: BehaviorSubject<User>;
    public user: Observable<User>;

    constructor(
        private router: Router,
        private http: HttpClient
    ) {
        this.userSubject = new BehaviorSubject<User>(JSON.parse(localStorage.getItem('user')));
        this.user = this.userSubject.asObservable();
    }

    public get userValue(): User {
        return this.userSubject.value;
    }

    login(uDocNumber: string, uPassword: string) {
        console.log(uDocNumber,uPassword);
        console.log(`${environment.apiUrl}/users/login`);
        return this.http.post<any>(`${environment.apiUrl}/users/login`, { uDocNumber, uPassword })
            .pipe(map(user => {
                // store user details and basic auth credentials in local storage to keep user logged in between page refreshes
                console.log("a intrat la localStorage handle");
                user.authdata = window.btoa(uDocNumber + ':' + uPassword);
                localStorage.setItem('user', JSON.stringify(user));
                this.userSubject.next(user);
                console.log(user);
                return user;
            }));
    }

    register(uName: string,uEmail:string,uBirthday:string,uInsuredCode:string,
        uDocNumber: string,uExpirationDate:string,uPassword: string) {
        console.log(uDocNumber,uPassword,uName,uInsuredCode,uEmail,uExpirationDate);
        return this.http.post<any>(`${environment.apiUrl}/users/register`, { uName,uEmail,uBirthday,uInsuredCode,uDocNumber,uExpirationDate,uPassword})
            .pipe(map(user => {
                // store user details and basic auth credentials in local storage to keep user logged in between page refreshes
                console.log("a intrat la localStorage handle");
                user.authdata = window.btoa(uDocNumber + ':' + uPassword);
                localStorage.setItem('user', JSON.stringify(user));
                this.userSubject.next(user);
                console.log(user);
                return user;
            }));
    }

    logout() {
        // remove user from local storage to log user out
        localStorage.removeItem('user');
        this.userSubject.next(null);
        this.router.navigate(['/login']);
    }
}