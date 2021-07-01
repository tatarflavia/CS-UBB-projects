const express = require('express');
const router = express.Router();
var md5 = require("md5")
let repo=require('../repositories/prescription.repository')
let PrescriptionRepository=repo.PrescriptionRepository;
let prescriptionRepo=new PrescriptionRepository();
let validator=require('../validators/prescription.validator')
let PrescriptionValidator=validator.PrescriptionValidator;
let prescriptionValid=new PrescriptionValidator();
let validatorMedList=require('../validators/medList.validator')
let MedListValidator=validatorMedList.MedListValidator;
let medListValid=new MedListValidator();
let serv=require('../services/prescription.service');
let PrescriptionService=serv.PrescriptionService;
let prescriptionServ=new PrescriptionService(prescriptionRepo,prescriptionValid,medListValid);
let repoFileUser=require('../repositories/user.repository')
let UserRepository=repoFileUser.UserRepository;
let userRepo=new UserRepository();
let validatorFileUser=require('../validators/user.validator')
let UserValidator=validatorFileUser.UserValidator;
let userValid=new UserValidator();
let servFileUser=require('../services/user.service');
let UserService=servFileUser.UserService;
let userServ=new UserService(userRepo,userValid);
let repoFileMed=require('../repositories/medication.repository')
let MedicationRepository=repoFileMed.MedicationRepository;
let medicationRepo=new MedicationRepository();
let validatorFileMed=require('../validators/medication.validator')
let MedicationValidator=validatorFileMed.MedicationValidator;
let medicationValid=new MedicationValidator();
let servFileMed=require('../services/medication.service');
let MedicationService=servFileMed.MedicationService;
let medicationServ=new MedicationService(medicationRepo,medicationValid);


// routes

router.post('/', getAllPrescriptionsByTypeByUserId);
router.post('/forPatient', getAllPrescriptionsForPatientId);
router.post('/medList', getMedListForPrescriptionId);
router.post('/add', addPrescriptionForPatient);
router.post('/addMed', addMedicationForPrescription)
router.get('/:id',getPrescriptionById);
router.post('/changeStatus',changePrescriptionStatus);
router.post('/medListMember',getMedListMemberByPresIdMedId)



module.exports = router;

async function getAllPrescriptionsByTypeByUserId(req, res, next) {
    console.log("get in prescriptions");
    var errors=[]
    //checking that we got both params
    if (!req.body.pType){
        errors.push("No type specified");
    }
    if (!req.body.uId){
        errors.push("No userId specified");
    }
    if (errors.length){
        res.status(400).json({ message:errors.join(",") })
        return;
    }
    try {
    //check that patient exist
    var patient=await userServ.getUserById(req.body.uId);
    prescriptionServ.getAllPrescriptionsByTypeByUserId(req.body.pType,req.body.uId)
        .then(prescriptions => prescriptions ? res.json(prescriptions) : res.status(400).json({ message: 'No such prescriptions' }));
    } catch (error) {
        res.status(400).json({ message: error.message})
    }
}
async function changePrescriptionStatus(req, res, next) {
    console.log("post in prescriptions/changeStatus");
    var errors=[]
    //checking that we got both params
    if (!req.body.pId){
        errors.push("No prescriptionId specified");
    }
    if (!req.body.pStatus){
        errors.push("No pStatus specified");
    }
    if (errors.length){
        res.status(400).json({ message:errors.join(",") })
        return;
    }
    try {
    prescriptionServ.changePrescriptionStatus(req.body.pId,req.body.pStatus)
        .then(prescription => prescription ? res.json(prescription) : res.status(400).json({ message: 'Wrong status change' })).catch(err=>next(err));
    } catch (error) {
        res.status(400).json({ message: error.message})
    }
    
}

async function getAllPrescriptionsForPatientId(req, res, next) {
    console.log("get in prescriptions/forPatient");
    var errors=[]
    //checking that we got both params
    if (!req.body.pPatientId){
        errors.push("No patientId specified");
    }
    if (!req.body.pDoctorId){
        errors.push("No doctorId specified");
    }
    if (errors.length){
        res.status(400).json({ message:errors.join(",") })
        return;
    }
    try {
    //check that patient exist and the same for doctor
    var patient=await userServ.getUserById(req.body.pPatientId);
    var doctor=await userServ.getUserById(req.body.pDoctorId);
    var isDoctor=await userServ.checkTheUserIsDoctor(req.body.pDoctorId);
    prescriptionServ.getAllPrescriptionsForPatientId(req.body.pPatientId,req.body.pDoctorId)
        .then(prescriptions => prescriptions ? res.json(prescriptions) : res.status(400).json({ message: 'No such prescriptions' }));
    } catch (error) {
        res.status(400).json({ message: error.message})
    }
    
}

