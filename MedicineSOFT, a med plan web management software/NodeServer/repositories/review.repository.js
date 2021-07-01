const sqlite3 = require('sqlite3');
var md5 = require("md5")


class ReviewRepository {
    
    constructor(){
    //reference to the db script
        this.databaseFile = require('../_helpers/database');
    }

    async getReviewsByUserId(uId){
        console.log("get review by uId incepe la repo");
        try{
            sqlite3.verbose();
            const db=await this.databaseFile.createDbConnection('medUserIntDB')
            var sql = "select * from Reviews where rPatientId=?"
            var params = [uId]
            //db.all retrives all rows from the sql query
            const rows=await db.all(sql, params);
            return rows;
        }
        catch (error) {
            console.log(error)
            throw new Error('Error retrieving reviews from db');
        }
    }

    async checkIfReviewExistsAlready(rPrescriptionId,rPatientId,rMedicationId){
        try {
            sqlite3.verbose();
            const db=await this.databaseFile.createDbConnection('medUserIntDB');
            var sql = "select count(*) from Reviews where rPrescriptionId = ? and rPatientId=? and rMedicationId=?"
            var params = [rPrescriptionId,rPatientId,rMedicationId]
            const count= await db.get(sql, params);
            var rowCount=parseInt(count["count(*)"]) ;
            if(rowCount===0){
                console.log("Review unique")
                return false;
            }
            else{
                  throw new Error('There is already a review for this med in the db');
            }
        } catch (error) {
            
            throw new Error(error.message);
        }
    }

    async checkIfPrescIdMedIdPatientIdExist(rPrescriptionId,rMedicationId,rPatientId){
        try {

            sqlite3.verbose();
            const db=await this.databaseFile.createDbConnection('medUserIntDB');
            //first get prescription
            var sql = "select * from Prescriptions where pId = ?"
            var params = [rPrescriptionId]
            var prescription= await db.get(sql, params);
            if(prescription!=null){
               var sql = "select count(*) from Reviews where rPrescriptionId = ? and rPatientId=? and rMedicationId=?"
            var params = [rPrescriptionId,rPatientId,rMedicationId]
            const count= await db.get(sql, params);
            var rowCount=parseInt(count["count(*)"]) ;
            if(rowCount===0){
                console.log("Review unique")
                return false;
            }
            else{
                  return true;
            } 
            }
            else{
                throw new Error('No such prescription in the db');
            }
            
        } catch (error) {
            
            throw new Error(error.message);
        }
    }

    
    async getLastId(){
        try {
            sqlite3.verbose();
            const db=await this.databaseFile.createDbConnection('medUserIntDB');
            var sql = "select max(rId) from Reviews"
            var params = []
            const answer= await db.get(sql, params);
            console.log(answer["max(rId)"]);
            var lastId=parseInt(answer["max(rId)"]) ;
            return lastId;
        } catch (error) {
            throw new Error('Error retrieving max(rId) from db');
        }
    }

