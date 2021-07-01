class OrganisationBelongings {
    #bId=0;
    #bDoctorId=0;
    #bOrgannisationId=0;
    #bPatientId=0;
    
    constructor(bId,bDoctorId,bOrganisationId,bPatientId) { 
            this.#bId=bId;
            this.#bDoctorId=bDoctorId;
            this.#bOrgannisationId=bOrganisationId;
            this.#bPatientId=bPatientId;
         }

         
        // Getters
        get bId() {
            return this.#bId;
        }
        get bDoctorId() {
            return this.#bDoctorId;
        }
        
        get bOrganisationId() {
            return this.#bOrgannisationId;
        }
        get bPatientId() {
            return this.#bPatientId;
        }
        
        set id(id) {
            this.#bId = id;
        }
       
  }


module.exports = {OrganisationBelongings:OrganisationBelongings}