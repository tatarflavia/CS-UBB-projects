//the service for the review model, helds a repo reference and is called by the review route
let reviewFile=require('../models/review');
let Review=reviewFile.Review;

class ReviewService {
    constructor(reviewRepository,reviewValidator){
        this.reviewRepository=reviewRepository;
        this.reviewValidator=reviewValidator;
    }

    async getReviewsByUserId(uId){
        console.log("get reviews by userID incepe la serv");
        var rows=await this.reviewRepository.getReviewsByUserId(uId);
        return rows;
    }

    async checkIfPrescIdMedIdPatientIdExist(rPrescriptionId,rMedicationId,rPatientId){
        return await this.reviewRepository.checkIfPrescIdMedIdPatientIdExist(rPrescriptionId,rMedicationId,rPatientId);
    }


    async addReviewWithoutPrescription(rPatientId,rMedicationId,rReactionObserved,rOverallFeel,rMedQuantity,rTakingMotive,rMedStartDate,rMedEndDate){
        try {
            var review=new Review(0,null,rPatientId,rMedicationId,rReactionObserved,rOverallFeel,rMedQuantity,rTakingMotive,rMedStartDate,rMedEndDate)
            this.reviewValidator.validateFull(review);
            var reviewExists=await this.reviewRepository.checkIfReviewExistsAlready(null,rPatientId,rMedicationId);
            console.log(reviewExists)
            if(!(reviewExists)){
                var addedReview=await this.reviewRepository.addReview(review);
                return addedReview;
            }
            else{
                throw new Error("Review exists already");
            }
        } catch (error) {
            throw new Error(error.message);
        }
    }

    async addReviewWithPrescription(rPrescriptionId,rPatientId,rMedicationId,rReactionObserved,rOverallFeel){
        try {
            var review=new Review(0,rPrescriptionId,rPatientId,rMedicationId,rReactionObserved,rOverallFeel,"","","","")
            this.reviewValidator.validate(review);
            var reviewExists=await this.reviewRepository.checkIfReviewExistsAlready(rPrescriptionId,rPatientId,rMedicationId);
            if(!(reviewExists)){
                var addedReview=await this.reviewRepository.addReview(review);
                return addedReview;
            }
            else{
                throw new Error("Review exists already");
            }
        } catch (error) {
            throw new Error(error.message);
        }
    }


    

    async getReviewById(rId){
        console.log("serv cred:"+rId);
        var row=await this.reviewRepository.getReviewById(rId);
        console.log("serv row:"+JSON.stringify(row))
        return row;
    }

    async getByPrescIdMedIdPatientId(rPrescriptionId,rMedicationId,rPatientId){
        var row=await this.reviewRepository.getByPrescIdMedIdPatientId(rPrescriptionId,rMedicationId,rPatientId);
        console.log("serv row:"+JSON.stringify(row))
        return row;
    }

       
  }


module.exports = {ReviewService:ReviewService}