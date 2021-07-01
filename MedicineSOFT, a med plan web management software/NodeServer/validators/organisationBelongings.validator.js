

class OrganisationBelongingsValidator {
    constructor(){}
    
 
    validate(organisationBelonging) {
        var errors="";
        if((typeof organisationBelonging.bId !=="number") || (organisationBelonging.bId<0)){
            errors+="Id must be a number bigger than 0.";
        }
        if((typeof organisationBelonging.bDoctorId !=="number") || (organisationBelonging.bDoctorId<0)){
            errors+="DoctorId must be a number bigger than 0.";
        }
        if((typeof organisationBelonging.bOrganisationId !=="number") || (organisationBelonging.bOrganisationId<0)){
            errors+="OrganisationId must be a number bigger than 0.";
        }
        if((typeof organisationBelonging.bPatientId !=="number") || (organisationBelonging.bPatientId<0)){
            errors+="PatientId must be a number bigger than 0.";
        }
        
        if(errors.length>0){
            throw new Error('Invalid OrganisationBelonging.'+errors);
        }
    }
  }


module.exports = {OrganisationBelongingsValidator:OrganisationBelongingsValidator}