const express = require('express');
const router = express.Router();
var md5 = require("md5")
const crawler = require('crawler-request');
let repo=require('../repositories/user.repository')
let UserRepository=repo.UserRepository;
let userRepo=new UserRepository();
let validator=require('../validators/user.validator')
let UserValidator=validator.UserValidator;
let userValid=new UserValidator();
let serv=require('../services/user.service');
let UserService=serv.UserService;
let userServ=new UserService(userRepo,userValid);
let repoFileDis=require('../repositories/disease.repository')
let DiseaseRepository=repoFileDis.DiseaseRepository;
let diseaseRepo=new DiseaseRepository();
let validatorFileDis=require('../validators/disease.validator')
let DiseaseValidator=validatorFileDis.DiseaseValidator;
let diseaseValid=new DiseaseValidator();
let servFileDis=require('../services/disease.service');
let DiseaseService=servFileDis.DiseaseService;
let diseaseServ=new DiseaseService(diseaseRepo,diseaseValid);
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
router.post('/login', authenticateUser);
router.post('/register', registerUser);
router.post('/addDoctor',addDoctorByDocNumber);
router.get('/', getAll);
router.get('/:id',getUserById);
router.post('/desc',getPersonalisedDescForUser)




module.exports = router;






async function authenticateUser(req, res, next) {
    console.log("post in users/login");
   
    var errors=[]
    //checking that we got both params
    if (!req.body.uPassword){
        errors.push("No password specified");
    }
    if (!req.body.uDocNumber){
        errors.push("No document number specified");
    }
    if (errors.length){
        res.status(400).json({ message:errors.join(",") })
        return;
    }
    try {
        var user=await userServ.authenticateUserByCredentials(req.body.uDocNumber,req.body.uPassword);
        if(user!=null){
            res.json(
              user
            )
        return; 
        }
        else{
            res.status(400).json({ message: 'Document number or password is incorrect' })
            return;
        }
    } catch (error) {
        res.status(400).json({ message: 'Document number or password is incorrect' })
    }
}

async function registerUser(req, res, next) {
    var errors=[]
    console.log("post in users/register")
    //checking that we got all params
    if (!req.body.uName){
        errors.push("No name specified");
    }
    if (!req.body.uEmail){
        errors.push("No email specified");
    }
    if (!req.body.uBirthday){
        errors.push("No birthday specified");
    }
    if (!req.body.uInsuredCode){
        errors.push("No insured code specified");
    }
    if (!req.body.uDocNumber){
        errors.push("No document number specified");
    }
    if (!req.body.uExpirationDate){
        errors.push("No expiration date specified");
    }
    if (!req.body.uPassword){
        errors.push("No password specified");
    }
    if (errors.length){
        res.status(400).json({ message: errors.join(",") })
        return;
    }
    try {
        var user=await userServ.registerUser(req.body.uName,
            req.body.uEmail,req.body.uBirthday,req.body.uInsuredCode,req.body.uDocNumber,req.body.uExpirationDate,req.body.uPassword);
        if(user!=null){
            var data = {
                uId:user.uId,
                uName:user.uName,
                uEmail:user.uEmail,
                uBirthday:user.uBirthday,
                uInsuredCode:user.uInsuredCode,
                uDocNumber: user.uDocNumber,
                uExpirationDate:user.uExpirationDate,
                uPassword : md5(user.uPassword),
                uIsDoctor:user.uIsDoctor ,
                uIsAdmin:user.uIsAdmin
            }
            res.json({"data": data,
                "uId" : user.uId})
        return; 
        }
        else{
            res.status(400).json({ message: 'There was an error while adding the user' })
            return;
        }
    } catch (error) {
        //console.log(error)
        res.status(400).json({ message: error.message})
    }     
    
}

async function addDoctorByDocNumber(req,res,next){
    var errors=[]
    if (!req.body.uDocNumber){
        errors.push("No docNumber specified");
    }
    if (errors.length){
        res.status(400).json({ message: errors.join(",") })
        return;
    }
    try {
        var doctor=await userServ.addDoctorByDocNumber(req.body.uDocNumber);
        if(doctor!=null){
            res.json(doctor)
        return; 
        }
        else{
            res.status(400).json({ message: 'There was an error while adding the doctor' })
            return;
        }
    } catch (error) {
        res.status(400).json({ message: error.message})
    }    

}

async function getUserById(req, res, next){
    console.log("get in users/:id");
    var errors=[]
    //checking that we got param
    if (!req.params.id){
        errors.push("No uId specified");
    }
    if (errors.length){
        res.status(400).json({ message:errors })
        return;
    }
    try {
        var user=await userServ.getUserById(req.params.id);
        if(user!=null){
            res.json(
              user
            )
        return; 
        }
        else{
            res.status(400).json({ message: 'User does not exist' })
            return;
        }
    } catch (error) {
        res.status(400).json({ message: 'User does not exist' })
    }

}


function getAll(req, res, next) {
    console.log("get in users");
    userServ.getAllUsers(req.query)
        .then(users => users ? res.json(users) : res.status(400).json({ message: 'No such user' }))
        .catch(err => next(err));
}

