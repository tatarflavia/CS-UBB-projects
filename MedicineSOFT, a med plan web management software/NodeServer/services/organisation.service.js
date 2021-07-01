//the service for the organisation model, helds a repo reference and is called by the organisation route

class OrganisationService {
    constructor(organisationRepository,organisationValidator){
        this.organisationRepository=organisationRepository;
        this.organisationValidator=organisationValidator;
    }
    
    async getOrganisationById(oId){
        console.log("serv cred:"+oId);
        var row=await this.organisationRepository.getOrganisationById(oId);
        console.log("serv row:"+JSON.stringify(row))
        return row;
    }

       
  }


module.exports = {OrganisationService:OrganisationService}