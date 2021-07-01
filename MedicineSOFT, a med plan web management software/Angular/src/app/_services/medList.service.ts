import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from '@environments/environment';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { MedList } from '@app/_models/medList';

@Injectable({ providedIn: 'root' })
export class MedListService {
    constructor(private http: HttpClient) { }

    getMedListForPrescriptionId(pId:number):Observable<Array<MedList>>{
        return this.http.post<Array<MedList>>(`${environment.apiUrl}/prescriptions/medList`,{pId}).pipe(map(medLists => {
            
            return medLists;}));
    }

    

    getMedListMemberByPrescIdMedId(mlPrescriptionId:number,mlMedicationId:number):Observable<MedList>{
        console.log(mlPrescriptionId,mlMedicationId);
        return this.http.post<MedList>(`${environment.apiUrl}/prescriptions/medListMember`,{mlPrescriptionId,mlMedicationId}).pipe(map(result=>{
            console.log(JSON.stringify(result));
            console.log(result["mlMedicationName"]);
            console.log(result.mlMedicationName);
            return result;}));
    }

    addMedListMemberToPresc(mlPrescriptionId:number,mlMedicationId:number,mlMedicationName:string,mlHowOften:string):any{
        console.log(`${environment.apiUrl}/prescriptions/addMed`)
        console.log(mlPrescriptionId,mlMedicationId,mlMedicationName,mlHowOften);
        return this.http.post<any>(`${environment.apiUrl}/prescriptions/addMed`,{mlPrescriptionId,mlMedicationId,mlMedicationName,mlHowOften}).pipe(map(result=>{
            
            console.log("res la serv:"+result);
            return result;
        }));
    }


    }