

class ReviewValidator {
    constructor(){}
    
    validate(review) {
        var errors="";
        if((typeof review.rId !=="number") || (review.rId<0)){
            errors+="Id must be a number bigger than 0.";
        }
        if((typeof review.rPrescriptionId !=="number") || (review.rPrescriptionId<0)){
            errors+="PrescriptionId must be a number bigger than 0.";
        }
        if((typeof review.rPatientId !=="number") || (review.rPatientId<0)){
            errors+="PatientId must be a number bigger than 0.";
        }
        console.log(review.rMedicationId)
        if((typeof review.rMedicationId !=="number") || (review.rMedicationId<0)){
            errors+="MedicationId must be a number bigger than 0.";
        }
        console.log(review.rReactionObserved)
        var regex = new RegExp('^[A-Za-z0-9 _,/-]*$');
        if((typeof review.rReactionObserved !=="string") || (!regex.test(review.rReactionObserved)) || review.rReactionObserved===null || review.rReactionObserved.length<4){
            errors+="ReactionObserved must contain only letters, numbers and a length of at least 4 characters.";
        }
        console.log(review.rOverallFeel)
        if((typeof review.rOverallFeel !=="number") || (review.rOverallFeel<1) || (review.rOverallFeel>5)){
            errors+="OverallFeel must be a number between 1 and 5.";
        }
        

        
        if(errors.length>0){
            throw new Error('Invalid Review.'+errors);
        }
    }

    validateFull(review) {
        var errors="";
        if((typeof review.rId !=="number") || (review.rId<0)){
            errors+="rId must be a number bigger than 0.";
        }
        if((typeof review.rPatientId !=="number") || (review.rPatientId<0)){
            errors+="rPatientId must be a number bigger than 0.";
        }
        if((typeof review.rMedicationId !=="number") || (review.rMedicationId<0)){
            errors+="rMedicationId must be a number bigger than 0.";
        }
        var regex = new RegExp('^[A-Za-z0-9 _,/-]*$');
        if((typeof review.rReactionObserved !=="string") || (!regex.test(review.rReactionObserved)) || review.rReactionObserved===null || review.rReactionObserved.length<4){
            errors+="rReactionObserved must contain only letters, numbers and a length of at least 4 characters.";
        }
        if((typeof review.rOverallFeel !=="number") || (review.rOverallFeel<1) || (review.rOverallFeel>5)){
            errors+="rOverallFeel must be a number between 1 and 5.";
        }
        if((typeof review.rMedQuantity !=="string") || (!regex.test(review.rMedQuantity)) || review.rMedQuantity===null || review.rMedQuantity.length<4){
            errors+="rMedQuantity must contain only letters, numbers and a length of at least 4 characters.";
        }
        if((typeof review.rTakingMotive !=="string") || (!regex.test(review.rTakingMotive)) || review.rTakingMotive===null || review.rTakingMotive.length<4){
            errors+="rTakingMotive must contain only letters, numbers and a length of at least 4 characters.";
        }
        var regex = new RegExp('^((19|20)\\d\\d[- /.](0[1-9]|1[012])[- /.](0[1-9]|[12][0-9]|3[01]))|((0[1-9]|[12][0-9]|3[01])[- /.](0[1-9]|1[012])[- /.](19|20)\\d\\d)$');
        if((typeof review.rMedStartDate !=="string") || (!regex.test(review.rMedStartDate)) || review.rMedStartDate===null || review.rMedStartDate.length!=10){
            errors+="rMedStartDate must be a valid date in the format yyyy-mm-dd or dd-mm-yyyy.";
        }
        if((typeof review.rMedEndDate !=="string") || (!regex.test(review.rMedEndDate)) || review.rMedEndDate===null || review.rMedEndDate.length!=10){
            errors+="rMedEndDate must be a valid date in the format yyyy-mm-dd or dd-mm-yyyy.";
        }

        
        if(errors.length>0){
            throw new Error('Invalid Review.'+errors);
        }
    }
  }


module.exports = {ReviewValidator:ReviewValidator}