//checks that the basic authentication from client is valid in order to allow access to the server API
//401 response is sent if they don't have access
let repo=require('../repositories/user.repository')
let UserRepository=repo.UserRepository;
let userRepo=new UserRepository();
let validator=require('../validators/user.validator')
let UserValidator=validator.UserValidator;
let userValid=new UserValidator();
let serv=require('../services/user.service');
let UserService=serv.UserService;
let userServ=new UserService(userRepo,userValid);

module.exports = basicAuth;

async function basicAuth(req, res, next) {
    // make login and register path public, the rest are not
    console.log(req.path)
    if (req.path === '/users/login' || req.path==='/users/register') {
        return next();
    }

    // check for basic auth header
    if (!req.headers.authorization || req.headers.authorization.indexOf('Basic ') === -1) {
        return res.status(401).json({ message: 'Missing Authorization Header' });
    }

    if(req.path === '/users/addDoctor'){
        //admins only
        console.log("admins only path")
        const base64Credentials =  req.headers.authorization.split(' ')[1];
        const credentials = Buffer.from(base64Credentials, 'base64').toString('ascii');
        const [uDocNumber, uPassword] = credentials.split(':');
        try{
            const user = await userServ.authenticateUserByCredentials( uDocNumber, uPassword );
            if (!user) {
                return res.status(401).json({ message: 'Invalid Login Credentials' });
            }
            if(parseInt(user["uIsAdmin"])!==1){
                return res.status(401).json({ message: 'You do not have permission' });
            }
            // attach user to request object
            req.user = user
            next();
        }
        catch (error) {
    
            return res.status(401).json({ message: 'Invalid Login Credentials' });
        }
    }
    else{
        
        var regexForOrganisations=new RegExp('^.*\/organisations\/[0-9]+$');
        var regexForReviews=new RegExp('^.*\/reviews\/byUser\/[0-9]+$');
        if(req.path === '/users'  || regexForOrganisations.test(req.path) ||regexForReviews.test(req.path)|| req.path==='/diseases/add' || req.path==='/orgs/add' || req.path==='/orgs/seeForPatient' || req.path==='/orgs/seePatients' || req.path==='/orgs/seePatientsCount' ||req.path==='/prescriptions/forPatient' || req.path==='/prescriptions/add' || req.path==="/prescriptions/addMed" || req.path==="/orgs/osForDoctor" || req.path==="/orgs/bsForDoctor" || req.path==='/orgs/bsForDoctorPatient'){
            //doctors only
            console.log("doctors only path")
            const base64Credentials =  req.headers.authorization.split(' ')[1];
            const credentials = Buffer.from(base64Credentials, 'base64').toString('ascii');
            const [uDocNumber, uPassword] = credentials.split(':');
            try{
                const user = await userServ.authenticateUserByCredentials( uDocNumber, uPassword );
                if (!user) {
                    return res.status(401).json({ message: 'Invalid Login Credentials' });
                }
                if(regexForReviews.test(req.path)){
                    var spl=req.path.split('/');
                    if(parseInt(user["uId"])===parseInt(spl[spl.length-1])){
                       // attach user to request object
                        req.user = user
                        next(); 
                    }
                    else{
                        if(parseInt(user["uIsDoctor"])!==1){
                            return res.status(401).json({ message: 'Access denied for patients' });
                        }
                        // attach user to request object
                        req.user = user
                        next();
                    }
                }
                else{

                
                
                if(parseInt(user["uIsDoctor"])!==1){
                    return res.status(401).json({ message: 'Access denied for patients' });
                }
                // attach user to request object
                req.user = user
                next();}
            }
            catch (error) {
        
                return res.status(401).json({ message: 'Invalid Login Credentials' });
            }
        }
        else 
        {
            var regexForUsers=new RegExp('^.*\/users\/[0-9]+$');
            var regexForDisease=new RegExp('^.*\/diseases\/[0-9]+$');
            var regexForMedication=new RegExp('^.*\/medications\/[0-9]+$');
            var regexForPrescription=new RegExp('^.*\/prescriptions\/[0-9]+$');
            var regexForReviews=new RegExp('^.*\/reviews\/[0-9]+$');
            if(regexForUsers.test(req.path)||regexForDisease.test(req.path) ||req.path==='/prescriptions/null'|| req.path === '/medications' || regexForMedication.test(req.path) || req.path === '/prescriptions' || req.path === '/prescriptions/medList' || regexForPrescription.test(req.path) || req.path==="/prescriptions/changeStatus" || req.path==="/reviews/add" || regexForReviews.test(req.path) || req.path==="/users/desc"||req.path==="/reviews/check"||req.path==="/reviews/byIds" || req.path==="/medications/null"||req.path==="/prescriptions/medListMember"){
                // verify auth credentials
                console.log("patients only path")
                const base64Credentials =  req.headers.authorization.split(' ')[1];
                const credentials = Buffer.from(base64Credentials, 'base64').toString('ascii');
                const [uDocNumber, uPassword] = credentials.split(':');
                try{
                    const user = await userServ.authenticateUserByCredentials( uDocNumber, uPassword );
                    if (!user) {
                        return res.status(401).json({ message: 'Invalid Login Credentials' });
                    }
                    // attach user to request object
                    req.user = user

                    next();
                }
                catch (error) {
                    return res.status(401).json({ message: 'Invalid Login Credentials' });
                }
            }

            else{return res.status(401).json({ message: 'Access denied' });}
        }

        
    }

    
    
    
    

    
}