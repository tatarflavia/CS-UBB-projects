export class MedList{
    mlId: number;
    mlPrescriptionId:number;
    mlMedicationId:number;
    mlMedicationName:string;
    mlHowOften:string;
    constructor(mlId: number,mlPrescriptionId:number,
        mlMedicationId:number,mlMedicationName:string,
        mlHowOften:string) {
        this.mlId=mlId;
        this.mlPrescriptionId=mlPrescriptionId;
        this.mlMedicationId=mlMedicationId;
        this.mlMedicationName=mlMedicationName;
        this.mlHowOften=mlHowOften;

    }
    
}