class User {
    #uId=0;
    #uName="";
    #uEmail="";
    #uBirthday="";
    #uInsuredCode="";
    #uDocNumber="";
    #uExpirationDate="";
    #uPassword="";
    #uIsDoctor=0;
    #uIsAdmin=0;
    constructor(uId,uName,
        uEmail,uBirthday,uInsuredCode,uDocNumber,uExpirationDate,
        uPassword,uIsDoctor,uIsAdmin) { 
            this.#uId=uId;
            this.#uName=uName;
           
            this.#uEmail=uEmail;
            this.#uBirthday=uBirthday;
            this.#uInsuredCode=uInsuredCode;
            this.#uDocNumber=uDocNumber;
            this.#uExpirationDate=uExpirationDate;
            this.#uPassword=uPassword;
            this.#uIsDoctor=uIsDoctor;
            this.#uIsAdmin=uIsAdmin;
         }

         
        // Getters
        get uId() {
            return this.#uId;
        }
        get uName() {
            return this.#uName;
        }
        
        get uEmail() {
            return this.#uEmail;
        }
        get uBirthday() {
            return this.#uBirthday;
        }
        get uInsuredCode() {
            return this.#uInsuredCode;
        }
        get uDocNumber() {
            return this.#uDocNumber;
        }
        get uExpirationDate() {
            return this.#uExpirationDate;
        }
        get uPassword() {
            return this.#uPassword;
        }
        get uIsDoctor() {
            return this.#uIsDoctor;
        }
        get uIsAdmin() {
            return this.#uIsAdmin;
        }
        set id(id) {
            this.#uId = id;
        }
       
  }


module.exports = {User:User}