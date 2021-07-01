

class MedicationValidator {
    constructor(){}
    
 
    validate(medication) {
        var errors="";
        if((typeof medication.mId !=="number") || (medication.mId<0)){
            errors+="Id must be a number bigger than 0.";
        }
        var regex = new RegExp('^[A-Za-z0-9 _,/-]*$');
        if((typeof medication.mName !=="string") || (!regex.test(medication.mName)) || medication.mName===null || medication.mName.length<3){
            errors+="Name must contain only letters, numbers and a length of at least 3 characters.";
        }
        if((typeof medication.mProducer !=="string") || (!regex.test(medication.mProducer)) || medication.mProducer===null || medication.mProducer.length<3){
            errors+="Producer must contain only letters, numbers and a length of at least 3 characters.";
        }
        if((typeof medication.mPharmaceticalForm !=="string") || (!regex.test(medication.mPharmaceticalForm )) || medication.mPharmaceticalForm ===null || medication.mPharmaceticalForm .length<3){
            errors+="PharmaceticalForm  must contain only letters, numbers and a length of at least 3 characters.";
        }
        if((typeof medication.mDCI !=="string") || (!regex.test(medication.mDCI )) || medication.mDCI===null || medication.mDCI.length<3){
            errors+="DCI  must contain only letters, numbers and a length of at least 3 characters.";
        }
        if((typeof medication.mTherapeuticAction !=="string") || (!regex.test(medication.mTherapeuticAction)) || medication.mTherapeuticAction===null || medication.mTherapeuticAction.length<3){
            errors+="TherapeuticAction  must contain only letters, numbers and a length of at least 3 characters.";
        }
        if((typeof medication.mPackaging!=="string") || (!regex.test(medication.mPackaging)) || medication.mPackaging===null || medication.mPackaging.length<3){
            errors+="Packaging  must contain only letters, numbers and a length of at least 3 characters.";
        }
       // /[-a-zA-Z0-9@:%._\+~#=]{1,256}\.[a-zA-Z0-9()]{1,6}\b([-a-zA-Z0-9()@:%_\+.~#?&//=]*)?/gi
        if((typeof medication.mProspectusLinkRO!=="string") || (!regex.test(medication.mProspectusLinkRO)) || medication.mProspectusLinkRO===null || medication.mProspectusLinkRO.length<3){
            errors+="ProspectusLinkRO  must contain only letters, numbers and a length of at least 3 characters.";
        }

        

        if(errors.length>0){
            throw new Error('Invalid Medication.'+errors);
        }
    }
  }


module.exports = {MedicationValidator:MedicationValidator}