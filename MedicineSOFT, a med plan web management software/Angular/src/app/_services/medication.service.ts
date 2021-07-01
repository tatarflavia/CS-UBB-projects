import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {Observable} from "rxjs";
import { environment } from '@environments/environment';
import { Medication } from '@app/_models/medication';

@Injectable({ providedIn: 'root' })
export class MedicationService {
    constructor(private http: HttpClient) { }

    

    getMedicationById(mId:number):Observable<Medication>{
        return this.http.get<Medication>(`${environment.apiUrl}/medications/${mId}`);

    }

    getAllMedications(): Observable<Array<Medication>> {
        return this.http.get<Array<Medication>>(`${environment.apiUrl}/medications`);
      }

    getAllMedicationsFiltered(searchStr:string):Observable<Array<Medication>>{
        console.log(`${environment.apiUrl}/medications`);
        return this.http.get<Array<Medication>>(`${environment.apiUrl}/medications?search=${searchStr}`);
    }
}