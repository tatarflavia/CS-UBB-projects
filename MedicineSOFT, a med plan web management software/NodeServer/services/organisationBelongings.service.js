//the service for the orgBelongings model, helds a repo reference and is called by the orgBelongings route
let orgBelongingsFile=require('../models/organisationBelongings');
let OrganisationBelongings=orgBelongingsFile.OrganisationBelongings;
class OrganisationBelongingsService {
    constructor(orgBelongingsRepository,orgBelongingsValidator){
        this.orgBelongingsRepository=orgBelongingsRepository;
        this.orgBelongingsValidator=orgBelongingsValidator;
    }
    
    async addOrganisationForPatient(bDoctorId,bOrganisationId,bPatientId){
      try {
        var orgBelonging=new OrganisationBelongings(0,bDoctorId,bOrganisationId,bPatientId)
        this.orgBelongingsValidator.validate(orgBelonging);
        var orgBelongingExistsAlready=await this.orgBelongingsRepository.checkIfOrgBelongingExistsAlready(orgBelonging);
        if(!(orgBelongingExistsAlready)){
            var addedOrgBelonging=await this.orgBelongingsRepository.addOrgBelonging(orgBelonging);
            return addedOrgBelonging;
        }
        else{
            throw new Error("Belonging exists already");
        }
    } catch (error) {
        throw new Error(error.message);
    }
    }

    async getAllOrganisationsByDoctorIdLessPatientId(bDoctorId,bPatientId){
      console.log("get orgs by doctorId not patientId incepe la serv");
      var rows=await this.orgBelongingsRepository.getAllOrganisationsByDoctorIdLessPatientId(bDoctorId,bPatientId);
      return rows;
    }

    async getOrgBelongsForDoctorPatient(bDoctorId,bPatientId){
      var rows=await this.orgBelongingsRepository.getOrgBelongsForDoctorPatient(bDoctorId,bPatientId);
      return rows;
    }

    async getAllPatientsByDoctorId(bDoctorId){
      console.log("get patients by doctorID incepe la serv");
      var rows=await this.orgBelongingsRepository.getAllPatientsByDoctorId(bDoctorId);
      return rows;
  }

    async  getAllPatientsCountByDoctorId(bDoctorId){
      console.log("get count(patients) by doctorID incepe la serv");
      var rows=await this.orgBelongingsRepository. getAllPatientsCountByDoctorId(bDoctorId);
      return rows;
  }

  async getAllOrganisationsByDoctorId(bDoctorId){
    var rows=await this.orgBelongingsRepository.getAllOrganisationsByDoctorId(bDoctorId);
      return rows;
  }

  async getAllOrganisationBelongingsByDoctorId(bDoctorId){
    var rows=await this.orgBelongingsRepository.getAllOrganisationBelongingsByDoctorId(bDoctorId);
      return rows;
  }





       
  }


module.exports = {OrganisationBelongingsService:OrganisationBelongingsService}