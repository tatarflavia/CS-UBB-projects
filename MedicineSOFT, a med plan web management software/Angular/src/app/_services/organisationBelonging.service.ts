import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { environment } from '@environments/environment';
import { Review } from '@app/_models/review';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { User } from '@app/_models/user';
import { OrganisationBelongings } from '@app/_models/organisationBelongings';

@Injectable({ providedIn: 'root' })
export class OrganisationBelongingService {
    constructor(private http: HttpClient) { }

    getAllOrganisationBelongingsByDoctorId(bDoctorId:number):Observable<Array<OrganisationBelongings>>{
        console.log("a ajuns"+`${environment.apiUrl}/orgs/bsForDoctor`)
        return this.http.post<Array<OrganisationBelongings>>(`${environment.apiUrl}/orgs/bsForDoctor`, { bDoctorId})
            .pipe(map(orgs => {
            
                return orgs;
            }));

    }

    getAllOrganisationBelongingsByDoctorIdPatientId(bDoctorId:number,bPatientId:number):Observable<Array<OrganisationBelongings>>{
        console.log("a ajuns"+`${environment.apiUrl}/orgs/bsForDoctorPatient`)
        return this.http.post<Array<OrganisationBelongings>>(`${environment.apiUrl}/orgs/bsForDoctorPatient`, { bDoctorId,bPatientId})
            .pipe(map(orgs => {
            
                return orgs;
            }));
    }

    savePatientToOrganisation(bDoctorId:number,bOrganisationId:number,bPatientId:number):any{
        return this.http.post<any>(`${environment.apiUrl}/orgs/add`, { bDoctorId,bOrganisationId,bPatientId}).pipe(
            map(orgBel=>{return orgBel;})
        )
    }

    




    
}