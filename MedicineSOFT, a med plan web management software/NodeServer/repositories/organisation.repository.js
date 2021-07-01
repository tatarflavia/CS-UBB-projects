const sqlite3 = require('sqlite3');
var md5 = require("md5")


class OrganisationRepository {
    
    constructor(){
    //reference to the db script
        this.databaseFile = require('../_helpers/database');
    }

    async getOrganisationById(oId){
        try {
            sqlite3.verbose();
            const db=await this.databaseFile.createDbConnection('medUserIntDB');
            var sql = "select * from Organisations where oId = ?"
            var params = [oId]
            const row= await db.get(sql, params);
            if(row!=null){
                console.log("row in repo:"+JSON.stringify(row))
                return row;
            }
            else{
                  throw new Error('No such organisation in the db');
            }
        } catch (error) {
            throw new Error('Error retrieving organisation with id from db');
        }

    }

       
  }


module.exports = {OrganisationRepository:OrganisationRepository}