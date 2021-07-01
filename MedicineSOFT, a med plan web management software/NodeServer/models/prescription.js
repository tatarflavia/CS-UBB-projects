class Prescription {
    #pId=0;
    #pStatus="";
    #pPatientId=0;
    #pDoctorId=0;
    #pStartDate="";
    #pEndDate="";
    #pDiagnosis="";
    constructor(pId,pStatus,pPatientId,pDoctorId,pStartDate,pEndDate,pDiagnosis) { 
            this.#pId=pId;
            this.#pStatus=pStatus;
            this.#pPatientId=pPatientId;
            this.#pDoctorId=pDoctorId;
            this.#pStartDate=pStartDate;
            this.#pEndDate=pEndDate;
            this.#pDiagnosis=pDiagnosis;
         }

         
        // Getters
        get pId() {
            return this.#pId;
        }
        get pStatus() {
            return this.#pStatus;
        }
        get pPatientId() {
            return this.#pPatientId;
        }
        get pDoctorId() {
            return this.#pDoctorId;
        }
        get pStartDate() {
            return this.#pStartDate;
        }
        get pEndDate() {
            return this.#pEndDate;
        }
        get pDiagnosis() {
            return this.#pDiagnosis;
        }

        
        
        set id(id) {
            this.#pId = id;
        }
       
  }


module.exports = {Prescription:Prescription}