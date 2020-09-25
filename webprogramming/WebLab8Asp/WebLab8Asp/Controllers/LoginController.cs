using System;
using System.Linq;
using System.Web.Mvc;
using WebLab8Asp.Models;
using WebLab8Asp.DataAbstractionLayer;

namespace WebLab8Asp.Controllers
{
    public class LoginController : Controller
    {
        [HttpGet]
        public ActionResult Login()
        {
            return View();
        }

        [HttpPost]
        public ActionResult Login(User objUser)
        {
            if (ModelState.IsValid)
            {
                string UserName = Request.Params["UserName"];
                string Password = Request.Params["Password"];
                var userDAL = new UserDAL();
                
                var user = userDAL.GetUser(UserName, Password);
                if (user != null)
                {
                    Session["UserName"] = user.UserName.ToString();
                    return RedirectToAction("HomePage","Main");
                }
            }
            return View("Login");
        }
    }
}