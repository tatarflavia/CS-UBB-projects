

class MedListValidator {
    constructor(){}
    
    validate(medList) {
        var errors="";
        if((typeof medList.mlId !=="number") || (medList.mlId<0)){
            errors+="Id must be a number bigger than 0.";
        }
        if((typeof medList.mlPrescriptionId !=="number") || (medList.mlPrescriptionId<0)){
            errors+="PrescriptionId must be a number bigger than 0.";
        }
        if((typeof medList.mlMedicationId !=="number") || (medList.mlMedicationId<0)){
            errors+="MedicationId must be a number bigger than 0.";
        }
        var regex = new RegExp('^[A-Za-z0-9 _,/-]*$');
        if((typeof medList.mlMedicationName !=="string") || (!regex.test(medList.mlMedicationName)) || medList.mlMedicationName===null || medList.mlMedicationName.length<4){
            errors+="MedicationName must contain only letters, numbers and a length of at least 4 characters.";
        }
        if((typeof medList.mlHowOften !=="string") || (!regex.test(medList.mlHowOften)) || medList.mlHowOften===null || medList.mlHowOften.length<4){
            errors+="HowOften must contain only letters, numbers and a length of at least 4 characters.";
        }
        

        if(errors.length>0){
            throw new Error('Invalid MedList.'+errors);
        }
    }
  }


module.exports = {MedListValidator:MedListValidator}