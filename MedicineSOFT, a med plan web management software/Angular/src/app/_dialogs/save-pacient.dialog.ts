import { Component, Inject } from "@angular/core";
import { MatDialogRef, MAT_DIALOG_DATA } from "@angular/material/dialog";
import { MatSnackBar } from "@angular/material/snack-bar";
import { Organisation } from "@app/_models/organisation";

@Component({
    selector: 'save-pacient-dialog',
    templateUrl: 'save-pacient.dialog.html',
  })
  export class SavePacientDialog{
  
    organisation:Organisation;
    constructor(
      public dialogRef: MatDialogRef<SavePacientDialog>,
      @Inject(MAT_DIALOG_DATA) public data: Array<Organisation>,private _snackBar: MatSnackBar) {
          this.organisation=null;
      }

      
  change(e){
    this.organisation=e.value;
  }
  
    
      onNoClick(): void {
      console.log("e la no click");
      this.dialogRef.close();
    }

    openSnackBar(message: string, action: string) {
        this._snackBar.open(message, action,{horizontalPosition: "center",
        verticalPosition: "top",});
      }


    onOKClick(){
        if(this.organisation!==null){
            this.dialogRef.close(this.organisation);
        }
        else{
            this.openSnackBar("You didn't choose anything.","OK");
            this.dialogRef.close()
        }
        
    }
  
  }