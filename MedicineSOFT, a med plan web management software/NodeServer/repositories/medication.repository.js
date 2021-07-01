const sqlite3 = require('sqlite3');
var md5 = require("md5")


class MedicationRepository {
    
    constructor(){
    //reference to the db script
        this.databaseFile = require('../_helpers/database');
    }

    async getAllMedications(){
        console.log("get medications incepe la repo");
        try{
            sqlite3.verbose();
            const db=await this.databaseFile.createDbConnection('medUserIntDB')
            var sql = "select * from Medications ORDER BY mName"
            var params = []
            //db.all retrives all rows from the sql query
            const rows=await db.all(sql, params);
            return rows;
        }
        catch (error) {
            console.log(error)
            throw new Error('Error retrieving medications from db');
        }
    }

    async getAllMedicationsFiltered(filteringStr){
        console.log("get meds filtered incepe la repo");
        try{
            sqlite3.verbose();
            const db=await this.databaseFile.createDbConnection('medUserIntDB')
            var sql = "select * from Medications where (mName like ?) ORDER BY mName"
            var params = ['%'+filteringStr+'%']
            
            //db.all retrives all rows from the sql query
            const rows=await db.all(sql, params);
            if(rows.length===0){
                return null;
            }
            return rows;
        }
        catch (error) {
            console.log(error)
            throw new Error('Error retrieving filtered medications from db');
        }
    }


    async getMedicationById(mId){
        try {
            //console.log(uDocNumber)
            sqlite3.verbose();
            const db=await this.databaseFile.createDbConnection('medUserIntDB');
            var sql = "select * from Medications where mId = ?"
            var params = [mId]
            const row= await db.get(sql, params);
            if(row!=null){
                console.log("row in repo:"+JSON.stringify(row))
                return row;
            }
            else{
                  throw new Error('No such medication in the db');
            }
        } catch (error) {
            //console.log(error)
            throw new Error('Error retrieving medication with Id from db');
        }

    }

       
  }


module.exports = {MedicationRepository:MedicationRepository}