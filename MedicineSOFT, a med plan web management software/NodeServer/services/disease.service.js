//the service for the disease model, helds a repo reference and is called by the disease route
let diseaseFile=require('../models/disease');
let Disease=diseaseFile.Disease;

class DiseaseService {
    constructor(diseaseRepository,diseaseValidator){
        this.diseaseRepository=diseaseRepository;
        this.diseaseValidator=diseaseValidator;
    }

    async getAllDiseasesByUserId(dPatientId){
        console.log("get diseases by userID incepe la serv");
        var rows=await this.diseaseRepository.getAllDiseasesByUserId(dPatientId);
        return rows;
        
    }

    async addDisease(dPatientId,dName,dType){
        try {
            var disease=new Disease(0,dPatientId,dName,dType)
            this.diseaseValidator.validate(disease);
            var diseaseExistsAlready=await this.diseaseRepository.checkIfDiseaseExistsAlready(disease);
            if(!(diseaseExistsAlready)){
                var addedDisease=await this.diseaseRepository.addDisease(disease);
                return addedDisease;
            }
            else{
                throw new Error("Disease exists already");
            }
        } catch (error) {
            throw new Error(error.message);
        }
    }

    
    
    

       
  }


module.exports = {DiseaseService:DiseaseService}