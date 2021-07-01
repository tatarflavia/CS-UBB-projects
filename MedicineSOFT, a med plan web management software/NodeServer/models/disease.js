class Disease {
    #dId=0;
    #dPatientId=0;
    #dName="";
    #dType="";
    
    constructor(dId,dPatientId,dName,dType) { 
            this.#dId=dId;
            this.#dPatientId=dPatientId;
            this.#dName=dName;
            this.#dType=dType;
         }

         
        // Getters
        get dId() {
            return this.#dId;
        }
        get dPatientId() {
            return this.#dPatientId;
        }
        get dName() {
            return this.#dName;
        }
        
        get dType() {
            return this.#dType;
        }
        
        set id(id) {
            this.#dId = id;
        }
       
  }


module.exports = {Disease:Disease}