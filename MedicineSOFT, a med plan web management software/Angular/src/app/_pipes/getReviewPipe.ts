import { Pipe, PipeTransform } from '@angular/core';
import { Review } from '@app/_models/review';
import { ReviewService } from '@app/_services/review.service';
import { Observable } from 'rxjs';
@Pipe({
  name:'getReviewPipe',
})
export class GetReviewPipe implements PipeTransform {
    constructor(){

    }
  transform(rPrescriptionId:number,rMedicationId:number,rPatientId:number,reviewService:ReviewService): Observable<Review>{
    return reviewService.getReviewByPrescIdMedIdPatientId(rPrescriptionId,rMedicationId,rPatientId);
  }
}