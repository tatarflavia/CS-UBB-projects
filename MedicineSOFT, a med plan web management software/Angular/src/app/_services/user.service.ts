import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { environment } from '@environments/environment';
import { User } from '@app/_models/user';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

@Injectable({ providedIn: 'root' })
export class UserService {
    constructor(private http: HttpClient) { }

    getAll() :Observable<Array<User>>{
        console.log(`${environment.apiUrl}/users`);
        return this.http.get<Array<User>>(`${environment.apiUrl}/users`);
    }

    getAllFiltered(searchStr:string):Observable<Array<User>>{
        console.log(`${environment.apiUrl}/users`);
        return this.http.get<Array<User>>(`${environment.apiUrl}/users?search=${searchStr}`);
    }

    getUserById(uId:number):Observable<User>{
        console.log(`${environment.apiUrl}/users/${uId}`);
        return this.http.get<User>(`${environment.apiUrl}/users/${uId}`);

    }

    getPatientsByDoctorId(bDoctorId:number):Observable<Array<User>>{
        console.log("a ajuns"+`${environment.apiUrl}/orgs/seePatients`)
        return this.http.post<Array<User>>(`${environment.apiUrl}/orgs/seePatients`, { bDoctorId})
            .pipe(map(patients => {
            
                return patients;
            }));

    }

    addDoctorByDocNumber(uDocNumber:string):Observable<User>{
        console.log(uDocNumber);
        return this.http.post<User>(`${environment.apiUrl}/users/addDoctor`, { uDocNumber}).pipe(map(
            (user=>{return user;})
        ));

    }

    getPersonalisedDescForUser(uId:number,mId:number):Observable<String>{
        console.log(uId,mId);
        return this.http.post<String>(`${environment.apiUrl}/users/desc`, { uId,mId}).pipe(map(
            (str=>{return str;})));

    }

    

    


}