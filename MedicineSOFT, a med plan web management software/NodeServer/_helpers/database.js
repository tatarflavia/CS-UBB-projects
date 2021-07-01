//create the db connection + init it in this file
var sqlite3 = require('sqlite3').verbose()
const { open } = require('sqlite');
//const { open } = require('sqlite');
//md5 creates a hash for passwords
var md5 = require('md5')
//db file
const DBSOURCE = "medUserIntDB"



//init of db
//by default, it creates it if it does not exist
//err is null if everything is ok
let db = new sqlite3.Database(DBSOURCE, (err) => {
    if (err) {
      // Cannot open database
      console.error(err.message)
      throw err
    }else{
        console.log('Connected to the SQLite database.')
        db.run(`CREATE TABLE Users (
            uId INTEGER PRIMARY KEY AUTOINCREMENT,
            uName text,
            uEmail text,
            uBirthday text,
            uInsuredCode text UNIQUE,
            uDocNumber text UNIQUE, 
            uExpirationDate text,
            uPassword text, 
            uIsDoctor integer,
            uIsAdmin integer,
            CONSTRAINT doc_unique UNIQUE (uDocNumber)
            CONSTRAINT insured_unique UNIQUE (uInsuredCode)
            )`,
        (err) => {
            if (err) {
                // Table already created
            }else{
                // Table just created, creating some rows
                var insert = 'INSERT INTO Users (uId,uName,uEmail,uBirthday,uInsuredCode,uDocNumber,uExpirationDate,  uPassword,uIsDoctor,uIsAdmin) VALUES (?,?,?,?,?,?,?,?,?,?)'
                db.run(insert, [1,"Tatar Flavia-Andreea","tatar.flavia25@gmail.com","1998-12-04","40509361323668999104","111111","2022-07-31",md5("1111"),0,0])
                db.run(insert, [2,"Stan George","stan.george@gmail.com","1999-07-07","40509361323668999103","222222","2022-07-07",md5("2222"),1,1])
                db.run(insert, [3,"Bogdan Andrei","bogdan.andrei@gmail.com","1998-08-08","40509361323668999102","333333","2022-07-09",md5("3333"),0,0])
                db.run(insert, [4,"Bogdan Andreea","bogdan.andreea@gmail.com","1995-08-09","40509361323668999101","444444","2023-07-13",md5("4444"),0,0])
                db.run(insert, [5,"Muntean Lavinia","muntean.lavinia@gmail.com","1993-08-04","40509361323668999105","555555","2023-08-09",md5("5555"),0,0])
            }
        });
        db.run(`CREATE TABLE Diseases (
            dId INTEGER PRIMARY KEY AUTOINCREMENT,
            dPatientId integer,
            dName text,
            dType text,
            FOREIGN KEY(dPatientId) REFERENCES Users(uId)
            )`,
        (err) => {
            if (err) {
                // Table already created
            }else{
                // Table just created, creating some rows
                var insert = 'INSERT INTO Diseases (dId,dPatientId,dName,dType) VALUES (?,?,?,?)'
                db.run(insert, [1,1,"pollen allergy","allergic"])
                db.run(insert, [2,3,"asthma","lung disease"])
                
            }
        }); 
        db.run(`CREATE TABLE Organisations(
            oId INTEGER PRIMARY KEY AUTOINCREMENT,
            oName text,
            oType text
            )`,
        (err) => {
            if (err) {
                // Table already created
            }else{
                // Table just created, creating some rows
                var insert = 'INSERT INTO Organisations (oId,oName,oType) VALUES (?,?,?)'
                db.run(insert, [1,"Life Memorial Hospital Bucuresti","Medlife private hospital"])
                db.run(insert, [2,"MEDSANA BUCHAREST MEDICAL CENTER SRL","Medsana private medical center"])
                db.run(insert, [3,"University Hospital Bucharest","state hospital"])
                
            }
        });  
        db.run(`CREATE TABLE OrganisationBelongings (
            bId INTEGER PRIMARY KEY AUTOINCREMENT,
            bDoctorId integer,
            bOrganisationId integer,
            bPatientId integer,
            FOREIGN KEY(bDoctorId) REFERENCES Users(uId),
            FOREIGN KEY(bPatientId) REFERENCES Users(uId),
            FOREIGN KEY(bOrganisationId) REFERENCES Organisations(oId)
            )`,
        (err) => {
            if (err) {
                // Table already created
            }else{
                // Table just created, creating some rows
                var insert = 'INSERT INTO OrganisationBelongings(bId,bDoctorId,bOrganisationId,bPatientId) VALUES (?,?,?,?)'
                db.run(insert, [1,2,1,1])
                db.run(insert, [2,2,1,3])
                
                
            }
        });  
        db.run(`CREATE TABLE Medications (
            mId INTEGER PRIMARY KEY AUTOINCREMENT,
            mName text,
            mProducer text,
            mPharmaceuticalForm text,
            mDCI text,
            mTherapeuticAction text,
            mPackaging text,
            mProspectusLinkRO text
            )`,
        (err) => {
            if (err) {
                // Table already created
            }else{
                // Table just created, creating some rows
                var insert = 'INSERT INTO Medications(mId,mName,mProducer,mPharmaceuticalForm,mDCI,mTherapeuticAction,mPackaging,mProspectusLinkRO) VALUES (?,?,?,?,?,?,?,?)'
                db.run(insert, [1,"5 - FLUOROURACIL EBEWE 50mg/ml","EBEWE PHARMA GES.M.B.H. NFG. KG - AUSTRIA","CONC. PT. SOL. INJ./PERF.","FLUOROURACILUM","ANTIMETABOLITI ANALOGI AI BAZELOR PIRIMIDINICE","Cutie cu 1 flac. din sticla bruna x 5 ml concentrat pt. sol. inj./perf.","https://www.anm.ro/_/_PRO/PRO_7321_22.01.15.pdf"])
                db.run(insert, [2,"ABACAVIR AUROBINDO 300 mg","APL SWIFT SERVICES (MALTA) LTD - MALTA","COMPR. FILM.","ABACAVIRUM","ANTIVIRALE CU ACTIUNE DIRECTA INHIB. NUCLEOZIDICI SI NUCLEOTIDICI AI REVERSTRANSCRIPTAZEI","Cutie cu blist. PVC/Al a 60 compr. film.","https://www.anm.ro/_/_PRO/PRO_13077_31.03.20.pdf"])
          }
        }); 
        db.run(`CREATE TABLE Prescriptions (
            pId INTEGER PRIMARY KEY AUTOINCREMENT,
            pStatus text,
            pPatientId integer,
            pDoctorId integer,
            pStartDate text,
            pEndDate text,
            pDiagnosis text,
            FOREIGN KEY(pPatientId) REFERENCES Users(uId),
            FOREIGN KEY(pDoctorId) REFERENCES Users(uId)
            )`,
        (err) => {
            if (err) {
                // Table already created
            }else{
                // Table just created, creating some rows
                var insert = 'INSERT INTO Prescriptions(pId,pStatus,pPatientId,pDoctorId,pDiagnosis) VALUES (?,?,?,?,?)'
                db.run(insert, [1,"Not Started",1,2,"Allergic Reaction"])
                db.run(insert, [2,"Not Started",3,2,"Skin rash"])
          }
        }); 
        db.run(`CREATE TABLE MedLists (
            mlId INTEGER PRIMARY KEY AUTOINCREMENT,
            mlPrescriptionId integer,
            mlMedicationId integer,
            mlMedicationName text,
            mlHowOften text,
            FOREIGN KEY(mlPrescriptionId) REFERENCES Prescriptions(pId),
            FOREIGN KEY(mlMedicationId) REFERENCES Medications(mId)
            )`,
        (err) => {
            if (err) {
                // Table already created
            }else{
                // Table just created, creating some rows
                var insert = 'INSERT INTO Medlists(mlId,mlPrescriptionId,mlMedicationId,mlMedicationName,mlHowOften) VALUES (?,?,?,?,?)'
                db.run(insert, [1,1,1,"5 - FLUOROURACIL EBEWE 50mg/ml","1 in the morning, 1 at night"])
                db.run(insert, [2,1,2,"ABACAVIR AUROBINDO 300 mg","1 at lunch"])
                db.run(insert, [3,2,2,"ABACAVIR AUROBINDO 300 mg","1 at lunch"])
          }
        }); 
        db.run(`CREATE TABLE Reviews (
            rId INTEGER PRIMARY KEY AUTOINCREMENT,
            rPrescriptionId integer,
            rMedicationId integer,
            rPatientId integer,
            rReactionObserved text,
            rOverallFeel integer,
            rMedQuantity text,
            rTakingMotive text,
            rMedStartDate text,
            rMedEndDate text,
            FOREIGN KEY(rMedicationId) REFERENCES Medications(mId),
            FOREIGN KEY(rPatientId) REFERENCES Users(uId),
            FOREIGN KEY(rPrescriptionId) REFERENCES Prescriptions(pId)
            )`,
        (err) => {
            if (err) {
                // Table already created
            }else{
                // Table just created, creating some rows
                var insert = 'INSERT INTO Reviews(rId,rPrescriptionId,rMedicationId,rPatientId,rReactionObserved,rOverallFeel,rMedQuantity,rTakingMotive,rMedStartDate,rMedEndDate) VALUES (?,?,?,?,?,?,?,?,?,?)'
                db.run(insert, [1,2,2,3,"Nothing observed",5,"1 at lunch","Skin rash","2021-05-30","2021-05-31"])
                //db.run(insert, [2,2,1,3,"Nothing observed",5,"1 in the morning, 1 at night","Skin rash","2021-05-30","2021-05-31"])
                
          }
        }); 
    }
});

module.exports = { db: db,
    createDbConnection:function(filename){
        return open({
            filename,
            driver: sqlite3.Database
        });
    } };