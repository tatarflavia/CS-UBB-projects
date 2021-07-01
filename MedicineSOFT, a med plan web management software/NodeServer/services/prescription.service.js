//the service for the prescription model, helds a repo reference and is called by the prescription route
let prescFile=require('../models/prescription')
let Prescription=prescFile.Prescription;
let medListFile=require('../models/medList');
let MedList=medListFile.MedList;

class PrescriptionService {
    constructor(prescriptionRepository,prescriptionValidator,medListValidator){
        this.prescriptionRepository=prescriptionRepository;
        this.prescriptionValidator=prescriptionValidator;
        this.medListValidator=medListValidator;
    }


    async getAllPrescriptionsByTypeByUserId(pType,uId){
        if(pType!=="Not Started" && pType!=="Ongoing" && pType!=="Finished" && pType!=="Dismissed"){
            return null;
        }
        var rows=await this.prescriptionRepository.getAllPrescriptionsByTypeByUserId(pType,uId);
        return rows;
    }

    async changePrescriptionStatus(pId,pStatus){
        if(pStatus!=="Ongoing" && pStatus!=="Finished" && pStatus!=="Dismissed"){
            return null;
        }
        var updatedPrescription=this.prescriptionRepository.changePrescriptionStatus(pId,pStatus);
        return updatedPrescription;
    }

    async getAllPrescriptionsForPatientId(pPatientId,pDoctorId){
        var rows=await this.prescriptionRepository.getAllPrescriptionsForPatientId(pPatientId,pDoctorId);
        return rows;
    }

    async getMedListForPrescriptionId(pId){
        var rows=await this.prescriptionRepository.getMedListForPrescriptionId(pId);
        return rows;
    }

    async getPrescriptionById(pId){
        console.log("serv cred:"+pId);
        var row=await this.prescriptionRepository.getPrescriptionById(pId);
        console.log("serv row:"+JSON.stringify(row))
        return row;
    }

    async getMedListMemberByPresIdMedId(mlPrescriptionId,mlMedicationId){
        var row=await this.prescriptionRepository.getMedListMemberByPresIdMedId(mlPrescriptionId,mlMedicationId);
        console.log("serv row:"+JSON.stringify(row))
        return row;
    }

    async addPrescriptionForPatient(pPatientId,pDoctorId,pDiagnosis){
        try {
            var prescription=new Prescription(0,"Not Started",pPatientId,pDoctorId,null,null,pDiagnosis);
            this.prescriptionValidator.validate(prescription);
            var addedPrescription=await this.prescriptionRepository.addPrescriptionForPatient(prescription);
            return addedPrescription;  
        } catch (error) {
            throw new Error(error.message);
        }
    }

    
    async addMedicationForPrescription(mlPrescriptionId,mlMedicationId,mlMedicationName,mlHowOften){
        try {
            var medListMember=new MedList(0,mlPrescriptionId,mlMedicationId,mlMedicationName,mlHowOften)
            this.medListValidator.validate(medListMember);
            var medIsAlreadyInPrescription=await this.prescriptionRepository.checkIfMedicationExistsAlreadyInPrescription(mlPrescriptionId,mlMedicationId);
            if(!(medIsAlreadyInPrescription)){
                var addedMedListMember=await this.prescriptionRepository.addMedicationForPrescription(medListMember);
                return addedMedListMember;
            }
            else{
                throw new Error("The medication is already in this prescription");
            }
        } catch (error) {
            throw new Error(error.message);
        }

    }

    
   

   

       
  }


module.exports = {PrescriptionService:PrescriptionService}