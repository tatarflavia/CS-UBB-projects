class Review {
    #rId=0;
    #rPrescriptionId=0;
    #rPatientId=0;
    #rMedicationId=0;
    #rReactionObserved="";
    #rOverallFeel=1;
    #rMedQuantity="";
    #rTakingMotive="";
    #rMedStartDate="";
    #rMedEndDate="";
    constructor(rId,rPrescriptionId,rPatientId,rMedicationId,rReactionObserved,rOverallFeel,rMedQuantity,rTakingMotive,rMedStartDate,rMedEndDate) { 
            this.#rId=rId;
            this.#rPrescriptionId=rPrescriptionId;
            this.#rPatientId=rPatientId;
            this.#rMedicationId=rMedicationId;
            this.#rReactionObserved=rReactionObserved;
            this.#rOverallFeel=rOverallFeel;
            this.#rMedQuantity=rMedQuantity;
            this.#rTakingMotive=rTakingMotive;
            this.#rMedStartDate=rMedStartDate;
            this.#rMedEndDate=rMedEndDate;
         }

         
        // Getters
        get rId() {
            return this.#rId;
        }
        get rPrescriptionId() {
            return this.#rPrescriptionId;
        }
        get rPatientId() {
            return this.#rPatientId;
        }
        get rMedicationId() {
            return this.#rMedicationId;
        }
        get rReactionObserved() {
            return this.#rReactionObserved;
        }
        get rOverallFeel() {
            return this.#rOverallFeel;
        }
        get rMedQuantity() {
            return this.#rMedQuantity;
        }
        get rTakingMotive() {
            return this.#rTakingMotive;
        }
        get rMedStartDate() {
            return this.#rMedStartDate;
        }
        get rMedEndDate() {
            return this.#rMedEndDate;
        }
        

        
        
        set id(id) {
            this.#rId = id;
        }
        set medQuantity(medQuantity) {
            this.#rMedQuantity=medQuantity;
        }
        set takingMotive(takingMotive) {
            this.#rTakingMotive=takingMotive;
        }
        set medStartDate(medStartDate) {
            this.#rMedStartDate=medStartDate;
        }
        set medEndDate(medEndDate) {
            this.#rMedEndDate=medEndDate;
        }
       
  }


module.exports = {Review:Review}