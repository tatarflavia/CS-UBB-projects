import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { environment } from '@environments/environment';
import { Review } from '@app/_models/review';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

@Injectable({ providedIn: 'root' })
export class ReviewService {
    constructor(private http: HttpClient) { }

    getReviewsByUserId(userId:number):Observable<Array<Review>>{
        console.log("a ajuns"+`${environment.apiUrl}/reviews/byUser/${userId}`)
        return this.http.get<Array<Review>>(`${environment.apiUrl}/reviews/byUser/${userId}`);
    }

    getReviewById(rId:number):Observable<Review>{
        return this.http.get<Review>(`${environment.apiUrl}/reviews/${rId}`);

    }

    checkIfReviewExists(rPrescriptionId:number,rMedicationId:number,rPatientId:number):Observable<Boolean>{
        console.log(rPrescriptionId,rMedicationId,rPatientId)
        return this.http.post<Boolean>(`${environment.apiUrl}/reviews/check`, { rPrescriptionId,rMedicationId,rPatientId}).pipe(map(result=>{return result;}));
    }

    getReviewByPrescIdMedIdPatientId(rPrescriptionId:number,rMedicationId:number,rPatientId:number):Observable<Review>{
        console.log(rPrescriptionId,rMedicationId,rPatientId)
        return this.http.post<Review>(`${environment.apiUrl}/reviews/byIds`, { rPrescriptionId,rMedicationId,rPatientId}).pipe(map(result=>{return result;}));
    }

    addReview(rMedicationId:number,rPatientId:number,rReactionObserved:string,rOverallFeel:number,rPrescriptionId:number,rMedQuantity:string,rTakingMotive:string,
        rMedStartDate:string,rMedEndDate:string) :any{
        console.log(rMedicationId,rReactionObserved,rOverallFeel);
        return this.http.post<any>(`${environment.apiUrl}/reviews/add`, { rMedicationId,rPatientId,rReactionObserved,rOverallFeel,rPrescriptionId,rMedQuantity,rTakingMotive,rMedStartDate,rMedEndDate})
            .pipe(map(review => {
                // store user details and basic auth credentials in local storage to keep user logged in between page refreshes
                return review;
            }));
    }




    
}