const express = require('express');
const router = express.Router();
var md5 = require("md5")
let repo=require('../repositories/organisationBelongings.repository')
let OrganisationBelongingsRepository=repo.OrganisationBelongingsRepository;
let organisationBelongingsRepo=new OrganisationBelongingsRepository();
let validator=require('../validators/organisationBelongings.validator')
let OrganisationBelongingsValidator=validator.OrganisationBelongingsValidator;
let organisationBelongingsValid=new OrganisationBelongingsValidator();
let serv=require('../services/organisationBelongings.service');
let OrganisationBelongingsService=serv.OrganisationBelongingsService;
let organisationBelongingsServ=new OrganisationBelongingsService(organisationBelongingsRepo,organisationBelongingsValid);
let repoFileUser=require('../repositories/user.repository')
let UserRepository=repoFileUser.UserRepository;
let userRepo=new UserRepository();
let validatorFileUser=require('../validators/user.validator')
let UserValidator=validatorFileUser.UserValidator;
let userValid=new UserValidator();
let servFileUser=require('../services/user.service');
let UserService=servFileUser.UserService;
let userServ=new UserService(userRepo,userValid);
let repoFileOrg=require('../repositories/organisation.repository')
let OrganisationRepository=repoFileOrg.OrganisationRepository;
let organisationRepo=new OrganisationRepository();
let validatorFileOrg=require('../validators/organisation.validator')
let OrganisationValidator=validatorFileOrg.OrganisationValidator;
let organisationValid=new OrganisationValidator();
let servFileOrg=require('../services/organisation.service');
let OrganisationService=servFileOrg.OrganisationService;
let organisationServ=new OrganisationService(organisationRepo,organisationValid);

router.post('/add', addOrganisationForAPacient);
router.post('/seeForPatient', getOrganisationsByDoctorIdNotPatientId);
router.post('/seePatients', getAllPatientsByDoctorId);
router.post('/seePatientsCount', getAllPatientsCountByDoctorId);
router.post('/bsForDoctor',getAllOrganisationBelongingsByDoctorId);
router.post('/osForDoctor',getAllOrganisationsByDoctorId);
router.post('/bsForDoctorPatient',checkPatientDoctor)


module.exports = router;


async function checkPatientDoctor(req,res,next){
    var errors=[]
    console.log("post in orgs/bsForDoctorPatient")
    //checking that we got all params
    if (!req.body.bDoctorId){
        errors.push("No doctorId specified");
    }
    if (!req.body.bPatientId){
        errors.push("No patientId specified");
    }
    if (errors.length){
        res.status(400).json({ message: errors.join(",") })
        return;
    }
    try {
        //check that patient and doctor exist
    var user=await userServ.getUserById(req.body.bPatientId);
    var doctor=await userServ.getUserById(req.body.bDoctorId);
    var isDoctor=await userServ.checkTheUserIsDoctor(req.body.bDoctorId);
    organisationBelongingsServ.getOrgBelongsForDoctorPatient(req.body.bDoctorId,req.body.bPatientId).then(result=>res.json(result)).catch(err=>next(err));

    } catch (error) {
        res.status(400).json({ message: error.message})
    }

}


async function addOrganisationForAPacient(req,res,next) {
    var errors=[]
    console.log("post in orgs/add")
    //checking that we got all params
    if (!req.body.bDoctorId){
        errors.push("No doctorId specified");
    }
    if (!req.body.bOrganisationId){
        errors.push("No organisationId specified");
    }
    if (!req.body.bPatientId){
        errors.push("No patientId specified");
    }
    if (errors.length){
        res.status(400).json({ message: errors.join(",") })
        return;
    }
    try {
        //check that patient and doctor exist
        var user=await userServ.getUserById(req.body.bPatientId);
        var doctor=await userServ.getUserById(req.body.bDoctorId);
        var isDoctor=await userServ.checkTheUserIsDoctor(req.body.bDoctorId);
        //check that org exists
        var organisation=await organisationServ.getOrganisationById(req.body.bOrganisationId);
        var orgBelonging=await organisationBelongingsServ.addOrganisationForPatient(req.body.bDoctorId,req.body.bOrganisationId,req.body.bPatientId);
        if(orgBelonging!=null){
            var data = {
                bId:orgBelonging.u=orgBelonging.bId,
                bDoctorId:orgBelonging.bDoctorId,
                bOrganisationId:orgBelonging.bOrganisationId,
                bPatientId:orgBelonging.bPatientId,
                
            }
            res.json({"data": data,
                "bId" : orgBelonging.bId})
        return; 
        }
        else{
            res.status(400).json({ message: 'There was an error while adding the organisation to user' })
            return;
        }
    } catch (error) {
        //console.log(error)
        res.status(400).json({ message: error.message})
    }     

}

