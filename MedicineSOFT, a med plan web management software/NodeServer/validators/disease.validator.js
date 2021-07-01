

class DiseaseValidator {
    constructor(){}
    
 
    validate(disease) {
        var errors="";
        if((typeof disease.dId !=="number") || (disease.dId<0)){
            errors+="Id must be a number bigger than 0.";
        }
        if((typeof disease.dPatientId !=="number") || (disease.dPatientId<0)){
            errors+="PatientId must be a number bigger than 0.";
        }
        var regex = new RegExp('^[a-zA-Z0-9 _,/-]*$');
        if((typeof disease.dName !=="string") || (!regex.test(disease.dName)) || disease.dName===null || disease.dName.length<3){
            errors+="Name must have only letters and numbers and space or '-' between words, min lenght of 3.";
        }

        if((typeof disease.dType !=="string") || (!regex.test(disease.dType)) || disease.dType===null || disease.dType.length<3){
            errors+="Type must have only letters and numbers and space or '-' between words, min lenght of 3.";
        }

        if(errors.length>0){
            throw new Error('Invalid Disease.'+errors);
        }
    }
  }


module.exports = {DiseaseValidator:DiseaseValidator}