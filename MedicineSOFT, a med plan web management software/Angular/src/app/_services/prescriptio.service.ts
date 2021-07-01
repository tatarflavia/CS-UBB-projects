import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from '@environments/environment';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { Prescription } from '@app/_models/prescription';
import { MedList } from '@app/_models/medList';

@Injectable({ providedIn: 'root' })
export class PrescriptionService {
    constructor(private http: HttpClient) { }

    getAllPrescriptionsForPacientId(pPatientId:number,pDoctorId:number):Observable<Array<Prescription>>{
        console.log("a ajuns"+`${environment.apiUrl}/prescriptions/forPacient`+pPatientId,pDoctorId)
        return this.http.post<Array<Prescription>>(`${environment.apiUrl}/prescriptions/forPatient`,{pPatientId,pDoctorId}).pipe(map(prescriptions => {
            
            return prescriptions;}));
        
    }

    

    getPrescriptionById(pId:number):Observable<Prescription>{
        return this.http.get<Prescription>(`${environment.apiUrl}/prescriptions/${pId}`);

    }

    

    

    getPrescriptionsByTypeByUserId(pType:string,uId:number):Observable<Array<Prescription>>{
        return this.http.post<Array<Prescription>>(`${environment.apiUrl}/prescriptions`,{pType,uId}).pipe(map(prescriptions => {
            
            return prescriptions;}));
    }

    changeStatusForPrescription(pId:number,pStatus:string):Observable<Prescription>{
        return this.http.post<Prescription>(`${environment.apiUrl}/prescriptions/changeStatus`,{pId,pStatus}).pipe(map(prescription => {
            
            return prescription;}));
    }

    addPrescription(pPatientId:number,pDoctorId:number,pDiagnosis:string):any{
        return this.http.post<any>(`${environment.apiUrl}/prescriptions/add`,{pPatientId,pDoctorId,pDiagnosis}).pipe(map(prescription => {
            return prescription;
        }));
    }
}