import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { environment } from '@environments/environment';
import { Review } from '@app/_models/review';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { User } from '@app/_models/user';
import { OrganisationBelongings } from '@app/_models/organisationBelongings';
import { Organisation } from '@app/_models/organisation';

@Injectable({ providedIn: 'root' })
export class OrganisationService {
    constructor(private http: HttpClient) { }

    getAllOrganisationsByDoctorId(bDoctorId:number):Observable<Array<Organisation>>{
        console.log("a ajuns"+`${environment.apiUrl}/orgs/bsForDoctor`)
        return this.http.post<Array<Organisation>>(`${environment.apiUrl}/orgs/osForDoctor`, { bDoctorId})
            .pipe(map(orgs => {
            
                return orgs;
            }));

    }

    getOrganisationById(oId:number):Observable<Organisation>{
        return this.http.get<Organisation>(`${environment.apiUrl}/organisations/${oId}`);

    }

    getOrganisationsByDoctorIdNotPatientI(bDoctorId:number,bPatientId:number):Observable<Array<Organisation>>{
        console.log("a ajuns"+`${environment.apiUrl}/orgs/seeForPatient`)
        return this.http.post<Array<Organisation>>(`${environment.apiUrl}/orgs/seeForPatient`,{bDoctorId,bPatientId}).pipe(
            map(organisations=>{return organisations;})
        )
    }

    

}