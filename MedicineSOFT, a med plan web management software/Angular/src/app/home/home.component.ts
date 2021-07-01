//gets all the users from the service and makes them available to the html page
import { AfterViewInit, Component, ElementRef } from '@angular/core';
import { first } from 'rxjs/operators';

import { User } from '@app/_models/user';
import { AuthenticationService } from '@app/_services/authentication.service';
import { Disease } from '@app/_models/disease';
import { DiseaseService } from '@app/_services/disease.service';
import { UserService } from '@app/_services/user.service';

@Component({ templateUrl: 'home.component.html' ,styleUrls: ['./home.component.css']})
export class HomeComponent implements AfterViewInit {
    loading = false;
    user: User;
    userDiseases:Array<Disease>;

    constructor(private elementRef: ElementRef,private userService: UserService,
        private authenticationService: AuthenticationService,
        private diseaseService:DiseaseService) { }
        ngAfterViewInit(){
            this.elementRef.nativeElement.ownerDocument.body.style.backgroundColor = '#d6e0f5';
         }

    ngOnInit() {
        this.loading = true;
        this.userService.getUserById(this.authenticationService.userValue.uId).pipe(first()).subscribe(user => {
            this.loading = false;
            this.user = user;
            this.getDiseasesForLoggedUser();
        });
    }
    getDiseasesForLoggedUser():void{
        this.diseaseService.getDiseasesByUserId(this.user.uId).subscribe(
            (diseases)=>{
                this.userDiseases=diseases.map(function (disease){
                    return disease;
                })
            }
        )
    }
}