function getIndicesOf(searchStr, str) {
    var searchStrLen = searchStr.length;
    var startIndex = 0, index, indices = [];
    while ((index = str.indexOf(searchStr, startIndex)) > -1) {
        indices.push(index);
        startIndex = index + searchStrLen;
    }
    console.log(indices);
    return indices;
  }


  //var localeIndexOf =require('locale-index-of')(Intl);

async function getPersonalisedDescForUser(req, res, next) {
    var errors=[]
    if (!req.body.uId){
        errors.push("No uId specified");
    }
    if (!req.body.mId){
        errors.push("No medicationId specified");
    }
    if (errors.length){
        res.status(400).json({ message: errors.join(",") })
        return;
    }
    try {
        var user=await userServ.getUserById(req.body.uId);
        if(user!=null){
            //now we can look for the desired desc
            //check the medID
            var medication=await medicationServ.getMedicationById(req.body.mId);
            if(medication!=null){
                //get pdf of the prospectus from the link
                var download = require('download-pdf')
                var pdf = medication["mProspectusLinkRO"];
                var options = {
                    directory: "./pdfs/",
                    filename: "sample.pdf"
                }

                //var downloadFile=await download(pdf,options);
                download(pdf, options, function(err){
                    if (err) throw err
                    const fs=require('fs')
                const pdfparse=require('pdf-parse')
                const pdfFile=fs.readFileSync('./pdfs/sample.pdf')
                
                //get the diseases of the user
                var stringFinalResult="";

                diseaseServ.getAllDiseasesByUserId(req.body.uId).then( async diseases => {
                    console.log("a ajuns la callback diseases")
                    if(diseases.length!==0){
                            for(var i=0;i<diseases.length;i++){
                            var disString=diseases[i]["dName"].substring(0,diseases[i]["dName"].length-1);
                            //make the search now 
                            //get text from online pdf prospectus link
                            // crawler(medication["mProspectusLinkRO"]).then(function(data){
                            //     if(data.length!==0){
                            //         // search the text
                            //         var text=data.text;
                            //         //start searching indexes of the 
                            //         var startIndex = 0, index, indices = [];
                            //         text = text.toLowerCase();
                            //         disString = disString.toLowerCase();
                            //         while ((index = text.indexOf(disString, startIndex)) > -1) {
                            //             indices.push(index);
                            //             startIndex = index + data.length;
                            //         }
                            //         //now we have indices, we search next for the words near them
                            //         for(var j=0;j<indices.length;j++){
                            //             var pos=parseInt(indices[j]) ;
                            //             console.log(data.text.substr(pos-100,pos+500));
                            //             console.log("\n\n\n")
                            //             //stringFinalResult+=data.text.substr(pos-100,pos+500);
                            //             //stringFinalResult+="\n\n\n\n"
                            //         }
                            //         return res.json(stringFinalResult);
                            //     }
                                
                            // });
                            await pdfparse(pdfFile).then(function async (data) {
                                console.log("a ajuns la callback pdfparse")
                                if(typeof data.text===null || typeof data.text===undefined){
                                    throw new Error('File can not be read')
                                }
                                // search the text
                            var text=data.text;
                            
                                            
                                //start searching indexes of the 
                                
                            var indices=getIndicesOf(disString,text);
                            
                            console.log("a gasit indicii")
                                //now we have indices, we search next for the words near them
                            for(var j=0;j<indices.length;j++){
                                                var pos=parseInt(indices[j]) ;
                                                var pos1=pos-300;
                                                var pos2=pos+700;
                                                var substring=text.substring(pos1,pos2);
                                                stringFinalResult+=substring;
                                                stringFinalResult+="\n\n\n\n\n\n\n\n"
                                            }
                            
                            
                            }) ;
                        }
                        
                        if(stringFinalResult.length===0){
                            stringFinalResult+="empty\n\n\n\n\n\n\n\n";
                            //parse and send all
                            pdfparse(pdfFile).then(function (data) {
                                console.log("a ajuns la callback pdfparse")
                                if(typeof data.text===null || typeof data.text===undefined){
                                    throw new Error('File can not be read')
                                }
                            
                            var text=data.text;
                            stringFinalResult+=text;
                            fs.unlinkSync('./pdfs/sample.pdf');
                            return res.json(stringFinalResult);
                            });
                        }
                        else{
                            fs.unlinkSync('./pdfs/sample.pdf');
                            return res.json(stringFinalResult);
                        }
                        
                    }
                    else{
                        console.log("no diseases");
                        //parse and send all
                        pdfparse(pdfFile).then(function (data) {
                            console.log("a ajuns la callback pdfparse")
                            if(typeof data.text===null || typeof data.text===undefined){
                                throw new Error('File can not be read')
                            }
                        
                        var text=data.text;
                        fs.unlinkSync('./pdfs/sample.pdf');
                        return res.json(text);
                    });
                    }
                    
                })
                }) 

                
            }
            else{
                res.status(400).json({ message: 'Medication does not exist' })
                return;
            }    
        }
        else{
            res.status(400).json({ message: 'User does not exist' })
            return;
        }
    } catch (error) {
        res.status(400).json({ message:error.message})
    }

    



 

    
}


