

class OrganisationValidator {
    constructor(){}
    
 
    validate(organisation) {
        var errors="";
        if((typeof organisation.oId !=="number") || (organisation.oId<0)){
            errors+="Id must be a number bigger than 0.";
        }
        var regex = new RegExp('^[A-Z]{1}[a-z]+((-|\s)[a-zA-Z]*)*$');
        if((typeof organisation.oName !=="string") || (!regex.test(organisation.oName)) || organisation.oName===null || organisation.oName.length<3){
            errors+="Name must have first big letter and a space or '-' between words, min lenght of 3.";
        }
        var regex = new RegExp('^[a-zA-Z _,/-]*$');
        if((typeof organisation.oType !=="string") || (!regex.test(organisation.oType)) || organisation.oType===null || organisation.oType.length<4){
            errors+="Type must contain only letters and space or '-' between words and a length of at least 4 characters.";
        }

        if(errors.length>0){
            throw new Error('Invalid Organisation.'+errors);
        }
    }
  }


module.exports = {OrganisationValidator:OrganisationValidator}