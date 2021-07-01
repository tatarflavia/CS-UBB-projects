const sqlite3 = require('sqlite3');
var md5 = require("md5")


class PrescriptionRepository {
    
    constructor(){
    //reference to the db script
        this.databaseFile = require('../_helpers/database');
    }

    async getAllPrescriptionsByTypeByUserId(pType,uId){
        console.log("get med plan incepe la repo");
        try{
            console.log(pType)
            console.log(uId)
            sqlite3.verbose();
            const db=await this.databaseFile.createDbConnection('medUserIntDB')
            var sql = "select * from Prescriptions where pStatus=? and pPatientId=?"
            var params = [pType,uId]
            //db.all retrives all rows from the sql query
            const rows=await db.all(sql, params);
            return rows;
        }
        catch (error) {
            console.log(error)
            throw new Error('Error retrieving prescriptions from db');
        }
    }

    async getAllPrescriptionsForPatientId(pPatientId,pDoctorId){
        console.log("get med plan incepe la repo");
        try{
            sqlite3.verbose();
            const db=await this.databaseFile.createDbConnection('medUserIntDB')
            var sql = "select * from Prescriptions where pDoctorId=? and pPatientId=? ORDER BY pStatus"
            var params = [pDoctorId,pPatientId]
            //db.all retrives all rows from the sql query
            const rows=await db.all(sql, params);
            return rows;
        }
        catch (error) {
            console.log(error)
            throw new Error('Error retrieving prescriptions for patient from db');
        }
    }

    async getMedListForPrescriptionId(pId){
        console.log("get med list for presc incepe la repo");
        try{
            sqlite3.verbose();
            const db=await this.databaseFile.createDbConnection('medUserIntDB')
            var prescription=await this.getPrescriptionById(pId);
            var sql = "select * from MedLists where mlPrescriptionId=?"
            var params = [pId]
            //db.all retrives all rows from the sql query
            const rows=await db.all(sql, params);
            return rows;
        }
        catch (error) {
            console.log(error)
            throw new Error('Error retrieving medList from db');
        }
    }

    async getMedListMemberByPresIdMedId(mlPrescriptionId,mlMedicationId){
        try{
            sqlite3.verbose();
            const db=await this.databaseFile.createDbConnection('medUserIntDB')
            var sql = "select * from MedLists where mlPrescriptionId=? and mlMedicationId=?"
            var params = [mlPrescriptionId,mlMedicationId]
            //db.all retrives all rows from the sql query
            const rows=await db.get(sql, params);
            return rows;
        }
        catch (error) {
            console.log(error)
            throw new Error(error.message);
        }
    }

    async getPrescriptionById(pId){
        try {
            sqlite3.verbose();
            const db=await this.databaseFile.createDbConnection('medUserIntDB');
            var sql = "select * from Prescriptions where pId = ?"
            var params = [pId]
            const row= await db.get(sql, params);
            if(row!=null){
                console.log("row in repo:"+JSON.stringify(row))
                return row;
            }
            else{
                  throw new Error('No such prescription in the db');
            }
        } catch (error) {
            throw new Error('Error retrieving prescription with id from db');
        }

    }

