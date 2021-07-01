const sqlite3 = require('sqlite3');
var md5 = require("md5")


class OrganisationBelongingsRepository {
    
    constructor(){
    //reference to the db script
        this.databaseFile = require('../_helpers/database');
    }

    async getAllOrganisationsByDoctorIdLessPatientId(bDoctorId,bPatientId){
      console.log("get organisations by DoctorId less bPatientId incepe la repo");
      try{
          sqlite3.verbose();
          const db=await this.databaseFile.createDbConnection('medUserIntDB')
          var sql = "select * from Organisations where oId IN(select DISTINCT bOrganisationId from OrganisationBelongings where bDoctorId=? except select DISTINCT bOrganisationId from OrganisationBelongings where bPatientId=?)"
          var params = [bDoctorId,bPatientId]
          //db.all retrives all rows from the sql query
          const rows=await db.all(sql, params);
          return rows;
      }
      catch (error) {
          console.log(error)
          throw new Error('Error retrieving organisations from db');
      }
  }

  async getAllOrganisationsByDoctorId(bDoctorId){
    console.log("get organisations by DoctorId incepe la repo");
    try{
        sqlite3.verbose();
        const db=await this.databaseFile.createDbConnection('medUserIntDB')
        var sql = "select * from Organisations where oId IN(select DISTINCT bOrganisationId from OrganisationBelongings where bDoctorId=?)"
        var params = [bDoctorId]
        //db.all retrives all rows from the sql query
        const rows=await db.all(sql, params);
        return rows;
    }
    catch (error) {
        console.log(error)
        throw new Error('Error retrieving organisations from db');
    }
}

async getAllOrganisationBelongingsByDoctorId(bDoctorId){
    console.log("get organisations by DoctorId incepe la repo");
    try{
        sqlite3.verbose();
        const db=await this.databaseFile.createDbConnection('medUserIntDB')
        var sql = "select * from OrganisationBelongings where bDoctorId=?"
        var params = [bDoctorId]
        //db.all retrives all rows from the sql query
        const rows=await db.all(sql, params);
        return rows;
    }
    catch (error) {
        console.log(error)
        throw new Error('Error retrieving organisationBelongings from db');
    }
}

async getOrgBelongsForDoctorPatient(bDoctorId,bPatientId){
    try{
        sqlite3.verbose();
        const db=await this.databaseFile.createDbConnection('medUserIntDB')
        var sql = "select * from OrganisationBelongings where bDoctorId=? and bPatientId=?"
        var params = [bDoctorId,bPatientId]
        //db.all retrives all rows from the sql query
        const rows=await db.all(sql, params);
        return rows;
    }
    catch (error) {
        console.log(error)
        throw new Error('Error retrieving organisationBelongings from db');
    }
}



  async getLastId(){
    try {
        sqlite3.verbose();
        const db=await this.databaseFile.createDbConnection('medUserIntDB');
        var sql = "select max(bId) from OrganisationBelongings"
        var params = []
        const answer= await db.get(sql, params);
        console.log(answer["max(bId)"]);
        var lastId=parseInt(answer["max(bId)"]) ;
        return lastId;
    } catch (error) {
        throw new Error('Error retrieving max(bId) from db');
    }
}

async addOrgBelonging(orgBelonging){
    try {
        sqlite3.verbose();
        const db=await this.databaseFile.createDbConnection('medUserIntDB');
        var sql ='INSERT INTO OrganisationBelongings(bDoctorId,bOrganisationId,bPatientId) VALUES (?,?,?)'
        var params =[orgBelonging.bDoctorId,orgBelonging.bOrganisationId,orgBelonging.bPatientId]
        await db.run(sql, params);
        orgBelonging.id=await this.getLastId();
        return orgBelonging;
    } catch (error) {
        throw new Error('Error inserting belonging in db');
    }
}

  async checkIfOrgBelongingExistsAlready(orgBelonging){
    try {
        sqlite3.verbose();
        const db=await this.databaseFile.createDbConnection('medUserIntDB');
        var sql = "select count(*) from OrganisationBelongings where bDoctorId = ? and bOrganisationId=? and bPatientId=?"
        var params = [orgBelonging.bDoctorId,orgBelonging.bOrganisationId,orgBelonging.bPatientId]
        const count= await db.get(sql, params);
        var rowCount=parseInt(count["count(*)"]) ;
        if(rowCount===0){
            console.log("Belonging does not exist for the patient")
            return false;
        }
        else{
              throw new Error('The patient is already in that organisation');
        }
    } catch (error) {
        throw new Error(error.message);
    }
}

async getAllPatientsByDoctorId(bDoctorId){
  console.log("get patients by DoctorId incepe la repo");
  try{
      sqlite3.verbose();
      const db=await this.databaseFile.createDbConnection('medUserIntDB')
      var sql = "select * from Users where uId IN(select bPatientId from OrganisationBelongings where bDoctorId=? order by bOrganisationId)"
      var params = [bDoctorId]
      //db.all retrives all rows from the sql query
      const rows=await db.all(sql, params);
      return rows;
  }
  catch (error) {
      console.log(error)
      throw new Error('Error retrieving users from db');
  }
}
//to know how many patients in one org for this doctor
async getAllPatientsCountByDoctorId(bDoctorId){
  console.log("get count(patients) by DoctorId incepe la repo");
  try{
      sqlite3.verbose();
      const db=await this.databaseFile.createDbConnection('medUserIntDB')
      var sql = "SELECT count(bOrganisationId) as patientCount,bOrganisationId FROM OrganisationBelongings where bDoctorId=? group by bOrganisationId ORDER BY bOrganisationId"
      var params = [bDoctorId]
      //db.all retrives all rows from the sql query
      const rows=await db.all(sql, params);
      if(rows.length===0){
        console.log("empty");
        return null;
      }
      else{
        return rows;
      }
  }
  catch (error) {
      console.log(error)
      throw new Error('Error retrieving users from db');
  }
}





    

       
  }


module.exports = {OrganisationBelongingsRepository:OrganisationBelongingsRepository}