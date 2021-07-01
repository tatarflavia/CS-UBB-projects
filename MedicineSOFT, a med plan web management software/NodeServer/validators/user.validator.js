

class UserValidator {
    constructor(){}
    
 
    validate(user) {
        var errors="";
        if((typeof user.uId !=="number") || (user.uId<0)){
            errors+="Id must be a number bigger than 0.";
        }
        var regex = new RegExp('^[A-Z]{1}[a-z]+((-|\\s)[A-Z]{1}[a-z]+)+$');
        
        if((typeof user.uName !=="string") || (!regex.test(user.uName)) || user.uName===null || user.uName.length<3){
            errors+="Name must have first big letter for every name and a space or '-' between them, min lenght of 3.";
        }
        var regex = new RegExp('^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$');
        if((typeof user.uEmail !=="string") || (!regex.test(user.uEmail)) || user.uEmail===null || user.uEmail.length<3){
            errors+="Email must be a valid email address of minimum 3 length.";
        }
        var regex = new RegExp('^((19|20)\\d\\d[- /.](0[1-9]|1[012])[- /.](0[1-9]|[12][0-9]|3[01]))|((0[1-9]|[12][0-9]|3[01])[- /.](0[1-9]|1[012])[- /.](19|20)\\d\\d)$');
        if((typeof user.uBirthday !=="string") || (!regex.test(user.uBirthday)) || user.uBirthday===null || user.uBirthday.length!=10){
            errors+="Birthday must be a valid date in the format yyyy-mm-dd or dd-mm-yyyy.";
        }
        var regex = new RegExp('^[0-9]{20}$');
        if((typeof user.uInsuredCode !=="string") || (!regex.test(user.uInsuredCode)) || user.uInsuredCode===null || user.uInsuredCode.length!=20){
            errors+="InsuredCode must have only digits and a length of 20 digits.";
        }
        var regex = new RegExp('^[0-9]{6,}$');
        if((typeof user.uDocNumber !=="string") || (!regex.test(user.uDocNumber)) || user.uDocNumber===null || user.uDocNumber.length<6){
            errors+="DocNumber must have only digits and a length of at least 16 digits.";
        }
        var regex = new RegExp('^[a-zA-Z0-9]*$');
        if((typeof user.uPassword !=="string") || (!regex.test(user.uPassword)) || user.uPassword===null || user.uPassword.length<4){
            errors+="Password must contain only letters, numbers and a length of at least 4 characters.";
        }
        if((typeof user.uIsDoctor !=="number") || (user.uIsDoctor!=0 && user.uIsDoctor!=1)){
            errors+="IsDoctor must be only 1 or 0.";
        }
        if((typeof user.uIsAdmin !=="number") || (user.uIsAdmin!=0 && user.uIsDoctor!=1)){
            errors+="IsAdmin must be only 1 or 0.";
        }

        if(errors.length>0){
            throw new Error('Invalid User.'+errors);
        }
    }
  }


module.exports = {UserValidator:UserValidator}