//the service for the user model, helds a repo reference and is called by the user route
let userFile=require('../models/user')
let User=userFile.User;

class UserService {
    constructor(userRepository,userValidator){
        this.userRepository=userRepository;
        this.userValidator=userValidator;
    }
    async getAllUsers(searchQuery){
        console.log("get users incepe la serv");
        //only filter by name, docNb, insuredCode, email
        console.log(searchQuery);
        console.log(searchQuery["search"]);
        if(searchQuery["search"]!==undefined){
            //we sent filtered users
            var rows=await this.userRepository.getAllUsersFiltered(searchQuery["search"]);
            return rows;
        }
        else{
            var rows=await this.userRepository.getAllUsers();
            return rows;
        }
    }
    async authenticateUserByCredentials(uDocNumber,uPassword){
        console.log("serv cred:"+uDocNumber,uPassword);
        var row=await this.userRepository.checkExistingUserByCredentials(uDocNumber,uPassword);
        console.log("serv row:"+JSON.stringify(row))
        return row;
    }
    async registerUser(uName,
        uEmail,uBirthday,uInsuredCode,uDocNumber,uExpirationDate,uPassword){
            try {
                var user=new User(0,uName,
                    uEmail,uBirthday,uInsuredCode,uDocNumber,uExpirationDate,uPassword,0,0)
                this.userValidator.validate(user);
                var insuredCodeExistsAlready=await this.userRepository.checkIfInsuredCodeExistsAlready(uInsuredCode);
                var docNumberExistsAlready=await this.userRepository.checkIfDocNumberExistsAlready(uDocNumber);
                if(!(insuredCodeExistsAlready) && !(docNumberExistsAlready)){
                    var addedUser=await this.userRepository.registerUser(user);
                    return addedUser;
                }
                else{
                    throw new Error("DocNb or InsuredCode is not unique");
                }
            } catch (error) {
                throw new Error(error.message);
            }
        
    }

    async checkTheUserIsDoctor(uId){
        var isDoct=await this.userRepository.checkTheUserIsDoctor(uId);
        return isDoct;
    }

    async addDoctorByDocNumber(uDocNumber){
        var regex = new RegExp('^[0-9]{6,}$');
        if(regex.test(uDocNumber)){
            var addedDoctor=await this.userRepository.addDoctorByDocNumber(uDocNumber);
            return addedDoctor;
        }
        else{
            return null;
        }
    }

    async getUserById(uId){
        console.log("serv cred:"+uId);
        var row=await this.userRepository.getUserById(uId);
        console.log("serv row:"+JSON.stringify(row))
        return row;
    }

       
  }


module.exports = {UserService:UserService}