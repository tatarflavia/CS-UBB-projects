const express = require('express');
const router = express.Router();
var md5 = require("md5")
let repo=require('../repositories/review.repository')
let ReviewRepository=repo.ReviewRepository;
let reviewRepo=new ReviewRepository();
let validator=require('../validators/review.validator')
let ReviewValidator=validator.ReviewValidator;
let reviewValid=new ReviewValidator();
let serv=require('../services/review.service');
let ReviewService=serv.ReviewService;
let reviewServ=new ReviewService(reviewRepo,reviewValid);
let repoFileMed=require('../repositories/medication.repository')
let MedicationRepository=repoFileMed.MedicationRepository;
let medicationRepo=new MedicationRepository();
let validatorFileMed=require('../validators/medication.validator')
let MedicationValidator=validatorFileMed.MedicationValidator;
let medicationValid=new MedicationValidator();
let servFileMed=require('../services/medication.service');
let MedicationService=servFileMed.MedicationService;
let medicationServ=new MedicationService(medicationRepo,medicationValid);
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
router.post('/add',addReview);
router.get('/:id',getReviewById);
router.get('/byUser/:id',getReviewsByUserId);
router.post('/check',checkIfPrescIdMedIdPatientIdExist);
router.post('/byIds',getByPrescIdMedIdPatientId);


module.exports = router;

async function getReviewsByUserId(req,res,next) {
    console.log("get in reviews/byUser/:id");
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
    reviewServ.getReviewsByUserId(req.params.id)
        .then(reviews => res.json(reviews))
    }
    catch(error){
        res.status(400).json({ message: 'No such user' })
    }
    

}

async function getByPrescIdMedIdPatientId(req, res, next){
    var errors=[]
    console.log("post in reviews/check")
    //checking that we got all params
    if (!req.body.rPrescriptionId){
        errors.push("No rPrescriptionId specified");
    }
    if (!req.body.rMedicationId){
        errors.push("No rMedicationId specified");
    }
    if (!req.body.rPatientId){
        errors.push("No rPatientId specified");
    }
    if (errors.length){
        res.status(400).json({ message: errors.join(",") })
        return;
    }
    try {
        //check that patient exist
        var patient=await userServ.getUserById(req.body.rPatientId);
        //check that the medication exists
        var medication=await medicationServ.getMedicationById(req.body.rMedicationId);
        var review=await reviewServ. getByPrescIdMedIdPatientId(req.body.rPrescriptionId,req.body.rMedicationId,req.body.rPatientId);
        if(review!=null){
            res.json(
                review
            )
        return; 
        }
        else{
            res.status(400).json({ message: 'Review does not exist' })
            return;
        }
    } catch (error) {
        res.status(400).json({ message: error.message })
    }

}

async function checkIfPrescIdMedIdPatientIdExist(req, res, next){
    var errors=[]
    console.log("post in reviews/check")
    //checking that we got all params
    if (!req.body.rPrescriptionId){
        errors.push("No rPrescriptionId specified");
    }
    if (!req.body.rMedicationId){
        errors.push("No rMedicationId specified");
    }
    if (!req.body.rPatientId){
        errors.push("No rPatientId specified");
    }
    if (errors.length){
        res.status(400).json({ message: errors.join(",") })
        return;
    }
    try {
        
        //check that patient exist
        var patient=await userServ.getUserById(req.body.rPatientId);
        //check that the medication exists
        var medication=await medicationServ.getMedicationById(req.body.rMedicationId);
        var result=await reviewServ.checkIfPrescIdMedIdPatientIdExist(req.body.rPrescriptionId,req.body.rMedicationId,req.body.rPatientId);
        if(result!=null){
            
            res.json(result)
            return; 
        }
        else{
            res.status(400).json({ message: 'There was an error' })
            return;
        }
    } catch (error) {
        console.log(error)
        res.status(400).json({ message: error.message})
    }     

}


