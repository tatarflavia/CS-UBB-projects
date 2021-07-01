import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {Observable} from "rxjs";
import { environment } from '@environments/environment';
import { Disease } from '@app/_models/disease';

@Injectable({ providedIn: 'root' })
export class DiseaseService {
    constructor(private http: HttpClient) { }

    getDiseasesByUserId(userId:number):Observable<Array<Disease>>{
        console.log(`${environment.apiUrl}/diseases/${userId}`);
        return this.http.get<Array<Disease>>(`${environment.apiUrl}/diseases/${userId}`);
    }

    addDisease(dPatientId:number,dName:string,dType:string):any{
        return this.http.post<any>(`${environment.apiUrl}/diseases/add`,{dPatientId,dName,dType});
    }

    
}