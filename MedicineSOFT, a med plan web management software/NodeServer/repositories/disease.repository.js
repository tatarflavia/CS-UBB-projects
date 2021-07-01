const sqlite3 = require('sqlite3');
var md5 = require("md5")


class DiseaseRepository {
    
    constructor(){
    //reference to the db script
        this.databaseFile = require('../_helpers/database');
    }

    async getAllDiseasesByUserId(dPatientId){
        console.log("get disease by uId incepe la repo");
        try{
            sqlite3.verbose();
            const db=await this.databaseFile.createDbConnection('medUserIntDB')
            var sql = "select * from Diseases where dPatientId=?"
            var params = [dPatientId]
            //db.all retrives all rows from the sql query
            const rows=await db.all(sql, params);
            return rows;
        }
        catch (error) {
            console.log(error)
            throw new Error('Error retrieving diseases from db');
        }
    }

    

    

    

    async checkIfDiseaseExistsAlready(disease){
        try {
            sqlite3.verbose();
            const db=await this.databaseFile.createDbConnection('medUserIntDB');
            var sql = "select count(*) from Diseases where dPatientId = ? and dName=?"
            var params = [disease.dPatientId,disease.dName]
            const count= await db.get(sql, params);
            var rowCount=parseInt(count["count(*)"]) ;
            if(rowCount===0){
                console.log("Disease does not exist for the patient")
                return false;
            }
            else{
                  throw new Error('There is already a Disease for this patient in the db');
            }
        } catch (error) {
            throw new Error(error.message);
        }
    }

    async getLastId(){
        try {
            sqlite3.verbose();
            const db=await this.databaseFile.createDbConnection('medUserIntDB');
            var sql = "select max(dId) from Diseases"
            var params = []
            const answer= await db.get(sql, params);
            console.log(answer["max(dId)"]);
            var lastId=parseInt(answer["max(dId)"]) ;
            return lastId;
        } catch (error) {
            throw new Error('Error retrieving max(dId) from db');
        }
    }

    async addDisease(disease){
        try {
            sqlite3.verbose();
            const db=await this.databaseFile.createDbConnection('medUserIntDB');
            var sql ='INSERT INTO Diseases (dPatientId,dName,dType) VALUES (?,?,?)'
            var params =[disease.dPatientId,disease.dName,disease.dType]
            await db.run(sql, params);
            disease.id=await this.getLastId();
            return disease;
        } catch (error) {
            throw new Error('Error inserting disease in db');
        }
    }

    

       
  }


module.exports = {DiseaseRepository:DiseaseRepository}