    async changePrescriptionStatus(pId,pStatus){
        try {
            sqlite3.verbose();
            const db=await this.databaseFile.createDbConnection('medUserIntDB');
            var prescInitial=await this.getPrescriptionById(pId);
            if(prescInitial===null || prescInitial===undefined){
                throw new Error("The prescription does not exist")
            }
            if(prescInitial["pStatus"]==="Finished"){
                throw new Error("The prescription is already finished")
            }
            if((prescInitial["pStatus"]==="Not Started" && pStatus!=="Dismissed" && pStatus!=="Ongoing")||
                (prescInitial["pStatus"]==="Ongoing" && pStatus!=="Dismissed" && pStatus!=="Finished") || 
                (prescInitial["pStatus"]==="Dismissed" && pStatus!=="Ongoing" && pStatus!=="Finished")){
                    throw new Error("You can't change the status like this")
                }
            if(pStatus==="Ongoing"){
                var sql ='UPDATE Prescriptions SET pStatus=?,pStartDate=date(\'now\') where pId=?'
                var params =[pStatus,pId]
                await db.run(sql, params);
                var presc=await this.getPrescriptionById(pId);
                return presc;
            }
            else if(pStatus==="Finished" && prescInitial["pStartDate"]!==null){
                var sql ='UPDATE Prescriptions SET pStatus=?,pEndDate=date(\'now\') where pId=?'
                var params =[pStatus,pId]
                await db.run(sql, params);
                var presc=await this.getPrescriptionById(pId);
                return presc;
            }
            else{
                var sql ='UPDATE Prescriptions SET pStatus=? where pId=?'
                var params =[pStatus,pId]
                await db.run(sql, params);
                var presc=await this.getPrescriptionById(pId);
                return presc;
            }
        } catch (error) {
            //console.log(error)
            throw new Error(error.message);
        }
    }





    

    async checkIfMedicationExistsAlreadyInPrescription(mlPrescriptionId,mlMedicationId){
        try {
            sqlite3.verbose();
            const db=await this.databaseFile.createDbConnection('medUserIntDB');
            var sql = "select count(*) from MedLists where mlPrescriptionId = ? and mlMedicationId=?"
            var params = [mlPrescriptionId,mlMedicationId]
            const count= await db.get(sql, params);
            var rowCount=parseInt(count["count(*)"]) ;
            if(rowCount===0){
                console.log("Medication on presc unique")
                return false;
            }
            else{
                  throw new Error('There is already this Medication in that prescription');
            }
        } catch (error) {
            
            throw new Error(error.message);
        }
    }

    async getLastIdForMedListsTable(){
        try {
            sqlite3.verbose();
            const db=await this.databaseFile.createDbConnection('medUserIntDB');
            var sql = "select max(mlId) from MedLists"
            var params = []
            const answer= await db.get(sql, params);
            console.log(answer["max(mlId)"]);
            var lastId=parseInt(answer["max(mlId)"]) ;
            return lastId;
        } catch (error) {
            throw new Error('Error retrieving max(mlId) from db');
        }
    }

    async getLastIdForPrescriptionsTable(){
        try {
            sqlite3.verbose();
            const db=await this.databaseFile.createDbConnection('medUserIntDB');
            var sql = "select max(pId) from Prescriptions"
            var params = []
            const answer= await db.get(sql, params);
            console.log(answer["max(pId)"]);
            var lastId=parseInt(answer["max(pId)"]) ;
            return lastId;
        } catch (error) {
            throw new Error('Error retrieving max(pId) from db');
        }
    }

    async addPrescriptionForPatient(prescription){
        try {
            sqlite3.verbose();
            const db=await this.databaseFile.createDbConnection('medUserIntDB');
            var sql ='INSERT INTO Prescriptions(pStatus,pPatientId,pDoctorId,pDiagnosis) VALUES (?,?,?,?)'
            var params =[prescription.pStatus,prescription.pPatientId,prescription.pDoctorId,prescription.pDiagnosis]
            await db.run(sql, params);
            prescription.id=await this.getLastIdForPrescriptionsTable();
            return prescription;
        } catch (error) {
            throw new Error('Error inserting prescription in db');
        }
    }

    async addMedicationForPrescription(medListMember){
        try {
            sqlite3.verbose();
            const db=await this.databaseFile.createDbConnection('medUserIntDB');
            var sql ='INSERT INTO Medlists(mlPrescriptionId,mlMedicationId,mlMedicationName,mlHowOften) VALUES (?,?,?,?)'
            var params =[medListMember.mlPrescriptionId,medListMember.mlMedicationId,medListMember.mlMedicationName,medListMember.mlHowOften]
            await db.run(sql, params);
            medListMember.id=await this.getLastIdForMedListsTable();
            return medListMember;
        } catch (error) {
            throw new Error('Error inserting medication into prescription in db');
        }
    }

    

    

    

    

       
  }


module.exports = {PrescriptionRepository:PrescriptionRepository}