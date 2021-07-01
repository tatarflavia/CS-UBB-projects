import { AfterViewInit, Component, ElementRef, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { ReviewDetailsDialog } from '@app/_dialogs/review-details.dialog';
import { Review } from '@app/_models/review';
import { AuthenticationService } from '@app/_services/authentication.service';
import { MedicationService } from '@app/_services/medication.service';
import { ReviewService } from '@app/_services/review.service';

@Component({
  selector: 'app-reviews',
  templateUrl: './reviews.component.html',
  styleUrls: ['./reviews.component.css']
})
export class ReviewsComponent implements OnInit,AfterViewInit {
  medicationService:MedicationService;
  userReviews:Array<Review>;
  constructor(private elementRef: ElementRef,public dialog: MatDialog,private reviewService:ReviewService,private authenticationService:AuthenticationService,private router:Router,medicatonService:MedicationService) {
    this.medicationService=medicatonService;
  }

  openDialog(rId:number): void {
    const dialogRef = this.dialog.open(ReviewDetailsDialog, {
      width: '1000px',
      maxHeight: '90vh',
      panelClass: 'my-dialog',
      data: rId
    });

    
  }

  ngOnInit(): void {
    this.getUserReviews();
  }

  getUserReviews():void{
    this.reviewService.getReviewsByUserId(this.authenticationService.userValue.uId).subscribe(
      (reviews)=>{
        this.userReviews=reviews.map(function (review){
          return review;

        })
      }
    )
  }

  ngAfterViewInit(): void {
    this.elementRef.nativeElement.ownerDocument.body.style.backgroundColor = '#d6e0f5';
  }

  
  
}