    async addReview(review){
        try {
            sqlite3.verbose();
            const db=await this.databaseFile.createDbConnection('medUserIntDB');
            //case with review from prescription
            if(review.rPrescriptionId!==null && review.rPrescriptionId!==undefined)
            {
                //patient and medication have been verified already to exist
                //first get prescription
                var sql = "select * from Prescriptions where pId = ?"
                var params = [review.rPrescriptionId]
                var prescription= await db.get(sql, params);
                if(prescription!=null){
                    //then get medListMember for medQuantity
                    var sql = "select mlHowOften from MedLists where mlPrescriptionId = ? and mlMedicationId=?"
                    var params = [review.rPrescriptionId,review.rMedicationId]
                    var medListMember= await db.get(sql, params);
                    if(medListMember!==null){
                        //now make the insert
                        //check if the med was used at all
                        if(prescription["pStartDate"]!==null){
                            //check if the status is finished or dismissed to know which date to put in the review for endDate
                            if(prescription["pStatus"]==="Dismissed"){
                                var sql ='INSERT INTO Reviews(rPrescriptionId,rMedicationId,rPatientId,rReactionObserved,rOverallFeel,rMedQuantity,rTakingMotive,rMedStartDate,rMedEndDate) VALUES (?,?,?,?,?,?,?,?,date(\'now\'))'
                                var params =[review.rPrescriptionId,review.rMedicationId,review.rPatientId,review.rReactionObserved,review.rOverallFeel,medListMember["mlHowOften"],prescription["pDiagnosis"],prescription["pStartDate"]]
                                await db.run(sql, params);
                                review.id=await this.getLastId();
                                review.medQuantity=medListMember["mlHowOften"];
                                review.takingMotive=prescription["pDiagnosis"];
                                review.medStartDate=prescription["pStartDate"];
                                var today = new Date();
                                var dd = String(today.getDate()).padStart(2, '0');
                                var mm = String(today.getMonth() + 1).padStart(2, '0');
                                var yyyy = String(today.getFullYear());
                                var today2 = yyyy + '-' +mm + '-' + dd;
                                review.medEndDate=today2;
                                return review;
                            }
                            else if(prescription["pStatus"]==="Finished"){
                                var sql ='INSERT INTO Reviews(rPrescriptionId,rMedicationId,rPatientId,rReactionObserved,rOverallFeel,rMedQuantity,rTakingMotive,rMedStartDate,rMedEndDate) VALUES (?,?,?,?,?,?,?,?,?)'
                                var params =[review.rPrescriptionId,review.rMedicationId,review.rPatientId,review.rReactionObserved,review.rOverallFeel,medListMember["mlHowOften"],prescription["pDiagnosis"],prescription["pStartDate"],prescription["pEndDate"]]
                                await db.run(sql, params);
                                review.id=await this.getLastId();
                                review.medQuantity=medListMember["mlHowOften"];
                                review.takingMotive=prescription["pDiagnosis"];
                                review.medStartDate=prescription["pStartDate"];
                                review.medEndDate=prescription["pEndDate"];
                                return review;
                            }
                            else{
                                throw new Error('You can not make a review');
                            }
                        }
                        else{
                            throw new Error('You can not make a review for an unused med');
                        }
                    }
                    else{
                        throw new Error('That med is not in this prescription in the db');
                    }
                }
                else{
                    throw new Error('No such prescription in the db');
                }

            }
            else{
                //case with new random med review
                //we have medId,patientId,reactionObs,OverallFeel,MedQuantity,TakingMotive,MedStartDate,MedEndDate
                var sql ='INSERT INTO Reviews(rMedicationId,rPatientId,rReactionObserved,rOverallFeel,rMedQuantity,rTakingMotive,rMedStartDate,rMedEndDate) VALUES (?,?,?,?,?,?,?,?)'
                var params =[review.rMedicationId,review.rPatientId,review.rReactionObserved,review.rOverallFeel,review.rMedQuantity,review.rTakingMotive,review.rMedStartDate,review.rMedEndDate]
                await db.run(sql, params);
                review.id=await this.getLastId();
                return review;
            }

            
        } catch (error) {
            throw new Error(error.message);
        }
    }






    
    async getReviewById(rId){
        try {
            sqlite3.verbose();
            const db=await this.databaseFile.createDbConnection('medUserIntDB');
            var sql = "select * from Reviews where rId = ?"
            var params = [rId]
            const row= await db.get(sql, params);
            if(row!=null){
                console.log("row in repo:"+JSON.stringify(row))
                return row;
            }
            else{
                  throw new Error('No such review in the db');
            }
        } catch (error) {
            throw new Error('Error retrieving review with id from db');
        }

    }

    async getByPrescIdMedIdPatientId(rPrescriptionId,rMedicationId,rPatientId){
        try {
            sqlite3.verbose();
            const db=await this.databaseFile.createDbConnection('medUserIntDB');
            var sql = "select * from Prescriptions where pId = ?"
            var params = [rPrescriptionId]
            var prescription= await db.get(sql, params);
            if(prescription!=null){
                var sql = "select * from Reviews where rPrescriptionId = ? and rPatientId=? and rMedicationId=?"
            var params = [rPrescriptionId,rPatientId,rMedicationId];
            const row= await db.get(sql, params);
            if(row!=null){
                console.log("row in repo:"+JSON.stringify(row))
                return row;
            }
            else{
                  throw new Error('No such review in the db');
            }
            }
            else{
                throw new Error('No such prescription in the db');
            }
            
        } catch (error) {
            throw new Error(error.message);
        }

    }


       
  }


module.exports = {ReviewRepository:ReviewRepository}