async function getMedListMemberByPresIdMedId(req, res, next){
    var errors=[]
    //checking that we got both params
    if (!req.body.mlPrescriptionId){
        errors.push("No prescriptionId specified");
    }
    if (!req.body.mlMedicationId){
        errors.push("No medicationId specified");
    }
    if (errors.length){
        res.status(400).json({ message:errors.join(",") })
        return;
    }
    try {
        var medListMember=await prescriptionServ.getMedListMemberByPresIdMedId(req.body.mlPrescriptionId,req.body.mlMedicationId);
        if(medListMember!=null && medListMember.length!==0){
            console.log(JSON.stringify(medListMember))
            res.json(
                medListMember
            )
        return; 
        }
        else{
            res.status(400).json({ message: 'MedListMember does not exist' })
            return;
        }
    } catch (error) {
        res.status(400).json({ message: error.message })
    }


}

async function getMedListForPrescriptionId(req, res, next) {
    console.log("get in prescriptions/medList");
    var errors=[]
    //checking that we got both params
    if (!req.body.pId){
        errors.push("No pId specified");
    }
    if (errors.length){
        res.status(400).json({ message:errors.join(",") })
        return;
    }
    prescriptionServ.getMedListForPrescriptionId(req.body.pId)
        .then(meds => meds ? res.json(meds) : res.status(400).json({ message: 'No such medList' }))
        .catch(err => next(err));
}

async function getPrescriptionById(req, res, next) {
    console.log("get in prescriptions/:id");
    var errors=[]
    //checking that we got param
    if (!req.params.id){
        errors.push("No pId specified");
    }
    if (errors.length){
        res.status(400).json({ message:errors })
        return;
    }
    try {
        var prescription=await prescriptionServ.getPrescriptionById(req.params.id);
        if(prescription!=null){
            res.json(
                prescription
            )
        return; 
        }
        else{
            res.status(400).json({ message: 'Prescription does not exist' })
            return;
        }
    } catch (error) {
        res.status(400).json({ message: 'Prescription does not exist' })
    }
}





async function addPrescriptionForPatient(req, res, next){
    var errors=[]
    console.log("post in prescriptions/add")
    //checking that we got all params
    if (!req.body.pPatientId){
        errors.push("No pPatientId specified");
    }
    if (!req.body.pDoctorId){
        errors.push("No pDoctorId specified");
    }
    if (!req.body.pDiagnosis){
        errors.push("No pDiagnosis specified");
    }
    if (errors.length){
        res.status(400).json({ message: errors.join(",") })
        return;
    }
    try {
        //check that patient exist and the same for doctor
        var patient=await userServ.getUserById(req.body.pPatientId);
        var doctor=await userServ.getUserById(req.body.pDoctorId);
        var isDoctor=await userServ.checkTheUserIsDoctor(req.body.pDoctorId);
        var prescription=await prescriptionServ.addPrescriptionForPatient(req.body.pPatientId,req.body.pDoctorId,req.body.pDiagnosis);
        if(prescription!=null){
            var data = {
                pId:prescription.pId,
                pStatus:prescription.pStatus,
                pPatientId:prescription.pPatientId,
                pDoctorId:prescription.pDoctorId,
                pStartDate:prescription.pStartDate,
                pEndDate:prescription.pEndDate,
                pDiagnosis:prescription.pDiagnosis   
            }
            res.json({"data": data,
                "pId" : prescription.pId})
        return; 
        }
        else{
            res.status(400).json({ message: 'There was an error while adding the prescription' })
            return;
        }
    } catch (error) {
        //console.log(error)
        res.status(400).json({ message: error.message})
    }     

}



async function addMedicationForPrescription(req, res, next){
    var errors=[]
    console.log("post in prescriptions/addMed")
    //checking that we got all params
    if (!req.body.mlPrescriptionId){
        errors.push("No mlPrescriptionId specified");
    }
    if (!req.body.mlMedicationId){
        errors.push("No mlMedicationId specified");
    }
    if (!req.body.mlMedicationName){
        errors.push("No mlMedicationName specified");
    }
    if (!req.body.mlHowOften){
        errors.push("No mlHowOften specified");
    }
    if (errors.length){
        res.status(400).json({ message: errors.join(",") })
        return;
    }
    try {
        //check that the presc and the med exist
        var med=await medicationServ.getMedicationById(req.body.mlMedicationId);
        var presc=await prescriptionServ.getPrescriptionById(req.body.mlPrescriptionId);
        var medListMember=await prescriptionServ.addMedicationForPrescription(req.body.mlPrescriptionId,req.body.mlMedicationId,req.body.mlMedicationName,req.body.mlHowOften);
        if(medListMember!=null){
            var data = {
                mlId:medListMember.mlId,
                mlPrescriptionId:medListMember.mlPrescriptionId,
                mlMedicationId:medListMember.mlMedicationId,
                mlMedicationName:medListMember.mlMedicationName,
                mlHowOften:medListMember.mlHowOften 
            }
            res.json({"data": data,
                "mlId" : medListMember.pId})
        return; 
        }
        else{
            res.status(400).json({ message: 'There was an error while adding the medListMember' })
            return;
        }
    } catch (error) {
        //console.log(error)
        res.status(400).json({ message: error.message})
    }     

}


