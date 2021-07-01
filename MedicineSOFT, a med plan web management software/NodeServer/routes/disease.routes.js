const express = require('express');
const router = express.Router();
var md5 = require("md5")
let repo=require('../repositories/disease.repository')
let DiseaseRepository=repo.DiseaseRepository;
let diseaseRepo=new DiseaseRepository();
let validator=require('../validators/disease.validator')
let DiseaseValidator=validator.DiseaseValidator;
let diseaseValid=new DiseaseValidator();
let serv=require('../services/disease.service');
let DiseaseService=serv.DiseaseService;
let diseaseServ=new DiseaseService(diseaseRepo,diseaseValid);
let repoFileUser=require('../repositories/user.repository')
let UserRepository=repoFileUser.UserRepository;
let userRepo=new UserRepository();
let validatorFileUser=require('../validators/user.validator')
let UserValidator=validatorFileUser.UserValidator;
let userValid=new UserValidator();
let servFileUser=require('../services/user.service');
let UserService=servFileUser.UserService;
let userServ=new UserService(userRepo,userValid);


// routes
router.post('/add', addDiseaseForAPacient);
router.get('/:id',getDiseaseByUserId);

module.exports = router;

async function getDiseaseByUserId(req,res,next) {
    console.log("get in diseases/:id");
    var errors=[]
    //checking that we got param
    if (!req.params.id){
        errors.push("No userId specified");
    }
    if (errors.length){
        res.status(400).json({ message:errors })
        return;
    }
    try{
        var user=await userServ.getUserById(req.params.id);
    diseaseServ.getAllDiseasesByUserId(req.params.id)
        .then(diseases => res.json(diseases))
    }
    catch(error){
        res.status(400).json({ message: 'No such user' })
    }
    

}

async function addDiseaseForAPacient(req,res,next) {
    var errors=[]
    console.log("post in diseases/add")
    //checking that we got all params
    if (!req.body.dPatientId){
        errors.push("No patientId specified");
    }
    if (!req.body.dName){
        errors.push("No name specified");
    }
    if (!req.body.dType){
        errors.push("No type specified");
    }
    if (errors.length){
        res.status(400).json({ message: errors.join(",") })
        return;
    }
    try {
        //check that user exists
        var user=await userServ.getUserById(req.body.dPatientId);
        var disease=await diseaseServ.addDisease(req.body.dPatientId,req.body.dName,req.body.dType);
        if(disease!=null){
            var data = {
                dId:disease.uId,
                dPatientId:disease.dPatientId,
                dName:disease.dName,
                dType:disease.dType
            }
            res.json({"data": data,
                "dId" : disease.dId})
        return; 
        }
        else{
            res.status(400).json({ message: 'There was an error while adding the disease' })
            return;
        }
    } catch (error) {
        //console.log(error)
        res.status(400).json({ message: error.message})
    }     

}




