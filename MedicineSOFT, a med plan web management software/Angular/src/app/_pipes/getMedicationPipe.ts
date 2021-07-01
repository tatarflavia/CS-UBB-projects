import { Pipe, PipeTransform } from '@angular/core';
import { Medication } from '@app/_models/medication';
import { MedicationService } from '@app/_services/medication.service';
import { Observable } from 'rxjs';
@Pipe({
  name:'getMedicationPipe',
})
export class GetMedicationPipe implements PipeTransform {
    constructor(){

    }
  transform(rMedicationId: number,medicationService:MedicationService): Observable<Medication>{
    return medicationService.getMedicationById(rMedicationId);
  }
}