async function getOrganisationsByDoctorIdNotPatientId(req,res,next) {
    var errors=[]
    console.log("post in orgs/seeForPatient")
    //checking that we got all params
    if (!req.body.bDoctorId){
        errors.push("No doctorId specified");
    }
    if (!req.body.bPatientId){
        errors.push("No patientId specified");
    }
    if (errors.length){
        res.status(400).json({ message: errors.join(",") })
        return;
    }
    try {
        //check that patient and doctor exist
    var user=await userServ.getUserById(req.body.bPatientId);
    var doctor=await userServ.getUserById(req.body.bDoctorId);
    var isDoctor=await userServ.checkTheUserIsDoctor(req.body.bDoctorId);
    organisationBelongingsServ.getAllOrganisationsByDoctorIdLessPatientId(req.body.bDoctorId,req.body.bPatientId).then(organisations=>res.json(organisations)).catch(err=>next(err));

    } catch (error) {
        res.status(400).json({ message: error.message})
    }
}

async function getAllOrganisationsByDoctorId(req,res,next){
    var errors=[]
    console.log("post in orgs/forDoctor")
    //checking that we got all params
    if (!req.body.bDoctorId){
        errors.push("No doctorId specified");
    }
    if (errors.length){
        res.status(400).json({ message: errors.join(",") })
        return;
    }
    try {
        //check that patient and doctor exist
    var doctor=await userServ.getUserById(req.body.bDoctorId);
    var isDoctor=await userServ.checkTheUserIsDoctor(req.body.bDoctorId);
    organisationBelongingsServ.getAllOrganisationsByDoctorId(req.body.bDoctorId).then(organisations=>res.json(organisations)).catch(err=>next(err));

    } catch (error) {
        res.status(400).json({ message: error.message})
    }
}
async function getAllOrganisationBelongingsByDoctorId(req,res,next){
    var errors=[]
    console.log("post in orgs/forDoctor")
    //checking that we got all params
    if (!req.body.bDoctorId){
        errors.push("No doctorId specified");
    }
    if (errors.length){
        res.status(400).json({ message: errors.join(",") })
        return;
    }
    try {
        //check that patient and doctor exist
    var doctor=await userServ.getUserById(req.body.bDoctorId);
    var isDoctor=await userServ.checkTheUserIsDoctor(req.body.bDoctorId);
    organisationBelongingsServ.getAllOrganisationBelongingsByDoctorId(req.body.bDoctorId).then(organisations=>res.json(organisations)).catch(err=>next(err));

    } catch (error) {
        res.status(400).json({ message: error.message})
    }
}

async function getAllPatientsByDoctorId(req,res,next){
    var errors=[]
    console.log("post in orgs/seePatients")
    //checking that we got all params
    if (!req.body.bDoctorId){
        errors.push("No doctorId specified");
    }
    if (errors.length){
        res.status(400).json({ message: errors.join(",") })
        return;
    }
    try {
        //check that patient and doctor exist
    var doctor=await userServ.getUserById(req.body.bDoctorId);
    var isDoctor=await userServ.checkTheUserIsDoctor(req.body.bDoctorId);
    organisationBelongingsServ.getAllPatientsByDoctorId(req.body.bDoctorId).then(organisations=>res.json(organisations)).catch(err=>next(err));
    } catch (error) {
        res.status(400).json({ message: error.message})
    }
}

async function getAllPatientsCountByDoctorId(req,res,next){
    var errors=[]
    console.log("post in orgs/seePatientsCount")
    //checking that we got all params
    if (!req.body.bDoctorId){
        errors.push("No doctorId specified");
    }
    if (errors.length){
        res.status(400).json({ message: errors.join(",") })
        return;
    }
    try {
        var doctor=await userServ.getUserById(req.body.bDoctorId);
        var isDoctor=await userServ.checkTheUserIsDoctor(req.body.bDoctorId);
        var patientCount=await organisationBelongingsServ.getAllPatientsCountByDoctorId(req.body.bDoctorId);
        if(patientCount!==null){
            res.json(patientCount);
            return; 
        }
        else{
            res.status(400).json({ message: 'No rows for this doctor' })
            return;
        }
    } catch (error) {
        console.log(error)
        res.status(400).json({ message: error.message})
    }     
}
