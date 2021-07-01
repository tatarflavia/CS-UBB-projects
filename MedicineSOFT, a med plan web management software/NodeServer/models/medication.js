
class Medication {
    #mId=0;
    #mName="";
    #mProducer="";
    #mPharmaceuticalForm="";
    #mDCI="";
    #mTherapeuticAction="";
    #mPackaging="";
    #mProspectusLinkRO="";
    
    constructor(mId,mName,mProducer,mPharmaceuticalForm,mDCI,mTherapeuticAction,mPackaging,mProspectusLinkRO) { 
            this.#mId=mId;
            this.#mName=mName;
            this.#mProducer=mProducer;
            this.#mPharmaceuticalForm=mPharmaceuticalForm;
            this.#mDCI=mDCI;
            this.#mTherapeuticAction=mTherapeuticAction;
            this.#mPackaging=mPackaging;
            this.#mProspectusLinkRO=mProspectusLinkRO;
         }

        
        // Getters
        get mId() {
            return this.#mId;
        }
        get mName() {
            return this.#mName;
        }
        get mProducer() {
            return this.#mProducer;
        }
        get mPharmaceuticalForm() {
            return this.#mPharmaceuticalForm;
        }
        get mDCI() {
            return this.#mDCI;
        }
        get mTherapeuticAction() {
            return this.#mTherapeuticAction;
        }
        get mPackaging() {
            return this.#mPackaging;
        }
        get mProspectusLinkRO() {
            return this.#mProspectusLinkRO;
        }
        
        set id(id) {
            this.#mId = id;
        }
       
  }


module.exports = {Medication:Medication}