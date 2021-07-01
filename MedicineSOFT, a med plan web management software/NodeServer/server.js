
const express = require("express")
const app = express()
const cors = require('cors');
const bodyParser = require("body-parser");
const basicAuth = require('./_helpers/basic-auth');
const errorHandler = require('./_helpers/error-handler');

app.use(bodyParser.urlencoded({ extended: false }));
app.use(bodyParser.json());
app.use(cors());


const db=require("./_helpers/database").db;
const { type } = require('os')

// use basic HTTP auth to secure the api
app.use(basicAuth);

// api routes
app.use('/users', require('./routes/user.routes'));
app.use('/organisations', require('./routes/organisation.routes'));
app.use('/diseases', require('./routes/disease.routes'));
app.use('/orgs', require('./routes/organisationBelongings.routes'));
app.use('/medications', require('./routes/medication.routes'));
app.use('/prescriptions', require('./routes/prescription.routes'));
app.use('/reviews', require('./routes/review.routes'));

// global error handler
app.use(errorHandler);


// Server port
const HTTP_PORT = process.env.NODE_ENV === 'production' ? 80 : 8080;
// Start server
app.listen(HTTP_PORT, () => {
    console.log("Server running on port %PORT%".replace("%PORT%",HTTP_PORT))
});
// Root endpoint
app.get("/", (req, res, next) => {
    res.json({"message":"Ok"})
});



// Insert here other API endpoints
//we have a req with headers, params and body and 
//a response which allows to send content

// //get list of users
// app.get("/users/", (req, res, next) => {
//     console.log("get users incepe");
//     var sql = "select * from Users"
//     var params = []
//     //db.all retrives all rows from the sql query
//     db.all(sql, params, (err, rows) => {
//         //callback fct receives an error and the rows from the db
//         if (err) {
//           res.status(400).json({"error":err.message});
//           return;
//         }
//         //this is the json success response with message and data
//         res.json(
//             rows
//         )
//       });
// });

// //get a single user by id
// app.get("/users/:id", (req, res, next) => {
//     console.log("get user by id")
//     var sql = "select * from Users where UId = ?"
//     //this time the request has params: the id
//     var params = [req.params.id]
//     db.get(sql, params, (err, row) => {
//         if (err) {
//           res.status(401).json({ message: 'No such user and it does not have permission' })
//           return;
//         }
//         //this is the json success response with message and data
//         res.json(
//             row
//         )
//       });
// });

// //get a single user by UDocNmber and UPassword
// //password is hashed already so we only check with the table
// app.get("/users/:UDocNumber/:UPassword", (req, res, next) => {
//     var sql = "select * from Users where UDocNumber = ? and UPassword = ?"
//     //this time the request has params: the id
//     var params = [req.params.UDocNumber,req.params.UPassword]
//     db.get(sql, params, (err, row) => {
//         if (err) {
//           res.status(400).json({"error":err.message});
//           return;
//         }
//         //this is the json success response with message and data
//         res.json({
//             "message":"success",
//             "data":row
//         })
//       });
// });

// //create a new user with post request

// app.post("/users/register/", (req, res, next) => {
//   var errors=[]
//   console.log("post in users/register")
//   //checking that we got all params
//   if (!req.body.ULastName){
//     errors.push("No last name specified");
//   }
//   if (!req.body.UFirstName){
//     errors.push("No first name specified");
//   }
//   if (!req.body.UEmail){
//     errors.push("No email specified");
//   }
//   if (!req.body.UInsuredCode){
//     errors.push("No insured code specified");
//   }
//   if (!req.body.UDocNumber){
//     errors.push("No document number specified");
//   }
//   if (!req.body.UExpirationDate){
//     errors.push("No document number specified");
//   }
//   if (!req.body.UPassword){
//       errors.push("No password specified");
//   }
//   if (!req.body.UIsDoctor){
//     errors.push("No doctor affiliation specified");
//   }
//   if (errors.length){
//       res.status(400).json({ message: errors.join(",") })
      
//       return;
//   }
//   console.log("a trecut de verificare null-uri")
//   //checking that the doc number and insured code are unique else break
//   var sql = "select * from Users where UDocNumber = ?"
//   var paramForDoc = [req.body.UDocNumber]
//   db.get(sql, paramForDoc, (err, row) => {
//     if (err || row!=null) {
//       res.status(400).json({ message: 'Document number is not unique' })

//       return;
//     }
//     else{
//       console.log("a trecut de verificare doc")
//       var sql = "select * from Users where UInsuredCode = ?"
//       var paramForDoc = [req.body.UInsuredCode]
//       db.get(sql, paramForDoc, (err, row) => {
//         if (err || row!=null) {
//           res.status(400).json({ message: 'Insured Code is not unique' })
        
//         return;
//       }
//       else{
        
//         console.log("a trecut de verificare insuredCode")  
//       //inserting into db
//       var isDoct=1;
//       if(req.body.UIsDoctor=="no"){
//         var isDoct=0;
//       }
//       var data = {
//           ULastName:req.body.ULastName,
//           UFirstName:req.body.UFirstName,
//           UEmail:req.body.UEmail,
//           UInsuredCode:req.body.UInsuredCode,
//           UDocNumber: req.body.UDocNumber,
//           UExpirationDate:req.body.UExpirationDate,
//           UPassword : md5(req.body.UPassword),
//           UIsDoctor:isDoct ,
//       }
//       var sql ='INSERT INTO Users (ULastName,UFirstName,UEmail,UInsuredCode,UDocNumber,UExpirationDate,  UPassword,UIsDoctor) VALUES (?,?,?,?,?,?,?,?)'
//       var params =[data.ULastName,data.UFirstName,data.UEmail,data.UInsuredCode,data.UDocNumber,data.UExpirationDate, data.UPassword,data.UIsDoctor]
//       db.run(sql, params, function (err, result) {
//           if (err){
//             res.status(400).json({ message: err.message })

//               return;
//           }
//           res.json({
//               //"message": "success",
//               "data": data,
//               "UId" : this.lastID
//           })
//       });
//     }
//   });
//     }
//     });
    
   
     
    
  
// })

//logging on with post request
// app.post("/users/login/", (req, res, next) => {
//   console.log("post in users/login");
//   var errors=[]
//   //checking that we got both params
//   if (!req.body.uPassword){
//       errors.push("No password specified");
//   }
//   if (!req.body.uDocNumber){
//       errors.push("No document number specified");
//   }
//   if (errors.length){
//     res.status(400).json({ message:errors.join(",") })
//       return;
//   }
//   //checking to see if there is such a user
//   var sql = "select * from Users where uDocNumber = ? and uPassword = ?"
//     //this time the request has params: the id
//     var params = [req.body.uDocNumber,md5(req.body.uPassword)]
//     db.get(sql, params, (err, row) => {
//         if (err) {
//           //res.status(400).json({"error":err.message});
//           //res.send({ status: 0, data: err });
//           res.status(400).json({ message: 'Document number or password is incorrect' })
//           return;
//         }
//         //this is the json success response with message and data
//         if(row!=null){
//           console.log("delivering succes");
//           console.log(row)
//           res.json(
//             //"message":"success",
//             //"data":row
//             row
//         )
//         //let token = jwt.sign({ data: row }, 'secret')
//         //res.send({ status: 1, data: row, token: token });
//         }
//         else{
//           res.status(400).json({ message: 'Document number or password is incorrect' })
//         }
//       });
// })




// Default response for any other request
app.use(function(req, res){
    res.status(404);
});






