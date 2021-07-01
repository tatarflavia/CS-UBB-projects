import { Pipe, PipeTransform } from '@angular/core';
import { User } from '@app/_models/user';
import { UserService } from '@app/_services/user.service';
import { Observable } from 'rxjs';
@Pipe({
  name:'getDoctorPipe',
})
export class GetDoctorPipe implements PipeTransform {
    constructor(){

    }
  transform(pDoctorId: number,userService:UserService): Observable<User>{
    return userService.getUserById(pDoctorId);
  }
}