const express = require('express');
const router = express.Router();
var md5 = require("md5")
let repo=require('../repositories/organisation.repository')
let OrganisationRepository=repo.OrganisationRepository;
let organisationRepo=new OrganisationRepository();
let validator=require('../validators/organisation.validator')
let OrganisationValidator=validator.OrganisationValidator;
let organisationValid=new OrganisationValidator();
let serv=require('../services/organisation.service');
let OrganisationService=serv.OrganisationService;
let organisationServ=new OrganisationService(organisationRepo,organisationValid);

// routes
router.get('/:id',getOrganisationById);

module.exports = router;

async function getOrganisationById(req, res, next){
    console.log("get in organisations/:id");
    var errors=[]
    //checking that we got param
    if (!req.params.id){
        errors.push("No id specified");
    }
    if (errors.length){
        res.status(400).json({ message:errors })
        return;
    }
    try {
        var organisation=await organisationServ.getOrganisationById(req.params.id);
        if(organisation!=null){
            res.json(
              organisation
            )
        return; 
        }
        else{
            res.status(400).json({ message: 'Organisation does not exist' })
            return;
        }
    } catch (error) {
        res.status(400).json({ message: 'Organisation does not exist' })
    }

}