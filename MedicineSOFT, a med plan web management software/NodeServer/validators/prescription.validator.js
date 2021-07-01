

class PrescriptionValidator {
    constructor(){}
    
    validate(prescription) {
        var errors="";
        if((typeof prescription.pId !=="number") || (prescription.pId<0)){
            errors+="Id must be a number bigger than 0.";
        }
        if((prescription.pStatus !=="Dismissed") && (prescription.pStatus !=="Not Started") && (prescription.pStatus !=="Finished") && (prescription.pStatus !=="Ongoing")){
            errors+="Status can only be Dismissed/Not Started/Finished/Ongoing";
        }
        if((typeof prescription.pPatientId !=="number") || (prescription.pPatientId<0)){
            errors+="PatientId must be a number bigger than 0.";
        }
        if((typeof prescription.pDoctorId !=="number") || (prescription.pDoctorId<0)){
            errors+="DoctorId must be a number bigger than 0.";
        }
        var regex = new RegExp('^[A-Za-z0-9 _,/-]*$');
        if((typeof prescription.pDiagnosis !=="string") || (!regex.test(prescription.pDiagnosis)) || prescription.pDiagnosis===null || prescription.pDiagnosis.length<4){
            errors+="Diagnosis must contain only letters, numbers and a length of at least 4 characters.";
        }
        

        if(errors.length>0){
            throw new Error('Invalid Prescription.'+errors);
        }
    }
  }


module.exports = {PrescriptionValidator:PrescriptionValidator}