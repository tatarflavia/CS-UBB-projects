//the service for the medication model, helds a repo reference and is called by the medication route
let medFile=require('../models/medication')
let Medication=medFile.Medication;

class MedicationService {
    constructor(medicationRepository,medicationValidator){
        this.medicationRepository=medicationRepository;
        this.medicationValidator=medicationValidator;
    }
    async getAllMedications(searchQuery){
        console.log("get medications incepe la serv");
        //only filter by name, docNb, insuredCode, email
        console.log(searchQuery["search"]);
        if(searchQuery["search"]!==undefined){
            //we sent filtered medications
            var rows=await this.medicationRepository.getAllMedicationsFiltered(searchQuery["search"]);
            return rows;
        }
        else{
            var rows=await this.medicationRepository.getAllMedications();
            return rows;
        }
    }
    
    

   

    async getMedicationById(mId){
        console.log("serv cred:"+mId);
        var row=await this.medicationRepository.getMedicationById(mId);
        console.log("serv row:"+JSON.stringify(row))
        return row;
    }

       
  }


module.exports = {MedicationService:MedicationService}