async function addReview(req, res, next) {
    var errors=[]
    console.log("post in reviews/add")
    //checking that we got all params
    if (!req.body.rMedicationId){
        errors.push("No rMedicationId specified");
    }
    if (!req.body.rPatientId){
        errors.push("No rPatientId specified");
    }
    if (!req.body.rReactionObserved){
        errors.push("No rReactionObserved specified");
    }
    if (!req.body.rOverallFeel){
        errors.push("No rOverallFeel specified");
    }
    if (!req.body.rPrescriptionId){
        errors.push("No rPrescriptionId specified");
    }
    if (errors.length){
        res.status(400).json({ message: errors.join(",") })
        return;
    }
    if(parseInt(req.body.rPrescriptionId) ===-100){
        //format with all the parameters
        var errors2=[]
        if (!req.body.rMedQuantity){
            errors2.push("No rMedQuantity specified");
        }
        if (!req.body.rTakingMotive){
            errors2.push("No rTakingMotive specified");
        }
        if (!req.body.rMedStartDate){
            errors2.push("No rMedStartDate specified");
        }
        if (!req.body.rMedEndDate){
            errors2.push("No rMedEndDate specified");
        }
        if (errors2.length){
            res.status(400).json({ message: errors2.join(",") })
            return;
        }
        try {
            //check that patient exist
            var patient=await userServ.getUserById(req.body.rPatientId);
            //check that the medication exists
            var medication=await medicationServ.getMedicationById(req.body.rMedicationId);
            var review=await reviewServ.addReviewWithoutPrescription(req.body.rPatientId,req.body.rMedicationId,req.body.rReactionObserved,req.body.rOverallFeel,req.body.rMedQuantity,req.body.rTakingMotive,req.body.rMedStartDate,req.body.rMedEndDate);
            if(review!=null){
                var data = {
                    rId:review.rId,
                    rPrescriptionId:review.rPrescriptionId,
                    rPatientId:review.rPatientId,
                    rMedicationId:review.rMedicationId,
                    rReactionObserved:review.rReactionObserved,
                    rOverallFeel:review.rOverallFeel,
                    rMedQuantity:review.rMedQuantity,
                    rTakingMotive:review.rTakingMotive,
                    rMedStartDate:review.rMedStartDate,
                    rMedEndDate:review.rMedEndDate
                }
                res.json({"data": data,
                    "rId" : review.rId})
            return; 
            }
            else{
                res.status(400).json({ message: 'There was an error while adding the review' })
                return;
            }
        } catch (error) {
            console.log(error)
            res.status(400).json({ message: error.message})
        }     
    }
    else{
        //add with prescription
        try {
            console.log("add with presc route")
            //check that patient exist
            var patient=await userServ.getUserById(req.body.rPatientId);
            //check that the medication exists
            var medication=await medicationServ.getMedicationById(req.body.rMedicationId);
            var review=await reviewServ.addReviewWithPrescription(req.body.rPrescriptionId, req.body.rPatientId,req.body.rMedicationId,req.body.rReactionObserved,req.body.rOverallFeel);
            if(review!=null){
                var data = {
                    rId:review.rId,
                    rPrescriptionId:review.rPrescriptionId,
                    rPatientId:review.rPatientId,
                    rMedicationId:review.rMedicationId,
                    rReactionObserved:review.rReactionObserved,
                    rOverallFeel:review.rOverallFeel,
                    rMedQuantity:review.rMedQuantity,
                    rTakingMotive:review.rTakingMotive,
                    rMedStartDate:review.rMedStartDate,
                    rMedEndDate:review.rMedEndDate
                }
                res.json({"data": data,
                    "rId" : review.rId})
            return; 
            }
            else{
                res.status(400).json({ message: 'There was an error while adding the review' })
                return;
            }
        } catch (error) {
            console.log(error)
            res.status(400).json({ message: error.message})
        } 
    }
    
}

async function getReviewById(req, res, next){
    console.log("get in reviews/:id");
    var errors=[]
    //checking that we got param
    if (!req.params.id){
        errors.push("No rId specified");
    }
    if (errors.length){
        res.status(400).json({ message:errors })
        return;
    }
    try {
        var review=await reviewServ.getReviewById(req.params.id);
        if(review!=null){
            res.json(
                review
            )
        return; 
        }
        else{
            res.status(400).json({ message: 'Review does not exist' })
            return;
        }
    } catch (error) {
        res.status(400).json({ message: 'Review does not exist' })
    }

}




