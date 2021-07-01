const express = require('express');
const router = express.Router();
var md5 = require("md5")
let repo=require('../repositories/medication.repository')
let MedicationRepository=repo.MedicationRepository;
let medicationRepo=new MedicationRepository();
let validator=require('../validators/medication.validator')
let MedicationValidator=validator.MedicationValidator;
let medicationValid=new MedicationValidator();
let serv=require('../services/medication.service');
let MedicationService=serv.MedicationService;
let medicationServ=new MedicationService(medicationRepo,medicationValid);

// routes
router.get('/', getAll);
router.get('/:id',getMedicationById);

module.exports = router;


async function getMedicationById(req, res, next){
    console.log("get in medications/:id");
    var errors=[]
    //checking that we got param
    if (!req.params.id){
        errors.push("No mId specified");
    }
    if (errors.length){
        res.status(400).json({ message:errors })
        return;
    }
    try {
        var medication=await medicationServ.getMedicationById(req.params.id);
        if(medication!=null){
            res.json(
                medication
            )
        return; 
        }
        else{
            res.status(400).json({ message: 'Medication does not exist' })
            return;
        }
    } catch (error) {
        res.status(400).json({ message: 'Medication does not exist' })
    }

}


function getAll(req, res, next) {
    console.log("get in medications");
    medicationServ.getAllMedications(req.query)
        .then(medications => medications ? res.json(medications) : res.status(400).json({ message: 'No such medication' }))
        .catch(err => next(err));
}
