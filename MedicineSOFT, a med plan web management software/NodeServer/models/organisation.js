class Organisation {
    #oId=0;
    #oName="";
    #oType="";
    
    constructor(oId,oName,oType) { 
            this.#oId=oId;
            this.#oName=oName;
            this.#oType=oType;
         }

         
        // Getters
        get oId() {
            return this.#oId;
        }
        get oName() {
            return this.#oName;
        }
        
        get oType() {
            return this.#oType;
        }
        
        set id(id) {
            this.#oId = id;
        }
       
  }


module.exports = {Organisation:Organisation}