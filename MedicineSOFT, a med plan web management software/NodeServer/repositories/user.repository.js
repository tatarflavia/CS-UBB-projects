const sqlite3 = require('sqlite3');
var md5 = require("md5")


class UserRepository {
    
    constructor(){
    //reference to the db script
        this.databaseFile = require('../_helpers/database');
    }

    async getAllUsers(){
        console.log("get users incepe la repo");
        try{
            sqlite3.verbose();
            const db=await this.databaseFile.createDbConnection('medUserIntDB')
            var sql = "select * from Users"
            var params = []
            //db.all retrives all rows from the sql query
            const rows=await db.all(sql, params);
            return rows;
        }
        catch (error) {
            console.log(error)
            throw new Error('Error retrieving users from db');
        }
    }

    async getAllUsersFiltered(filteringStr){
        console.log("get users filtered incepe la repo");
        try{
            sqlite3.verbose();
            const db=await this.databaseFile.createDbConnection('medUserIntDB')
            var sql = "select * from Users where (uName like ?) or (uDocNumber like ?) or (uEmail like ?) or (uInsuredCode like ?)"
            var params = ['%'+filteringStr+'%','%'+filteringStr+'%','%'+filteringStr+'%','%'+filteringStr+'%']
            
            //db.all retrives all rows from the sql query
            const rows=await db.all(sql, params);
            if(rows.length===0){
                return null;
            }
            return rows;
        }
        catch (error) {
            console.log(error)
            throw new Error('Error retrieving filtered users from db');
        }
    }

    async checkExistingUserByCredentials(uDocNumber,uPassword) {
        try {
            sqlite3.verbose();
            const db=await this.databaseFile.createDbConnection('medUserIntDB');
            var sql = "select * from Users where uDocNumber = ? and uPassword = ?"
            var params = [uDocNumber,md5(uPassword)]
            const row= await db.get(sql, params);
            if(row!=null){
                console.log("row in repo:"+JSON.stringify(row))
                return row;
            }
            else{
                  throw new Error('Row is null, no such row');
            }
        } catch (error) {
            throw new Error('Error retrieving user with credentials from db');
        }

    }

    async checkIfDocNumberExistsAlready(uDocNumber){
        try {
            sqlite3.verbose();
            const db=await this.databaseFile.createDbConnection('medUserIntDB');
            var sql = "select count(*) from Users where uDocNumber = ?"
            var params = [uDocNumber]
            const count= await db.get(sql, params);
            var rowCount=parseInt(count["count(*)"]) ;
            if(rowCount===0){
                console.log("DocNb unique")
                return false;
            }
            else{
                  throw new Error('There is already a DocNumber in the db');
            }
        } catch (error) {
            
            throw new Error('Error retrieving user with DocNumber from db');
        }
    }

    async checkIfInsuredCodeExistsAlready(uInsuredCode){
        try {
            sqlite3.verbose();
            const db=await this.databaseFile.createDbConnection('medUserIntDB');
            var sql = "select count(*) from Users where uInsuredCode = ?"
            var params = [uInsuredCode]
            const count= await db.get(sql, params);
            var rowCount=parseInt(count["count(*)"]) ;
            if(rowCount===0){
                console.log("InsuredCode unique")
                return false;
            }
            else{
                  throw new Error('There is already an InsuredCode in the db');
            }
        } catch (error) {
            
            throw new Error(error.message);
        }
    }

    async getLastId(){
        try {
            sqlite3.verbose();
            const db=await this.databaseFile.createDbConnection('medUserIntDB');
            var sql = "select max(uId) from Users"
            var params = []
            const answer= await db.get(sql, params);
            console.log(answer["max(uId)"]);
            var lastId=parseInt(answer["max(uId)"]) ;
            return lastId;
        } catch (error) {
            throw new Error('Error retrieving max(uId) from db');
        }
    }

    async registerUser(user){
        try {
            sqlite3.verbose();
            const db=await this.databaseFile.createDbConnection('medUserIntDB');
            var sql ='INSERT INTO Users (uName,uEmail,uBirthday,uInsuredCode,uDocNumber,uExpirationDate, uPassword,uIsDoctor,uIsAdmin) VALUES (?,?,?,?,?,?,?,?,?)'
            var params =[user.uName,user.uEmail,user.uBirthday,user.uInsuredCode,user.uDocNumber,user.uExpirationDate, md5(user.uPassword) ,user.uIsDoctor,user.uIsAdmin]
            await db.run(sql, params);
            user.id=await this.getLastId();
            return user;
        } catch (error) {
            throw new Error(error.message);
        }
    }

    async addDoctorByDocNumber(uDocNumber){
        try {
            //console.log(uDocNumber)
            sqlite3.verbose();
            const db=await this.databaseFile.createDbConnection('medUserIntDB');
            var doctorInitial=await this.getUserByDocNumber(uDocNumber);
            //console.log(parseInt( doctorInitial["uIsDoctor"]))
            if(parseInt( doctorInitial["uIsDoctor"])!==0){
                throw new Error("The user is already a doctor")
            }
            var sql ='UPDATE Users SET uIsDoctor=1 where uDocNumber=?'
            var params =[uDocNumber]
            await db.run(sql, params);
            var doctor=await this.getUserByDocNumber(uDocNumber);
            return doctor;
        } catch (error) {
            //console.log(error)
            throw new Error(error.message);
        }
    }

    async checkTheUserIsDoctor(uId){
        try {
            sqlite3.verbose();
            const db=await this.databaseFile.createDbConnection('medUserIntDB');
            var sql = "select count(*) from Users where uId = ? and uIsDoctor=1"
            var params = [uId]
            const count= await db.get(sql, params);
            var rowCount=parseInt(count["count(*)"]) ;
            if(rowCount===1){
                console.log("User is doctor")
                return true;
            }
            else{
                  throw new Error('The user is not a doctor');
            }
        } catch (error) {
            
            throw new Error(error.message);
        }
    }

    async getUserById(uId){
        try {
            sqlite3.verbose();
            const db=await this.databaseFile.createDbConnection('medUserIntDB');
            var sql = "select * from Users where uId = ?"
            var params = [uId]
            const row= await db.get(sql, params);
            if(row!=null){
                console.log("row in repo:"+JSON.stringify(row))
                return row;
            }
            else{
                  throw new Error('No such user in the db');
            }
        } catch (error) {
            throw new Error('Error retrieving user with id from db');
        }

    }

    async getUserByDocNumber(uDocNumber){
        try {
            //console.log(uDocNumber)
            sqlite3.verbose();
            const db=await this.databaseFile.createDbConnection('medUserIntDB');
            var sql = "select * from Users where uDocNumber = ?"
            var params = [uDocNumber]
            const row= await db.get(sql, params);
            if(row!=null){
                console.log("row in repo:"+JSON.stringify(row))
                return row;
            }
            else{
                  throw new Error('No such user in the db');
            }
        } catch (error) {
            //console.log(error)
            throw new Error('Error retrieving user with DocNumber from db');
        }

    }

       
  }


module.exports = {UserRepository:UserRepository}