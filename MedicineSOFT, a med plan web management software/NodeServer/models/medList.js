class MedList {
    #mlId=0;
    #mlPrescriptionId=0;
    #mlMedicationId=0;
    #mlMedicationName="";
    #mlHowOften="";
    constructor(mlId,mlPrescriptionId,mlMedicationId,mlMedicationName,mlHowOften) { 
            this.#mlId=mlId;
            this.#mlPrescriptionId=mlPrescriptionId;
            this.#mlMedicationId=mlMedicationId;
            this.#mlMedicationName=mlMedicationName;
            this.#mlHowOften=mlHowOften;
         }

         
        // Getters
        get mlId() {
            return this.#mlId;
        }
        get mlPrescriptionId() {
            return this.#mlPrescriptionId;
        }
        get mlMedicationId() {
            return this.#mlMedicationId;
        }
        get mlMedicationName() {
            return this.#mlMedicationName;
        }
        get mlHowOften(){
            return this.#mlHowOften;
        }
        

        
        
        set id(id) {
            this.#mlId = id;
        }
       
  }


module.exports = {MedList:MedList}